package resistorSorter.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	
	//Initialize fields//
	private int binCapacity;
	private int userRemoveLimit;
	private int inventory_id;

	
	//Constructor//
	public Inventory(int inventory_id, int binCapacity, int userRemoveLimit){
		this.binCapacity = binCapacity;
		this.userRemoveLimit = userRemoveLimit;
		this.inventory_id = inventory_id;
	}
	
	//Define Methods//
	
	//Setters
	public void setBinCapacity(int num){
		this.binCapacity = num;
	}
	
	public void setUserRemoveLimit(int num){
		this.userRemoveLimit = num;
	}
	//Getters
	public int getBinCapacity(){
		return binCapacity;
	}
	
	public int getUserRemoveLimit(){
		return userRemoveLimit;
	}
	
	public int getInventory_id(){
		return inventory_id;
	}
}
