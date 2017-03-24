package resistorSorter.controllers;

import java.util.HashMap;

import resistorSorter.model.Bin;
import resistorSorter.model.Rack;

public class RackController {
	Rack model;
	
	public RackController(Rack model){
		this.model = model;
	}
	
	//Get a bin
		public Bin getBin(String s){
			if(model.getBins().containsKey(s)){
				return model.getBins().get(s);
			}
			else{
				System.out.println("Bin does not exist");
				return null;
			}
		}
		
		//Add a bin
		//Unlike adding a rack the user enters resistance (string) and amount separately(int)
		public void addBin(String s, int num){
			s += "-ohm";								//Turn 100 -> 100-ohm
			HashMap<String, Bin> updatedBins = model.getBins();
			updatedBins.put(s, new Bin(num, s, false));		//Add the bin to the HashMap
			model.setBins(updatedBins);
		}
		
		//Remove a bin
		public void removeBin(String s){
			s+="-ohm";
			HashMap<String, Bin> updatedBins = model.getBins();
			updatedBins.remove(s);
			model.setBins(updatedBins);
		}
		
		public int numBins(){
			return model.getBins().size();
		}
		
		public Object[] listBins(){
			return model.getBins().keySet().toArray();
		}
}
