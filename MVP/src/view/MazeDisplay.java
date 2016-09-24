package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;

public class MazeDisplay extends Canvas {
	public static final int PATH = 0;
	public static final int WALL = 1;
	String viewPlane;
	boolean solved;
	Maze3d maze;
	
	public MazeDisplay(Composite parent, int style) {
		super(parent,style);
		viewPlane = "XZ";
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
	
}
