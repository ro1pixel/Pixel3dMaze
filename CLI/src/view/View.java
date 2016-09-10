package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;

public interface View {
	void start();
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void displayCrossSection(Maze3d maze,int z,int y,int x);
	void setCommands(HashMap<String, Command> commands); 
}
