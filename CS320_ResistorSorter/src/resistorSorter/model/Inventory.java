package resistorSorter.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	
	//Initialize fields//
	private int binCapacity;
	private int userRemoveLimit;
	private int rackLength;
	private int id;

	
	//Constructor//
	public Inventory(int binCapacity, int userRemoveLimit){
		this.binCapacity = binCapacity;
		this.userRemoveLimit = userRemoveLimit;

	}
	
	//Define Methods//
	
	//Setters
	public void setBinCapacity(int num){
		this.binCapacity = num;
	}
	
	public void setUserRemoveLimit(int num){
		this.userRemoveLimit = num;
	}
	public void SetID(int id){
		this.id = id;
	}
	//Getters
	public int getBinCapacity(){
		return binCapacity;
	}
	
	public int getUserRemoveLimit(){
		return userRemoveLimit;
	}
	public int getID(){
		return id;
	}
	
}
