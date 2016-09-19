package algorithms.search;

import static org.junit.Assert.*;
import org.junit.Test;

import algorithms.demo.Maze3dSearchableAdapter;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.LastPositionChooser;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;

public class BFSTest {

	@Test
	public void testBFS() {
		BFS<Position> BFSSearcher = new BFS<>();
		Maze3dGenerator mg = new GrowingTreeGenerator(new LastPositionChooser());
		
		Maze3dSearchableAdapter maze = null;
		Solution<Position> BFSsolution;
		
		//test 1 - null maze
		BFSsolution = BFSSearcher.search(maze);
		assertNull(BFSsolution);
		
		//test 2 - small maze
		maze = new Maze3dSearchableAdapter(1,10,10);
		maze.maze3d = mg.generate(5,10,10);
		BFSsolution = BFSSearcher.search(maze);
		assertNotNull(BFSsolution);
		
		//test 3 - large maze
		maze.maze3d = mg.generate(50,50,50);
		BFSsolution = BFSSearcher.search(maze);
		assertNotNull(BFSsolution);
		
	}

}
