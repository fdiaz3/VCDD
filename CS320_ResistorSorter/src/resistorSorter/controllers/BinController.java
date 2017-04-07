package resistorSorter.controllers;
import java.util.List;

import resistorSorter.model.Bin;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.IDatabase;
import resistorSorterdb.persist.TestDerbyDatabase;

public class BinController {

	Bin model;
	IDatabase db;
	
	public BinController(Bin model, String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
		this.model = model;
	}
	
	//Empty Constructor
	public BinController(String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
	}
	
	
		public void addBin(int rack_id, int resistance, int count){
			db.insertBin(rack_id, resistance, count);
		}
		
		public void removeBin(int binID){
			db.removeBin(binID);
		}
		
		public List<Bin> displayBins(int rackID){
			return db.getAllBins(rackID);
		}
		
		public void addResistor(int bin_id, int count){
			//get userRemoveLimit from database based on bin_id
			db.addResistors(bin_id, count);
		}
		
		public void removeResistor(int bin_id, int count){
			db.removeResistors(bin_id, count);
		}
		
		public int getCount(int bin_id){
			return db.getCount(bin_id);
		}
		public int getUserRemoveLimit(int bin_id){
			return db.getUserRemoveLimit(bin_id);
		}
		public int getCapacity(int bin_id){
			return db.getCapacity(bin_id);
		}
		
}
