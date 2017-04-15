package resistorSorter.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import resistorSorter.persist.DBUtil;
import resistorSorter.model.*;

public class DerbyDatabase implements IDatabase {
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
		
		Connection conn = DriverManager.getConnection("jdbc:derby:Inventory.db;create=true");

		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	public void createTables(){
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;

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
					
				System.out.println("Inventory Created");
				} catch(SQLException e){
					
					System.out.println("Inventory Loaded");
					
				}finally{
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
				return true;
			}
		});
	}
	
	public static void loadDataBase(){
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
	}
	
	public static void deleteDataBase(){
		DerbyDatabase db = new DerbyDatabase();
		db.dropTables();
	}
	
	public void dropTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				try {
					//delete all tables
					stmt1 = conn.prepareStatement(
						"drop table bins"
					);
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
						"drop table racks"
					);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
						"drop table inventories"
					);
					stmt3.executeUpdate();
					System.out.println("Inventory deleted");
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
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
				System.out.println(wattage);
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

	




}