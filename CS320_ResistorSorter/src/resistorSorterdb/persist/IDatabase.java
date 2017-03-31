package resistorSorterdb.persist;

import java.util.List;

import resistorSorter.model.*;

public interface IDatabase {
	//All of the inserting methods
	public void insertInventory(int binCapacity, int userRemoveLimit);
	public void insertRack(int inventory_id, float tolerance, float wattage);
	public void insertBin(int inventory_id, int rack_id, int resistance, int count);
	public void addResistors(int inventory_id, int rack_id, int resistance, int count);
	public void removeResistors(int inventory_id, int rack_id, int resistance, int count);
	//Deleting methods

	public void removeRack(int rackID, int inventoryID);
	public void removeBin(int binID, int rackID, int inventoryID);
	//All of the retrieving methods
	public List<Inventory> getAllInventories();
	public List<Rack> getAllRacks(int inventoryID);
	public List<Bin> getAllBins(int inventoryID, int rackID);
	
	//All of the removing methods
	public void removeInventory(int inventoryID);
	
}
