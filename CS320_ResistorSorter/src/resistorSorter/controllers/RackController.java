package resistorSorter.controllers;

import java.util.HashMap;
import java.util.List;

import resistorSorter.model.Bin;
import resistorSorter.model.Rack;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.IDatabase;

public class RackController {
	Rack model;
	
	public RackController(Rack model){
		this.model = model;
	}
	
	//Add rack is pressed
	public void addRack(float tolerance, float wattage, int inventory_id){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		db.insertRack(inventory_id, tolerance, wattage);
	}
	
	//Delete rack is pressed
	public void removeRack(int rackID){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		db.removeRack(rackID);
	}
	
	public List<Rack> displayRacks(int inventory_id){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		return db.getAllRacks(inventory_id);
	}
}
