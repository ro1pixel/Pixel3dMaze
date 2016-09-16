package view;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import presenter.Command;
import presenter.Controller;

/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * Start.
	 */
	void start();
	
	/**
	 * Notify maze is ready.
	 *
	 * @param name the name
	 */
	void notifyMazeIsReady(String name);
	
	/**
	 * Display maze.
	 *
	 * @param maze the maze
	 */
	void displayMaze(Maze3d maze);
	
	/**
	 * Display cross section.
	 *
	 * @param maze the maze
	 * @param z the z
	 * @param y the y
	 * @param x the x
	 */
	void displayCrossSection(Maze3d maze,int z,int y,int x);
	
	/**
	 * Sets the commands.
	 *
	 * @param commands the commands
	 */
	void setCommands(HashMap<String, Command> commands); 
	
	/**
	 * Display solution.
	 *
	 * @param solution the solution
	 */
	void displaySolution(Solution<Position> solution);
	
	/**
	 * Notify solution is ready.
	 *
	 * @param name the name
	 */
	void notifySolutionIsReady(String name);
	
	/**
	 * Display files.
	 *
	 * @param files the files
	 */
	void displayFiles(File[] files);
	
	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	void setController(Controller controller);
	
	/**
	 * Prints the to screen.
	 *
	 * @param string the string
	 */
	void printToScreen(String string);
	
}
