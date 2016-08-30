package algorithms.search;

import java.util.LinkedList;

/**
 * The Class Solution.
 * Defines the solution that will be returned to the user.
 * 
 * @param <T> the state being checked
 */
public class Solution<T> {
	
	/** The path of the solution. */
	private LinkedList<State<T>> path = new LinkedList<>();
	
	public Solution() {
	}
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public LinkedList<State<T>> getPath() {
		return path;
	}
	
	/**
	 * Sets the path.
	 * 
	 * @param path the new path
	 */
	public void setPath(LinkedList<State<T>> path) {
		this.path = path;
	}
	
	/**
	 * Size of linked list.
	 * Returns the path size
	 *
	 * @return the size of the path list
	 */
	public int pathSize() {
		return path.size();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : path) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
	
}
