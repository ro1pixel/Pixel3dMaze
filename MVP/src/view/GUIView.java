package view;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Controller;

public class GUIView extends Observable implements View, Observer {
	
	MazeWindow mazeWindow;
	String mazeName;
	Maze3d maze;
	
	public GUIView() {
		mazeWindow = new MazeWindow(500, 500);
		mazeWindow.generateMazeSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateMazeWindow gmw = new GenerateMazeWindow(300, 200);
				gmw.run();
				mazeName = gmw.getName();
				setChanged();
				notifyObservers("generate_maze " +mazeName + " "+ gmw.getFloors()+ " " + gmw.getHeight()+ " "+ gmw.getWidth());
				mazeWindow.displayInfoMessage("Success", "Maze has been generated Successfully");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayCrossSection(Maze3d maze, int z, int y, int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifySolutionIsReady(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayFiles(File[] files) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setController(Controller controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printToScreen(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
