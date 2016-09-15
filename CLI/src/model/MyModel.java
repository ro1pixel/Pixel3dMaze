package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.demo.Maze3dSearchableAdapter;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.RandomPositionChooser;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * The Class MyModel.
 */
public class MyModel implements Model {
	
	/** The saved maze Map. */
	Map<String, Maze3d> savedMaze;
	
	/** The solutions Map. */
	HashMap<String, Solution<Position>> solutions;
	
	/** The exec service. */
	private ExecutorService execService;
	
	/** The controller. */
	Controller controller;

	/**
	 * Instantiates a new my model.
	 */
	public MyModel() {
		savedMaze = new ConcurrentHashMap<>();
		solutions = new HashMap<>();
		this.execService = Executors.newFixedThreadPool(20);
	}
	
	/**
	 * Instantiates a new my model.
	 *
	 * @param controller the controller
	 */
	public MyModel(Controller controller) {
		this.controller = controller;
		savedMaze = new ConcurrentHashMap<>();
		solutions = new HashMap<>();
		this.execService = Executors.newFixedThreadPool(20);
	}

	/* (non-Javadoc)
	 * @see model.Model#generateMaze(java.lang.String, int, int, int)
	 */
	@Override
	public void generateMaze(String name, int floors, int height, int width) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				if (name!=null){
					GrowingTreeGenerator generator = new GrowingTreeGenerator(new RandomPositionChooser());
					Maze3d maze = generator.generate(floors,height, width);
					savedMaze.put(name, maze);
				
				}
				else{
					controller.printToScreen("invalid data");
				}
			}	
		});	
		
		thread.start();
		controller.notifyMazeIsReady(name);
		execService.submit(thread);
	}

	/* (non-Javadoc)
	 * @see model.Model#getMaze(java.lang.String)
	 */
	@Override
	public Maze3d getMaze(String name) {
		return savedMaze.get(name);
	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveMaze(String name, String file_name) {
		
		Maze3d maze = getMaze(name);
		
		try {
			OutputStream out=new MyCompressorOutputStream(
					new FileOutputStream(file_name));
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
		
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void loadMaze(String file_name, String name) {
		
		if(!savedMaze.containsKey(name)) {
			try {
				InputStream in=new MyDecompressorInputStream(
						new FileInputStream(file_name));
				InputStream in2=new MyDecompressorInputStream(
						new FileInputStream(file_name));
				
				Scanner scanner = new Scanner(in);
				Scanner input = scanner.useDelimiter(",");
				byte[] b = new byte[Integer.valueOf(input.next())];
				
				try {
					in2.read(b);
					Maze3d loaded=new Maze3d(b);	
					savedMaze.put(name,loaded);
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					controller.printToScreen("Maze loaded successfuly!");
				}
			} catch (FileNotFoundException e) {
				controller.printToScreen("Error loading your maze.");
				e.printStackTrace();
			}
		}
		else {
			controller.printToScreen("Maze already exists!");
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#solveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void solveMaze(String name, String algorithm) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Maze3d maze = getMaze(name);
				Solution<Position> solution = new Solution<>();
				Maze3dSearchableAdapter mazeAdapter = new Maze3dSearchableAdapter(maze);
				
				if(algorithm.equals("BFS") || algorithm.equals("bfs")) {
					BFS<Position> searcher = new BFS<>();
					solution = searcher.search(mazeAdapter);
				}
				else if (algorithm.equals("DFS") || algorithm.equals("dfs")) {
					DFS<Position> searcher = new DFS<>();
					solution = searcher.search(mazeAdapter);
				}else {
					System.out.println("Wrong method. Available options are BFS/DFS");
				}
				
				solutions.put(name, solution);
			}
		});
		
		controller.notifySolutionIsReady(name);
		thread.start();
		execService.submit(thread);
		
	}
	
	/* (non-Javadoc)
	 * @see model.Model#getSolution(java.lang.String)
	 */
	public Solution<Position> getSolution(String name) {
		return solutions.get(name);
	}

	/* (non-Javadoc)
	 * @see model.Model#listFiles(java.lang.String)
	 */
	@Override
	public File[] listFiles(String path) {
		return (new File(path).listFiles());
	}

	/* (non-Javadoc)
	 * @see model.Model#setController(controller.Controller)
	 */
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#exit(java.lang.String[])
	 */
	public void exit(String[] args) {
		execService.shutdown();
		controller.printToScreen("Closing Application...");
		System.exit(1);
	}

}
