package view;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import model.MyModel;
import presenter.Controller;

/**
 * The Class GUI.
 */
public class GUI extends Observable implements View, Observer {
	
	/** The maze window. */
	MazeWindow mazeWindow;
	
	/** The maze name. */
	String mazeName;
	
	/** The maze. */
	Maze3d maze;
	
	/**
	 * Instantiates a new gui.
	 */
	public GUI() {
		mazeWindow = new MazeWindow(500, 500);
		
		mazeWindow.generateMazeSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateMazeWindow gmw = new GenerateMazeWindow(200, 180);
				gmw.run();
				mazeName = gmw.getName();
				setChanged();
				notifyObservers("generate_maze " +mazeName + " "+ gmw.getFloors()+ " " + gmw.getHeight()+ " "+ gmw.getWidth());
				setChanged();
				notifyObservers("display " + mazeName);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.solveSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeWindow.getMaze() != null) {
					boolean sure = mazeWindow.displayQuestion("Give Up", "Are you sure you want to give up?!?!!? :(");
					if(sure) {
						setChanged();
						notifyObservers("solve " + mazeName + " BFS");
						setChanged();
						notifyObservers("display_solution " + mazeName);
					}
				}
				else {
					mazeWindow.displayErrorMessage("Error", "There is no maze to solve!");
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.getHintSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if(mazeWindow.getMaze() != null) {
					if(mazeWindow.getMazeDisplay().getCurrentPosition().getZ()>mazeWindow.getMazeDisplay().getGoal().getZ())
						mazeWindow.displayInfoMessage("Hint", "The goal is BELOW you! (pageDown)");
					else if (mazeWindow.getMazeDisplay().getCurrentPosition().getZ()<mazeWindow.getMazeDisplay().getGoal().getZ())
						mazeWindow.displayInfoMessage("Hint", "The goal is ABOVE you! (pageUp)");
					else
						mazeWindow.displayInfoMessage("Hint", "The goal is ON THIS FLOOR!");
				}
				else {
					mazeWindow.displayErrorMessage("Error", "You need to generate maze!");
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		mazeWindow.resetGameSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				boolean response= mazeWindow.displayQuestion("Reset Game", "Reset Game?");
                if (response)
                       mazeWindow.getMazeDisplay().moveStart();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.loadMazeSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String fileName = mazeWindow.displayFileDialog(SWT.OPEN, "Load Maze", new String[] { "*.maz" },"C:\\");
				if(fileName != null) {
					mazeName=fileName;
					setChanged();
					notifyObservers("load_maze "+mazeName+ " "+fileName);
					setChanged();
					notifyObservers("display "+mazeName);
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.saveMazeSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(mazeWindow.getMaze() != null) {
					String fileName = mazeWindow.displayFileDialog(SWT.SAVE, "Save maze", new String[] { "*.maz" }, "C:\\");
					if(fileName != null) {
						setChanged();
						notifyObservers("save_maze "+mazeName+" "+fileName);
					}
				}
				else {
					mazeWindow.displayErrorMessage("Error", "No maze to save!");
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.propertiesSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				PropertiesWindow pw = new PropertiesWindow(300,200);			
				pw.run();
				setChanged();
				notifyObservers("edit_properties " +pw.getGenerateMaze()+ " "+pw.getSolutionAlgorithm()+ " " +
						pw.getMaxThreads()+" "+pw.getViewStyle());
				//mazeWindow.displayInfoMessage("Edit Properties", "the Properties was edited succesfully");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.importPropertiesSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String fileName = mazeWindow.displayFileDialog(SWT.SAVE, "Save properties", new String[] { "*.xml" }, "C:\\");
				if(fileName != null) {
					setChanged();
					notifyObservers("load_properties "+fileName);
					mazeWindow.displayInfoMessage("Load Properties", "The properties was loaded succesfully");
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		mazeWindow.aboutSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeWindow.displayInfoMessage("About", "Developed by Roy Shavit\nVersion: 1.3.173");				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.exitSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				boolean response= mazeWindow.displayQuestion("Exit", "Are you sure you want to exit?");
				if (response){
					setChanged();
					notifyObservers("exit");
				}
				mazeWindow.exit();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.zoomInOutScreenListener(new MouseWheelListener() {

			@Override
			public void mouseScrolled(MouseEvent g) {
				if((g.stateMask & SWT.CONTROL) == SWT.CONTROL) {
					mazeWindow.performZoom(g.count);
					}
				}
			});
		
		mazeWindow.generateKeyListener(new KeyAdapter() {
			 public void keyPressed(KeyEvent e) {
						switch(e.keyCode) {
							case SWT.ARROW_LEFT:
								getMazeWindow().getMazeDisplay().moveLeft();
								break;
							case SWT.ARROW_RIGHT:
								getMazeWindow().getMazeDisplay().moveRight();
								break;
							case SWT.ARROW_UP:
								getMazeWindow().getMazeDisplay().moveUp();
								break;
							case SWT.ARROW_DOWN:
								getMazeWindow().getMazeDisplay().moveDown();
								break;
							case SWT.PAGE_UP:
								getMazeWindow().getMazeDisplay().moveFloorUp();
								break;
							case SWT.PAGE_DOWN:
								getMazeWindow().getMazeDisplay().moveFloorDown();
								break;
						}
					
			 }
		
		 });
			
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		mazeWindow.run();
	}

	/* (non-Javadoc)
	 * @see view.View#notifyMazeIsReady(java.lang.String)
	 */
	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see view.View#displayMaze(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void displayMaze(Maze3d maze) {
		if(maze!=null) {
			setChanged();
			notifyObservers("display_cross_section " + mazeName + " " + "Z " +  maze.getStartPosition().getZ());
			mazeWindow.getMazeDisplay().setMaze(maze);
			//mazeWindow.displayInfoMessage("Maze", "Maze created");
		}
	}

	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(algorithms.mazeGenerators.Maze3d, int, int, int)
	 */
	@Override
	public void displayCrossSection(Maze3d maze, int z, int y, int x) {
		
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(int[][])
	 */
	@Override
	public void displayCrossSection(int[][] maze2d) {
		this.mazeWindow.getMazeDisplay().setMazeData(maze2d);
	}

	/* (non-Javadoc)
	 * @see view.View#displaySolution(algorithms.search.Solution)
	 */
	@Override
	public void displaySolution(Solution<Position> solution) {
		mazeWindow.mazeDisplay.solveMaze(solution);
	}

	/* (non-Javadoc)
	 * @see view.View#notifySolutionIsReady(java.lang.String)
	 */
	@Override
	public void notifySolutionIsReady(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see view.View#displayFiles(java.io.File[])
	 */
	@Override
	public void displayFiles(File[] files) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see view.View#setController(presenter.Controller)
	 */
	@Override
	public void setController(Controller controller) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see view.View#printToScreen(java.lang.String)
	 */
	@Override
	public void printToScreen(String string) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o == mazeWindow) {
			setChanged();
			notifyObservers(arg);
		}
	}

	/**
	 * Gets the maze window.
	 *
	 * @return the maze window
	 */
	public MazeWindow getMazeWindow() {
		return mazeWindow;
	}

	/**
	 * Gets the maze name.
	 *
	 * @return the maze name
	 */
	public String getMazeName() {
		return mazeName;
	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze3d getMaze() {
		return maze;
	}

}
