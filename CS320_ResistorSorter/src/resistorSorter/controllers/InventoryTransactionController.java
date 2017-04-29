package resistorSorter.controllers;
import java.sql.Timestamp;
import java.util.List;

import resistorSorter.model.Bin;
import resistorSorter.model.InventoryTransaction;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.TestDerbyDatabase;

public class InventoryTransactionController {

	InventoryTransaction model;
	IDatabase db;
	
	public InventoryTransactionController(InventoryTransaction model, String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
		this.model = model;
	}
	
	//Empty Constructor
	public InventoryTransactionController(String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
	}
	
	public void addTransaction(String userName, int bin_id, int quantity, boolean transactionType){
		Timestamp transactionTime = new Timestamp(System.currentTimeMillis());
		db.addTransaction(userName, bin_id, transactionTime, transactionType, quantity);
	}
	
	public List<InventoryTransaction> displayUserInventoryTransactions(String username){
		return db.getAllUserTransactions(username);
	}
	public List<InventoryTransaction> displayInventoryTransactions(){
		return db.getAllTransactions();
	}
	
}
