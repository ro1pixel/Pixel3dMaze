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
	public void testBFS(int floors, int height, int width) {
		BFS<Position> BFSSearcher = new BFS<>();
		Maze3dGenerator mg = new GrowingTreeGenerator(new LastPositionChooser());
		
		Maze3dSearchableAdapter maze = new Maze3dSearchableAdapter(floors,height,width);
		maze.maze3d = mg.generate(floors,height,width);
		
		Solution<Position> BFSsolution = BFSSearcher.search(maze);
		
		assertNotNull(BFSsolution);
	}

}
