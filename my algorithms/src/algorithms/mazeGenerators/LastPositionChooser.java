package algorithms.mazeGenerators;

import java.util.LinkedList;

/**
 * The Class LastPositionChooser.
 * gets the last cell that was added to the list.
 */
public class LastPositionChooser extends GeneralPositionChooser {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.PositionChooser#choose(java.util.LinkedList)
	 */
	@Override
	public Position choose(LinkedList<Position> cells) {
		Position position = cells.getLast();
		return position;
	}

}
