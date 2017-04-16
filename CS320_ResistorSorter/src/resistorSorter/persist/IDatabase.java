package resistorSorter.persist;

import java.util.List;

import resistorSorter.model.*;

public interface IDatabase {
	
	//All of the inserting methods
	
	public void insertInventory(int binCapacity, int userRemoveLimit);
	public void insertRack(int inventory_id, float tolerance, float wattage);
	public void insertBin(int rack_id, int resistance, int count);
	public void addResistors(int bin_id, int count);
	public void removeResistors(int bin_id, int count);
	
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
	
	//All of the removing methods
	public void removeInventory(int inventoryID);
	
	//User related methods
	public void createAccount(String username, String password, String firstname, String lastname, boolean adminReq);
	public boolean checkExistingUsernames(String username);
	public boolean validateCredentials(String username, String password);
}