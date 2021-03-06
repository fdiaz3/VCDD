package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.IDatabase;
import resistorSorter.controllers.BinController;
import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.RackController;
import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;

public class TestViewInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InventoryController inventoryController;
	private RackController rackController;
	private BinController binController;
	
	private int inventory_id;
	private int rack_id;
	
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
		
		rackController = new RackController("inventory");
		displayRacks(req);
		
		binController = new BinController("inventory");
		displayBins(req);
		
		req.getRequestDispatcher("/_view/TestViewInventory.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getParameter("resetInventory") != null) {
			DerbyDatabase.deleteDataBase();
			DerbyDatabase.loadDataBase();
		}
		else if (req.getParameter("logout") != null) {
			System.out.println("logout");
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		req.getRequestDispatcher("/_view/TestViewInventory.jsp").forward(req, resp);
	}
	
	
	private void displayInventories(HttpServletRequest req){
		//display inventories
		List<Inventory> inventories = inventoryController.displayInventories();
		req.setAttribute("inventories", inventories);
	}
	
	private void displayRacks(HttpServletRequest req){
		//display inventories
		//-1 used to display all racks regardless of inventory_id
		List<Rack> racks = rackController.displayRacks(-1);
		req.setAttribute("racks", racks);
		
	}
	
	private void displayBins(HttpServletRequest req){
		//display bins
		//-1 used to display all bins regardless of rackID
		List<Bin> bins = binController.displayBins(-1);
		req.setAttribute("bins", bins);
	}

	
}
