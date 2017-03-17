package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.model.Inventory;

public class BinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Inventory model;
	
	private String binInfo;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/InitializeInventory.jsp").forward(req, resp);
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
			
			
			
		

			if (req.getParameter("addResistors") != null) {
				
				
				
			}else if (req.getParameter("subResistors") != null) {
				
				
				
			}else if (req.getParameter("return") != null) {

				
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
