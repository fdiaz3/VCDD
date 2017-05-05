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


public class AllTransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InventoryTransactionController inventoryTransactionController;
	private LoginController loginController;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//initialize controllers
		inventoryTransactionController = new InventoryTransactionController("inventory");
		loginController = new LoginController("inventory");
		
		String email = (String) req.getSession().getAttribute("email");
		if (email == null) {
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		//Showing user status based on adminReq
		//Also to allow admin to view all transactions or not
		if(loginController.getAdminFlag((String)req.getSession().getAttribute("email"))){

		}
		else{
			resp.sendRedirect(req.getContextPath() + "/Login");
		}
		

		
		//send info to jsp
		displayAllTransactions(req);
		req.getRequestDispatcher("/_view/AllTransactions.jsp").forward(req, resp);
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
	
		displayAllTransactions(req);
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/AllTransactions.jsp").forward(req, resp);
	}
	
	private void displayAllTransactions(HttpServletRequest req){
		//display inventories
		List<InventoryTransaction> transactions = inventoryTransactionController.displayInventoryTransactions();
		req.setAttribute("transactions", transactions);
	}
	
}
