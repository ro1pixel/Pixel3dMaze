package algorithms.search;

/**
 * The Interface Searcher.
 * Defines an implementation for searcher.
 * 
 * @param <T> the generic type
 */
public interface Searcher<T> {
	
	/**
	 * Search.
	 * The method that solves a Searchable.
	 *
	 * @param s the searchable
	 * @return the solution
	 */
	public Solution<T> search(Searchable<T> s);
	
	/**
	 * Gets the number of nodes evaluated.
	 *
	 * @return the number of nodes evaluated
	 */
	public int getNumberOfNodesEvaluated();
}
