package resistorSorter.controllers;
import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;

public class BinController {

	Bin model;
	
	public BinController(Bin model)
	{
		this.model = model;
	}
	//Resistor related methods
	
		//Adding resistors
		//Must pass in instance of inventory class!
		public boolean addResistor(int count, Inventory i){
			if(model.getCount()+count > i.getBinCapacity()){
				System.out.println("Error: exceeded max bin capacity");	//Fails
				return false;
			}
			else{
				model.setCount(model.getCount() + count);				//Passes
				return true;
			}
		}
		
		//Removing resistors
		//Must pass in an instance of inventory class!
		public boolean removeResistor(int count, Inventory i){
			if(model.getCount() - count < 0){
				System.out.println("Error: requested to remove more than available");
				return false;											//Removing more than bin count
			}
			else if(count > i.getUserRemoveLimit()){
				System.out.println("Error: maximum amount allowed to remove exceeded");
				return false;
			}
			else{
				model.setCount(model.getCount() - count);				//Passes
				return true;
			}
		}
		//
}
