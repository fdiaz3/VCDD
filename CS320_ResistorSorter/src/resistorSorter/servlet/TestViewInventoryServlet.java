package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorterdb.persist.DerbyDatabase;
import resistorSorterdb.persist.DatabaseProvider;
import resistorSorterdb.persist.IDatabase;
import resistorSorter.model.Inventory;

public class TestViewInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Inventory model;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/TestViewInventory.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		//List<Inventory> inventories = db.getAllInventories();
		//ArrayList<Inventory> inventories = new ArrayList<Inventory>();
		//inventories.add(new Inventory(10, 10));
		
		
		//req.setAttribute("inventories", inventories);

		
		req.setAttribute("inventory", new Inventory(10, 10));
	
		req.getRequestDispatcher("/_view/TestViewInventory.jsp").forward(req, resp);
	}
	
}
