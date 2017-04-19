package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.BinController;
import resistorSorter.controllers.RackController;
import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;

public class BinsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BinController binController;
	private int rack_id;
	private int resistance;
	private int count;
	
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
		
		req.getRequestDispatcher("/_view/Bins.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//setup controller
		binController = new BinController("inventory");

		//get parameters from jsp
		rack_id = getInteger(req, "rack_id");
		resistance = getInteger(req, "resistance");
		count = getInteger(req, "count");
		
		//add a bin
		if (req.getParameter("addBin") != null) {
			binController.addBin(rack_id, resistance, count);;
		}
		
		//delete a bin
		if (req.getParameter("deleteBin") != null) {
			int deleteBinID = getInteger(req, "deleteBin");
			binController.removeBin(deleteBinID);
		}
		
		//re-send info to be displayed
		displayBins(req);
		
		//pass inventory_id back to jsp
		req.setAttribute("rack_id", rack_id);
		req.getRequestDispatcher("/_view/Bins.jsp").forward(req, resp);
	}	
		

	
	private int getInteger(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Integer.parseInt(req.getParameter(name));
		}
		else{
			return 0;
		}
	}
	
	
	private void displayBins(HttpServletRequest req){
		//display bins
		rack_id = getInteger(req, "rack_id");
		List<Bin> bins = binController.displayBins(rack_id);
		req.setAttribute("bins", bins);
	}
}
