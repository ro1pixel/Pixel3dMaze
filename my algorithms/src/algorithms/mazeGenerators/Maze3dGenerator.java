package algorithms.mazeGenerators;

/**
 * The Interface Maze3dGenerator.
 * Defines how a maze generator has to be implemented.
 *
 * @author Roysha
 */
public interface Maze3dGenerator {
	
	/**
	 * Generate.
	 *
	 * @param z the floors
	 * @param y the height
	 * @param x the width
	 * @return the maze3d
	 */
	Maze3d generate(int z, int y, int x);
	
	/**
	 * Measure algorithm time.
	 * returns the runTime of the generate algorithm.
	 *
	 * @param z the floors
	 * @param y the height
	 * @param x the width
	 * @return the time in string
	 */
	String measureAlgorithmTime(int z, int y, int x);
}
