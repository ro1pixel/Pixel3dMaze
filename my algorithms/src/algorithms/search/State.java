package algorithms.search;

/**
 * The Class State.
 * This class defines the state.
 * 
 * @param <T> the generic type
 */
public class State<T> implements Comparable<State<T>> {
	
	/** The came from.
	 *  The state this the current state has come from.*/
	private State<T> cameFrom;
	
	/** The cost of moving to this state.
	 *  BFS is using that.*/
	private double cost;
	
	/** The state itself. */
	private T value;
	
	/** The key of the state. */
	private String key;

	public State(T state) {
		super();
		this.value = state;
	}

	/**
	 * Gets the cost of moving to this state.
	 *
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Sets the cost of moving to this state.
	 *
	 * @param cost the new cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Gets the key of this state.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key of this state.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the state itself.
	 *
	 * @return the value of the state
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value of the state itself.
	 *
	 * @param value the new value of state
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Gets the state that led to this state.
	 *
	 * @return the came from
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * Sets the state that this state has come from.
	 *
	 * @param cameFrom the new came from
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		State<T> s = (State<T>)obj;
		return this.value.equals(s.value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}	
}
