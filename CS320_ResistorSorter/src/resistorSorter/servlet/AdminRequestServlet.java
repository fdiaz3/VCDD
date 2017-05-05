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
		String email = req.getParameter("email");
		String uuid = req.getParameter("uuid");
		loginController = new LoginController("inventory");
		try{
			if(req.getParameter("admin").equals("true") && loginController.checkUUID(email, uuid)){
				req.setAttribute("admin", "granted");
				access = true;
			}
			else if(req.getParameter("admin").equals("false") && loginController.checkUUID(email, uuid)){
				req.setAttribute("admin", "revoked");
			}
			else{
				resp.sendRedirect(req.getContextPath() + "/Login");
			}
			loginController.updateAdminFlag(email, access);
			req.setAttribute("email", email);
			req.getRequestDispatcher("/_view/AdminRequest.jsp").forward(req, resp);
		}catch(IllegalStateException e){
			System.out.println(email + " attempting to crack uuid!!");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
	}

}
