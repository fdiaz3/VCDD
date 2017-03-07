package resistorSorter;

public class Bin {
	
	//Parameters//
	private int count;
	private String resistance;
	private Boolean selected;
	
	//Constructor//
	public Bin(int count, String resistance, Boolean selected){
		this.count = count;
		this.resistance = resistance;
		this.selected = selected;
	}
	
	//Methods//
	
	//Getters
	public int getCount(){
		return this.count;
	}
	
	public String getResistance(){
		return this.resistance;
	}
	
	public Boolean getSelected(){
		return this.selected;
	}
	//Setters
	public void setCount(int num){
		this.count = num;
	}
	
	public void setResistance(String s){
		this.resistance = s;
	}
	
	public void setSelected(Boolean b){
		this.selected = b;
	}
	//Resistor related methods
	
	//Adding resistors
	//Must pass in instance of inventory class!
	public Boolean addResistor(int count, Inventory i){
		if(this.count+count > i.getBinCapacity()){
			System.out.println("Error: exceeded max bin capacity");	//Fails
			return false;
		}
		else{
			this.count+= count;										//Passes
			return true;
		}
	}
	
	//Removing resistors
	//Must pass in an instance of inventory class!
	public Boolean removeResistor(int count, Inventory i){
		if(this.count - count < 0){
			System.out.println("Error: requested to remove more than available");
			return false;											//Removing more than bin count
		}
		else if(count > i.getUserRemoveLimit()){
			System.out.println("Error: maximum amount allowed to remove exceeded");
			return false;
		}
		else{
			this.count-=count;										//Passes
			return true;
		}
	}
}
