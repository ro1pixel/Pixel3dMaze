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

public class CLI{
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commands;
	
	
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> stringToCommand) {
		this.in = in;
		this.out = out;
		this.commands = stringToCommand;
	}
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
	}

	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String input;
				try {
					do {
						printToScreen("Please enter your command and arguments comma seperated: ");
						input = in.readLine();
						String command = input.split(" ")[0];
						if(commands.containsKey(command)) {
							commands.get(command).doCommand(input.substring(input.indexOf(" ")+1,input.length()).split(" "));
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

	public HashMap<String, Command> getCommands() {
		return commands;
	}

	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}
	
	
}
