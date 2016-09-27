package view;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.MyModel;
import presenter.Controller;

public class GUI extends Observable implements View, Observer {
	
	MazeWindow mazeWindow;
	String mazeName;
	Maze3d maze;
	
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
				setChanged();
				notifyObservers("solve " + mazeName);
				setChanged();
				notifyObservers("display_solution " + mazeName);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.getHintSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.resetGameSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeWindow.mazeDisplay.moveStart();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.loadMazeSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String fileName = mazeWindow.displayFileDialog(SWT.OPEN, "Load Maze", new String[] { "*.maz" },"C:\\");
				if(fileName != null) {
					setChanged();
					notifyObservers("load_maze "+mazeName+ " "+fileName);
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.saveMazeSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String fileName = mazeWindow.displayFileDialog(SWT.SAVE, "Save maze", new String[] { "*.maz" }, "C:\\");
				if(fileName != null) {
					setChanged();
					notifyObservers("save_maze "+mazeName+" "+fileName);
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
				mazeWindow.displayInfoMessage("Edit Properties", "the Properties was edited succesfully");
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
					notifyObservers("load_properties");
					mazeWindow.displayInfoMessage("Load Properties", "The properties was loaded succesfully");
				}				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.exportPropertiesSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String folderName = mazeWindow.displayDirectoryDialog(SWT.OPEN, "select folder", "c:\\");
				if(folderName != null) {
					setChanged();
					notifyObservers("save_properties");
					//mazeWindow.displayInfoMessage("Save Properties", "The properties was saved succesfully");
				}				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		mazeWindow.aboutSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeWindow.displayInfoMessage("About Maze3D", "Version: 1.0.0 \nDeveloped by Roy Shavit");				
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

	@Override
	public void start() {
		mazeWindow.run();
	}

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMaze(Maze3d maze) {
		if(maze!=null) {
			setChanged();
			notifyObservers("display_cross_section " + mazeName + " " + "Z " +  maze.getStartPosition().getZ());
			mazeWindow.getMazeDisplay().setMaze(maze);
			//mazeWindow.displayInfoMessage("Maze", "Maze created");
		}
	}

	@Override
	public void displayCrossSection(Maze3d maze, int z, int y, int x) {
		
	}
	
	@Override
	public void displayCrossSection(int[][] maze2d) {
		this.mazeWindow.getMazeDisplay().setMazeData(maze2d);
	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		
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
	public void update(Observable o, Object arg) {
		if (o == mazeWindow) {
			setChanged();
			notifyObservers(arg);
		}
	}

	public MazeWindow getMazeWindow() {
		return mazeWindow;
	}

	public String getMazeName() {
		return mazeName;
	}

	public Maze3d getMaze() {
		return maze;
	}

}
