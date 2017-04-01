package resistorSorter.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rack{
	
	//Parameters//
	private float tolerance;
	private float wattage;
	private int binLength;
	private int inventory_id;
	private int rack_id;
	
	//Constructor//
	public Rack(int rack_id, int inventory_id, float tolerance, float wattage){
		this.tolerance = tolerance;
		this.wattage = wattage;
		this.inventory_id = inventory_id;
		this.rack_id = rack_id;
	}
	
	//Define Methods//
	
	//Getters
	public float getTol(){
		return tolerance;
	}
	
	public float getWatt(){
		return wattage;
	}
	
	//Setters
	public void setTol(float t){
		this.tolerance = t;
		
	}
	
	public void setWatt(float w){
		this.wattage = w;
	}
	
	public void setInvId(int id){
		this.inventory_id = id;
	}
	
	public int getInvId(){
		return inventory_id;
	}
	
	public int getRackId(){
		return rack_id;
	}

	

	
	
}