package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.NumbersController;
import resistorSorter.model.Numbers;

public class MultiplyNumbersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/MultiplyNumbers.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		Numbers model = new Numbers();
		NumbersController controller = new NumbersController();
		
		controller.setModel(model);
		
		Double first = getDouble(req, "first");
		Double second = getDouble(req, "second");
		
			if (req.getParameter("submit") != null) {
				
				if(first == null || second == null){
					model.setProduct(0);
				}
				else{
					controller.multiply(first, second);
				}
				
				
			} else {
				throw new ServletException("Unknown command");
			}	
			
		req.setAttribute("game", model);
		
		req.getRequestDispatcher("/_view/MultiplyNumbers.jsp").forward(req, resp);
		

		
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
