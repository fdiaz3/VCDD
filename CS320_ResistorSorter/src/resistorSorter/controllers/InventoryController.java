package resistorSorter.controllers;

import java.util.List;

import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;
import resistorSorterdb.persist.IDatabase;

public class InventoryController {

	Inventory model;
	//With model
	public InventoryController(Inventory model)
	{
		this.model = model;
	}
	public InventoryController()
	{
		model = null;
	}
	//Rack related methods
	
		//Add rack is pressed
		public void addInventory(int binCapacity, int userRemoveLimit){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.insertInventory(binCapacity, userRemoveLimit);
		}
		
		//Delete rack is pressed
		public void removeInventory(int inventoryID){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			db.removeInventory(inventoryID);
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
