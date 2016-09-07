package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;

public class MyView implements View {
	
	CLI cli;
	
	public MyView(CLI cli) {
		super();
		this.cli = cli;
	}

	@Override
	public void start() {
		cli.start();		
	}

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		// TODO Auto-generated method stub
		
	}

}
