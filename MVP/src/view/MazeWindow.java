package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
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

public class MazeWindow extends BasicWindow {
	
	String mazeName;
	Maze2dDisplay mazeDisplay;
	Maze3d maze;
	Button generateMazeButton;
	Button solveMazeButton;
	Button getHintButton;
	Button resetGameButton;
	Menu menuBar;
	Menu fileMenu;
	Menu helpMenu;
	MenuItem cascadeFileMenu;
	MenuItem cascadeHelpMenu;
	MenuItem newGameMenuItem;
	MenuItem loadMazeMenuItem;
	MenuItem saveMazeMenuItem;
	MenuItem propertiesMenuItem;
	MenuItem importPropertiesMenuItem;
	MenuItem exportPropertiesMenuItem;
	MenuItem aboutMenuItem;
	MenuItem exitMenuItem;
	
	public MazeWindow(int width, int height) {
		super(width,height);
	}

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
		
		importPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		importPropertiesMenuItem.setText("Import Properties");
		
		exportPropertiesMenuItem = new MenuItem(fileMenu, SWT.PUSH);
		exportPropertiesMenuItem.setText("Export Properties");
		
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
	
	public void generateKeyListener(KeyAdapter listener) {
		mazeDisplay.addKeyListener(listener);
	}
	
	public void generateMazeSelectionListener(SelectionListener listener) {
		generateMazeButton.addSelectionListener(listener);
		newGameMenuItem.addSelectionListener(listener);
		mazeDisplay.setMaze(maze);
	}
	
	public void solveSelectionListener(SelectionListener listener) {
		solveMazeButton.addSelectionListener(listener);
	}
	
	public void getHintSelectionListener(SelectionListener listener) {
		getHintButton.addSelectionListener(listener);
	}
	
	public void resetGameSelectionListener(SelectionListener listener) {
		resetGameButton.addSelectionListener(listener);
	}
	
	public void loadMazeSelectionListener(SelectionListener listener) {
		loadMazeMenuItem.addSelectionListener(listener);
	}
	
	public void saveMazeSelectionListener(SelectionListener listener) {
		saveMazeMenuItem.addSelectionListener(listener);
	}
	
	public void propertiesSelectionListener(SelectionListener listener) {
		propertiesMenuItem.addSelectionListener(listener);
	}
	
	public void importPropertiesSelectionListener(SelectionListener listener) {
		importPropertiesMenuItem.addSelectionListener(listener);
	}
	
	public void exportPropertiesSelectionListener(SelectionListener listener) {
		exportPropertiesMenuItem.addSelectionListener(listener);
	}
	
	public void aboutSelectionListener(SelectionListener listener) {
		aboutMenuItem.addSelectionListener(listener);
	}
	
	public void exitSelectionListener(SelectionListener listener) {
		exitMenuItem.addSelectionListener(listener);
	}
	
	public void exit() {
		shell.dispose();
	}
	
	public void redraw() {
		shell.redraw();
	}
	
	public void setMaze(Maze3d maze) {
		mazeDisplay.setMaze(maze);
		mazeDisplay.redraw();
	}

	public Maze2dDisplay getMazeDisplay() {
		return mazeDisplay;
	}
	


}
