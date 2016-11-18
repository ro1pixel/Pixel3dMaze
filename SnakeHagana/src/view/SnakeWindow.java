package view;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.print.attribute.standard.RequestingUserName;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import haganaBk15.Link;
import haganaBk15.Snake;
import haganaBk15.SnakesBoard;
import presenter.CommandsManager.SolveMaze;


/**
 * MazeWindow class extends BasicWindow.
 */
public class SnakeWindow extends BasicWindow{
	
    protected Maze2dDisplay mazeDisplay;
	protected Maze3d maze;
	SnakesBoard snakesBoard;
	Button go;
	int[][][] array3d;
	Thread t;

    /**
     * CTOR.
     *
     * @param width of window
     * @param height of window
     */
	public SnakeWindow(int width, int height) {
		super(width, height);	
	}

	/**
	 * initWidget- create the buttons.
	 */
	@Override
	public void initWidgets() {
		GridLayout gridLayout = new GridLayout(1,false);
		shell.setLayout(gridLayout);
		shell.setText("Snake Window");
		
		snakesBoard = new SnakesBoard(shell, SWT.FILL, 30, 30);
		snakesBoard.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));

		
		go = new Button(shell, SWT.PUSH);
		go.setText("GO!!!");
		go.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
	}
	
	public void setGoButtonListener(SelectionListener listener) {
		go.addSelectionListener(listener);
	}

	/**
	 * close the shell.
	 */
	public void exit(){
		shell.dispose();
	}

	/**
	 * getter of mazeDisplay
	 * @return the maze display
	 */
	public Maze2dDisplay getMazeDisplay() {
		return mazeDisplay;
	}

	/**
	 * setter of mazeDisplay
	 * @param mazeD the new maze display
	 */
	public void setMazeDisplay(Maze2dDisplay mazeD) {
		this.mazeDisplay = mazeD;
	}

	/**
	 * getter of the maze
	 * @return maze- Maze3d
	 */
	public Maze3d getMaze() {
		return maze;
	}

	/**
	 * setter of maze3D
	 * @param maze the new maze
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
	
	
	/**
	 * Function to perform zoom in or zoom out with ctrl + mouse wheel
	 * @param scroll - Positive for zoom in , Negative for zoom out
	 */
	public void performZoom(int scroll) {
		int length = shell.getSize().x;
		int width = shell.getSize().y;

		if(scroll < 0)
			shell.setSize((int)(length*0.99), (int)(width*0.99));
		else
			shell.setSize((int)(length*1.01), (int)(width*1.01));
	}
	
	/**
	 * redraw.
	 */
	public void redraw() {
        shell.redraw();
	}	
	
	public void moveChar(Position p) {
		int x = snakesBoard.getCharacterCol();
		int y = snakesBoard.getCharacterRow();
		
		if(p.getX()>x)
			snakesBoard.moveRight();
		else if(p.getX()<x)
			snakesBoard.moveLeft();
		else if(p.getY()>y)
			snakesBoard.moveUp();
		else if(p.getY()<y)
			snakesBoard.moveDown();
		
		snakesBoard.moveDown();	
	}
	
	public void displaySolution(Solution<Position> solution) {
		
		LinkedList<State<Position>> sol = solution.getPath();
		Position currPos = new Position(0,(snakesBoard.getCharacterRow()),(snakesBoard.getCharacterCol()));
		Position end= new Position(0,(snakesBoard.getExitRow()),(snakesBoard.getExitCol()));
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try{Thread.sleep(140);} catch (Exception e) {};
					Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							if(!currPos.equals(end)){
								try{
									moveChar(sol.get(0).getValue());
									if(currPos.equals(sol.get(0).getValue()))
										sol.remove(0);
									else
									{
										currPos.setY(sol.get(0).getValue().getY());
										currPos.setX(sol.get(0).getValue().getX());
									}
								}
								catch (Exception e) {
									t.interrupt();
								}
							}
							else
								snakesBoard.moveDown();						
						}
					});
				}
				
			}
		});
		
		t.start();
	}
		
		

//		new Thread(new Runnable() {
//			
//			LinkedList<State<Position>> currPath = new LinkedList<>(solution.getPath());
//			Position currentPosition = currPath.get(1).getValue();
//			Position goal = currPath.getLast().getValue();
//			
//			@Override
//			public void run() {
//				while(true) {
//					try {Thread.sleep(140); } catch (Exception e) {}
//					if(!currentPosition.equals(goal)) {
//						currentPosition = currPath.pop().getValue();
//						moveChar(currentPosition);
//						//notifyObservers(maze);
//					}
//				}
//			}
//		}).start();
	}


