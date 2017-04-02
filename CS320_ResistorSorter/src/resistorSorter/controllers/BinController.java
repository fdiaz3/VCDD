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
	public BinController()
	{

	}
		//Adding resistors
		//Must pass in instance of inventory class!
		public void addBin(int rack_id, int resistance, int count){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.insertBin(rack_id, resistance, count);
		}
		
		//Removing resistors
		//Must pass in an instance of inventory class!
		public void removeBin(int binID){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.removeBin(binID);
		}
		
		public List<Bin> displayBins(int rackID){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			return db.getAllBins(rackID);
		}
		
		public void addResistor(int bin_id, int count){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.addResistors(bin_id, count);
		}
		
		public void removeResistor(int bin_id, int count){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.removeResistors(bin_id, count);
		}
}
