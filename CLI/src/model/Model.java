package model;

import algorithms.mazeGenerators.Maze3d;

public interface Model {
	void generateMaze(String name, int floors, int height, int width);
	Maze3d getMaze(String name);
}
