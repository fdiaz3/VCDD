package resistorSorterdb.persist;

import java.util.List;

import resistorSorter.model.*;

public interface IDatabase {
	//All of our inserting methods
	public void insertInventory(int binCapacity, int userRemoveLimit);
	public void insertRack(double tolerance, double wattage);
	public void insertBin(int resistance, int count);
	
	//All of our retrieving methods
	
}
