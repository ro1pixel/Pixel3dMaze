package boot;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastPositionChooser;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.RandomPositionChooser;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class Run {

	public static void main(String[] args) {
	//	testMazeGenerator(new SimpleMaze3dGenerator());
	//	testMazeGenerator(new GrowingTreeGenerator(new RandomPositionChooser()));
		testMazeGenerator(new GrowingTreeGenerator(new LastPositionChooser()));
	}
	
	private static void testMazeGenerator(Maze3dGenerator mg) {
		// prints the time it takes the algorithm to run
		//System.out.println("Algorithm Runtime: " +mg.measureAlgorithmTime(4,12,24));
		
		// generate another 3d maze
		Maze3d maze=mg.generate(1,9,9);
		
		//print maze
//		int array[][][] = maze.getArray3d();
//		for(int[][] arr3 : array) {
//			System.out.println();
//			for(int[] arr2 : arr3) {
//				System.out.println();
//				for(int val:arr2) {
//					System.out.print(val+ " ");
//					
//				}
//			}
//		}
		
		// get the maze entrance
		Position p = maze.getStartPosition();
		
		// print the position
		System.out.println("\n\nStart Position: "+p);
		
		// get all the possible moves from a position
		String moves[]= maze.getPossibleMoves(p);
		
		// print the moves
		for(String move : moves) {
			if(move!=null)
				System.out.println(move);
		}
			
		
		// prints the maze exit position
		System.out.println("Goal Position:  "+maze.getGoalPosition());
		try{
			// get 2d cross sections of the 3d maze
			int[][] maze2dx=maze.getCrossSectionByX(2);
			for(int[] arr2 : maze2dx) {
				System.out.println();
				for(int val:arr2) {
					System.out.print(val+ " ");
				}
			}
			System.out.println("\n");
			int[][] maze2dy=maze.getCrossSectionByY(5);
			for(int[] arr2 : maze2dy) {
					System.out.println();
				for(int val:arr2) {
					System.out.print(val+ " ");
				}
			}
			System.out.println("\n");
			int[][] maze2dz=maze.getCrossSectionByZ(1);
			for(int[] arr2 : maze2dz) {
					System.out.println();
				for(int val:arr2) {
					System.out.print(val+ " ");
				}
			}
			System.out.println("\n");
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		} catch (IndexOutOfBoundsException e){
			System.out.println("OUT OF BOUNDS!");
		}
	}

}
