package resistorSorter.controllers;

import resistorSorter.persist.IDatabase;

import java.util.Random;

import resistorSorter.model.EmailSend;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.TestDerbyDatabase;
import java.util.UUID;

public class LoginController {
	private IDatabase db;
	
	public LoginController(String database){

		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
	}
	
	public void updateAdminFlag(String email, boolean admin){
		db.updateAdminFlag(email, admin);
	}
	public boolean getAdminFlag(String username){
		return db.getAdminFlag(username);
	}
	public boolean containsNoSpacesUsername(String username){
		if(username.contains(" ")){
			return false;
		}
		return true;
	}
	public boolean checkUUID(String email, String uuid){
		return db.checkUUID(email, uuid);
	}
	public boolean checkIfInDatabase(String email){
		return db.checkIfInDatabase(email);
	}
	public boolean checkYCP(String email){
		if(email.contains("ycp.edu")){
			return true;
		}
		return false;
	}
	//DISCLAIMER//
	//All work seen in here has been copied, but modified from the Library example//
	
	
}