package resistorSorter.controllers;
import java.util.HashMap;

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
	
		//Method that lets user/ admin add a rack
		//Assume user will enter values like "5 0.25"
		public void addRack(double tolerance, double wattage){
			String p = "%, ";								//Prepare to append to tol -> "%, "
			String w = "w";									//Prepare to append to "watt -> "w"
			double tol = tolerance;								//Initialize tol to (double)to
			double watt = wattage;								//Initialize watt to (double)wa
			String s = null;								//Initialize string s to null
			s = tol + p + watt + w; 						//Creating string object "5%, 0.25w"
			System.out.println(s);
			HashMap<String, Rack> updatedRacks = model.getRacks();
			updatedRacks.put(s, new Rack(tol, watt));
			model.setRacks(updatedRacks);					//Add rack to HashMap with our key and new Rack object
		}
		
		//Method that lets user remove a rack object
		//Assume user will enter values like "5 0.25"
		public void removeRack(double tolerance, double wattage){
			String p = "%, ";										//Prepare to append to "5" -> "5%, "
			String w = "w";											//Prepare to append to "0.25" -> "0.25w"
			double tol = tolerance;										//Initialize tol to (double)to
			double watt = wattage;										//Initialize watt to (double)wa
			String s = null;										//Initialize string s to null
			s = tol + p + watt + w; 							//Creating string object "5%, 0.25w"
			System.out.println(s);
			//Temporary racks Object
			HashMap<String, Rack> updatedRacks = model.getRacks();
			if(updatedRacks.containsKey(s)){									//Checking to see if desired rack exists
				updatedRacks.remove(s);										//Removing rack
				System.out.println("Rack successfully removed");	//Inform user that rack was removed
			}
			else{
				System.out.println("Rack does not exist");			//Rack object did not exist, report to user.
			}
		}
		
		
		
		//Length of rack list
		
		public Rack getRack(String s){
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			
			
			
			
		}

		public Inventory getModel() {
			
			return model;
		}
}
