package resistorSorter.model;

import java.sql.Timestamp;
public class InventoryTransaction{
	
	//Parameters//
	private int transaction_id;
	private String transactionTime;
	private String username;
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
		//parsing the timeStamp
		String temptt = transactionTime.toString();
		String yyyy = temptt.substring(0, 4);
		String dd = temptt.substring(8,10);
		String mm = temptt.substring(5,7);
		String minute = temptt.substring(14, 16);
		String dayPeriod = null;
		int twentyFourHour = Integer.parseInt(temptt.substring(11, 13));
		if(twentyFourHour > 12){
			twentyFourHour -= 12;
			dayPeriod = "pm";
		}else{
			dayPeriod = "am";
		}
		this.transactionTime = mm + "/" + dd + "/" + yyyy + "  " + twentyFourHour + ":" + minute + " " + dayPeriod;
				
		this.username = email.substring(0, email.indexOf('@'));
		this.resistance = resistance;
		this.wattage = wattage;
		this.tolerance = tolerance;
		this.quantity = quantity;
		//parsing the transactionType
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
	public String getUsername(){
		return username;
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