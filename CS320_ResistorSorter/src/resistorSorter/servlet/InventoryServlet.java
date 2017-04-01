package resistorSorter.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.InventoryController;
import resistorSorter.model.Inventory;

public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InventoryController controller;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		controller = new InventoryController();
		displayInventories(req);
		req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//setup controller
		controller = new InventoryController();
		
		//getting parameters from jsp
		int binCapacity = getInteger(req, "binCapacity");
		int userRemoveLimit = getInteger(req, "userRemoveLimit");
		
		
		
		//if initializeInventory is pressed
		if (req.getParameter("initializeInventory") != null) {
			
			controller.addInventory(binCapacity, userRemoveLimit);
			displayInventories(req);
			req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);
		
		}
		
		//delete an inventory
		for(int i=1; i<1000; i++){
			if(req.getParameter("deleteInventory" + i) != null){
				controller.removeInventory(i);
				displayInventories(req);
				req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);
			}
		}

		
	}
	
	private int getInteger(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Integer.parseInt(req.getParameter(name));
		}
		else{
			return 0;
		}
	}
	
	private void displayInventories(HttpServletRequest req){
		//display inventories
		List<Inventory> inventories = controller.displayInventories();
		req.setAttribute("inventories", inventories);
	}
	
}
