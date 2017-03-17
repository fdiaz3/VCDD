package resistorSorter.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rack{
	
	//Parameters//
	private double tolerance;
	private double wattage;
	private HashMap<String, Bin> bins;
	
	//Constructor//
	public Rack(double tolerance, double wattage){
		this.tolerance = tolerance;
		this.wattage = wattage;
		bins = new HashMap<String, Bin>();
	}
	
	//Define Methods//
	
	//Getters
	public double getTol(){
		return tolerance;
	}
	
	public double getWatt(){
		return wattage;
	}
	
	public HashMap<String, Bin> getBins(){
		return bins;
	}
	
	//Setters
	public void setTol(double t){
		this.tolerance = t;
		
	}
	
	public void setWatt(double w){
		this.wattage = w;
	}
	
	public void setBins(HashMap<String, Bin> bins){
		this.bins = bins;
	}
	
	//Bin related methods
	
	
}