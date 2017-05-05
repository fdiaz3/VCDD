package resistorSorter.model;

import java.sql.Timestamp;
public class InventoryTransaction{
	
	//Parameters//
	private int transaction_id;
	private String transactionTime;
	private String email;
	private int resistance;
	private float wattage;
	private float tolerance;
	private int quantity;
	//TRUE = ADD, FALSE = REMOVE
	private char transactionType;
	private int remaining;
	
	
	//Constructor//
	public InventoryTransaction(int transaction_id, Timestamp transactionTime, String email, int resistance, float wattage, float tolerance, int quantity, boolean transactionType, int remaining){
		this.transaction_id = transaction_id;
		this.transactionTime = transactionTime.toString().substring(0, transactionTime.toString().indexOf("."));
		this.email = email;
		this.resistance = resistance;
		this.wattage = wattage;
		this.tolerance = tolerance;
		this.quantity = quantity;
		if(transactionType == true){
			this.transactionType = '+';
		}else{
			this.transactionType = '-';
		}
		this.remaining = remaining;
		
	}
	
	public int getTransaction_id(){
		return transaction_id;
	}
	public String getTransactionTime(){
		return transactionTime;
	}
	public String getEmail(){
		return email;
	}
	public int getResistance(){
		return resistance;
	}
	public float getWattage(){
		return wattage;
	}
	public float getTolerance(){
		return tolerance;
	}
	public int getQuantity(){
		return quantity;
	}
	public char getTransactionType(){
		return transactionType;
	}
	public int getRemaining(){
		return remaining;
	}
	

	
}