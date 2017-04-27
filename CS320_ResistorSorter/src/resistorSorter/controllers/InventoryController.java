package resistorSorter.controllers;

import java.util.List;

import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.model.Inventory;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.TestDerbyDatabase;

public class InventoryController {

	Inventory model;
	IDatabase db;
	
	//constructor with model
	public InventoryController(Inventory model, String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
		this.model = model;
	}
	
	//constructor without model
	public InventoryController(String database){
		model = null;
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
	}
	
	
	
	
		//Add rack is pressed
		public String addInventory(int binCapacity, int userRemoveLimit){
			if(binCapacity <= 0 || userRemoveLimit <= 0){
				return "Cannot have negative//zero/large int for Bin Capacity or User Remove Limit";
			}
			else if(userRemoveLimit > binCapacity){
				return "Cannot have Bin Capacity smaller than the User Remove Limit";
			}
			db.insertInventory(binCapacity, userRemoveLimit);
			return null;
		}
		
		//Delete rack is pressed
		public void removeInventory(int inventoryID){
			db.removeInventory(inventoryID);
		}
		
		//For Edit rack pressed
		public List<Inventory> displayInventories(){
			return db.getAllInventories();			
		}

		public Inventory getModel() {	
			return model;
		}
}
