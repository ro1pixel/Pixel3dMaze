package model;

import java.io.File;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Controller;

/**
 * The Interface Model.
 */
public interface Model {
	
	/**
	 * Generate maze.
	 *
	 * @param name the name
	 * @param floors the floors
	 * @param height the height
	 * @param width the width
	 */
	void generateMaze(String name, int floors, int height, int width);
	
	/**
	 * Gets the maze with a given name.
	 *
	 * @param name the name
	 * @return the maze
	 */
	Maze3d getMaze(String name);
	
	/**
	 * Save maze to a file.
	 *
	 * @param name the name
	 * @param file_name the file name
	 */
	void saveMaze(String name, String file_name);
	
	/**
	 * Load maze from a file.
	 * 
	 * @param file_name the file name
	 * @param name the name
	 */
	void loadMaze(String file_name, String name);
	
	/**
	 * Solve maze.
	 *
	 * @param name the name
	 * @param algorithm the algorithm
	 */
	void solveMaze(String name, String algorithm);
	
	/**
	 * Gets the solution of a solved maze by name.
	 *
	 * @param name the name
	 * @return the solution
	 */
	Solution<Position> getSolution(String name);
	
	/**
	 * List files in directory.
	 *
	 * @param path the path
	 * @return the file[]
	 */
	File[] listFiles(String path);
	
	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	void setController(Controller controller);
	
	/**
	 * Exit the application.
	 */
	public void saveProperties();
	
	/**
	 * Load properties.
	 */
	public void loadProperties(String fileName);
	
	/**
	 * Edits the properties.
	 *
	 * @param generationType the generation type
	 * @param solutionAlgorithm the solution algorithm
	 * @param maxThreads the max threads
	 * @param viewStyle the view style
	 */
	public void editProperties(String generationType, String solutionAlgorithm, Integer maxThreads, String viewStyle);
	
	/**
	 * Exit.
	 *
	 * @param args the args
	 */
	void exit(String[] args);
	
	/**
	 * Gets the cross section.
	 *
	 * @param name the name
	 * @param axis the axis
	 * @param floor the floor
	 * @return the cross section
	 */
	public int[][] getCrossSection(String name, String axis, Integer floor);
}
