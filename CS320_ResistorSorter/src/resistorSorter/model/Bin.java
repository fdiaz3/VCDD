package resistorSorter.model;

public class Bin {
	
	//Parameters//
	private int count;
	private String resistance;
	private boolean selected;
	
	
	//Constructor//
	public Bin(int count, String resistance, boolean selected){
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
	
	public boolean getSelected(){
		return this.selected;
	}
	//Setters
	public void setCount(int num){
		this.count = num;
	}
	
	public void setResistance(String s){
		this.resistance = s;
	}
	
	public void setSelected(boolean b){
		this.selected = b;
	}
	
}
