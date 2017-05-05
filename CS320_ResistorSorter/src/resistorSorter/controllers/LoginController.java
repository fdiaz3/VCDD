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
	
	public void userRequestAdmin(String email, boolean adminReq){
		//Grant or deny admin request
		if(adminReq){
			String uuid = db.getUUID(email);
			String[] to = {"dwebb4@ycp.edu", "blinne@ycp.edu" , "fdiaz@ycp.edu"};
	
			String message = "Hello creators of the VCDD, this is your automated email sending unit up and running (hopefully). \n"
					+"User: "+email+", "+"has requested for admin permissions. \n"
					+"Click here to grant: \n"
					+"http://localhost:8081/resistorSorter/AdminRequest?username="+email+"&adminReq=true&uuid="+uuid+"\n"
					+"http://calientecraft.org:8081/resistorSorter/AdminRequest?username="+email+"&adminReq=true&uuid="+uuid+"\n"
					+"Click here to revoke: \n"
					+"http://localhost:8081/resistorSorter/AdminRequest?username="+email+"&adminReq=false&uuid="+uuid+"\n"
					+"http://calientecraft.org:8081/resistorSorter/AdminRequest?username="+email+"&adminReq=false&uuid="+uuid+"\n";
			if(EmailSend.sendMail("vcddProj@gmail.com", "team_dbf", message, to, email)){
				System.out.println("Message sent successfuly");
			}
			else{
				if(EmailSend.sendMail("vcddProj@gmail.com", "team_dbf", message, to, email)){
					System.out.println("Message sent successfuly");
				}
				else{
					System.out.println("Email error occured, something went really wrong");
				}
				
			}
		}
	}
	public void insertNewUser(String email){
		String uuid = UUID.randomUUID().toString();
		db.insertNewUser(email, uuid);
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