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
	
	private BinController controller;
	private int rack_id;
	private int resistance;
	private int count;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/Bins.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//setup controller
		controller = new BinController("inventory");
		//first time entering this servlet

		//get parameters from jsp
		rack_id = getInteger(req, "rack_id");
		resistance = getInteger(req, "resistance");
		count = getInteger(req, "count");
		System.out.println(rack_id);
			
		if (req.getParameter("displayBins") != null) {
			displayBins(req);
		}
		
		//add a bin
		if (req.getParameter("addBin") != null) {
			
			controller.addBin(rack_id, resistance, count);;
			displayBins(req);
		
		}
		
		//delete a bin
		for(int i=1; i<1000; i++){
			if(req.getParameter("deleteBin" + i) != null){
				controller.removeBin(i);;
				displayBins(req);
				
			}
		}
		
		
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
		List<Bin> bins = controller.displayBins(rack_id);
		req.setAttribute("bins", bins);
	}
}
