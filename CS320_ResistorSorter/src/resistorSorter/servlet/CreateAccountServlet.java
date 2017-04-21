package resistorSorter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.controllers.LoginController;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("/_view/CreateAccount.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String errorMessage = null;
		String username = req.getParameter("username");
		String password =  req.getParameter("password");
		String passwordCheck = req.getParameter("passwordCheck");
		String firstname = req.getParameter("firstname");
		String lastname =  req.getParameter("lastname");
		boolean adminReq = req.getParameter("adminReq") != null;
		
		boolean validAccount = false;
		// Decode form parameters and dispatch to controller		
		controller = new LoginController("inventory");
		//Empty fields
		if (username.length() == 0 || password.length() == 0 || passwordCheck.length() == 0 || firstname.length() == 0 || lastname.length() == 0) {
			errorMessage = "Please fill out all the required fields";
			validAccount = false;
		}
		//Fields too large
		else if(username.length() > 20|| password.length() > 20|| firstname.length() > 20 || lastname.length() > 20){
			errorMessage = "The maximum number of characters allowed is 20";
			validAccount = false;
		}
		//Checking to see if username already exists
		else if (controller.checkExistingUsernames(username)) {
			errorMessage = "A user with that username already exists.";
			validAccount = false;
		} 
		//Making sure passwords match
		else if(!password.equals(passwordCheck)){
			errorMessage = "Passwords do not match";
			validAccount = false;
		}
		else {
			controller = new LoginController("inventory");
			controller.createAccount(username, password, firstname, lastname, adminReq);
			validAccount = true;
		}

		// Add parameters as request attributes
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("passwordCheck", req.getParameter("passwordCheck"));
		req.setAttribute("firstname", req.getParameter("firstname"));
		req.setAttribute("lastname", req.getParameter("lastname"));
		req.setAttribute("adminReq", req.getParameter("adminReq"));
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);

		// if login is valid, start a session
		if (validAccount) {

			// redirect to Login so we can login!
			resp.sendRedirect(req.getContextPath() + "/Login");

			return;
		}

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/CreateAccount.jsp").forward(req, resp);
	}
	
	//DISCLAIMER//
	//All work seen in here has been copied, but modified from the Library example//
}
