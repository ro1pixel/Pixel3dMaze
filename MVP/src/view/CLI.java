package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyRep;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import presenter.Command;

/**
 * The Class CLI.
 */
public class CLI extends Observable{
	
	/** The in. */
	BufferedReader in;
	
	/** The out. */
	PrintWriter out;
	
	
	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param stringToCommand the string to command
	 */
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
	}

	/**
	 * Start.
	 */
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String input;
				try {
					do {
						printToScreen("Please enter your command and arguments seperated with space: ");
						input = in.readLine();
						setChanged();
						notifyObservers(input);
//						String command = input.split(" ")[0];
//						if(commands.containsKey(command)) {
//							commands.get(command).doCommand(input.substring(input.indexOf(" ")+1,input.length()).split(" "));
//						}
//						else {
//							printToScreen("Please enter a valid command!");
//						}
					} while (input != "exit");
				}
				catch (IOException e) {
					e.printStackTrace();
					start();
				}
				catch(Exception e) {
					printToScreen("Invalid arguments!");
					start();
				}
				
			}
		}).start();
	}
	
	/**
	 * Prints the to screen.
	 *
	 * @param str the string
	 */
	public void printToScreen(String str) {
		out.println(str);
		out.flush();
	}
	
    /**
     * Print a menu to the viewer
     */
	public void printMenu(){
		printToScreen("\n\n********************************");
		printToScreen("what do want to do? ");
		printToScreen("		dir <path>");
		printToScreen("		generate_maze <mazeName> <x> <y> <z>");
		printToScreen("		display <name>");
		printToScreen("		display_cross_section <axle> <index> <mazeName>");
    	printToScreen("		save_maze <mazeName> <fileName>");
    	printToScreen("		load_maze <mazeName> <fileName>");
    	printToScreen("		solve <mazeName> <algorithm>");
    	printToScreen("		display_solution <mazeName>");
    	printToScreen("		exit");
    	printToScreen("********************************\n");
		/*for (String command : commands.keySet()) {
			out.print(command + ",");
		}*/
	}
	
}
