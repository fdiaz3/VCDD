package resistorSorter.model;

public class Bin {
	
	//Parameters//
	private int count;
	private String resistance;
	private boolean selected;
	int rack_id;
	int bin_id;
	
	//Constructor//
	public Bin(int bin_id, int rack_id, int count, String resistance){
		this.count = count;
		this.resistance = resistance;
		this.selected = false;
		this.rack_id = rack_id;
		this.bin_id = bin_id;
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
	
	public int getRack_id(){
		return rack_id;
	}
	
	public void setRack_id(int id){
		this.rack_id = id;
	}
	
	public int getBin_id(){
		return bin_id;
	}

}
