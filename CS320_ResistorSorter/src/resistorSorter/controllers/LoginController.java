package resistorSorter.controllers;

import resistorSorter.model.Library;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.TestDerbyDatabase;

public class LoginController {
	private Library model = null;
	private IDatabase db;
	
	public LoginController(Library model, String database) {
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
		this.model = model;
	}
	public LoginController(String database){
		model = null;
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
	}
	
	public void createAccount(String username, String password, String firstname, String lastname, boolean adminReq){
		db.createAccount(username, password, firstname, lastname, adminReq);
	}
	public boolean checkUserName(String name) {
		return model.validateUserName(name);
	}
	
	public boolean validateCredentials(String name, String pw) {
		return db.validateCredentials(name, pw);
	}
	public boolean checkExistingUsernames(String username){
		//System.out.println(username);
		return db.checkExistingUsernames(username);
	}
	
	//DISCLAIMER//
	//All work seen in here has been copied, but modified from the Library example//
	
	
}