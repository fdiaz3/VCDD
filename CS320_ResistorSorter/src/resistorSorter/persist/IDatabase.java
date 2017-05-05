package resistorSorter.persist;

import java.sql.Timestamp;
import java.util.List;

import resistorSorter.model.*;

public interface IDatabase {
	
	//All of the inserting methods
	
	public void insertInventory(int binCapacity, int userRemoveLimit, String inventoryName);
	public void insertRack(int inventory_id, float tolerance, float wattage);
	public void insertBin(int rack_id, int resistance, int count);
	public void addResistors(int bin_id, int count);
	public void removeResistors(int bin_id, int count);
	
	public boolean checkExistingRacks(float tolerance, float wattage, int inventory_id);
	public boolean checkExistingBins(int rack_id, int resistance);
	
	//Deleting methods
	
	public void removeRack(int rackID);
	public void removeBin(int binID);
	
	//All of the retrieving methods
	
	public List<Inventory> getAllInventories();
	public List<Rack> getAllRacks(int inventoryID);
	public List<Bin> getAllBins(int rackID);
	public int getCount(int bin_id);
	public int getUserRemoveLimit(int bin_id);
	public int getCapacity(int bin_id);
	public int getCapacityFromRack(int rack_id);
	public int getResistanceFromBin(int bin_id);
	public float getToleranceFromBin(int bin_id);
	
	//All of the removing methods
	public void removeInventory(int inventoryID);
	
	//User related methods
	public void updateAdminFlag(String email, boolean admin);
	public boolean checkUUID(String email, String uuid);
	public boolean getAdminFlag(String email);
	public boolean checkIfInDatabase(String email);
	public boolean insertNewUser(String email, String uuid);
	public String getUUID(String email);
	
	//Profile related
	public List<InventoryTransaction> getAllUserTransactions(String email);
	public List<InventoryTransaction> getAllTransactions();
	public void addTransaction(String email, int bin_id, Timestamp transactionTime, boolean transactionType, int quantity);

	
}
