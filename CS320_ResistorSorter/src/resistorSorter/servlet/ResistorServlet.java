package resistorSorter.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.BinController;
import resistorSorter.controllers.InventoryTransactionController;
import resistorSorter.persist.PersistenceException;

public class ResistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BinController binController;
	private InventoryTransactionController inventoryTransactionController;
	private int bin_id;
	private int count;
	private int userRemoveLimit;
	private int capacity;
	private int countChange;
	private int maxCount;
	private String user;
	String error;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		user = (String) req.getSession().getAttribute("user");
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
		inventoryTransactionController = new InventoryTransactionController("inventory");
		
		if (req.getParameter("logout") != null) {
			System.out.println("logout");
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		//get parameters from jsp
		bin_id = getInteger(req, "bin_id");
		//If bin_id = 0
		if(bin_id == 0){
			error = "Invalid Bin ID";
		}else{
			error = null;
		}
		countChange = getInteger(req, "countChange");
		//get user from session
		user = (String) req.getSession().getAttribute("user");
		
		if (req.getParameter("addResistors") != null) {
			error = binController.addResistor(bin_id, countChange);
			if(error == null){
				inventoryTransactionController.addTransaction(user, bin_id, countChange, "adding");
			}
		}
		
		else if (req.getParameter("removeResistors") != null) {
			error = binController.removeResistor(bin_id, countChange);
			if(error == null){
				inventoryTransactionController.addTransaction(user, bin_id, countChange, "removing");
			}
		}
		//getting updated info based on bin_id
		
		count = binController.getCount(bin_id);
		userRemoveLimit = binController.getUserRemoveLimit(bin_id);
		capacity = binController.getCapacity(bin_id);		
		maxCount = binController.getMaxCount(bin_id);
		
		//sending info back to jsp
		req.setAttribute("errorMessage", error);
		req.setAttribute("bin_id", bin_id);
		req.setAttribute("count", count);
		req.setAttribute("max_count", maxCount);
		req.setAttribute("userRemoveLimit", userRemoveLimit);
		req.setAttribute("capacity", capacity);
		
		
		req.getRequestDispatcher("/_view/Resistor.jsp").forward(req, resp);
	}
	
	private int getInteger(HttpServletRequest req, String name) {
		if(req.getParameter(name) != ""){
			return Integer.parseInt(req.getParameter(name));
		}
		else{
			return 1;
		}
	}

}
