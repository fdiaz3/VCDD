package resistorSorter.controllers;

import java.util.List;

import resistorSorter.model.Rack;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.IDatabase;
import resistorSorterdb.persist.TestDerbyDatabase;

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
	public void addRack(float tolerance, float wattage, int inventory_id){
		db.insertRack(inventory_id, tolerance, wattage);
	}
	
	//Delete rack is pressed
	public void removeRack(int rackID){
		db.removeRack(rackID);
	}
	
	public List<Rack> displayRacks(int inventory_id){
		return db.getAllRacks(inventory_id);
	}
}
