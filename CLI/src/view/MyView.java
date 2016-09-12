package view;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
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

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void notifyMazeIsReady(String name) {
		System.out.println("Maze " + name + " is READY!");
		
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
		
		System.out.println();	
		
	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		cli.setCommands(commands);	
	}

	@Override
	public void displayCrossSection(Maze3d maze,int z, int y, int x) {
		int[][] zSection = maze.getCrossSectionByZ(z);
		
		for(int i=y;i<zSection.length;i++) {
			System.out.println(" ");
			for(int j=x;j<zSection[0].length;j++) {
				System.out.print(zSection[i][j] + " ");
			}
		}
		System.out.println();		
		
	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		System.out.println("----- Paths -----");
		System.out.println("BFS Path: " + solution);
		System.out.println();
	}

	@Override
	public void notifySolutionIsReady(String name) {
		System.out.println("Solution for maze " + name + " is READY!");		
	}

	@Override
	public void displayFiles(File[] files) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<files.length;i++) {
					if(files[i].isFile()) {
						System.out.println("File " + files[i].getName());
					} else if(files[i].isDirectory()) {
						System.out.println("Directory " + files[i].getName());
					}
				}
				
			}
		});
		thread.start();
	}
	
	public void printToScreen(String str) {
		cli.printToScreen(str);
	}

}
