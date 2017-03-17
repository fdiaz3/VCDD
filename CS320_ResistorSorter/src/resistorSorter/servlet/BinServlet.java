package resistorSorter.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resistorSorter.model.Bin;
import resistorSorter.model.Inventory;
import resistorSorter.model.Rack;

public class BinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Inventory model;
	private Bin bin;
	private String binInfo;
	private String rackInfo;
	private Rack rack;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/InitializeInventory.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		//first time entering this servlet
			if(model == null){
				
			//get instance of inventory from initialize inventory
			String inventoryId = req.getParameter("inventoryId");
			Object inventorObj = req.getSession().getAttribute(inventoryId);
			model = (Inventory) inventorObj;
			
			//get instance of inventory from initialize inventory
			String rackId = req.getParameter("rackId");
			Object rackObj = req.getSession().getAttribute(rackId);
			rack = (Rack) rackObj;
			
			//get binInfo 
			binInfo = (String) req.getParameter("binInfo");
			String binInfoWithoutCount = binInfo.substring(0, binInfo.indexOf(','));
			//get rackInfo
			rackInfo = (String) req.getParameter("rackInfo");
			
			System.out.println("something model: " + model.getBinCapacity());
			System.out.println("something rack: " + model.getRack(rackInfo).getBinLength());
			System.out.println(binInfo);
			
			bin = model.getRack(rackInfo).getBin(binInfoWithoutCount);
			
			}else{
				//System.out.println("nothing");
			}
			
			//pass binInfo to jsp
			req.setAttribute("binInfo", binInfo);
			
			
		

			if (req.getParameter("addResistors") != null) {
				//first add the resistor
				bin.addResistor(getInteger(req, "count"), model);
				
				//updates binInfo
				ArrayList<String> bins = rack.getBins();
				int binNum = getInteger(req, "binNum");
				binInfo = bins.get(binNum);
				req.setAttribute("binInfo", binInfo);
				
				
				
				req.getRequestDispatcher("/_view/Bin.jsp").forward(req, resp);
				
				
			}else if (req.getParameter("subResistors") != null) {
				//first subtract the resistor
				bin.removeResistor(getInteger(req, "count"), model);
				
				//updates binInfo
				ArrayList<String> bins = rack.getBins();
				int binNum = getInteger(req, "binNum");
				binInfo = bins.get(binNum);
				req.setAttribute("binInfo", binInfo);
				
				
				
				req.getRequestDispatcher("/_view/Bin.jsp").forward(req, resp);
				
				
			}else if (req.getParameter("return") != null) {

				req.getRequestDispatcher("/_view/Rack.jsp").forward(req, resp);
				
			}else {
				throw new ServletException("Unknown command");
			}
			
			

		
		
	}
	
	private int getInteger(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Integer.parseInt(req.getParameter(name));
		}
		else{
			return 0;
		}
	}
	
	private double getDouble(HttpServletRequest req, String name) {

		if(req.getParameter(name) != ""){
			return Double.parseDouble(req.getParameter(name));
		}
		else{
			return 0;
		}

	}
}
