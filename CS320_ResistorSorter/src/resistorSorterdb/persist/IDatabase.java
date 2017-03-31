package resistorSorterdb.persist;

import java.util.List;

import resistorSorter.model.*;

public interface IDatabase {
	//All of the inserting methods
	public void insertInventory(int binCapacity, int userRemoveLimit);
	public void insertRack(int inventory_id, float tolerance, float wattage);
	public void insertBin(int inventory_id, int rack_id, int resistance, int count);
	
	//Deleting methods
	public void deleteInventory(int inventory_id);
	public void deleteRack(int rack_id, int inventory_id);
	public void deleteBin(int bin_id, int rack_id, int inventory_id);
	//All of the retrieving methods
	public List<Inventory> getAllInventories();
	
}
