package algorithms.mazeGenerators;

import java.util.Random;

/**
 * The Class SimpleMaze3dGenerator.
 * This class creates a very simple maze.
 */
public class SimpleMaze3dGenerator extends GeneralMaze3dGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int floors,int height, int width) {
		Random random = new Random();
		Maze3d maze3d = new Maze3d(floors, height, width);
		int array3d[][][] = maze3d.getArray3d();
		int xAxis = random.nextInt(maze3d.getWidth());
		
		initializeMaze(maze3d);
		
		//set random start and goal positions
		maze3d.setStartPosition(0,0,xAxis);
		maze3d.setGoalPosition(maze3d.getFloors()-1,maze3d.getHeight()-1,xAxis);
		
		for(int i=0;i<maze3d.getFloors();i++) {
			for(int j=0;j<maze3d.getHeight();j++) {
				array3d[i][j][xAxis] = 0;
			}
		}		
		
		for(int i=0;i<maze3d.getFloors();i++) {
			for(int j=0;j<maze3d.getHeight();j++) {
				for(int k=0;k<maze3d.getWidth();k++) {
					if(array3d[i][j][k]==1)
						array3d[i][j][k] = random.nextInt(2);
				}
			}
		}

		return maze3d;
	}

}
