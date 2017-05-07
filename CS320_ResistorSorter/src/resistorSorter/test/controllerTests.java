package resistorSorter.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import resistorSorter.controllers.BinController;
import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.LoginController;
import resistorSorter.controllers.RackController;
import resistorSorter.persist.TestDerbyDatabase;


public class controllerTests {

	private InventoryController inventoryController;
	private RackController rackController;
	private BinController binController;
	
	@Before
	public void setUp() throws Exception{
		
		/////////////////////////////////////////////
		inventoryController = new InventoryController("test");
		rackController = new RackController("test");
		binController = new BinController("test");
		
		//create new tables
		TestDerbyDatabase.loadDataBase();
		
		inventoryController.addInventory(500, 100,"KEC123","testing-fbd-1234");
		
		rackController.addRack(5, (float) 0.5, 1,"testing-fbd-1234");
		rackController.addRack(7, (float)0.25, 1,"testing-fbd-1234");
		
		binController.addBin(1, 500, 22,"testing-fbd-1234");
		binController.addBin(1, 220, 333,"testing-fbd-1234");
		binController.addBin(2, 1000, 100,"testing-fbd-1234");
		binController.addBin(2, 7200, 56,"testing-fbd-1234");
		
	}
		
	//Tests///////////////////////////////////////////////////////////////////////////


	@Test 
	public void testAddInventory(){
		assertTrue(inventoryController.displayInventories().get(0).getBinCapacity() == 500);
		assertTrue(inventoryController.displayInventories().get(0).getUserRemoveLimit() == 100);
	}
	
	@Test
	public void testAddRack(){
		assertTrue(rackController.displayRacks(1).get(0).getTolerance() == 5);
		assertTrue(rackController.displayRacks(1).get(1).getTolerance() == 7);
	}
	@Test
	public void testAddBin(){
		assertTrue(binController.displayBins(1).get(0).getCount() == 22);
		assertTrue(binController.displayBins(2).get(0).getCount() == 100);
	}
	@Test 
	public void testRemoveInventory(){
		int size1 = inventoryController.displayInventories().size();
		inventoryController.removeInventory(inventoryController.displayInventories().size(),"testing-fbd-1234");
		int size2 = inventoryController.displayInventories().size();
		assertTrue(size2 == size1-1);
		
	}
	@Test 
	public void testGetCountOfInventories(){
		assertEquals(inventoryController.getCountOfInventories(), 1);
		assertNotEquals(inventoryController.getCountOfInventories(), 0);
		assertNotEquals(inventoryController.getCountOfInventories(), 2);
	}
	@Test 
	public void testRemoveRack(){
		int size1 = rackController.displayRacks(1).size();
		rackController.removeRack(1, "testing-fbd-1234");
		int size2 = rackController.displayRacks(1).size();
		assertEquals(size2, size1-1);
	}
	@Test 
	public void testRemoveInventoryWithRacks(){
		int size1 = inventoryController.displayInventories().size();
		try{
			inventoryController.removeInventory(1,"testing-fbd-1234");
		}
		catch(resistorSorter.persist.PersistenceException e){
			System.out.println("Not removing inventories correctly!!");
			assertTrue(false);
		}
		int size2 = inventoryController.displayInventories().size();
		assertTrue(size2 == size1-1);

	}

	//bin/rack controller tests///////////////////////////////////////////////////////////////
	
	@Test 
	public void testgetCount(){	
		assertTrue(binController.getCount(1) == 22);
		assertTrue(binController.getCount(2) == 333);
		assertTrue(binController.getCount(3) == 100);
		assertTrue(binController.getCount(4) == 56);

	}
	
	@Test 
	public void testgetUserRemoveLimit(){		
		assertTrue(binController.getUserRemoveLimit(1) == 100);
	}
	@Test 
	public void testgetCapacity(){		
		assertTrue(binController.getCapacity(1) == 500);
	}
	
	@Test
	public void testGetCapacityFromRack(){
		assertTrue(binController.getCapacityFromRack(1) == 500);
	}
	@Test 
	public void testGetMaxChangeInCount(){
		assertTrue(binController.getMaxChangeInCount(1) == 478);
		assertTrue(binController.getMaxChangeInCount(2) == 167);		
		assertTrue(binController.getMaxChangeInCount(3) == 400);	
	}
	@Test
	public void testGetResistorColors(){
		String[] r1 = {"green","black","brown","gold"};
		String[] r2 = {"red","red","brown","gold"};
		String[] r3 = {"brown","black","red","silver"};
		for(int i=0; i<4; i++){
			assertTrue(binController.getResistorColors(1)[i].equals(r1[i]));
			assertTrue(binController.getResistorColors(2)[i].equals(r2[i]));
			assertTrue(binController.getResistorColors(3)[i].equals(r3[i]));
		}
	}
	@Test
	public void testRemoveBin(){
		int size1 = binController.displayBins(1).size();
		binController.removeBin(1, "testing-fbd-1234");
		int size2 = binController.displayBins(1).size();
		assertEquals(size2, size1-1);
	}
	@Test 
	public void testGetResistance(){
		assertEquals(binController.getResistance(1),500);
		assertEquals(binController.getResistance(2),220);
		assertEquals(binController.getResistance(3),1000);
		assertEquals(binController.getResistance(4),7200);
	}
	@Test 
	public void testRemoveResistor(){
		binController.removeResistor(1, 20);
		binController.removeResistor(2, 333);
		binController.removeResistor(3, 50);
		assertEquals(binController.getCount(1), 2);
		assertEquals(binController.getCount(2), 333);
		assertEquals(binController.getCount(3), 50);
	}
	 @After
	 public void after() throws Exception {
		 TestDerbyDatabase.deleteDataBase();
	 }

}
