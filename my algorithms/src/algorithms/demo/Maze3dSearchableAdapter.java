package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * The Class Maze3dSearchableAdapter.
 * Transforms a Maze3d to a Searchable.
 */
public class Maze3dSearchableAdapter implements Searchable<Position> {
	
	/** The maze3d. */
	public Maze3d maze3d;
	
	/** The current position. */
	State<Position> currentPosition;

	/**
	 * Instantiates a new maze 3 d searchable adapter.
	 *
	 * @param floors the floors
	 * @param height the height
	 * @param width the width
	 */
	public Maze3dSearchableAdapter(int floors,int height,int width) {
		maze3d = new Maze3d(floors, height, width);
		currentPosition = getStartState();
	}
	
	public Maze3dSearchableAdapter(Maze3d maze3d) {
		this.maze3d = maze3d;
		currentPosition = getStartState();
	}	

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getStartState()
	 */
	@Override
	public State<Position> getStartState() {
		State<Position> s = new State<>(maze3d.getStartPosition());
		return s;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	@Override
	public State<Position> getGoalState() {
		State<Position> s = new State<>(maze3d.getGoalPosition());
		return s;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getAllPossibleStates(algorithms.search.State)
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> state) {
		Position p = state.getValue();
		ArrayList<State<Position>> possibleMoves = new ArrayList<>();
		int[][][] array3d = maze3d.getArray3d();
		int floors = maze3d.getFloors();
		int height = maze3d.getHeight();
		int width = maze3d.getWidth();
		
		if(state.getValue().getX()<width-1 && array3d[p.getZ()][p.getY()][p.getX()+1] == 0) {
			Position pos = new Position(p.getZ(), p.getY(), p.getX()+1);
			State<Position> right = new State<>(pos);
			possibleMoves.add(right);
		}
		if(p.getX()>0 && array3d[p.getZ()][p.getY()][p.getX()-1] == 0) {
			Position pos = new Position(p.getZ(), p.getY(), p.getX()-1);
			State<Position> left = new State<>(pos);
			possibleMoves.add(left);
		}
		if(p.getY()<height-1 && array3d[p.getZ()][p.getY()+1][p.getX()] == 0) {
			Position pos = new Position(p.getZ(), p.getY()+1, p.getX());
			State<Position> down = new State<>(pos);
			possibleMoves.add(down);
		}
		if(p.getY()>0 && array3d[p.getZ()][p.getY()-1][p.getX()] == 0) {
			Position pos = new Position(p.getZ(), p.getY()-1, p.getX());
			State<Position> up = new State<>(pos);
			possibleMoves.add(up);
		}
		if(p.getZ()<floors-1 && array3d[p.getZ()+1][p.getY()][p.getX()] == 0) {
			Position pos = new Position(p.getZ()+1, p.getY(), p.getX());
			State<Position> above = new State<>(pos);
			possibleMoves.add(above);
		}
		if(p.getZ()>0 && array3d[p.getZ()-1][p.getY()][p.getX()] == 0) {
			Position pos = new Position(p.getZ()-1, p.getY(), p.getX());
			State<Position> below = new State<>(pos);
			possibleMoves.add(below);
		}
		
		return possibleMoves;
	}

	/**
	 * Gets the maze3d.
	 *
	 * @return the maze3d
	 */
	public Maze3d getMaze3d() {
		return maze3d;
	}

	/**
	 * Sets the maze3d.
	 *
	 * @param maze3d the new maze3d
	 */
	public void setMaze3d(Maze3d maze3d) {
		this.maze3d = maze3d;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getMoveCost(algorithms.search.State, algorithms.search.State)
	 */
	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {
		return 1;
	}
	
}
