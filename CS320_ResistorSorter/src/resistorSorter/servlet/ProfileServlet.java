package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.LoginController;
import resistorSorter.model.Library;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Library model;
	private LoginController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getParameter("logout") != null) {
			
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		req.setAttribute("username", (String)req.getSession().getAttribute("user"));
		System.out.println((String)req.getSession().getAttribute("name"));
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Profile.jsp").forward(req, resp);
	}
	
	//DISCLAIMER//
	//All work seen in here has been copied, but modified from the Library example//
}
