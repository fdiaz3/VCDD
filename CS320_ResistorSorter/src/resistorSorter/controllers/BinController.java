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
		
		public String addResistor(int bin_id, int addition){
			int count = db.getCount(bin_id);
			int capacity = db.getCapacity(bin_id);
			
			if(addition < 0){
				return "Can't add negative values";
			} else if(count + addition > capacity){
				return "Exceeding Capacity";
			}
			
			//if all tests pass
			db.addResistors(bin_id, addition);
			return null;
		}
		
		public String removeResistor(int bin_id, int subtraction){
			int count = db.getCount(bin_id);
			int removelimit = db.getUserRemoveLimit(bin_id);
			
			if(subtraction < 0){
				return "Can't subtract negative values";
			} else if(count > removelimit){
				return "Exceeding Remove Limit";
			} else if(count - subtraction < 0){
				return "Subtracting more than avaliable";
			}			
			
			//if all tests pass
			db.removeResistors(bin_id, subtraction);
			return null;
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
