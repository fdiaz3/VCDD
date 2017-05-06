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
	String username;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//initialize controllers
		inventoryTransactionController = new InventoryTransactionController("inventory");
		loginController = new LoginController("inventory");
		
		//check to see if user is logged in
		String email = (String) req.getSession().getAttribute("user");
		if (email == null) {			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		username = email.substring(0, email.indexOf('@'));
		
		//send info to jsp
		req.setAttribute("username", username);
		displayUserTransactions(req);
		displayUserStatus(req);
		spamFilter(req);
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//initialize controllers
		inventoryTransactionController = new InventoryTransactionController("inventory");
		
		//get info from jsp
		String email = (String) req.getSession().getAttribute("user");
		username = email.substring(0, email.indexOf('@'));
		
		if (req.getParameter("logout") != null) {
			System.out.println("logout");
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		if (req.getParameter("requestAdmin") != null && (!loginController.checkRequest(email))) {
			loginController.userRequestAdmin(email);
			loginController.updateRequest(email, true);
		}
		
		//Send to transactions
		if(req.getParameter("viewTransactions") != null){
			resp.sendRedirect(req.getContextPath() + "/AllTransactions");
			return;
		}
		
		
		//send info to jsp
		req.setAttribute("username", username);
		displayUserTransactions(req);
		displayUserStatus(req);
		spamFilter(req);
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void displayUserTransactions(HttpServletRequest req){
		//display transactions
		String username = (String) req.getSession().getAttribute("user");
		List<InventoryTransaction> transactions = inventoryTransactionController.displayUserInventoryTransactions(username);
		req.setAttribute("transactions", transactions);
	}
	
	private void displayUserStatus(HttpServletRequest req){
		//Showing user status based on adminReq
		//Also to allow admin to view all transactions or not
		if(loginController.getAdminFlag((String)req.getSession().getAttribute("user"))){
			req.setAttribute("adminFlag", "Administrator");
			req.setAttribute("admin", true);
		}
		else{
			req.setAttribute("adminFlag", "User");
			req.setAttribute("admin", false);
		}
	}
	private void spamFilter(HttpServletRequest req){
		if(!loginController.checkRequest((String)req.getSession().getAttribute("user"))){
			//System.out.println("The user has not submitted a request yet");
			req.setAttribute("request", true);
		}
		else{
			//System.out.println("The user has already submitted a request");
			req.setAttribute("request", false);
		}
	}
	
}
