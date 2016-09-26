package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import presenter.Command;
import presenter.Controller;

/**
 * The Class MyView.
 */
public class MyView extends Observable implements View, Observer {
	
	private BufferedReader in;
	
	private PrintWriter out;
	
	/** The cli. */
	CLI cli;
	
	/** The controller. */
	Controller controller;
	
	/**
	 * Instantiates a new my view.
	 *
	 * @param cli the cli
	 */
	public MyView(CLI cli) {
		super();
		this.cli = cli;
	}
	
	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
				
		cli = new CLI(this.in, this.out);
		cli.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		cli.start();		
	}

	/* (non-Javadoc)
	 * @see view.View#setController(controller.Controller)
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see view.View#notifyMazeIsReady(java.lang.String)
	 */
	@Override
	public void notifyMazeIsReady(String name) {
		System.out.println("Maze " + name + " is READY!");
		
	}

	/* (non-Javadoc)
	 * @see view.View#displayMaze(algorithms.mazeGenerators.Maze3d)
	 */
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


	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(algorithms.mazeGenerators.Maze3d, int, int, int)
	 */
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

	/* (non-Javadoc)
	 * @see view.View#displaySolution(algorithms.search.Solution)
	 */
	@Override
	public void displaySolution(Solution<Position> solution) {
		System.out.println("----- Solution Path -----");
		System.out.println(solution);
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see view.View#notifySolutionIsReady(java.lang.String)
	 */
	@Override
	public void notifySolutionIsReady(String name) {
		System.out.println("Solution for maze " + name + " is READY!");		
	}

	/* (non-Javadoc)
	 * @see view.View#displayFiles(java.io.File[])
	 */
	@Override
	public void displayFiles(File[] files) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<files.length;i++) {
					if(files[i].isFile()) {
						System.out.println("File: " + files[i].getName());
					} else if(files[i].isDirectory()) {
						System.out.println("Directory: " + files[i].getName());
					}
				}
				
			}
		});
		thread.start();
	}
	
	/* (non-Javadoc)
	 * @see view.View#printToScreen(java.lang.String)
	 */
	public void printToScreen(String str) {
		cli.printToScreen(str);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o == cli) {
			setChanged();
			notifyObservers(arg);
		}	
	}

	@Override
	public void displayCrossSection(int[][] maze2d) {
		// TODO Auto-generated method stub
		
	}

}
