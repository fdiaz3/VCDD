package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.model.Inventory;

public class ReplaceResistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Inventory model;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/ReplaceResistor.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		
			if (req.getAttribute("inventory") != null) {
				model = (Inventory) req.getAttribute("inventory");
				
				System.out.println( model.getBinCapacity() );
				
			}
			
			else if (req.getParameter("submit") != null){
				System.out.println( model.getBinCapacity() );
			}
			else {
				throw new ServletException("Unknown command");
			}	

			
			
		
		req.getRequestDispatcher("/_view/ReplaceResistor.jsp").forward(req, resp);
		
		
		
		
	}

	
}
