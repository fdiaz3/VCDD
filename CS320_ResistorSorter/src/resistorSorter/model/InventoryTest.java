package resistorSorter.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import resistorSorter.controllers.InventoryController;

public class InventoryTest {
	private Inventory model;
	private InventoryController controller;
	@Before
	public void setUp() throws Exception {
		model = new Inventory(200, 15);
		controller = new InventoryController(model);
		
	}
	
	private void initInventory() {
		for (int i = 0; i < 5; i++){
			controller.addRack(i*2, i*.2);
		}
	}

	@Test
	public void testInventoryController() {
		assertNotNull(model);
		assertEquals(200, model.getBinCapacity());
		assertEquals(15, model.getUserRemoveLimit());
		assertNotNull(controller);
		
	}

	@Test
	public void testAddRack() {
		assertEquals(0, controller.getModel().getNumRacks());
		System.out.println("//addRack Test\\");
		initInventory();
		assertEquals(5, controller.getModel().getNumRacks());
	}

	

	@Test
	public void testRemoveRack() {
		System.out.println("//removeRack Test\\");
		initInventory();
		System.out.println("*removal*");
		controller.removeRack(2.0, .2);
		assertEquals(4, controller.getModel().getNumRacks());
		System.out.println("//Test\\");
		for (int i = 0; i < controller.getModel().getNumRacks(); i++){
			System.out.println(model.getRacks().keySet());
		}
		
	}

	@Test
	public void testListRacks() {
		initInventory();
		for (int i = 0; i < 5; i++){
			System.out.println(controller.listRacks()[i]);
		}
	}

	//@Test
	//public void testNumRacks() {
		//If add racks passes this must
	//}

	@Test
	public void testGetRack() {
		initInventory();
		assertEquals(new Rack(4, .4).getTol(), controller.getRack("4.0%, 0.4w").getTol(), .00000005);
		assertEquals(new Rack(4, .4).getWatt(), controller.getRack("4.0%, 0.4w").getWatt(), .00000005);

	}

}
