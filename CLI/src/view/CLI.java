package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI extends Thread{
	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> stringToCommand;
	
		
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> stringToCommand) {
		super();
		this.in = in;
		this.out = out;
		this.stringToCommand = stringToCommand;
	}

	public void run() {
		String input = Integer.toString(-1);
		while(input != "exit") {
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
