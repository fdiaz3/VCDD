package resistorSorter.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import resistorSorter.controllers.BinController;
import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.RackController;
import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;
import resistorSorter.model.User;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.IDatabase;
import resistorSorterdb.persist.PersistenceException;
import resistorSorterdb.persist.TestDerbyDatabase;


public class controllerTests {

	private InventoryController InventoryController;
	private RackController RackController;
	private BinController BinController;
	
	@Before
	public void setUp() throws Exception{
		
		/////////////////////////////////////////////
		InventoryController = new InventoryController("test");
		RackController = new RackController("test");
		BinController = new BinController("test");
		
		//try to delete old tables
		DatabaseProvider.setInstance(new TestDerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		try{
			db.dropAllTables();
		}
		catch(PersistenceException e){
			System.out.println("no tables to delete");
		}
		//create new tables
		TestDerbyDatabase.loadDataBase();
	}
		
	//Tests///////////////////////////////////////////////////////////////////////////
	
	//Inventory Methods test//////////////////////////////////////////////////////////

	@Test 
	public void testAddInventory(){
		InventoryController.addInventory(500, 100);
		assertTrue(InventoryController.displayInventories().get(0).getBinCapacity() == 500);
		assertTrue(InventoryController.displayInventories().get(0).getUserRemoveLimit() == 100);
	}
	
	@Test 
	public void testRemoveInventory(){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		InventoryController.addInventory(500, 100);
		int size1 = db.getAllInventories().size();
		db.removeInventory(db.getAllInventories().size());
		int size2 = db.getAllInventories().size();
		assertTrue(size2 == size1-1);
	}
	
	@Test 
	public void testRemoveInventoryWithRacks(){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		InventoryController.addInventory(500, 100);
		RackController.addRack(5, (float) 0.5, 1);
		RackController.addRack(7, (float)0.25, 1);
		int size1 = db.getAllInventories().size();
		
		try{
			db.removeInventory(1);
		}
		catch(resistorSorterdb.persist.PersistenceException e){
			System.out.println("Not removing inventories correctly!!");
			assertTrue(false);
		}
		int size2 = db.getAllInventories().size();
		assertTrue(size2 == size1-1);

	}
	//////////////////////////////////////////////////////////////////////////////////
	
	//Rack Methods test///////////////////////////////////////////////////////////////
	


	/////////////////////////////////////////////////////////////////////////////////
	
	//Bin Methods test///////////////////////////////////////////////////////////////
	

	/////////////////////////////////////////////////////////////////////////////////

	
}
