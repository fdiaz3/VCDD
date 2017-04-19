package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.BinController;

public class ResistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BinController binController;
	private int bin_id;
	private int count;
	private int userRemoveLimit;
	private int capacity;
	String error;
	
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
		
		
		req.getRequestDispatcher("/_view/Resistor.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		binController = new BinController("inventory");
		
		//get parameters from jsp
		bin_id = getInteger(req, "bin_id");

		if (req.getParameter("addResistors") != null) {
			error = binController.addResistor(bin_id, getInteger(req, "countChange"));
			
		}
		else if (req.getParameter("removeResistors") != null) {
			error = binController.removeResistor(bin_id, getInteger(req, "countChange"));
		}
		
		//getting updated info based on bin_id
		count = binController.getCount(bin_id);
		userRemoveLimit = binController.getUserRemoveLimit(bin_id);
		capacity = binController.getCapacity(bin_id);
		
		
		//sending info back to jsp
		req.setAttribute("errorMessage", error);
		req.setAttribute("bin_id", bin_id);
		req.setAttribute("count", count);
		req.setAttribute("userRemoveLimit", userRemoveLimit);
		req.setAttribute("capacity", capacity);
		
		
		req.getRequestDispatcher("/_view/Resistor.jsp").forward(req, resp);
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
