package resistorSorter.controllers;
import java.util.List;

import resistorSorter.model.Bin;
import resistorSorter.persist.DatabaseProvider;
import resistorSorter.persist.DerbyDatabase;
import resistorSorter.persist.IDatabase;
import resistorSorter.persist.PersistenceException;
import resistorSorter.persist.TestDerbyDatabase;
import java.lang.*;

public class ColorController {

	Bin model;
	IDatabase db;
	
	
	public ColorController(Bin model, String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
		this.model = model;
	}
	
	//Empty Constructor
	public ColorController(String database){
		if (database == "inventory"){
			DatabaseProvider.setInstance(new DerbyDatabase());
		}else{
			DatabaseProvider.setInstance(new TestDerbyDatabase());
		}
		db = DatabaseProvider.getInstance();
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
		
		System.out.println("Resistance in: "+resistance);
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
			System.out.println("Rest to multiply: "+resToMultiply);
			
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
					
					System.out.println("2nd number to get color of: "+num2);
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
			
			System.out.println("Second Color: "+color2);
			System.out.println("What gets multiplied to find 3rd color: "+resToMultiply);
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
				multiplier = "Gold";
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
		String[] result = {color1, color2, multiplier, "nothing yet"};
		for(int i=0;i<result.length;i++){
			System.out.println(result[i]);
		}
		return result;
	}
}
