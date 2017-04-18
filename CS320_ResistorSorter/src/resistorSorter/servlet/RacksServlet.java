package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.RackController;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;

public class RacksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RackController rackController;
	private InventoryController inventoryController;
	
	private int inventory_id;
	private float tolerance;
	private float power;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//setup inventoryController and display inventory ids
		inventoryController = new InventoryController("inventory");
		displayInventories(req);
		
		req.getRequestDispatcher("/_view/Racks.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//setup controllers
		rackController = new RackController("inventory");
		inventoryController = new InventoryController("inventory");
		
		
		
		//get parameters from jsp
		inventory_id = getInteger(req, "inventory_id");
		tolerance = getFloat(req, "tolerance");
		power = getFloat(req, "power");
		
		//display racks
		if (req.getParameter("displayRacks") != null) {
			displayRacks(req);
		}
		
		//add a Rack
		if (req.getParameter("addRack") != null) {
			
			rackController.addRack(tolerance, power, inventory_id);
			displayRacks(req);
		
		}
		
		//delete a rack
		for(int i=1; i<1000; i++){
			if(req.getParameter("deleteRack" + i) != null){
				rackController.removeRack(i);
				displayRacks(req);
				
			}
		}
		
		
		//pass inventory_id back to jsp
		req.setAttribute("inventory_id", inventory_id);
		//pass list of inventories back to jsp
		displayInventories(req);
		
		req.getRequestDispatcher("/_view/Racks.jsp").forward(req, resp);
	}
	
	private int getInteger(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Integer.parseInt(req.getParameter(name));
		}
		else{
			return 0;
		}
	}
	
	private float getFloat(HttpServletRequest req, String name) {
		
		if(req.getParameter(name) != ""){
			
			return Float.parseFloat(req.getParameter(name));
		}
		else{
			return 0;
		}
	}
	
	private void displayRacks(HttpServletRequest req){
		//display inventories
		inventory_id = getInteger(req, "inventory_id");
		List<Rack> racks = rackController.displayRacks(inventory_id);
		req.setAttribute("racks", racks);
	}
	
	private void displayInventories(HttpServletRequest req){
		//display inventories
		List<Inventory> inventories = inventoryController.displayInventories();
		req.setAttribute("inventories", inventories);
	}
	
}