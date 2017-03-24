package resistorSorter.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;
import resistorSorter.model.User;


public class resistorSorterTest {

	private Inventory modelInventory;
	private Rack modelRack;
	private Bin modelBin;
	private User modelUser;

	@Before
	public void setUp() {
			
		//Initialize inventory object with a bin capacity of 500 and user remove limit of 100
		modelInventory = new Inventory(500,100);
			
		//Create a rack object to test 5%, 0.25w
		modelRack = new Rack(5, 0.25);
			
		//Create a bin object to test
		modelBin = new Bin(200, "100-ohm", false);
		
		//Create a user object to test
		modelUser = new User("username", 14);
	}
		
	//Tests///////////////////////////////////////////////////////////////////////////
	
	//Inventory Methods test//////////////////////////////////////////////////////////
		
	@Test
	public void testGetBinCapacity() {
		assertTrue(modelInventory.getBinCapacity() == 500);
	}
	
	@Test
	public void testSetBinCapacity() {
		modelInventory.setBinCapacity(450);
		assertFalse(modelInventory.getBinCapacity() == 500);
		assertTrue(modelInventory.getBinCapacity() == 450);
	}
	
	@Test
	public void testGetUserRemoveLimit(){
		assertTrue(modelInventory.getUserRemoveLimit() == 100);
	}
	
	@Test
	public void testSetUserRemoveLimit() {
		modelInventory.setUserRemoveLimit(55);
		assertFalse(modelInventory.getUserRemoveLimit() == 100);
		assertTrue(modelInventory.getUserRemoveLimit() == 55);
	}
	

	//////////////////////////////////////////////////////////////////////////////////
	
	//Rack Methods test///////////////////////////////////////////////////////////////
	
	@Test
	public void testGetTolerance(){
		assertTrue(modelRack.getTol() == 5);
	}
	
	@Test
	public void testGetWatt(){
		assertTrue(modelRack.getWatt() == 0.25);
	}
	
	@Test 
	public void testSetTolerance(){
		modelRack.setTol(7);
		assertFalse(modelRack.getTol() == 5);
		assertTrue(modelRack.getTol() == 7);
	}
	
	@Test
	public void testSetWatt(){
		modelRack.setWatt(0.5);
		assertFalse(modelRack.getWatt() == 0.25);
		assertTrue(modelRack.getWatt() == 0.5);
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	//Bin Methods test///////////////////////////////////////////////////////////////
	
	@Test
	public void testGetCount(){
		assertTrue(modelBin.getCount() == 200);
	}
	
	@Test
	public void testGetResistance(){
		assertEquals(modelBin.getResistance(), "100-ohm");
	}
	
	@Test
	public void testGetSelected(){
		assertFalse(modelBin.getSelected());
	}
	
	@Test 
	public void testSetCount(){
		modelBin.setCount(56);
		assertFalse(modelBin.getCount() == 200);
		assertTrue(modelBin.getCount() == 56);
	}
	
	@Test
	public void testSetResistance(){
		modelBin.setResistance("67-ohm");
		assertNotEquals(modelBin.getResistance(),"100-ohm");
		assertEquals(modelBin.getResistance(), "67-ohm");
	}
	
	@Test
	public void testSetSelected(){
		modelBin.setSelected(true);
		assertTrue(modelBin.getSelected());
	}
	/////////////////////////////////////////////////////////////////////////////////
}
