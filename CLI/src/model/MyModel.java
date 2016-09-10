package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import algorithms.demo.Maze3dSearchableAdapter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {
	
	HashMap<String, Maze3d> savedMaze = new HashMap<>();
	HashMap<String, Solution<Position>> solutions = new HashMap<>();
	
	Controller controller;

	public MyModel(HashMap<String, Maze3d> savedMaze, Controller controller) {
		this.savedMaze = savedMaze;
		this.controller = controller;
	}

	@Override
	public void generateMaze(String name, int floors, int height, int width) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void solveMaze(String name, String algorithm) {
		
		new Thread(new Runnable() {
			
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
		}).start();
		
	}
	
	public Solution<Position> getSolution(String name) {
		return solutions.get(name);
	}
	
}
