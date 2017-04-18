package resistorSorter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) throws IOException {
		//Initiate keyboard and resistor value//
		Main frame = new Main();
		
		//Embedded GUI//
		frame.add(new Interface());

		//When the frame is closed, exit the program//
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Making the frame visible starts the program//
        frame.setVisible(true);
        
        //Create instances of scanner and inventory
        Scanner keyboard = new Scanner(System.in);
        Inventory inventory;
        
        System.out.println("Please set Bin capacity for this inventory: ");
        int binCapacity = Integer.parseInt(keyboard.next());
        
        System.out.println("Please enter remove limit for the user: ");
        int removeLimit = Integer.parseInt(keyboard.next());
        
        inventory = new Inventory(binCapacity, removeLimit);
       
		inventory.addRack("5 0.25");
		inventory.addRack("10 0.5");
		
		
	}
		
}