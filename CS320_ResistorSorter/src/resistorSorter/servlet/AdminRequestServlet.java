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
		String username = req.getParameter("username");
		String uuid = req.getParameter("uuid");
		loginController = new LoginController("inventory");
		try{
			if(req.getParameter("adminReq").equals("true") && loginController.checkUUID(username, uuid)){
				req.setAttribute("adminReq", "granted");
				access = true;
			}
			else if(req.getParameter("adminReq").equals("false") && loginController.checkUUID(username, uuid)){
				req.setAttribute("adminReq", "revoked");
			}
			else{
				resp.sendRedirect(req.getContextPath() + "/Login");
			}
			loginController.updateAdminFlag(username, access);
			req.setAttribute("username", username);
			req.getRequestDispatcher("/_view/AdminRequest.jsp").forward(req, resp);
		}catch(IllegalStateException e){
			System.out.println(username + " attempting to crack uuid!!");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
	}

}
