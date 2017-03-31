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
		float tol = tolerance;								//Initialize tol to (double)to
		float watt = wattage;								//Initialize watt to (double)wa
		int inv_id = inventory_id;
	}
	
	//Delete rack is pressed
	public void removeRack(float tolerance, float wattage, int inventory_id){
		double tol = tolerance;										//Initialize tol to (double)to
		double watt = wattage;										//Initialize watt to (double)wa
		int inv_id = inventory_id;
	}
	
	public List<Rack> displayRacks(int inventory_id){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		return db.
	}
}
