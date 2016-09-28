package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The Class MazeDisplay.
 */
public abstract class MazeDisplay extends Canvas {
	
	/** The view plane. */
	String viewPlane;
	
	/** The solved. */
	boolean solved;
	
	/** The maze. */
	Maze3d maze;
	
	/** The array 3 d. */
	int[][][] array3d;
	
	/** The current position. */
	Position currentPosition;
	
	/**
	 * Instantiates a new maze display.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public MazeDisplay(Composite parent, int style) {
		super(parent,style);
		viewPlane = "ZX";
	}

	/**
	 * Sets the maze.
	 *
	 * @param maze the new maze
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
		if(maze!=null) {
			array3d = maze.getArray3d();
            currentPosition = new Position(maze.getStartPosition());
		}
	}
	
	/**
	 * Sets the view plane.
	 *
	 * @param plane the new view plane
	 */
	public void setViewPlane(String plane) {
		viewPlane = plane;
		redraw();
	}
	
	/**
	 * Sets the solved.
	 *
	 * @param solved the new solved
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
		redraw();
	}
	
	/**
	 * move character one step up.
	 */
	public abstract void moveUp();
	
	/**
	 * move character one step down.
	 */
	public abstract  void moveDown();
	
	/**
	 * move character one step left.
	 */
	public abstract  void moveLeft();
	
	/**
	 * move character one step Right.
	 */
	public  abstract void moveRight();
	
	/**
	 * move character one step floor up.
	 */
	public  abstract void moveFloorUp();
	
	/**
	 * move character one step floor down.
	 */
	public  abstract void moveFloorDown();
	
	/**
	 * move character to start position of the maze.
	 */
	public abstract void moveStart();
	
	/**
	 * move character to new position.
	 *
	 * @param p position to move to
	 */
	public abstract void move(Position p);

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze3d getMaze() {
		return maze;
	}

	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public Position getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Sets the current position.
	 *
	 * @param currentPosition the new current position
	 */
	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}
}
