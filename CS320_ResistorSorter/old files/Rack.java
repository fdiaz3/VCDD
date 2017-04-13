package resistorSorter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rack{
	
	//Parameters//
	private double tolerance;
	private double wattage;
	private HashMap<String, Bin> m;
	
	//Constructor//
	public Rack(double tolerance, double wattage){
		this.tolerance = tolerance;
		this.wattage = wattage;
		m = new HashMap<String, Bin>();
	}
	
	//Define Methods//
	
	//Getters
	public double getTol(){
		return tolerance;
	}
	
	public double getWatt(){
		return wattage;
	}
	
	//Setters
	public void setTol(double t){
		this.tolerance = t;
		
	}
	
	public void setWatt(double w){
		this.wattage = w;
	}
	
	//Bin related methods
	
	//Get a bin
	public Bin getBin(String s){
		if(m.containsKey(s)){
			return m.get(s);
		}
		else{
			System.out.println("Bin does not exist");
			return null;
		}
	}
	
	//Add a bin
	//Unlike adding a rack the user enters resistance (string) and amount separately(int)
	public void addBin(String s, int num){
		s += "-ohm";							//Turn 100 -> 100-ohm
		m.put(s, new Bin(num, s, false));		//Add the bin to the HashMap
	}
	
	//Remove a bin
	public void removeBin(String s){
		s+="-ohm";
		m.remove(s);
	}
}