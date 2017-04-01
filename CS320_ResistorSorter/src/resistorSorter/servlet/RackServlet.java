package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.RackController;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;

public class RackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RackController controller;
	private int inventory_id;
	private float tolerance;
	private float power;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		controller = new RackController();
		displayRacks(req);
		req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//setup controller
		controller = new RackController();
		
		//get parameters from jsp
		inventory_id = getInteger(req, "inventory_id");
		tolerance = getFloat(req, "tolerance");
		power = getFloat(req, "power");
		
		//if displayRacks is pressed
		if (req.getParameter("displayRacks") != null) {
			
			displayRacks(req);
			
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
		List<Rack> racks = controller.displayRacks(inventory_id);
		req.setAttribute("racks", racks);
		
	}
}
