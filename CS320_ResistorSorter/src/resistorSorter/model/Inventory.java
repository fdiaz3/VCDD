package resistorSorter.model;
import java.util.HashMap;

public class Inventory {
	
	//Initialize fields//
	private int binCapacity;
	private int userRemoveLimit;
	private HashMap<String, Rack> m;
	
	//Constructor//
	public Inventory(int binCapacity, int userRemoveLimit){
		this.binCapacity = binCapacity;
		this.userRemoveLimit = userRemoveLimit;
		m = new HashMap<String, Rack>();
	}
	
	//Define Methods//
	
	//Setters
	public void setBinCapacity(int num){
		this.binCapacity = num;
	}
	
	public void setUserRemoveLimit(int num){
		this.userRemoveLimit = num;
	}
	
	//Getters
	public int getBinCapacity(){
		return this.binCapacity;
	}
	
	public int getUserRemoveLimit(){
		return this.userRemoveLimit;
	}
	
	//Very important, this is how to access rack
	//object in order to get to its methods
	public Rack getRack(String s){
		if(m.containsKey(s)){
			return m.get(s);
		}
		else{
			System.out.println("Rack does not exist");
			return null;
		}
	}
	//Rack related methods
	
	//Method that lets user/ admin add a rack
	//Assume user will enter values like "5 0.25"
	public void addRack(String s){
		String p = "%, ";								//Prepare to append to "5" -> "5%, "
		String w = "w";									//Prepare to append to "0.25" -> "0.25w"
		String[] result = s.split("\\s");				//Split string s into two substrings at space
		double tol = Double.parseDouble(result[0]);		//Convert "5" into double
		double watt = Double.parseDouble(result[1]);	//Convert "0.25" into double
		s = result[0]+p+result[1]+w;					//Creating string object "5 0.25" -> "5%, 0.25w"
		m.put(s, new Rack(tol, watt));					//Add rack to HashMap with our key and new Rack object
	}
	
	//Method that lets user remove a rack object
	//Assume user will enter values like "5 0.25"
	public void removeRack(String s){
		String p = "%, ";										//Prepare to append to "5" -> "5%, "
		String w = "w";											//Prepare to append to "0.25" -> "0.25w"
		String[] result = s.split("\\s");						//Split string s into two substrings at space
		s = result[0]+p+result[1]+w;							//Creating string object "5 0.25" -> "5%, 0.25w"
		if(m.containsKey(s)){									//Checking to see if desired rack exists
			m.remove(s);										//Removing rack
			System.out.println("Rack successfully removed");	//Inform user that rack was removed
		}
		else{
			System.out.println("Rack does not exist");			//Rack object did not exist, report to user.
		}
	}
}
