package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.NumbersController;
import resistorSorter.model.Numbers;

import resistorSorter.model.Inventory;

public class InitializeInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/InitializeInventory.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		Inventory model;
		
		
		
		int binCapacity = getInteger(req, "binCapacity");
		int userRemoveLimit = getInteger(req, "userRemoveLimit");

		
			if (req.getParameter("submit") != null) {
				
				model = new Inventory(binCapacity, userRemoveLimit);
						

			} else {
				throw new ServletException("Unknown command");
			}	
			
		
		req.setAttribute("inventory", model);
		
		req.getRequestDispatcher("/_view/InitializeInventory.jsp").forward(req, resp);
		
		
		
		
	}
	
	private int getInteger(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Integer.parseInt(req.getParameter(name));
		}
		else{
			return 0;
		}
	}
}
