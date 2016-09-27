package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public abstract class MazeDisplay extends Canvas {
	String viewPlane;
	boolean solved;
	Maze3d maze;
	int[][][] array3d;
	Position currentPosition;
	
	public MazeDisplay(Composite parent, int style) {
		super(parent,style);
		viewPlane = "ZX";
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
		if(maze!=null) {
			array3d = maze.getArray3d();
			currentPosition = maze.getStartPosition();
		}
	}
	
	public void setViewPlane(String plane) {
		viewPlane = plane;
		redraw();
	}
	
	public void setSolved(boolean solved) {
		this.solved = solved;
		redraw();
	}
	
	/**
	 * move character one step up
	 */
	public abstract void moveUp();
	/**
	 * move character one step down
	 */
	public abstract  void moveDown();
	/**
	 * move character one step left
	 */
	public abstract  void moveLeft();
	/**
	 * move character one step Right
	 */
	public  abstract void moveRight();
	/**
	 * move character one step floor up
	 */
	public  abstract void moveFloorUp();
	/**
	 * move character one step floor down
	 */
	public  abstract void moveFloorDown();
	/**
	 * move character to start position of the maze
	 */
	public abstract void moveStart();
	/**
	 * move character to new position
	 * @param p position to move to
	 */
	public abstract void move(Position p);
	
}
