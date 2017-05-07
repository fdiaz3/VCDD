package resistorSorter.model;

public class Bin {
	
	//Parameters//
	private int count;
	private String resistance;
	private String[] colorBands;
	private boolean selected;
	int rack_id;
	int bin_id;
	
	//Constructor//
	public Bin(int bin_id, int rack_id, int count, String resistance, float tolerance){
		this.count = count;
		this.resistance = resistance;
		this.colorBands = getResistorColors(resistance, tolerance);
		this.selected = false;
		this.rack_id = rack_id;
		this.bin_id = bin_id;
	}
	
	public int getCount(){
		return count;
	}
	public String getResistance(){
		return resistance;
	}
	public String[] getColorBands(){
		return colorBands;
	}
	public boolean getSelected(){
		return selected;
	}
	public int getRack_id(){
		return rack_id;
	}
	public int getBin_id(){
		return bin_id;
	}
	
	private String[] getResistorColors(String stringResistance, float tol){
		int resistance = Integer.getInteger(stringResistance);
		//Real values
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
			//Getting multiplier and getting 2nd band color
			resToMultiply = resToMultiply + num1;
			
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

		return result;
	}
	
}
