package resistorSorter.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import resistorSorter.controllers.BinController;
import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.RackController;
import resistorSorterdb.persist.TestDerbyDatabase;


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
		
		
	}
		
	//Tests///////////////////////////////////////////////////////////////////////////


	@Test 
	public void testAddInventory(){
		inventoryController.addInventory(500, 100);
		assertTrue(inventoryController.displayInventories().get(0).getBinCapacity() == 500);
		assertTrue(inventoryController.displayInventories().get(0).getUserRemoveLimit() == 100);
	}
	
	@Test 
	public void testRemoveInventory(){
		inventoryController.addInventory(500, 100);
		int size1 = inventoryController.displayInventories().size();
		inventoryController.removeInventory(inventoryController.displayInventories().size());
		int size2 = inventoryController.displayInventories().size();
		assertTrue(size2 == size1-1);
	}
	
	@Test 
	public void testRemoveInventoryWithRacks(){
		inventoryController.addInventory(500, 100);
		rackController.addRack(5, (float) 0.5, 1);
		rackController.addRack(7, (float)0.25, 1);
		int size1 = inventoryController.displayInventories().size();
		
		try{
			inventoryController.removeInventory(1);
		}
		catch(resistorSorterdb.persist.PersistenceException e){
			System.out.println("Not removing inventories correctly!!");
			assertTrue(false);
		}
		int size2 = inventoryController.displayInventories().size();
		assertTrue(size2 == size1-1);

	}
	
	//bin controller tests///////////////////////////////////////////////////////////////
	
	@Test 
	public void testgetCount(){
		inventoryController.addInventory(500, 100);
		rackController.addRack(5, (float) 0.5, 1);
		binController.addBin(1, 250, 150);
		
		assertTrue(binController.getCount(1) == 150);

	}
	
	@Test 
	public void testgetUserRemoveLimit(){
		inventoryController.addInventory(500, 100);
		rackController.addRack(5, (float) 0.5, 1);
		binController.addBin(1, 250, 150);
		
		assertTrue(binController.getUserRemoveLimit(1) == 100);


	}
	@Test 
	public void testgetCapacity(){
		inventoryController.addInventory(500, 100);
		rackController.addRack(5, (float) 0.5, 1);
		binController.addBin(1, 250, 150);
		
		assertTrue(binController.getCapacity(1) == 500);


	}
	

	 @After
	 public void after() throws Exception {
		 
		 TestDerbyDatabase.deleteDataBase();
		 
	 }

}
