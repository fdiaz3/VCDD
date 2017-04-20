package resistorSorter.model;

import java.sql.Timestamp;
public class InventoryTransaction{
	
	//Parameters//
	private int transaction_id;
	private int user_id;
	private int inventory_id;
	private int rack_id;
	private int bin_id;
	private Timestamp transactionTime;
	private String transacationType;
	private int quantity;
	
	
	//Constructor//
	public InventoryTransaction(int transaction_id, int user_id, int inventory_id, int rack_id, int bin_id, Timestamp transactionTime, String transacationType, int quantity){
		this.transaction_id = transaction_id;
		this.user_id = user_id;
		this.inventory_id = inventory_id;
		this.rack_id = rack_id;
		this.bin_id = bin_id;
		this.transactionTime = transactionTime;
		this.transacationType = transacationType;
		this.quantity = quantity;
	}
	

	
	

	

	

	
	
}