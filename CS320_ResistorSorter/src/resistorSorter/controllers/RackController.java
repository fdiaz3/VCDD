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
		if(inventory_id == 0){
			return "Adding to invalid inventory";
		}
		if(tolerance > 25){
			return "Only a maximum tolerance of 25% is acceptable";
		}
		if(tolerance <= 0 || wattage <= 0){
			return "Tolerance or wattage cannot be  negative/string/zero/large ";
		}
		if(db.checkExistingRacks(tolerance, wattage, inventory_id)){
			return "Cannot have matching racks under one inventory";
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
