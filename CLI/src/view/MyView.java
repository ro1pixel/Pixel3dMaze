package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;
import controller.Controller;

public class MyView implements View {
	
	CLI cli;
	Controller controller;
	
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
		int array[][][] = maze.getArray3d();
		for(int[][] arr3 : array) {
			System.out.println();
			for(int[] arr2 : arr3) {
				System.out.println();
				for(int val:arr2) {
					System.out.print(val+ " ");
					
				}
			}
		}
		
	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		cli.setCommands(commands);	
	}

	@Override
	public void displayCrossSection(Maze3d maze,int z, int y, int x) {
		int[][] zSection = maze.getCrossSectionByZ(z);
		
		for(int i=y;i<zSection.length;i++) {
			System.out.println();
			for(int j=x;j<zSection[0].length;j++) {
				System.out.println(zSection[i][j]);
			}
		}
		
		
	}

}
