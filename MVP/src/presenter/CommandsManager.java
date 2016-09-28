package presenter;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;
import model.Model;
import view.View;

/**
 * The Class CommandsManager.
 */
public class CommandsManager {
	
	/** The model. */
	private Model model;
	
	/** The view. */
	private View view;
	
	/**
	 * Instantiates a new commands manager.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Gets the commands map.
	 *
	 * @return the commands map
	 */
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
		commands.put("load_properties", new loadPropertiesCommand());
		commands.put("save_properties", new savePropertiesCommand());
		commands.put("edit_properties", new editPropertiesCommand());
		commands.put("exit", new ExitCommand());
		
		return commands;
	}
	
	/**
	 * The Class GenerateMazeCommand generates a maze and saves to Map.
	 */
	public class GenerateMazeCommand implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int floors = Integer.parseInt(args[1]);
			int height = Integer.parseInt(args[2]);
			int width = Integer.parseInt(args[3]);
			model.generateMaze(name, floors, height, width);
		}
		
	}
	
	/**
	 * The Class DisplayMazeCommand.
	 */
	public class DisplayMazeCommand implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			view.displayMaze(model.getMaze(args[0]));	
		}
		
	}
	
	/**
	 * The Class ViewFilesAndFoldersInPath.
	 */
	public class ViewFilesAndFoldersInPath implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			String path = args[0];
			view.displayFiles(model.listFiles(path));
		}
		
	}
	
	/**
	 * The Class DisplayCrossSection.
	 */
	public class DisplayCrossSection implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String axis = args[1];
			Integer floors = Integer.parseInt(args[2]);
			view.displayCrossSection(model.getCrossSection(name,axis,floors));
		}
		
	}
	
	/**
	 * The Class SaveMaze.
	 */
	public class SaveMaze implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String file_name = args[1];
			model.saveMaze(name, file_name);
		}
		
	}
	
	/**
	 * The Class LoadMaze.
	 */
	public class LoadMaze implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			String name = args[1];
			String file_name = args[0];
			model.loadMaze(file_name, name);
		}
		
	}
	
	/**
	 * The Class SolveMaze.
	 */
	public class SolveMaze implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String algorithm = args[1];
			model.solveMaze(name, algorithm);
		}
		
	}
	
	/**
	 * The Class DisplaySolution.
	 */
	public class DisplaySolution implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			view.displaySolution(model.getSolution(name));
		}
		
	}
	
	public class loadPropertiesCommand implements Command{
		
		@Override
		public void doCommand(String[] args) {
			model.loadProperties();
		}
	}
	
	public class savePropertiesCommand implements Command{
		
		@Override
		public void doCommand(String[] args) {
			model.saveProperties();
		}
	}
	
public class editPropertiesCommand implements Command{
		
		@Override
		public void doCommand(String[] args) {
			String generationType=args[0];
			String solvingAlgorithm=args[1];
			Integer maxThreads=Integer.parseInt(args[2]);
			String viewStyle=args[3];
			model.editProperties(generationType, solvingAlgorithm, maxThreads, viewStyle);
		}
	}
	
	/**
	 * The Class ExitCommand.
	 */
	public class ExitCommand implements Command {

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] args) {
			model.exit(args);
		}
		
	}
	
}
