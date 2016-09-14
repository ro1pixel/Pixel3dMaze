package controller;

/**
 * The Interface Controller.
 */
public interface Controller {
	/**
	 * Notify Maze is Ready.
	 *
	 * @param name the name
	 */
	void notifyMazeIsReady(String name);
	
	/**
	 * Notify Solution is Ready.
	 *
	 * @param name the name
	 */
	void notifySolutionIsReady(String name);
	
	/**
	 * Print to Screen.
	 *
	 * @param string the string to print.
	 */
	void printToScreen(String string);
}
