package resistorSorterdb.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import resistorSorterdb.persist.DBUtil;
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
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;

				try {
					stmt1 = conn.prepareStatement(
						"create table inventories (" +
						"	inventory_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	bincapacity integer," +
						"	userremovelimit integer" +
						")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table racks (" +
							"	rack_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	inventory_id integer constraint inventory_id references inventories, " +
							"	tolerance float," +
							"   wattage float " +
							")"
					);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
							"create table bins (" +
							"	bin_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +	
							"		rack_id integer constraint rack_id references racks, " +
							"	count integer," +
							"	resistance integer" +
							")"
						);	
					stmt3.executeUpdate();
						
						
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		//System.out.println("Loading initial data...");
		//db.loadInitialData();
		
		System.out.println("Success!");
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
	public void insertBin(int inventory_id, int rack_id, int resistance, int count) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement("insert into bins (inventory_id, rack_id, resistance, count) values (?, ?, ?, ?)");
					stmt.setInt(1, inventory_id);
					stmt.setInt(2, rack_id);
					stmt.setInt(3, resistance);
					stmt.setInt(4, count);
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
						int userRemovelimit = resultSet.getInt(3);
						
						Inventory inventory = new Inventory(binCapacity, userRemovelimit);
						inventory.SetID(inventoryNum);
						
						result.add(inventory);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("no Inventories in the inventories table");
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
					stmt = conn.prepareStatement(
							"select * from racks"
							+ " where inventory_id = ?"
					);
					stmt.setInt(1, inventoryID);
					
					List<Rack> result = new ArrayList<Rack>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						int rackID = resultSet.getInt(1);
						int inventoryID = resultSet.getInt(2);
						float tolerance = resultSet.getInt(3);
						float wattage = resultSet.getInt(4);
						
						Rack rack = new Rack(tolerance, wattage, inventoryID);
						
						result.add(rack);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("no Racks in the Racks table");
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
	public List<Bin> getAllBins(int inventoryID, int rackID) {
		return executeTransaction(new Transaction<List<Bin>>() {
			@Override
			public List<Bin> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from racks"
							+ " where inventory_id = ?"
							+ " where rack_id = ?"
					);
					stmt.setInt(1, inventoryID);
					stmt.setInt(2, inventoryID);
					
					List<Bin> result = new ArrayList<Bin>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						int rackID = resultSet.getInt(1);
						int inventoryID = resultSet.getInt(2);
						float tolerance = resultSet.getInt(3);
						float wattage = resultSet.getInt(4);
						
						Rack rack = new Rack(tolerance, wattage, inventoryID);
						
						result.add(rack);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("no Racks in the Racks table");
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
	public void removeRack(int rackID, int inventoryID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;


				try {
					stmt = conn.prepareStatement(
							"delete from inventories"
							+ " where inventory_id = ? and rack_id = ?"
					);
					stmt.setInt(1, inventoryID);
					stmt.setInt(2, rackID);
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
	public void removeBin(int binID, int rackID, int inventoryID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet resultSet = null;


				try {
					stmt = conn.prepareStatement(
							"delete from inventories"
							+ " where inventory_id = ? and rack_id = ? and bin_id = ?"
					);
					stmt.setInt(1, inventoryID);
					stmt.setInt(2, rackID);
					stmt.setInt(3, binID);
					stmt.executeUpdate();
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});		
	}



}
