package model;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

public class SnakeBoardSearchable implements Searchable<Position> {

	Maze3d maze3d;
	
	public SnakeBoardSearchable(Maze3d maze3d) {
		super();
		this.maze3d = maze3d;
	}

	@Override
	public State<Position> getStartState() {
		State<Position> s = new State<>(maze3d.getStartPosition());
		return s;
	}

	@Override
	public State<Position> getGoalState() {
		State<Position> s = new State<>(maze3d.getGoalPosition());
		return s;
	}

	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> state) {
		Position p = state.getValue();
		ArrayList<State<Position>> possibleMoves = new ArrayList<>();
		int[][][] array3d = maze3d.getArray3d();
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
		
		return possibleMoves;
	}

	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {
		return 1;
	}
	
	

}
