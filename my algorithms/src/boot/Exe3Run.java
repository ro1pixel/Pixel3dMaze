package boot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastPositionChooser;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.RandomPositionChooser;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

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
		Maze3d maze=mg.generate(3,10,10);	
	
//	//print maze
//	int[][][] array2 = maze.getArray3d();
//	int numberOfFloors = 0;
//	for(int[][] arr3 : array2) {
//		System.out.println("\nFloor:" + numberOfFloors);
//		for(int[] arr2 : arr3) {
//			System.out.println();
//			for(int val:arr2) {
//				System.out.print(val+ " ");
//				
//			}
//		}
//		numberOfFloors++;
//	}
	
	try {
		OutputStream out=new MyCompressorOutputStream(
				new FileOutputStream("1.maz"));
				try {
					out.write(maze.toByteArray());
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		InputStream in=new MyDecompressorInputStream(
				new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		try {
			in.read(b);
			Maze3d loaded=new Maze3d(b);
			
			//print maze
			int[][][] array2 = loaded.getArray3d();
			int numberOfFloors = 0;
			for(int[][] arr3 : array2) {
				System.out.println("\nFloor:" + numberOfFloors);
				for(int[] arr2 : arr3) {
					System.out.println();
					for(int val:arr2) {
						System.out.print(val+ " ");
						
					}
				}
				numberOfFloors++;
			}	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
		
}
