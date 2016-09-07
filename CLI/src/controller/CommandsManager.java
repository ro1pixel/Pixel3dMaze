package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public class CommandsManager {
	
	private Model model;
	private View view;
	
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<>();
		commands.put("generate_3d_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		
		return commands;
	}
	
	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int floors = Integer.parseInt(args[1]);
			int height = Integer.parseInt(args[2]);
			int width = Integer.parseInt(args[3]);
			model.generateMaze(name, floors, height, width);			
		}
		
	}
	
	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
