package resistorSorterdb.persist;

import java.util.List;

import resistorSorter.model.*;

public interface IDatabase {
	//All of the inserting methods
	public void insertInventory(int binCapacity, int userRemoveLimit);
	public void insertRack(int inventory_id, float tolerance, float wattage);
	public void insertBin(int inventory_id, int rack_id, int resistance, int count);
	
	//All of the deleting methods
	public void deleteInventory(int inventory_id);
	public void deleteRack(int inventory_id, int rack_id);
	public void deleteBin(int inventory_id, int rack_id, int bin_id);
	
	//Retrieving methods
	public Inventory getInventory(int binCapacity, int userRemoveLimit);
	public Rack getRack(int inventory_id, float tolerance, float wattage);
	public Bin getBin(int inventory_id, int rack_id, int resistance, int count);
	
}
