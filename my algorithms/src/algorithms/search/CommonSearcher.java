package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * The Class CommonSearcher.
 * Implements Searcher and defines general things.
 *
 * @param <T> the state.
 */
public abstract class CommonSearcher<T> implements Searcher<T> {
	
	/** The evaluated nodes.
	 *  Counts the states being checked for the search method*/
	protected int evaluatedNodes;
	
	/** The open list.
	 *  A list of states that hasn't been checked yet.*/
	protected PriorityQueue<State<T>> openList;
	
	/**
	 * Instantiates a new common searcher.*/
	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>();
		this.evaluatedNodes = 0;
	}
	
	/**
	 * Poll from open list.
	 * polls a state from the list and updates the evaluatedNodes.
	 * @return the state
	 */
	protected State<T> pollFromOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}
	
	/**
	 * Adds a state to the open list.
	 *
	 * @param state the state
	 */
	protected void addToOpenList(State<T> state) {
		openList.add(state);
	}
	
	/**
	 * Back traces the solution that was found to find the way.
	 *
	 * @param goalState the goal state
	 * @param startState the start state
	 * @return the solution
	 */
	protected Solution<T> backTrace(State<T> goalState, State<T> startState) {
		Solution<T> sol = new Solution<>();
		State<T> currentState = goalState;
		LinkedList<State<T>> states = sol.getPath();
		
		while(currentState != null) {
			states.addFirst(currentState);
			currentState = currentState.getCameFrom();
		}
		return sol;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#getNumberOfNodesEvaluated()
	 */
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}

}
