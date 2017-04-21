package resistorSorter.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.InventoryController;
import resistorSorter.model.Inventory;

public class InventoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InventoryController inventoryController;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String user = (String) req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		
		inventoryController = new InventoryController("inventory");
		displayInventories(req);
		req.getRequestDispatcher("/_view/Inventories.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//setup controller
		inventoryController = new InventoryController("inventory");
		
		//getting parameters from jsp
		int binCapacity = getInteger(req, "binCapacity");
		int userRemoveLimit = getInteger(req, "userRemoveLimit");
		
		//if initializeInventory is pressed
		if (req.getParameter("initializeInventory") != null) {
			inventoryController.addInventory(binCapacity, userRemoveLimit);
		}
		
		//delete an inventory
		if (req.getParameter("deleteInventory") != null) {
			int deleteInventoryID = getInteger(req, "deleteInventory");
			inventoryController.removeInventory(deleteInventoryID);
		}
		
		//re-send info to be displayed
		displayInventories(req);
		req.getRequestDispatcher("/_view/Inventories.jsp").forward(req, resp);
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
		List<Inventory> inventories = inventoryController.displayInventories();
		req.setAttribute("inventories", inventories);
	}
	
}
