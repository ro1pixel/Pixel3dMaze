package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
/*
 * Answers:
 * 1. BFS:
 * 		- Finds the SHORTEST path available!
 * 		- Will never get stuck in a dead end.
 * 
 *    DFS:
 * 		- Better runtime.
 * 		- Memory requierment is better.
 * 
 * 2. I chose to write BFS the way it is
 * 		because it isolates the problem from the solution.
 * 		It's a generic solution.
 * 
 * */
/**
 * The BFS search algorithm class.
 * Solving a Searchable using Best-Depth Search.
 *
 * @author Roysha
 * @param <T> the state that is being checked.
 */

public class BFS<T> extends CommonSearcher<T> {
	
	private HashSet<State<T>> closedSet = new HashSet<>();

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		addToOpenList(startState);
		State<T> goalState = s.getGoalState();
		
		while(!openList.isEmpty()) {
			State<T> currState = pollFromOpenList();
			closedSet.add(currState);
			
			if(currState.equals(goalState))
				return backTrace(currState,startState);
			
			ArrayList<State<T>> successors = s.getAllPossibleStates(currState);
			
			for(State<T> successor: successors) {
				if((!closedSet.contains(successor)) && (!openList.contains(successor))) {
					successor.setCameFrom(currState);
					successor.setCost(currState.getCost() + s.getMoveCost(currState, successor));
					addToOpenList(successor);
				}
				else {
					double newPathCost = currState.getCost() + s.getMoveCost(currState, successor);
					if(successor.getCost() > newPathCost) {
						successor.setCost(newPathCost);
						successor.setCameFrom(currState);
						
						if(!openList.contains(successor)) {
							addToOpenList(successor);
						}
						else {
							openList.remove(successor);
							addToOpenList(successor);
						}
					}
				}
			}
		}	
		return null;
	}

}
