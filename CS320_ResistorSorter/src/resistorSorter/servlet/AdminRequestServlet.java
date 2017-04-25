package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.LoginController;

public class AdminRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginController loginController;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean access = false;
		loginController = new LoginController("inventory");;
		if(req.getParameter("adminReq").equals("true")){
			req.setAttribute("adminReq", "granted");
			access = true;
		}
		else{
			req.setAttribute("adminReq", "denied");
		}
		
		String username = req.getParameter("username");
		loginController.updateAdminFlag(username, access);
		req.setAttribute("username", username);
		req.getRequestDispatcher("/_view/AdminRequest.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
	}
	
	//DISCLAIMER//
	//All work seen in here has been copied, but modified from the Library example//
}
