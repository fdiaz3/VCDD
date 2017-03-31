package resistorSorter.controllers;
import java.util.HashMap;
import java.util.List;

import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;
import resistorSorterdb.persist.IDatabase;

public class InventoryController {

	Inventory model;
	
	public InventoryController(Inventory model)
	{
		this.model = model;
	}
	//Rack related methods
	
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
		
		//For Edit rack pressed
		public List<Inventory> displayInventories(){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			return db.getAllInventories();			
		}

		public Inventory getModel() {	
			return model;
		}
}
