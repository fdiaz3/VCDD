package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.InventoryController;
import resistorSorter.controllers.LoginController;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//System.out.println("\nLoginServlet: doGet");

		req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doPost");

		String errorMessage = null;
		String email         = null;
		boolean validLogin  = false;

		controller = new LoginController("inventory");

		// Decode form parameters and dispatch to controller
		email = req.getParameter("email");

		// Add parameters as request attributes
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("login",        validLogin);

		// if login is valid, start a session
		if (validLogin) {
			// store user object in session
			req.getSession().setAttribute("email", email);

			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/Inventories");

			return;
		}

		//System.out.println("   Invalid login - returning to /login");

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
	}
	
	//DISCLAIMER//
	//All work seen in here has been copied, but modified from the Library example//
}
