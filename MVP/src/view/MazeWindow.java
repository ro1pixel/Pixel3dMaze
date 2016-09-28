package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.internal.win32.INITCOMMONCONTROLSEX;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Controller;

/**
 * The Class MazeWindow.
 */
public class MazeWindow extends BasicWindow {
	
	/** The maze name. */
	String mazeName;
	
	/** The maze display. */
	Maze2dDisplay mazeDisplay;
	
	/** The maze. */
	Maze3d maze;
	
	/** The generate maze button. */
	Button generateMazeButton;
	
	/** The solve maze button. */
	Button solveMazeButton;
	
	/** The get hint button. */
	Button getHintButton;
	
	/** The reset game button. */
	Button resetGameButton;
	
	/** The menu bar. */
	Menu menuBar;
	
	/** The file menu. */
	Menu fileMenu;
	
	/** The help menu. */
	Menu helpMenu;
	
	/** The cascade file menu. */
	MenuItem cascadeFileMenu;
	
	/** The cascade help menu. */
	MenuItem cascadeHelpMenu;
	
	/** The new game menu item. */
	MenuItem newGameMenuItem;
	
	/** The load maze menu item. */
	MenuItem loadMazeMenuItem;
	
	/** The save maze menu item. */
	MenuItem saveMazeMenuItem;
	
	/** The properties menu item. */
	MenuItem propertiesMenuItem;
	
	/** The about menu item. */
	MenuItem aboutMenuItem;
	
	/** The exit menu item. */
	MenuItem exitMenuItem;
	
	/**
	 * Instantiates a new maze window.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public MazeWindow(int width, int height) {
		super(width,height);
	}

	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
	@Override
	public void initWidgets() {
		initMenu();
		
		GridLayout gridLayout = new GridLayout(2, false);
		shell.setLayout(gridLayout);
		shell.setText("The Amazing Maze Game!");
		
		generateMazeButton = new Button(shell, SWT.PUSH);
		generateMazeButton.setText("Generate Maze");
		generateMazeButton.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		
		mazeDisplay = new Maze2dDisplay(shell,SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,4));
		
		solveMazeButton = new Button(shell, SWT.PUSH);
		solveMazeButton.setText("Solve Maze");
		solveMazeButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 1, 1));
		
		getHintButton = new Button(shell, SWT.PUSH);
		getHintButton.setText("I Need A Hint!");
		getHintButton.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		
		resetGameButton = new Button(shell, SWT.PUSH);
		resetGameButton.setText("Reset Game");
		resetGameButton.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		
	}

	/**
	 * Inits the menu.
	 */
	private void initMenu() {
		menuBar = new Menu(shell,SWT.BAR);
		cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("File");
		
		fileMenu = new Menu(shell,SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);
		
		newGameMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		newGameMenuItem.setText("New Game");
		
		loadMazeMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		loadMazeMenuItem.setText("Load Maze");
		
		saveMazeMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		saveMazeMenuItem.setText("Save Maze");
		
		propertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		propertiesMenuItem.setText("Properties...");
		
		exitMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		exitMenuItem.setText("Exit");
		
		cascadeHelpMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeHelpMenu.setText("Help");
		
		helpMenu = new Menu(shell,SWT.DROP_DOWN);
		cascadeHelpMenu.setMenu(helpMenu);
		
		aboutMenuItem = new MenuItem(helpMenu, SWT.PUSH);
		aboutMenuItem.setText("About");
		
		shell.setMenuBar(menuBar);
	}
	
	/**
	 * Generate key listener.
	 *
	 * @param listener the listener
	 */
	public void generateKeyListener(KeyAdapter listener) {
		mazeDisplay.addKeyListener(listener);
	}
	
	/**
	 * Generate maze selection listener.
	 *
	 * @param listener the listener
	 */
	public void generateMazeSelectionListener(SelectionListener listener) {
		generateMazeButton.addSelectionListener(listener);
		newGameMenuItem.addSelectionListener(listener);
		mazeDisplay.setMaze(maze);
	}
	
	/**
	 * Solve selection listener.
	 *
	 * @param listener the listener
	 */
	public void solveSelectionListener(SelectionListener listener) {
		solveMazeButton.addSelectionListener(listener);
	}
	
	/**
	 * Gets the hint selection listener.
	 *
	 * @param listener the listener
	 * @return the hint selection listener
	 */
	public void getHintSelectionListener(SelectionListener listener) {
		getHintButton.addSelectionListener(listener);
	}
	
	/**
	 * Reset game selection listener.
	 *
	 * @param listener the listener
	 */
	public void resetGameSelectionListener(SelectionListener listener) {
		resetGameButton.addSelectionListener(listener);
	}
	
	/**
	 * Load maze selection listener.
	 *
	 * @param listener the listener
	 */
	public void loadMazeSelectionListener(SelectionListener listener) {
		loadMazeMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Save maze selection listener.
	 *
	 * @param listener the listener
	 */
	public void saveMazeSelectionListener(SelectionListener listener) {
		saveMazeMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Properties selection listener.
	 *
	 * @param listener the listener
	 */
	public void propertiesSelectionListener(SelectionListener listener) {
		propertiesMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * About selection listener.
	 *
	 * @param listener the listener
	 */
	public void aboutSelectionListener(SelectionListener listener) {
		aboutMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Exit selection listener.
	 *
	 * @param listener the listener
	 */
	public void exitSelectionListener(SelectionListener listener) {
		exitMenuItem.addSelectionListener(listener);
	}
	
	/**
	 * Zoom in out screen listener.
	 *
	 * @param listener the listener
	 */
	public void zoomInOutScreenListener(MouseWheelListener listener){
		shell.addMouseWheelListener(listener);
	}
	
	/**
	 * Exit.
	 */
	public void exit() {
		shell.dispose();
	}
	
	/**
	 * Redraw.
	 */
	public void redraw() {
		shell.redraw();
	}
	
	/**
	 * Sets the maze.
	 *
	 * @param maze the new maze
	 */
	public void setMaze(Maze3d maze) {
		mazeDisplay.setMaze(maze);
		mazeDisplay.redraw();
	}

	/**
	 * Gets the maze display.
	 *
	 * @return the maze display
	 */
	public Maze2dDisplay getMazeDisplay() {
		return mazeDisplay;
	}
	
	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze3d getMaze() {
		return mazeDisplay.getMaze();
	}
	
	/**
	 * Function that performs zoom in/out with ctrl + mouse wheel.
	 *
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
	


}
