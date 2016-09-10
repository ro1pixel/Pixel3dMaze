package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Controller;
import io.MyCompressorOutputStream;

public class MyModel implements Model {
	
	HashMap<String, Maze3d> savedMaze = new HashMap<>();
	
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
	
}
