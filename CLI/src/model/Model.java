package model;

import java.io.File;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public interface Model {
	void generateMaze(String name, int floors, int height, int width);
	Maze3d getMaze(String name);
	void saveMaze(String name, String file_name);
	void loadMaze(String name, String file_name);
	void solveMaze(String name, String algorithm);
	Solution<Position> getSolution(String name);
	File[] listFiles(String path);
	void setController(Controller controller);
	void exit(String[] args);
}
