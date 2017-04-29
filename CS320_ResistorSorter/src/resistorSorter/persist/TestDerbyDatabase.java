package resistorSorter.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import resistorSorter.persist.DBUtil;
import resistorSorter.model.*;

public class TestDerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:derby:Test.db;create=true");

		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	public void createTables(){
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null, stmt2 = null, stmt3 = null, stmt4 = null, stmt5 = null;

				try {
					stmt1 = conn.prepareStatement(

						"CREATE TABLE inventories("
						+ " inventory_id integer primary key"
						+ " generated always as identity (start with 1, increment by 1),"
						+ " bincapacity integer,"
						+ " userremovelimit integer"
						+ ")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
						"CREATE TABLE racks("
						+ " rack_id integer primary key"
						+ " generated always as identity (start with 1, increment by 1),"
						+ " inventory_id integer constraint inventory_id references inventories on delete cascade,"
						+ " tolerance float,"
						+ " wattage float"
						+ ")"
					);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
						"CREATE TABLE bins("
						+ " bin_id integer primary key"
						+ " generated always as identity (start with 1, increment by 1),"
						+ " rack_id integer constraint rack_id references racks on delete cascade,"
						+ " count integer,"
						+ " resistance integer"
						+ ")"
					);
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
							"CREATE TABLE users("
							+ " user_id integer primary key"
							+ " generated always as identity (start with 1, increment by 1),"
							+ " username varchar(20), password varchar(20), firstname varchar(20), lastname varchar(20), adminReq boolean"
							+ ")"
						);
					stmt4.executeUpdate();
					
					//
					stmt5 = conn.prepareStatement(
							"CREATE TABLE transactions("
							+ " transaction_id integer primary key"
							+ " generated always as identity (start with 1, increment by 1),"
							+ " user_id integer constraint user_id references users on delete cascade,"
							//this username is based on user_id
							+ " username varchar(20),"
							//had to add 2 to the ids because there can only be one unique constraint
							+ " inventory_id integer constraint inventory_id2 references inventories on delete cascade,"
							+ " rack_id integer constraint rack_id2 references racks on delete cascade,"
							+ " bin_id integer constraint bin_id2 references bins on delete cascade,"
							+ " transactionTime timestamp,"
							+ " transactionType varchar(20),"
							+ " quantity integer"
							+ ")"
						);
					stmt5.executeUpdate();
					
					
				System.out.println("testInventory Created");
				} catch(SQLException e){
					
					System.out.println("testInventory Loaded or creation failed");
					
				}finally{
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
				}
				return true;
			}
		});
	}
	
	public static void loadDataBase(){
		TestDerbyDatabase db = new TestDerbyDatabase();
		db.createTables();
	}
	
	public static void deleteDataBase(){
		TestDerbyDatabase db = new TestDerbyDatabase();
		db.dropTables();
	}
	
	
	public void dropTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				try {
					//delete all tables
					stmt1 = conn.prepareStatement(
						"drop table transactions"
						);
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
						"drop table bins"
					);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
						"drop table racks"
					);
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
						"drop table inventories"
					);
					stmt4.executeUpdate();
					
					stmt5 = conn.prepareStatement(
						"drop table users"
					);
					stmt5.executeUpdate();
					
					System.out.println("testdatabase deleted");
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
				}
			}
		});
	}

	@Override
	public void insertInventory(int binCapacity, int userRemoveLimit) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement("insert into inventories (binCapacity, userRemoveLimit) values (?, ?)");
					stmt.setInt(1, binCapacity);
					stmt.setInt(2, userRemoveLimit);
					stmt.executeUpdate();
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public void insertRack(int inventory_id, float tolerance, float wattage) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				//System.out.println(wattage);
				try {
					stmt = conn.prepareStatement("insert into racks (inventory_id, tolerance, wattage) values (?, ?, ?)");
					stmt.setInt(1, inventory_id);
					stmt.setFloat(2, tolerance);
					stmt.setFloat(3, wattage);
					stmt.executeUpdate();
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
		
	}

	@Override
	public void insertBin(int rack_id, int resistance, int count) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement("insert into bins (rack_id, resistance, count) values (?, ?, ?)");
					stmt.setInt(1, rack_id);
					stmt.setInt(2, resistance);
					stmt.setInt(3, count);
					stmt.executeUpdate();
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
		
	}

	@Override
	public List<Inventory> getAllInventories() {
		return executeTransaction(new Transaction<List<Inventory>>() {
			@Override
			public List<Inventory> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from inventories"
					);
					
					List<Inventory> result = new ArrayList<Inventory>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						int inventoryNum = resultSet.getInt(1);
						int binCapacity = resultSet.getInt(2);
						int userRemoveLimit = resultSet.getInt(3);
						
						Inventory inventory = new Inventory(inventoryNum, binCapacity, userRemoveLimit);
						
						result.add(inventory);
					}
					
					// check if the title was found
					if (!found) {
						//System.out.println("no Inventories in the inventories table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});

	}
	@Override
	public List<Rack> getAllRacks(int inventoryID) {
		return executeTransaction(new Transaction<List<Rack>>() {
			@Override
			public List<Rack> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					//used for testViewInventory to display allRacks
					if(inventoryID == -1){
						stmt = conn.prepareStatement(
								"select * from racks"
						);
					}
					//most commonly used when displaying a specific rack
					else{
						stmt = conn.prepareStatement(
								"select * from racks"
								+ " where inventory_id = ?"
						);
						stmt.setInt(1, inventoryID);
					}	
					
					List<Rack> result = new ArrayList<Rack>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						int rackID = resultSet.getInt(1);
						int inventoryID = resultSet.getInt(2);
						float tolerance = resultSet.getFloat(3);
						float wattage = resultSet.getFloat(4);
						
						Rack rack = new Rack(rackID, inventoryID, tolerance, wattage);
						
						result.add(rack);
					}
					
					// check if the title was found
					if (!found) {
						//System.out.println("no Racks in the Racks table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});

	}
	
	@Override
	public List<Bin> getAllBins(int rackID) {
		return executeTransaction(new Transaction<List<Bin>>() {
			@Override
			public List<Bin> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					//used for testViewInventory to display allBins
					if(rackID == -1){
						stmt = conn.prepareStatement(
								"select * from bins"
						);
					}
					//most commonly used when displaying a specific Bin
					else{
						stmt = conn.prepareStatement(
								"select * from bins"
								+ " where rack_id = ?"
						);
						stmt.setInt(1, rackID);
					}	
					
					List<Bin> result = new ArrayList<Bin>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						int binID = resultSet.getInt(1);
						int rackID = resultSet.getInt(2);
						int count = resultSet.getInt(3);
						String resistance = resultSet.getString(4);
						
						Bin bin = new Bin(binID, rackID, count, resistance);
						
						result.add(bin);
					}
					
					// check if the title was found
					if (!found) {
						//System.out.println("no Bins in the Bins table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public void removeInventory(int inventoryID) {
		
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;


				try {
					stmt1 = conn.prepareStatement(
							"delete from inventories"
							+ " where inventory_id = ?"
					);
					stmt1.setInt(1, inventoryID);
					stmt1.executeUpdate();
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});	
		
	}

	@Override
	public void removeRack(int rackID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;


				try {
					stmt = conn.prepareStatement(
							"delete from racks"
							+ " where rack_id = ?"
					);
					stmt.setInt(1, rackID);
					stmt.executeUpdate();
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});	
		
	}

	@Override
	public void removeBin(int binID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;


				try {
					stmt = conn.prepareStatement(
							"delete from bins"
							+ " where bin_id = ?"
					);
					stmt.setInt(1, binID);
					stmt.executeUpdate();
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});		
	}


	@Override
	public void addResistors(int bin_id, int count) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				
				try {
					//get count
					stmt1 = conn.prepareStatement(
							"select bins.count from bins"
							+ " where bin_id = ?"
									
					);
					stmt1.setInt(1, bin_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					int newCount = resultSet.getInt(1) + count;
				
					
					//update count
					stmt2 = conn.prepareStatement(
							"update bins"
							+ " set count = ?"	
							+ " where bin_id = ?"
					);
					stmt2.setInt(1, newCount);
					stmt2.setInt(2, bin_id);
					stmt2.executeUpdate();
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});		
		
	}

	@Override
	public void removeResistors(int bin_id, int count) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				
				try {
					//get count
					stmt1 = conn.prepareStatement(
							"select bins.count from bins"
							+ " where bin_id = ?"
									
					);
					stmt1.setInt(1, bin_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					int newCount = resultSet.getInt(1) - count;
				
					
					//update count
					stmt2 = conn.prepareStatement(
							"update bins"
							+ " set count = ?"	
							+ " where bin_id = ?"		
					);
					stmt2.setInt(1, newCount);
					stmt2.setInt(2, bin_id);
					stmt2.executeUpdate();
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});		
		
	}

	
	@Override
	public int getCount(int bin_id) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select bins.count"
							+ " from bins"
							+ " where bin_id = ?"
									
					);
					stmt1.setInt(1, bin_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					return resultSet.getInt(1);
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});		
		
	}

	@Override
	public int getUserRemoveLimit(int bin_id) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select inventories.userremovelimit"
							+ " from inventories, racks, bins"
							+ " where bins.rack_id = racks.rack_id"
							+ " and racks.inventory_id = inventories.inventory_id"
							+ " and bin_id = ?"
									
					);
					stmt1.setInt(1, bin_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					return resultSet.getInt(1);
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});	
	}

	@Override
	public int getCapacity(int bin_id) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select inventories.bincapacity"
							+ " from inventories, racks, bins"
							+ " where bins.rack_id = racks.rack_id"
							+ " and racks.inventory_id = inventories.inventory_id"
							+ " and bin_id = ?"
									
					);
					stmt1.setInt(1, bin_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					return resultSet.getInt(1);
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});	
	}

	@Override
	public void createAccount(String username, String password, String firstname, String lastname, boolean adminReq, String uuid) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement("insert into users (username, password, firstname, lastname, adminReq, uuid) values (?, ?, ?, ?, ?, ?)");
					stmt.setString(1, username);
					stmt.setString(2, password);
					stmt.setString(3, firstname);
					stmt.setString(4, lastname);
					stmt.setBoolean(5, adminReq);
					stmt.setString(6, uuid);
					stmt.executeUpdate();
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
		
	}

	@Override
	public boolean checkExistingUsernames(String username) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				//System.out.println(username);
				try {
					stmt1 = conn.prepareStatement(
							"select count(username)"
							+ " from users"
							+ " where users.username = ?" 		
					);
					//System.out.println(username);
					stmt1.setString(1, username);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));
					//If result set is 0 listings then username is good
					if(resultSet.getInt(1) == 0){
						return false;
					}
					return true;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});		
	}

	@Override
	public boolean validateCredentials(String username, String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				//System.out.println(username);
				try {
					stmt1 = conn.prepareStatement(
							"select count(username)"
							+ " from users"
							+ " where users.username = ? and users.password = ?" 		
					);
					//System.out.println(username);
					stmt1.setString(1, username);
					stmt1.setString(2, password);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));
					//If result set is 1 listings then user exists
					if(resultSet.getInt(1) == 0){
						return false;
					}
					return true;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});		

	}

	@Override
	public List<InventoryTransaction> getAllUserTransactions(String username) {
		return executeTransaction(new Transaction<List<InventoryTransaction>>() {
			@Override
			public List<InventoryTransaction> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select * from transactions"
							+ " where transactions.username = ?"
					);
					stmt.setString(1, username);
					
					
					List<InventoryTransaction> result = new ArrayList<InventoryTransaction>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						int transaction_id = resultSet.getInt(1);
						int user_id = resultSet.getInt(2);
						String userName = resultSet.getString(3);
						int inventory_id = resultSet.getInt(4);
						int rack_id = resultSet.getInt(5);
						int bin_id = resultSet.getInt(6);
						Timestamp transactionTime = resultSet.getTimestamp(7);
						String transactionType = resultSet.getString(8);
						int quantity = resultSet.getInt(9);
						
						
						InventoryTransaction inventoryTransaction= new InventoryTransaction(transaction_id, user_id, userName, inventory_id, rack_id, bin_id, transactionTime, transactionType, quantity);
						
						result.add(inventoryTransaction);
					}
					
					// check if the inventoryTransaction was found
					if (!found) {
						//do nothing
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});

	}
	
	@Override
	public void addTransaction(String username, int bin_id, Timestamp transactionTime, String transactionType, int quantity) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet3 = null;

				try {
					stmt1 = conn.prepareStatement(
							"select rack_id from bins"
							+ " where bins.bin_id = ?"
					);
					stmt1.setInt(1, bin_id);
					resultSet1 = stmt1.executeQuery();
					resultSet1.next();
					int rack_id = resultSet1.getInt(1);
					
					stmt2 = conn.prepareStatement(
							"select inventory_id from racks"
							+ " where racks.rack_id = ?"
					);
					stmt2.setInt(1, rack_id);
					resultSet2 = stmt2.executeQuery();
					resultSet2.next();
					int inventory_id = resultSet2.getInt(1);
					
					stmt3 = conn.prepareStatement(
							"select username from users"
							+ " where users.username = ?"
					);
					stmt3.setString(1, username);
					
					resultSet3 = stmt3.executeQuery();
					resultSet3.next();
					int user_id = resultSet2.getInt(1);
					
					stmt4 = conn.prepareStatement(
							"insert into transactions "
							+ "(user_id, username, inventory_id, rack_id, bin_id, transactionTime, transactionType, quantity)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)");
					stmt4.setInt(1, user_id);
					stmt4.setString(2, username);
					stmt4.setInt(3, inventory_id);
					stmt4.setInt(4, rack_id);
					stmt4.setInt(5, bin_id);
					stmt4.setTimestamp(6, transactionTime);
					stmt4.setString(7, transactionType);
					stmt4.setInt(8, quantity);
					stmt4.executeUpdate();
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});

	}

	@Override
	public int getCapacityFromRack(int rack_id) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select inventories.bincapacity"
							+ " from inventories, racks"
							+ " where inventories.inventory_id = racks.inventory_id"
							+ " and racks.rack_id = ?"
									
					);
					stmt1.setInt(1, rack_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					return resultSet.getInt(1);
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});	
	}

	@Override
	public void updateAdminFlag(String username, boolean adminReq) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement("UPDATE users" 
							+ " SET adminReq = ?"
							+ " WHERE username = ?");
					
					stmt.setBoolean(1, adminReq);
					stmt.setString(2, username);
					stmt.executeUpdate();
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
		
	}

	@Override
	public boolean getAdminFlag(String username) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				//System.out.println(username);
				try {
					stmt1 = conn.prepareStatement(
							"select users.adminReq"
							+ " from users"
							+ " where users.username = ?" 		
					);
					//System.out.println(username);
					stmt1.setString(1, username);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));
					//If result set is 1 listings then user exists
					if(resultSet.getBoolean(1)){
						return true;
					}
					return false;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});		
	}

	@Override
	public boolean checkUUID(String username, String uuid) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				//System.out.println(username);
				try {
					stmt1 = conn.prepareStatement(
							"select users.uuid"
							+ " from users"
							+ " where users.username = ?" 		
					);
					//System.out.println(username);
					stmt1.setString(1, username);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));

					if(resultSet.getString(1).equals(uuid)){
						return true;
					}
					return false;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});		
	}

	@Override
	public List<InventoryTransaction> getAllTransactions() {
		return executeTransaction(new Transaction<List<InventoryTransaction>>() {
			@Override
			public List<InventoryTransaction> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select * from transactions"
					);					
					
					List<InventoryTransaction> result = new ArrayList<InventoryTransaction>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						int transaction_id = resultSet.getInt(1);
						int user_id = resultSet.getInt(2);
						String userName = resultSet.getString(3);
						int inventory_id = resultSet.getInt(4);
						int rack_id = resultSet.getInt(5);
						int bin_id = resultSet.getInt(6);
						Timestamp transactionTime = resultSet.getTimestamp(7);
						String transactionType = resultSet.getString(8);
						int quantity = resultSet.getInt(9);
						
						
						InventoryTransaction inventoryTransaction= new InventoryTransaction(transaction_id, user_id, userName, inventory_id, rack_id, bin_id, transactionTime, transactionType, quantity);
						
						result.add(inventoryTransaction);
					}
					
					// check if the inventoryTransaction was found
					if (!found) {
						//do nothing
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean checkExistingRacks(float tolerance, float wattage, int inventory_id) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				try {
					stmt1 = conn.prepareStatement(
							"select count(rack_id)"
							+ " from racks"
							+ " where racks.tolerance = ? and racks.wattage = ?" 		
					);
					stmt1.setFloat(1, tolerance);
					stmt1.setFloat(2, wattage);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));
					//If result set is 0 listings then rack is good
					if(resultSet.getInt(1) == 0){
						return false;
					}
					return true;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});			
	}

	@Override
	public boolean checkExistingBins(int rack_id, int resistance) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				try {
					stmt1 = conn.prepareStatement(
							"select count(bin_id)"
							+ " from bins, racks"
							+ " where racks.rack_id = ? and bins.rack_id = ? and bins.resistance = ?" 		
					);
					stmt1.setInt(1, rack_id);
					stmt1.setInt(2, rack_id);
					stmt1.setInt(3, resistance);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));
					//If result set is 0 listings then rack is good
					if(resultSet.getInt(1) == 0){
						return false;
					}
					return true;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});		
	}

	@Override
	public int getResistanceFromBin(int bin_id) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select bins.resistance"
							+ " from bins"
							+ " where bin_id = ?"							
					);
					stmt1.setInt(1, bin_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));
					return resultSet.getInt(1);
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});	
	}

	@Override
	public float getToleranceFromBin(int bin_id) {
		return executeTransaction(new Transaction<Float>() {
			@Override
			public Float execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"select bins.rack_id"
							+ " from bins"
							+ " where bins.bin_id = ?"							
					);
					stmt1.setInt(1, bin_id);
					resultSet = stmt1.executeQuery();
					resultSet.next();
					//System.out.println(resultSet.getInt(1));
					int rack_id = resultSet.getInt(1);
					resultSet = null;
					stmt2 = conn.prepareStatement(
							"select racks.tolerance"
							+ " from racks"
							+ " where rack_id = ?"							
					);
					stmt2.setInt(1, rack_id);
					resultSet = stmt2.executeQuery();
					resultSet.next();
					return resultSet.getFloat(1);
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});	
	}


}