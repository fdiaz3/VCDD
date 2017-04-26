package resistorSorter.controllers;
import java.util.List;

import resistorSorter.model.Bin;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.PersistenceException;
import resistorSorter.persist.TestDerbyDatabase;

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
	
	
		public String addBin(int rack_id, int resistance, int count){
			int capacity;
			try{
				capacity = db.getCapacityFromRack(rack_id);
			}catch(PersistenceException e){
				return "Adding to invalid rack";
			}
			if(count > 0 && resistance > 0){
				if(count > capacity){
					System.out.println("Exceeding cap");
					return "Count cannot exceed Bin Capacity";
				}
				else{
					db.insertBin(rack_id, resistance, count);
					return null;
				}
			}
			else{
				return "Cannot enter negative/string/zero/large values";
			}
		}
		
		public void removeBin(int binID){
			db.removeBin(binID);
		}
		
		public List<Bin> displayBins(int rackID){
			return db.getAllBins(rackID);
		}
		
		public String addResistor(int bin_id, int addition){
			int count;
			int capacity;
			//Making sure bin is valid
			if(bin_id == 0){
				return "Adding to Invalid Bin ID";
			}
			try{
				count = count= db.getCount(bin_id);
				capacity = capacity= db.getCapacity(bin_id);
			}catch(PersistenceException e){
				return "Adding to Invalid Bin ID";
			}
			
			
			if(addition > 0){
				if(count + addition > capacity){
					return "Exceeding Capacity";
				}
			}
			else{
				
			}
			//if all tests pass
			db.addResistors(bin_id, addition);
			return null;
		}
		
		public String removeResistor(int bin_id, int subtraction){
			int count;
			int removelimit;
			if(bin_id == 0){
				return "Removing from Invalid Bin ID";
			}
			try{
				count = db.getCount(bin_id);
				removelimit = db.getUserRemoveLimit(bin_id);
			}catch(PersistenceException e){
				return "Removing from Invalid Bin ID";
			}
			if(subtraction > 0){
				if(subtraction > removelimit){
					return "Exceeding Remove Limit";
				}
				else if(count - subtraction < 0){
					return "Subtracting more than avaliable";
				}			
			}
			else{
				return "Cannot enter negative/string/zero/large values";
			}
			//if all tests pass
			db.removeResistors(bin_id, subtraction);
			return null;
		}
		
		public int getCount(int bin_id){
			try{
				return db.getCount(bin_id);
			}catch(PersistenceException e){
				return 0;
			}
		}
		public int getUserRemoveLimit(int bin_id){
			try{
				return db.getUserRemoveLimit(bin_id);
			}catch(PersistenceException e){
				return 0;
			}
		}
		public int getCapacity(int bin_id){
			try{
				return db.getCapacity(bin_id);
			}catch(PersistenceException e){
				return 0;
			}
		}
		
}
