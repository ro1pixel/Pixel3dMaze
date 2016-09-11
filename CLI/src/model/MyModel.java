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

public class MyModel implements Model {
	
	Map<String, Maze3d> savedMaze;
	HashMap<String, Solution<Position>> solutions;
	private ExecutorService execService;
	
	Controller controller;

	public MyModel() {}
	
	public MyModel(Controller controller) {
		this.controller = controller;
		savedMaze = new ConcurrentHashMap<>();
		solutions = new HashMap<>();
		this.execService = Executors.newFixedThreadPool(20);
	}

	@Override
	public void generateMaze(String name, int floors, int height, int width) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				if (name!=null){
					GrowingTreeGenerator generator = new GrowingTreeGenerator(new RandomPositionChooser());
					Maze3d maze = generator.generate(floors,height, width);
					savedMaze.put(name, maze);
				
					controller.notifyMazeIsReady(name);
				}
				else{
					controller.printToScreen("invalid data");
				}
			}	
		});	
		
	//	execService.submit(thread);
	}

	@Override
	public Maze3d getMaze(String name) {
		return null;
	}

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

	@Override
	public void loadMaze(String name, String file_name) {
		
		Maze3d maze = getMaze(name);
		
		try {
			InputStream in=new MyDecompressorInputStream(
					new FileInputStream(file_name));
			byte b[]=new byte[maze.toByteArray().length];
			try {
				in.read(b);
				Maze3d loaded=new Maze3d(b);				
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
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void solveMaze(String name, String algorithm) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Maze3d maze = getMaze(name);
				Solution<Position> solution = new Solution<>();
				Maze3dSearchableAdapter mazeAdapter = new Maze3dSearchableAdapter(maze);
				
				if(algorithm.equals("BFS")) {
					BFS<Position> searcher = new BFS<>();
					solution = searcher.search(mazeAdapter);
				}
				else if (algorithm.equals("DFS")) {
					DFS<Position> searcher = new DFS<>();
					solution = searcher.search(mazeAdapter);
				}
				
				solutions.put(name, solution);
				controller.notifySolutionIsReady(name);
			}
		});
		
		thread.start();
		execService.submit(thread);
		
	}
	
	public Solution<Position> getSolution(String name) {
		return solutions.get(name);
	}

	@Override
	public File[] listFiles(String path) {
		return (new File(path).listFiles());
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void exit(String[] args) {
		execService.shutdown();
		controller.printToScreen("Closing Application...");
	}

}
