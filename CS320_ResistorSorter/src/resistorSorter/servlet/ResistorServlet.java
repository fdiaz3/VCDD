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
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.IDatabase;

public class ResistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BinController binController;
	private int bin_id;
	private int count;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/Resistor.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		binController = new BinController();
		
		//get parameters from jsp
		bin_id = getInteger(req, "bin_id");
		
		if (req.getParameter("addResistors") != null) {
			binController.addResistor(bin_id, getInteger(req, "countChange"));
			
		}
		else if (req.getParameter("removeResistors") != null) {
			binController.removeResistor(bin_id, getInteger(req, "countChange"));
		}
		
		//getting updated count based on bin_id
		count = binController.getCount(bin_id);
		
		//sending info back to jsp
		req.setAttribute("bin_id", bin_id);
		req.setAttribute("count", count);
		
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
