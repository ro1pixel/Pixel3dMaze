package algorithms.mazeGenerators;

import java.util.LinkedList;

/**
 * The Interface PositionChooser defines how to implement a position chooser.
 */
public interface PositionChooser {
	
	/**
	 * Picks a position from list.
	 *
	 * @param cells the list
	 * @return the position
	 */
	public Position choose(LinkedList<Position> cells);
}
