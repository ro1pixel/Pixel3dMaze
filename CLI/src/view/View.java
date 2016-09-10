package view;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Command;

public interface View {
	void start();
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void displayCrossSection(Maze3d maze,int z,int y,int x);
	void setCommands(HashMap<String, Command> commands); 
	void displaySolution(Solution<Position> solution);
	void notifySolutionIsReady(String name);
	void displayFiles(File[] files);
}
