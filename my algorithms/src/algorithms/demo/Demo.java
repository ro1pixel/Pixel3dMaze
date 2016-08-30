package algorithms.demo;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastPositionChooser;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;

/**
 * The Class Demo.
 * Tests the search methods.
 */
public class Demo {
	
	/**
	 * Runs the demo.
	 * 
	 *
	 * @param floors the floors
	 * @param height the height
	 * @param width the width
	 */
	public void Run(int floors,int height, int width) {
		Maze3dGenerator mg = new GrowingTreeGenerator(new LastPositionChooser());
		Maze3dSearchableAdapter maze = new Maze3dSearchableAdapter(floors,height,width);
		maze.maze3d = mg.generate(floors,height,width);
		BFS<Position> BFSSearcher = new BFS<>();
		DFS<Position> DFSSearcher = new DFS<>();
		
		//print maze
		int array[][][] = maze.maze3d.getArray3d();
		int numberOfFloors = 0;
		for(int[][] arr3 : array) {
			System.out.println("\nFloor:" + numberOfFloors);
			for(int[] arr2 : arr3) {
				System.out.println();
				for(int val:arr2) {
					System.out.print(val+ " ");
					
				}
			}
			numberOfFloors++;
		}
		
		System.out.println("\n");
		System.out.println("Start Position: " + maze.maze3d.getStartPosition());
		System.out.println("Goal Position: " + maze.maze3d.getGoalPosition());
		System.out.println();
		
		Solution<Position> BFSsolution = BFSSearcher.search(maze);
		Solution<Position> DFSsolution = DFSSearcher.search(maze);
	    
		System.out.println("----- Paths -----");
		System.out.println("BFS Path: " +BFSsolution);
		System.out.println("DFS Path: " +DFSsolution);
		System.out.println();
		
		System.out.println("----- Step Counter -----");
		System.out.println("BFS Nodes: " + BFSSearcher.getNumberOfNodesEvaluated());	
		System.out.println("BFS Solution: " + BFSsolution.pathSize());	
		System.out.println("DFS Nodes: " + DFSSearcher.getNumberOfNodesEvaluated());	
		System.out.println("DFS Solution: " + DFSsolution.pathSize());
	}
	
}
