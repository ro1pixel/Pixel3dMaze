package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;
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
		commands.put("dir", new ViewFilesAndFoldersInPath());
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("display_cross_section", new DisplayCrossSection());
		commands.put("save_maze", new SaveMaze());
		commands.put("load_maze", new LoadMaze());
		commands.put("solve", new SolveMaze());
		commands.put("display_solution", new DisplaySolution());
		
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
			view.displayMaze(model.getMaze(args[0]));	
		}
		
	}
	
	public class ViewFilesAndFoldersInPath implements Command {

		@Override
		public void doCommand(String[] args) {
			String path = args[0];
			view.displayFiles(model.listFiles(path));
		}
		
	}
	
	public class DisplayCrossSection implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int z = Integer.parseInt(args[1]);
			int y = Integer.parseInt(args[2]);
			int x = Integer.parseInt(args[3]);
			view.displayCrossSection(model.getMaze(name),z,y,x);
		}
		
	}
	
	public class SaveMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String file_name = args[1];
			model.saveMaze(name, file_name);
		}
		
	}
	
	public class LoadMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String file_name = args[1];
			model.loadMaze(name, file_name);
		}
		
	}
	
	public class SolveMaze implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String algorithm = args[1];
			model.solveMaze(name, algorithm);
		}
		
	}
	
	public class DisplaySolution implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			view.displaySolution(model.getSolution(name));
		}
		
	}
	
}
