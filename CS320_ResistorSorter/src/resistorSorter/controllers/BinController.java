package resistorSorter.controllers;
import java.util.List;



import resistorSorter.model.Bin;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.PersistenceException;
import resistorSorter.persist.TestDerbyDatabase;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BinController {

	Bin model;
	IDatabase db;
	
	public BinController(Bin model, String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
		this.model = model;
	}
	
	//Empty Constructor
	public BinController(String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
	}
	
	
		public String addBin(int rack_id, int resistance, int count, String email){
			int capacity = db.getCapacityFromRack(rack_id);
			int counter = 0;
			//Checking for valid resistance
			String res = Integer.toString(resistance);
			for(int i=2; i<res.length(); i++){
				if(res.charAt(i) != '0'){
					System.out.println(res.charAt(i));
					counter = 1;
				}
			}
			if(db.checkExistingBins(rack_id, resistance)){
				return "Cannot add identical bins under same rack";
			}
			else if(count > capacity){
				return "Cannot add a count higher than the capcity";
			}
			else if(counter == 1){
				return "The resistance you entered is an invalid value";
			}
			else if(!db.getAdminFlag(email)){
				return "Only admistrators can do that";
			}
			else{
				db.insertBin(rack_id, resistance, count);
				return null;
			}
			
		}
		
		public String removeBin(int binID, String email){
			if(!db.getAdminFlag(email)){
				return "Only admistrators can do that";
			}
			db.removeBin(binID);
			return null;
		}
		
		public List<Bin> displayBins(int rackID){
			return db.getAllBins(rackID);
		}
		
		public String addResistor(int bin_id, int addition){
			int count = db.getCount(bin_id);
			int capacity = db.getCapacity(bin_id);
			//Making sure bin is valid
			
			if(count + addition > capacity){
				return "Exceeding Capacity";
			}
			//if all tests pass
			db.addResistors(bin_id, addition);
			return null;
		}
		
		public String removeResistor(int bin_id, int subtraction){
			int count;
			int removelimit;
			if(bin_id == 0){
				return "Removing from Invalid Bin ID";
			}
			try{
				count = db.getCount(bin_id);
				removelimit = db.getUserRemoveLimit(bin_id);
			}catch(PersistenceException e){
				return "Removing from Invalid Bin ID";
			}
			if(subtraction > 0){
				if(subtraction > removelimit){
					return "Exceeding Remove Limit";
				}
				else if(count - subtraction < 0){
					return "Subtracting more than avaliable";
				}			
			}
			else{
				return "Cannot enter negative/string/zero/large values";
			}
			//if all tests pass
			db.removeResistors(bin_id, subtraction);
			return null;
		}
		
		public int getCount(int bin_id){
			return db.getCount(bin_id);
		}
		public int getUserRemoveLimit(int bin_id){
			return db.getUserRemoveLimit(bin_id);
		}
		public int getCapacity(int bin_id){
			return db.getCapacity(bin_id);
		}
		public int getCapacityFromRack(int rack_id){
			return db.getCapacityFromRack(rack_id);
		}
		public int getResistance(int bin_id){
			return db.getResistanceFromBin(bin_id);
		}
		public int getMaxChangeInCount(int bin_id){
			if(db.getUserRemoveLimit(bin_id) > db.getCapacity(bin_id)-db.getCount(bin_id)){
				return db.getUserRemoveLimit(bin_id);
			}
			return db.getCapacity(bin_id)-db.getCount(bin_id);
		}
		public String[] getResistorColors(int bin_id){
			//Real values
			int resistance = db.getResistanceFromBin(bin_id);
			char num1;
			char num2;
			String color1 = null;
			String color2 = null;
			String multiplier = null;
			String tolerance = null;
			String resToMultiply = "";
			int resToMult;
			char iVal;
			String iValcmp;
			//Actual colors
			String[] colors ={"black","brown","red","orange","yellow","green","blue","violet","grey","white"};
			
			//System.out.println("Resistance in: "+resistance);
			//4 bands

			//Getting first band color
			String res = Integer.toString(resistance);
			//More than 2 digits
			if(res.length() > 2){
				num1 =res.charAt(0);

				for(int i = 1; i<=9; i++){
					iValcmp = Integer.toString(i);
					iVal = iValcmp.charAt(0);
					if(num1 == iVal){
						color1 = colors[i];
					}
				}
				//System.out.println(color1);
				//Getting multiplier and getting 2nd band color
				resToMultiply = resToMultiply + num1;
				//System.out.println("Rest to multiply: "+resToMultiply);
				
				for(int i = 1; i<res.length(); i++){
					if(res.charAt(i) == '0'){
						resToMultiply = resToMultiply +"0";
						if(res.length()-i == 1){
							color2 = colors[0];
							resToMultiply = "";
							resToMultiply = resToMultiply + res.charAt(0);
						}
					}
					else{
						num2 = res.charAt(i);
						resToMultiply = resToMultiply + num2;
						
						//System.out.println("2nd number to get color of: "+num2);
						for(int j = 1; j<=9; j++){
							iValcmp = Integer.toString(j);
							iVal = iValcmp.charAt(0);
							if(num2 == iVal){
								color2 = colors[j];
								break;
							}
						}
						break;
					}
				}
				
				//System.out.println("Second Color: "+color2);
				//System.out.println("What gets multiplied to find 3rd color: "+resToMultiply);
				//System.out.println(num2);

				resToMult = Integer.parseInt(resToMultiply);
				
				for(int i=1; i<=7; i++){
					if(Math.pow(10, i)*resToMult == resistance){
						multiplier = colors[i];
						if(resToMultiply.length() == 1){
							multiplier = colors[i-1];
						}
					}
				}
			}
			//Only 2 digits or less
			
			else{
				num1 =res.charAt(0);
				//Digit length 1 
				if(res.length() == 1){
					for(int i=1; i<=9; i++){
						iValcmp = Integer.toString(i);
						iVal = iValcmp.charAt(0);
						if(num1 == iVal){
							color1 = colors[i];
						}
					}
					color2 = colors[0];
					multiplier = "gold";
				}
				else{
					num2 =res.charAt(1);
					
					for(int i=1; i<=9; i++){
						iValcmp = Integer.toString(i);
						iVal = iValcmp.charAt(0);
						if(num1 == iVal){
							color1 = colors[i];
						}
						if(num2 == iVal){
							color2 = colors[i];
						}
					}
					if(num2 == '0'){
						color2 = colors[0];
					}		
					multiplier = colors[0];
				}
				
				
			}
			
			float tol = db.getToleranceFromBin(bin_id);
			//System.out.println(tol);
			//1% tolerance
			if(tol <= 1){
				tolerance = "brown";
			}
			//2%
			else if(tol <= 2){
				tolerance = "red";
			}
			//5%
			else if(tol <= 5){
				tolerance = "gold";
			}
			//10%
			else if(tol <= 10){
				tolerance = "silver";
			}
			//Any other
			else{
				tolerance = "none";
			}
			String[] result = {color1, color2, multiplier, tolerance};
			for(int i=0;i<result.length;i++){
				//System.out.println(result[i]);
			}
			return result;
		}
		
		public void drawResistors(int binid){
			String[] colors = getResistorColors(binid);
			
			try {
			      int width = 200, height = 200;

			      // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			      // into integer pixels
			      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			      Graphics2D ig2 = bi.createGraphics();


			      Font font = new Font("TimesRoman", Font.BOLD, 20);
			      ig2.setFont(font);
			      String message = "www.java2s.com!";
			      FontMetrics fontMetrics = ig2.getFontMetrics();
			      int stringWidth = fontMetrics.stringWidth(message);
			      int stringHeight = fontMetrics.getAscent();
			      ig2.setPaint(Color.black);
			      ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

			      ImageIO.write(bi, "PNG", new File("/yourImageName.PNG"));
			      //ImageIO.write(bi, "JPEG", new File("/yourImageName.JPG"));
			      //ImageIO.write(bi, "gif", new File("/yourImageName.GIF"));
			      //ImageIO.write(bi, "BMP", new File("/war/_view/images/yourImageName.BMP"));
			      
			    } catch (IOException ie) {
			      ie.printStackTrace();
			    }
			
			
		}
}
