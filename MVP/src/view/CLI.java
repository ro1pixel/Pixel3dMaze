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
				String input = "-1";
				printToScreen("Please enter your commands and arguments seperated with space: ");
				try {
					while(input != "exit") {
						input = in.readLine();
						setChanged();
						notifyObservers(input);
					}
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
	
}
