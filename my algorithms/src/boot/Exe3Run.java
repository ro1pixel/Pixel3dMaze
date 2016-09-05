package boot;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastPositionChooser;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.RandomPositionChooser;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class Exe3Run {

	public static void main(String[] args) {
	//	testMazeGenerator(new SimpleMaze3dGenerator());
	//	testMazeGenerator(new GrowingTreeGenerator(new RandomPositionChooser()));
		testMazeGenerator(new GrowingTreeGenerator(new LastPositionChooser()));
	}
	
	private static void testMazeGenerator(Maze3dGenerator mg) {
		// prints the time it takes the algorithm to run
		//System.out.println("Algorithm Runtime: " +mg.measureAlgorithmTime(4,12,24));
		
		// generate another 3d maze
		Maze3d maze=mg.generate(4,600,9);
		
		maze.toByteArray();
	}
}
