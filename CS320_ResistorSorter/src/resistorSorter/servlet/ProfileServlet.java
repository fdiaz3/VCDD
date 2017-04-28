package resistorSorter.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.InventoryTransactionController;
import resistorSorter.controllers.LoginController;
import resistorSorter.model.InventoryTransaction;


public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InventoryTransactionController inventoryTransactionController;
	private LoginController loginController;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//initialize controllers
		inventoryTransactionController = new InventoryTransactionController("inventory");
		loginController = new LoginController("inventory");
		
		String user = (String) req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		//Showing user status based on adminReq
		//Also to allow admin to view all transactions or not
		if(loginController.getAdminFlag((String)req.getSession().getAttribute("user"))){
			req.setAttribute("adminFlag", "Administrator");
			req.setAttribute("viewAll", true);
		}
		else{
			req.setAttribute("adminFlag", "User");
			req.setAttribute("viewAll", false);
		}
		

		
		//send info to jsp
		req.setAttribute("username", (String)req.getSession().getAttribute("user"));
		displayTransactions(req);
		
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//initialize controllers
		inventoryTransactionController = new InventoryTransactionController("inventory");
		
		if (req.getParameter("logout") != null) {
			System.out.println("logout");
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		//Send to transactions
		if(req.getParameter("viewTransactions") != null){
			resp.sendRedirect(req.getContextPath() + "/AllTransactions");
			return;
		}
		//send info to jsp
		req.setAttribute("username", (String)req.getSession().getAttribute("user"));
		displayTransactions(req);
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
	}
	
	private void displayTransactions(HttpServletRequest req){
		//display inventories
		String username = (String) req.getSession().getAttribute("user");
		List<InventoryTransaction> transactions = inventoryTransactionController.displayUserInventoryTransactions(username);
		req.setAttribute("transactions", transactions);
	}
	
}
