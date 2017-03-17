package resistorSorter.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rack{
	
	//Parameters//
	private double tolerance;
	private double wattage;
	private HashMap<String, Bin> m;
	private ArrayList<String> bins;
	private int binLength;
	
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
		System.out.println("bin added");
	}
	
	//Remove a bin
	public void removeBin(String s){
		s = s.substring(0, s.indexOf(','));
		System.out.println(s);
		//update bins arraylist
		//getBins();
		
		m.remove(s);
	}
	
	//showing all bin objects as strings in an arraylist
		public ArrayList<String> getBins(){
			bins = new ArrayList<String>(m.keySet());
			
			for(int i=0; i<m.size(); i++){
				bins.set(i, bins.get(i) + ", " + getBin(bins.get(i)).getCount());
			}
			return bins;
		}
		
		//Length of bins list (number of bins in a rack)
		public int getBinLength(){
			bins = new ArrayList<String>(m.keySet());
			binLength = bins.size();
			return binLength;
		}
	
	
}