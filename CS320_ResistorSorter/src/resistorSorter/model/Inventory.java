package resistorSorter.model;

import java.util.HashMap;

public class Inventory {
	
	//Initialize fields//
	private int binCapacity;
	private int userRemoveLimit;
	private Object listRacks;
	//private int numRacks; 			//No need for this, count of keys can be gotten directly from map
	//private ArrayList<String> racks; //No Need For This given that a set can be converted to a string implicitly
	private HashMap<String, Rack> racks;
	
	//Constructor//
	public Inventory(int binCapacity, int userRemoveLimit){
		this.binCapacity = binCapacity;
		this.userRemoveLimit = userRemoveLimit;
		racks = new HashMap<String, Rack>();
	}
	
	//Define Methods//
	
	//Setters
	public void setBinCapacity(int num){
		this.binCapacity = num;
	}
	
	public void setUserRemoveLimit(int num){
		this.userRemoveLimit = num;
	}
	
	public void setRacks(HashMap<String,Rack> racks){
		this.racks = racks;
	}
	
	
	
	//Getters
	public int getBinCapacity(){
		return this.binCapacity;
	}
	
	public int getUserRemoveLimit(){
		return this.userRemoveLimit;
	}
	public HashMap<String, Rack> getRacks(){
		return this.racks;
	}
	
	public int getNumRacks(){
		
		return getRacks().size();
	}
	
	public Object getListRacks(){
		listRacks = getRacks().keySet();
		return listRacks;
	}
	
	//Very important, this is how to access rack
	//object in order to get to its methods
	
	
}
