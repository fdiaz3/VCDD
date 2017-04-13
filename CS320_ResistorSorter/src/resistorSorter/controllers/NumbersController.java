package resistorSorter.controllers;

import resistorSorter.model.Numbers;

public class NumbersController {
	
	private Numbers model;
	
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	
	
	
	
	
	public void add(Double first, Double second, Double third) {
		model.setFirst(first);
		model.setSecond(second);
		model.setThird(third);
		model.setSum(first + second + third);
	}
	public void multiply(Double first, Double second) {
		model.setFirst(first);
		model.setSecond(second);
		model.setProduct(first * second);
	}
	
}
