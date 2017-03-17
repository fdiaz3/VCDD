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
	private String rackInfo;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//first time entering this servlet
		if(model == null){
			
		//get instance of inventory from initialize inventory
		String inventoryId = req.getParameter("inventoryId");
		Object inventorObj = req.getSession().getAttribute(inventoryId);
		model = (Inventory) inventorObj;
		
		//get rackInfo 
		rackInfo = (String) req.getParameter("rackInfo");
		
		//get instance of designated rack
		rack = model.getRack((String) req.getParameter("rackInfo"));
		}else{
			//System.out.println("nothing");
		}
		

		
		//pass inventory reference to jsp
		req.setAttribute("inventory", model);
		//pass rackinfo reference to jsp
		req.setAttribute("rackInfo", rackInfo);
		//pass rack reference to jsp
		req.setAttribute("rack", rack);
		

		
		
		//IF ADDBIN IS PUSHED
		if (req.getParameter("addBin") != null) {
			
		//add bin to rack
		rack.addBin( (String) req.getParameter("resistance"), getInteger(req, "count"));

		
		req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
		//IF EDITBIN IS PUSHED
		}else if (req.getParameter("editBin") != null) {

			
		
		req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
		//IF DELETEBIN IS PUSHED
		}else if (req.getParameter("deleteBin") != null) {
			
			ArrayList<String> bins = rack.getBins();
			rack.removeBin(bins.get(getInteger(req, "binNum") - 1));
			req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
			
			
		//IF RETURN IS PUSHED
		}else if (req.getParameter("return") != null) {
			
			
			
			req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
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
