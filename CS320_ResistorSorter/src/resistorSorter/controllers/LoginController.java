package resistorSorter.controllers;

import resistorSorter.persist.IDatabase;
import resistorSorter.model.EmailSend;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.TestDerbyDatabase;



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
	
	public void createAccount(String username, String password, String firstname, String lastname, boolean adminReq){
		//Grant or deny admin request
		if(adminReq){
			String[] to = {"dwebb4@ycp.edu", "blinne@ycp.edu" , "fdiaz@ycp.edu"};
			
			String message = "Hello creators of the VCDD, this is your automated email sending unit up and running (hopefully). \n"
					+"User: "+username+", "+"First Name: "+firstname+", "+"Lastname: "+lastname+", "+"has requested for admin permissions. \n"
					+"Click here to grant: "
					+"http://localhost:8081/resistorSorter/AdminRequest?username="+username+"&adminReq=true \n"
					+"Click here to deny: "
					+"http://localhost:8081/resistorSorter/AdminRequest?username="+username+"&adminReq=false \n";
			if(EmailSend.sendMail("vcddProj@gmail.com", "team_dbf", message, to, username)){
				System.out.println("Message sent successfuly");
			}
			else{
				if(EmailSend.sendMail("vcddProj@gmail.com", "team_dbf", message, to, username)){
					System.out.println("Message sent successfuly");
				}
				else{
					System.out.println("Email error occured, something went really wrong");
				}
				
			}
		}
		db.createAccount(username, password, firstname, lastname, false);
	}
	public boolean validateCredentials(String name, String pw) {
		return db.validateCredentials(name, pw);
	}
	public boolean checkExistingUsernames(String username){
		//System.out.println(username);
		return db.checkExistingUsernames(username);
	}
	
	public void updateAdminFlag(String username, boolean adminReq){
		db.updateAdminFlag(username, adminReq);
	}
	//DISCLAIMER//
	//All work seen in here has been copied, but modified from the Library example//
	
	
}