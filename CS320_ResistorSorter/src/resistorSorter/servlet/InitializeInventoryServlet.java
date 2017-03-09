package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.model.Inventory;

public class InitializeInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Inventory model;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/InitializeInventory.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
		
		
		
		int binCapacity = getInteger(req, "binCapacity");
		int userRemoveLimit = getInteger(req, "userRemoveLimit");

		
		//initialize model if submit is pressed
			if (req.getParameter("submit") != null) {
				
				model = new Inventory(binCapacity, userRemoveLimit);
				req.setAttribute("inventory", model);
				req.getRequestDispatcher("/_view/InitializeInventory.jsp").forward(req, resp);
			} 
			
		//go to index if changeToIndex is pressed
			else if (req.getParameter("changeToTake") != null) {
				
				
				//req.getRequestDispatcher("/_view/TakeResistor.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/TakeResistor");
				
				
				
			}else if (req.getParameter("changeToReplace") != null) {
				
				req.getRequestDispatcher("/_view/ReplaceResistor.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/ReplaceResistor");
				
				
				
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
}
