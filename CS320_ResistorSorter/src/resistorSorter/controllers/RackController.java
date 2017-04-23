package resistorSorter.controllers;

import java.util.List;

import resistorSorter.model.Rack;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.TestDerbyDatabase;

public class RackController {
	Rack model;
	IDatabase db;
	
	public RackController(Rack model, String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
		this.model = model;
	}
	public RackController(String database){
		model = null;
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
	}
	//Add rack is pressed
	public String addRack(float tolerance, float wattage, int inventory_id){
		if(tolerance < 0 || wattage < 0){
			return "Tolerance or wattage cannot be negative";
		}
		db.insertRack(inventory_id, tolerance, wattage);
		return null;
	}
	
	//Delete rack is pressed
	public void removeRack(int rackID){
		db.removeRack(rackID);
	}
	
	public List<Rack> displayRacks(int inventory_id){
		return db.getAllRacks(inventory_id);
	}
}
