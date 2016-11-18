package view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.Solution;
import algorithms.search.State;
import haganaBk15.Link;
import haganaBk15.Snake;
import model.MyModel;
import model.SnakeBoardSearchable;
import presenter.Command;
import presenter.Controller;

/**
 * The Class GUI.
 */
public class SnakeGUIWindow extends Observable implements View, Observer {

	/** The maze window. */
	SnakeWindow snakeWindow;

	/** The maze name. */
	String mazeName;

	/** The maze. */
	Maze3d maze;
	
	Position currPos;

	/**
	 * Instantiates a new gui.
	 */
	public SnakeGUIWindow() {
		snakeWindow = new SnakeWindow(500, 500);
		
		snakeWindow.setGoButtonListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				Maze3d maze3d = new Maze3d(1,30,30);
				int[][][] array3d = maze3d.getArray3d();
				maze3d.initializeMazeWithPathsOnly();
				maze3d.setStartPosition(new Position(0, snakeWindow.snakesBoard.getCharacterRow(), snakeWindow.snakesBoard.getCharacterCol()));
				maze3d.setGoalPosition(new Position(0, snakeWindow.snakesBoard.getExitRow(), snakeWindow.snakesBoard.getExitCol()));
				
				TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						for(Snake s : snakeWindow.snakesBoard.getSnakes())
							for(Link l : s.getLinks())
								array3d[0][l.y][l.x] = 1;
						snakeWindow.setMaze(maze3d);
						setChanged();
						notifyObservers(maze3d);
						
					}
				};
				
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(task, 0, 140);		
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
	
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		snakeWindow.run();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#notifyMazeIsReady(java.lang.String)
	 */
	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#displayMaze(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void displayMaze(Maze3d maze) {
		if (maze != null) {
			setChanged();
			notifyObservers("display_cross_section " + mazeName + " " + "Z " + maze.getStartPosition().getZ());
			snakeWindow.getMazeDisplay().setMaze(maze);
			// mazeWindow.displayInfoMessage("Maze", "Maze created");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#displayCrossSection(algorithms.mazeGenerators.Maze3d, int,
	 * int, int)
	 */
	@Override
	public void displayCrossSection(Maze3d maze, int z, int y, int x) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#displayCrossSection(int[][])
	 */
	@Override
	public void displayCrossSection(int[][] maze2d) {
		this.snakeWindow.getMazeDisplay().setMazeData(maze2d);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#displaySolution(algorithms.search.Solution)
	 */
	@Override
	public void displaySolution(Solution<Position> solution) {
		snakeWindow.displaySolution(solution);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#notifySolutionIsReady(java.lang.String)
	 */
	@Override
	public void notifySolutionIsReady(String name) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#displayFiles(java.io.File[])
	 */
	@Override
	public void displayFiles(File[] files) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#setController(presenter.Controller)
	 */
	@Override
	public void setController(Controller controller) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.View#printToScreen(java.lang.String)
	 */
	@Override
	public void printToScreen(String string) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o == snakeWindow) {
			setChanged();
			notifyObservers(arg);
		}
	}

	/**
	 * Gets the maze window.
	 *
	 * @return the maze window
	 */
	public SnakeWindow getSnakeWindow() {
		return snakeWindow;
	}

	/**
	 * Gets the maze name.
	 *
	 * @return the maze name
	 */
	public String getMazeName() {
		return mazeName;
	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze3d getMaze() {
		return maze;
	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		// TODO Auto-generated method stub
		
	}

}
