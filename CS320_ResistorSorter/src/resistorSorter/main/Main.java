package resistorSorter.main;

import java.util.Scanner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import resistorSorter.persist.DerbyDatabase;
import resistorSorter.model.*;

public class Main {
	private static EmailReceive model;
	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);

		// Create and register a webapp context
		WebAppContext handler = new WebAppContext();
		handler.setContextPath("/resistorSorter");
		handler.setWar("./war"); // web app is in the war directory of the project
		server.setHandler(handler);
		
		// Use 20 threads to handle requests
		server.setThreadPool(new QueuedThreadPool(20));
		
		// Start the server
		server.start();

		//creates the database if it doesn't exist
		DerbyDatabase.loadDataBase();

		//Check up on emails
        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "vcddProj@gmail.com";// change accordingly
        String password = "team_dbf";// change accordingly

        
		// Wait for the user to type "quit"
		System.out.println("Web server started, type quit to shut down");
		Scanner keyboard = new Scanner(System.in);
		while (keyboard.hasNextLine()) {
			String line = keyboard.nextLine();
			if (line.trim().toLowerCase().equals("quit")) {
				String[] requests = EmailReceive.check(host, mailStoreType, username, password);
				for(int i=0; i<requests.length; i++){
					//System.out.println(requests[i]);
					String subjectLine = requests[i].substring(requests[i].indexOf(" "), requests[i].indexOf(" ", requests[i].indexOf(" ")));
					System.out.println(subjectLine);
				}
				break;
			}
		}
		
		System.out.println("Shutting down...");
		keyboard.close();
		server.stop();
		server.join();
		System.out.println("Server has shut down, exiting");
	}
}
