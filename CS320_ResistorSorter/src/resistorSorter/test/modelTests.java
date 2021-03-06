package resistorSorter.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;
import resistorSorter.model.User;



public class modelTests {

	private Inventory modelInventory1, modelInventory2;
	private Rack modelRack1, modelRack2, modelRack3, modelRack4;
	private Bin modelBin1, modelBin2, modelBin3, modelBin4;
	private User modelUser;


	
	@Before
	public void setUp() throws Exception{
		
		//Initialize inventory object with a bin capacity of 500 and user remove limit of 100
		modelInventory1 = new Inventory(1,500,100,"username");
		modelInventory2 = new Inventory(2,400,150,"username");
		
		//Create a rack object to test 5%, 0.25w
		modelRack1 = new Rack(1, 1, 5, (float) 0.25);
		modelRack2 = new Rack(2, 1, 10, (float) 0.5);
		modelRack3 = new Rack(3, 2, 5, (float) 0.25);
		modelRack4 = new Rack(4, 2, 10, (float) 0.5);
		//Create a bin object to test
		modelBin1 = new Bin(1, 1, 50, "100-ohm");
		modelBin2 = new Bin(2, 1, 25, "60-ohm");
		modelBin3 = new Bin(3, 2, 60, "25-ohm");
		modelBin4 = new Bin(4, 4, 100, "10-ohm");
		//Create a user object to test
		modelUser = new User("username", 14);
	}
		
	//Tests///////////////////////////////////////////////////////////////////////////
	
	//Inventory Methods test//////////////////////////////////////////////////////////
		
	@Test
	public void testGetBinCapacity() {
		assertTrue(modelInventory1.getBinCapacity() == 500);
		assertTrue(modelInventory2.getBinCapacity() == 400);
	}
	
	@Test
	public void testSetBinCapacity() {
		modelInventory1.setBinCapacity(450);
		assertFalse(modelInventory1.getBinCapacity() == 500);
		assertTrue(modelInventory1.getBinCapacity() == 450);
		
		modelInventory2.setBinCapacity(700);
		assertFalse(modelInventory2.getBinCapacity() == 400);
		assertTrue(modelInventory2.getBinCapacity() == 700);
	}
	
	@Test
	public void testGetUserRemoveLimit(){
		assertTrue(modelInventory1.getUserRemoveLimit() == 100);
		assertTrue(modelInventory2.getUserRemoveLimit() == 150);
	}
	
	@Test
	public void testSetUserRemoveLimit() {
		modelInventory1.setUserRemoveLimit(55);
		assertFalse(modelInventory1.getUserRemoveLimit() == 100);
		assertTrue(modelInventory1.getUserRemoveLimit() == 55);
		
		modelInventory2.setUserRemoveLimit(70);
		assertFalse(modelInventory2.getUserRemoveLimit() == 150);
		assertTrue(modelInventory2.getUserRemoveLimit() == 70);
	}
	
	@Test
	public void testGetInventory_id(){
		assertTrue(modelInventory1.getInventory_id() == 1);
		assertTrue(modelInventory2.getInventory_id() == 2);
		
		assertTrue(modelRack1.getInventory_id() == 1);
	}

	//Rack Methods test///////////////////////////////////////////////////////////////
	
	@Test
	public void testGetTolerance(){
		assertTrue(modelRack1.getTolerance() == 5);
		assertTrue(modelRack4.getTolerance() == 10);
	}
	
	@Test
	public void testGetWatt(){
		assertTrue(modelRack1.getWattage() == 0.25);
		assertTrue(modelRack2.getWattage() == 0.5);
	}
	
	@Test
	public void testGetRack_id(){
		assertTrue(modelRack2.getRack_id() == 2);
		assertTrue(modelRack4.getRack_id() == 4);
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	//Bin Methods test///////////////////////////////////////////////////////////////
	
	@Test
	public void testGetCount(){
		assertTrue(modelBin1.getCount() == 50);
		assertTrue(modelBin3.getCount() == 60);
	}
	
	@Test
	public void testGetResistance(){
		assertEquals(modelBin1.getResistance(), "100-ohm");
		assertEquals(modelBin2.getResistance(), "60-ohm");
	}
	
	@Test
	public void testGetSelected(){
		assertFalse(modelBin1.getSelected());
		assertFalse(modelBin2.getSelected());
		assertFalse(modelBin3.getSelected());
		assertFalse(modelBin4.getSelected());
	}
	
	@Test 
	public void testSetCount(){
		modelBin1.setCount(56);
		assertFalse(modelBin1.getCount() == 50);
		assertTrue(modelBin1.getCount() == 56);
	}
	
	@Test
	public void testSetResistance(){
		modelBin1.setResistance("67-ohm");
		assertNotEquals(modelBin1.getResistance(),"100-ohm");
		assertEquals(modelBin1.getResistance(), "67-ohm");
	}
	
	@Test
	public void testSetSelected(){
		modelBin1.setSelected(true);
		assertTrue(modelBin1.getSelected());
	}
	
	@Test
	public void testGetBinID(){
		assertTrue(modelBin1.getBin_id() == 1);
		assertTrue(modelBin4.getBin_id() == 4);
	}
	/////////////////////////////////////////////////////////////////////////////////

	
}
