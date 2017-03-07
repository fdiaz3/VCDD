package resistorSorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class resistorSorterTest {

	private Inventory inventory;

	@Before
	public void setUp() {
			
		//Initialize inventory object with a bin capacity of 500 and user remove limit of 100
		inventory = new Inventory(500,100);
			
		//Create a couple rack objects to test
		inventory.addRack("5 0.25");			//5%, 0.25w//
		inventory.addRack("10 0.5");			//10%, 0.5w//
		inventory.addRack("1 1");				//1%, 1w//
		inventory.addRack("20 1.5");			//20%, 1.5w//
			
		//Create a couple bin objects within the racks
		inventory.getRack("5%, 0.25w").addBin("100", 20);		//20 100ohm resistors//
		inventory.getRack("5%, 0.25w").addBin("68", 123);		//123 68ohm resistors//
		
		inventory.getRack("10%, 0.5w").addBin("100", 10);		//10 100ohm resistors//
			
		inventory.getRack("20%, 1.5w").addBin("15", 18);		//18 15ohm resistors//
			
	}
		
	//Tests///////////////////////////////////////////////////////////////////////////
	
	//Inventory Methods test//////////////////////////////////////////////////////////
		
	@Test
	public void testGetBinCapacity() {
		assertTrue(inventory.getBinCapacity() == 500);
	}
	
	@Test
	public void testSetBinCapacity() {
		inventory.setBinCapacity(450);
		assertTrue(inventory.getBinCapacity() == 450);
	}
	
	@Test
	public void testGetUserRemoveLimit(){
		assertTrue(inventory.getUserRemoveLimit() == 100);
	}
	
	@Test
	public void testSetUserRemoveLimit() {
		inventory.setUserRemoveLimit(75);
		assertTrue(inventory.getUserRemoveLimit() == 75);
	}
	
	@Test
	public void testAddRack() {
		assertTrue(inventory.getRack("8%, 0.25w") == null);
		inventory.addRack("8 0.25");
		assertTrue(inventory.getRack("8%, 0.25w") != null);
	}
	
	@Test 
	public void testRemoveRack() {
		inventory.addRack("15 0.5");
		assertTrue(inventory.getRack("15%, 0.5w") != null);
		inventory.removeRack("15 0.5");
		assertTrue(inventory.getRack("15%, 0.5w") == null);
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	//Rack Methods test///////////////////////////////////////////////////////////////
	
	@Test
	public void testGetTolerance(){
		assertTrue(inventory.getRack("5%, 0.25w").getTol() == 5);
		assertTrue(inventory.getRack("1%, 1w").getTol() == 1);
		assertTrue(inventory.getRack("10%, 0.5w").getTol() == 10);
		assertTrue(inventory.getRack("20%, 1.5w").getTol() == 20);
	}
	
	@Test
	public void testGetWatt(){
		assertTrue(inventory.getRack("5%, 0.25w").getWatt() == 0.25);
		assertTrue(inventory.getRack("1%, 1w").getWatt() == 1);
		assertTrue(inventory.getRack("10%, 0.5w").getWatt() == 0.5);
		assertTrue(inventory.getRack("20%, 1.5w").getWatt() == 1.5);
	}
	
	@Test
	public void testGetBin(){
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm") != null);
		assertTrue(inventory.getRack("1%, 1w").getBin("100-ohm") == null);
	}
	
	@Test
	public void testAddBin(){
		assertTrue(inventory.getRack("1%, 1w").getBin("100-ohm") == null);
		inventory.getRack("1%, 1w").addBin("100", 15);
		assertTrue(inventory.getRack("1%, 1w").getBin("100-ohm") != null);
	}
	
	@Test
	public void testRemoveBin(){
		assertTrue(inventory.getRack("5%, 0.25w").getBin("68-ohm") != null);
		inventory.getRack("5%, 0.25w").removeBin("68");
		assertTrue(inventory.getRack("5%, 0.25w").getBin("68-ohm") == null);
	}
	/////////////////////////////////////////////////////////////////////////////////
	
	//Bin Methods test///////////////////////////////////////////////////////////////
	
	@Test
	public void testGetCount(){
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getCount() == 20);
		assertTrue(inventory.getRack("10%, 0.5w").getBin("100-ohm").getCount() == 10);
		assertTrue(inventory.getRack("20%, 1.5w").getBin("15-ohm").getCount() == 18);
	}
	
	@Test
	public void testGetResistance(){
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getResistance().equals("100-ohm"));
		assertTrue(inventory.getRack("10%, 0.5w").getBin("100-ohm").getResistance().equals("100-ohm"));
		assertTrue(inventory.getRack("20%, 1.5w").getBin("15-ohm").getResistance().equals("15-ohm"));
	}
	
	@Test
	public void testGetSelected(){
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getSelected() == false);
		inventory.getRack("5%, 0.25w").getBin("100-ohm").setSelected(true);
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getSelected() == true);
		inventory.getRack("5%, 0.25w").getBin("100-ohm").setSelected(false);
	}
	
	@Test
	public void testAddResistor(){
		inventory.getRack("5%, 0.25w").getBin("100-ohm").addResistor(10, inventory);		//Add 10 Resistors
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getCount() == 30);		//Count should be 30 now
		inventory.getRack("5%, 0.25w").getBin("100-ohm").addResistor(1000, inventory);		//Add more than bin cap(fails)
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getCount() == 30);		//Count should remain 30
	}
	
	@Test 
	public void testRemoveResistor(){
		inventory.getRack("5%, 0.25w").getBin("100-ohm").addResistor(430, inventory);
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getCount() == 450);		//Allowed to add 430
		inventory.getRack("5%, 0.25w").getBin("100-ohm").removeResistor(10, inventory);		//Remove 10
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getCount() == 440);		//Passes
		inventory.getRack("5%, 0.25w").getBin("100-ohm").removeResistor(500, inventory);	//Remove more than in bin(fails)
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getCount() == 440);		//Bin count stays same
		inventory.getRack("5%, 0.25w").getBin("100-ohm").removeResistor(101, inventory);	//Remove more than user remove limit
		assertTrue(inventory.getRack("5%, 0.25w").getBin("100-ohm").getCount() == 440);		//Bin count stays same
	}
	/////////////////////////////////////////////////////////////////////////////////
}
