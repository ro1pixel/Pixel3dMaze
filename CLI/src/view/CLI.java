package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyRep;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import controller.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class CLI.
 */
public class CLI{
	
	/** The in. */
	BufferedReader in;
	
	/** The out. */
	PrintWriter out;
	
	/** The commands. */
	HashMap<String, Command> commands;
	
	
	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param stringToCommand the string to command
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> stringToCommand) {
		this.in = in;
		this.out = out;
		this.commands = stringToCommand;
	}
	
	/**
	 * Instantiates a new cli.
	 *
	 * @param in the input
	 * @param out the output
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
						String command = input.split(" ")[0];
						if(commands.containsKey(command)) {
							commands.get(command).doCommand(input.substring(input.indexOf(" ")+1,input.length()).split(" "));
						}
						else {
							printToScreen("Please enter a valid command!");
						}
					} while (input != "exit");
				}
				catch (IOException e) {
					e.printStackTrace();
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
	 * Gets the commands.
	 *
	 * @return the commands
	 */
	public HashMap<String, Command> getCommands() {
		return commands;
	}

	/**
	 * Sets the commands.
	 *
	 * @param commands the commands
	 */
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}
	
}
