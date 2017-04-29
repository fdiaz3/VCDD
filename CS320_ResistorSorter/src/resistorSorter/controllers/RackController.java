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
	public String addRack(float tolerance, float wattage, int inventory_id, String user){
		if(inventory_id == 0){
			return "Adding to invalid inventory";
		}
		else if(tolerance > 25){
			return "Only a maximum tolerance of 25% is acceptable";
		}
		else if(tolerance <= 0 || wattage <= 0){
			return "Tolerance or wattage cannot be  negative/string/zero/large ";
		}
		else if(db.checkExistingRacks(tolerance, wattage, inventory_id)){
			return "Cannot have matching racks under one inventory";
		}
		else if(!db.checkAdminStatus(user)){
			return "Only administrators can do that";
		}
		db.insertRack(inventory_id, tolerance, wattage);
		return null;
	}
	
	//Delete rack is pressed
	public String removeRack(int rackID, String user){
		if(!db.checkAdminStatus(user)){
			return "Only administrators can do that";
		}
		db.removeRack(rackID);
		return null;
	}
	
	public List<Rack> displayRacks(int inventory_id){
		return db.getAllRacks(inventory_id);
	}
}
