package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The DFS search algorithm class.
 * Solving a Searchable using DFS.
 * 
 * @param <T> the state that is being checked.
 */
public class DFS<T> extends CommonSearcher<T> {

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		HashSet<State<T>> discovered = new HashSet<State<T>>();
		addToOpenList(s.getStartState());
		
		while(!openList.isEmpty()) {
			State<T> currState = pollFromOpenList();
			
			if(currState.equals(s.getGoalState()))
				return backTrace(currState,s.getStartState());
			
			if(!discovered.contains(currState)) {
				
				discovered.add(currState);
				ArrayList<State<T>> successors = s.getAllPossibleStates(currState);
				
				for(State<T> successor: successors) {
					successor.setCameFrom(currState);
					openList.add(successor);
				}
			}
		}
		
		return null;
	}

}
