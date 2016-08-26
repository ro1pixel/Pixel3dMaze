package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.Random;

/**
 * The Class RandomPositionChooser.
 * Gets a random cell from a list.
 */
public class RandomPositionChooser extends GeneralPositionChooser {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.PositionChooser#choose(java.util.LinkedList)
	 */
	@Override
	public Position choose(LinkedList<Position> cells) {
		Random random = new Random();
		Position position = cells.get(random.nextInt(cells.size()));
		return position;
	}

}
