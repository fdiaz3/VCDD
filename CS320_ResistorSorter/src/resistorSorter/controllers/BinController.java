package resistorSorter.controllers;
import java.util.List;

import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.IDatabase;

public class BinController {

	Bin model;
	
	public BinController(Bin model)
	{
		this.model = model;
	}
	//Resistor related methods
	
		//Adding resistors
		//Must pass in instance of inventory class!
		public void addBin(int inventory_id, int rack_id, int resistance, int count){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.insertBin(inventory_id, rack_id, resistance, count);
		}
		
		//Removing resistors
		//Must pass in an instance of inventory class!
		public void removeBin(int binID, int rackID, int inventoryID){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.removeBin(binID, rackID, inventoryID);
		}
		
		public List<Bin> displayBins(int inventoryID, int rackID){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			return db.getAllBins(inventoryID, rackID);
		}
		
		public void addResistor(int inventory_id, int rack_id, int resistance, int count){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.addResistors(inventory_id, rack_id, resistance, count);
		}
		
		public void removeResistor(int inventory_id, int rack_id, int resistance, int count){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.removeResistors(inventory_id, rack_id, resistance, count);
		}
}
