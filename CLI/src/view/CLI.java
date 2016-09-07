package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI{
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commands;
	
		
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> stringToCommand) {
		this.in = in;
		this.out = out;
		this.commands = stringToCommand;
	}

	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String input;
				try {
					do {
						printToScreen("What would you like to do?");
						input = in.readLine();
						if(commands.containsKey(input)) {
							commands.get(input).doCommand();
						}
						else {
							printToScreen("Please enter a valid command!");
						}
					} while ((in.readLine()) != "exit");
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	public void printToScreen(String str) {
		out.println(str);
		out.flush();
	}
	
}
