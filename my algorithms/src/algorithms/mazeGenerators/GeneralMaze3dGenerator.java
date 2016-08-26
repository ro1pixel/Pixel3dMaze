package algorithms.mazeGenerators;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 * The Class GeneralMaze3dGenerator.
 * A general implementation of Maze3dGenerator.
 */
public abstract class GeneralMaze3dGenerator implements Maze3dGenerator {
	
	/** The chooser. */
	protected PositionChooser chooser = null;
	
	/** The list of cells. */
	protected LinkedList<Position> cells = null;

	@Override
	public String measureAlgorithmTime(int floors, int height, int width) {
		long startTime = System.currentTimeMillis();
		generate(floors, height, width);
		long stopTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("ss:SSS");
		Date totalTime = new Date(stopTime-startTime);
		
		return sdf.format(totalTime);
	}
	
	/**
	 * Initialize maze.
	 * Sets the whole maze to walls.
	 * 
	 * @param maze3d the maze3d
	 */
	protected void initializeMaze(Maze3d maze3d) {
		int[][][] array3d = maze3d.getArray3d();

		for(int i=0;i<array3d.length;i++) {
			for(int j=0; j<array3d[i].length;j++) {
				for(int k=0;k<array3d[i][j].length;k++) {
					array3d[i][j][k] = 1;
				}
			}
	}
	}

}
