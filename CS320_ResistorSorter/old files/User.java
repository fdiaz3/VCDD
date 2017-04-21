package resistorSorter;

public class User {

	//Parameters//
	private String username;
	private Boolean Admin;
	private long hash;
	
	//Constructor//
	public User(String username, long hash){
		this.username = username;
		this.hash = hash;
		this.Admin = false;
	}
	
	//Methods//
	
	//Getters 
	public String getUsername(){
		return this.username;
	}
	
	public Boolean getAdmin(){
		return false;
	}
	
	public long getHash(){
		return this.hash;
	}
	
	//Setters
	public void setUsername(String s){
		this.username = s;
	}
	
	public void setHash(long h){
		this.hash = h;
	}
	
	//User related methods
	
	//Login process
	public void login(){
		if("username" == username && Long.toString(hash) == "password"){
			System.out.println("You are now logged in as admin");
			this.Admin = true;
		}
		else{
			System.out.println("Login failed");
		}
	}
	
	//Logout
	public void logout(){
		System.out.println("Logout sucessful");
		this.Admin = false;
	}
	
	//When you timeout
	public void timeOut(){
		System.out.println("You were logged out");
		this.Admin = false;
	}
}
