package presenter;

import java.io.Serializable;

/**
 * The game properties.
 */
public class Properties implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The generation type. */
	private String generationType;
	
	/** The solving algorithm. */
	private String solvingAlgorithm;
	
	/** The max threads. */
	private int maxThreads;
	
	private String viewStyle;
	
	public Properties() {
		this.generationType=null;
		this.solvingAlgorithm=null;
		this.viewStyle=null;
	}	
	
	/**
	 * Instantiates the properties.
	 *
	 * @param generationType the generation type
	 * @param solvingAlgorithm the solving algorithm
	 * @param maxThreads the max threads
	 */
	public Properties(String generationType, String solvingAlgorithm, int maxThreads, String viewStyle) {
		super();
		this.generationType = generationType;
		this.solvingAlgorithm = solvingAlgorithm;
		this.maxThreads = maxThreads;
		this.viewStyle = viewStyle;
	}
	
	/**
	 * Instantiates a new properties.
	 *
	 * @param p the p
	 */
	public Properties(Properties p) {
		this.generationType = p.getGenerationType();
		this.solvingAlgorithm = p.getSolvingAlgorithm();
		this.maxThreads = p.getMaxThreads();
		this.viewStyle=p.viewStyle;
	}

	/**
	 * Gets the generation type.
	 *
	 * @return the generation type
	 */
	public String getGenerationType() {
		return generationType;
	}

	/**
	 * Sets the generation type.
	 *
	 * @param generationType the new generation type
	 */
	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}

	/**
	 * Gets the solving algorithm.
	 *
	 * @return the solving algorithm
	 */
	public String getSolvingAlgorithm() {
		return solvingAlgorithm;
	}

	/**
	 * Sets the solving algorithm.
	 *
	 * @param solvingAlgorithm the new solving algorithm
	 */
	public void setSolvingAlgorithm(String solvingAlgorithm) {
		this.solvingAlgorithm = solvingAlgorithm;
	}

	/**
	 * Gets the max threads.
	 *
	 * @return the max threads
	 */
	public int getMaxThreads() {
		return maxThreads;
	}

	/**
	 * Sets the max threads.
	 *
	 * @param maxThreads the new max threads
	 */
	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}

	public String getViewStyle() {
		return viewStyle;
	}

	public void setViewStyle(String viewStyle) {
		this.viewStyle = viewStyle;
	}
	

}
