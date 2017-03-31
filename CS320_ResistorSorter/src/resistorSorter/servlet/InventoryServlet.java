package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.IDatabase;
import resistorSorter.model.Inventory;

public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Inventory model;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		int binCapacity = getInteger(req, "binCapacity");
		int userRemoveLimit = getInteger(req, "userRemoveLimit");
		
		//setup database
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		//display inventories
		List<Inventory> inventories = db.getAllInventories();
		req.setAttribute("inventories", inventories);
		
		//if initializeInventory is pressed
			if (req.getParameter("initializeInventory") != null) {
				
				
				db.insertInventory(binCapacity, userRemoveLimit);
				
				
				model = new Inventory(binCapacity, userRemoveLimit);
				
				req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);
				
			
			
		//if initializeRack is pressed
			}else if (req.getParameter("initializeRack") != null) {
				
				req.setAttribute("inventory", model);
				double tolerance = getDouble(req, "tolerance");
				double power = getDouble(req, "power");
				model.addRack( tolerance, power);
				req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);
				
		//if edit rack is pressed	
			}else if (req.getParameter("editRack") != null) {
				
				ArrayList<String> racks = model.getRacks();
				//subtract one for indexing
				int rackNum = getInteger(req, "rackNum") - 1;
				
				
				
				
				String inventoryId = UUID.randomUUID().toString();
				req.getSession().setAttribute(inventoryId, model);
				req.setAttribute("myObjectId", inventoryId);
				

				
				req.setAttribute("rackInfo", racks.get(rackNum));
				
				//resp.sendRedirect(req.getContextPath() + "/Rack");
				req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
				
		// if delete rack is pressed
			}else if (req.getParameter("deleteRack") != null) {
				req.setAttribute("inventory", model);
				
				ArrayList<String> racks = model.getRacks();
				//subtract one for indexing
				int rackNum = getInteger(req, "rackNum") - 1;
				
				double tolerance = model.getRack(racks.get(rackNum)).getTol();
				double power = model.getRack(racks.get(rackNum)).getWatt();
				
				model.removeRack(tolerance, power);

				req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);
			}else {
				throw new ServletException("Unknown command");
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
	
	private double getDouble(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Double.parseDouble(req.getParameter(name));
		}
		else{
			return 0;
		}

	}
}
