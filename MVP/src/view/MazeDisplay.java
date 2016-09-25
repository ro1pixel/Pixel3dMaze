package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public abstract class MazeDisplay extends Canvas {
	String viewPlane;
	boolean solved;
	Maze3d maze;
	Position characterPosition;
	
	public MazeDisplay(Composite parent, int style) {
		super(parent,style);
		viewPlane = "ZX";
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
		setCharacterPosition(characterPosition);
	}
	
	public void setCharacterPosition(Position position) {
		characterPosition = position;
		redraw();
	}
	
	public void setViewPlane(String plane) {
		viewPlane = plane;
		redraw();
	}
	
	public void setSolved(boolean solved) {
		this.solved = solved;
		redraw();
	}
	
}
