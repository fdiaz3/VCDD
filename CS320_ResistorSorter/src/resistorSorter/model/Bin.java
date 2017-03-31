package resistorSorter.model;

public class Bin {
	
	//Parameters//
	private int count;
	private String resistance;
	private boolean selected;
	int inventory_id;
	int rack_id;
	
	//Constructor//
	public Bin(int count, String resistance, boolean selected, int inventory_id, int rack_id){
		this.count = count;
		this.resistance = resistance;
		this.selected = selected;
		
		this.inventory_id = inventory_id;
		this.rack_id = rack_id;
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
	
	public int getInvId(){
		return inventory_id;
	}
	
	public void setInvId(int id){
		this.inventory_id = id;
	}
	
	public int getRackId(){
		return rack_id;
	}
	
	public void setRackId(int id){
		this.rack_id = id;
	}
	

}
