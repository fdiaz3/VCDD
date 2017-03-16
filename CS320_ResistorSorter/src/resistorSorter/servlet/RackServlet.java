package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;

public class RackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Inventory model;
	private Rack rack;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
		
		System.out.println((String) req.getParameter("rackInfo"));
		req.setAttribute("rackInfo", req.getParameter("rackInfo"));
		
		req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
		
		
		
		
		
//		//get instance of inventory
//		model = (Inventory) req.getAttribute("Inventory");
//		
//		
//		System.out.println(model);
//
//		
//		
//		System.out.println("inventory bincapacity is: "  + model.getBinCapacity());
//		System.out.println("rackInfo is: " + (String) req.getAttribute("rackInfo"));
//		
//		
//		
//		//get instance of designated rack
//		rack = model.getRack((String) req.getAttribute("rackInfo"));
//		//pass inventory reference to jsp
//		req.setAttribute("inventory", model);
//		//pass rack reference to jsp
//		req.setAttribute("rack", rack);
//		
//		
//		
//		//if addbin is pressed
//		if (req.getParameter("addBin") != null) {
//			
//		rack.addBin( (String) req.getAttribute("resistance"), getInteger(req, "count"));
//		req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
//				
//		}else {
//			throw new ServletException("Unknown command");
//		}

		
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
