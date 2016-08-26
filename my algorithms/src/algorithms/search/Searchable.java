package algorithms.search;

import java.util.ArrayList;

/**
 * The Interface Searchable.
 * Defines the implementation for a Searchable.
 *
 * @param <T> the state that searchable holds.
 */
public interface Searchable<T> {
	
	/**
	 * Gets the start state.
	 *
	 * @return the start state
	 */
	public State<T> getStartState();
	
	/**
	 * Gets the goal state.
	 *
	 * @return the goal state
	 */
	public State<T> getGoalState();
	
	/**
	 * Gets the all possible states.
	 *
	 * @param state the state
	 * @return the all possible states
	 */
	public ArrayList<State<T>> getAllPossibleStates(State<T> state);
	
	/**
	 * Gets the move cost.
	 *
	 * @param currState the current state
	 * @param neighbor the neighbor
	 * @return the move cost, for the BFS
	 */
	public double getMoveCost(State<T> currState, State<T> neighbor);
}
