package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The Class Maze2dDisplay.
 */
public class Maze2dDisplay extends MazeDisplay {
	
	/** The start. */
	Position start;
	
	/** The goal. */
	Position goal;
	
	/** The solve. */
	Thread solve;
	
	/** The winner. */
	boolean winner;

	/** The start image. */
	Image startImage;
	
	/** The goal image. */
	Image goalImage;
	
	/** The character image. */
	Image characterImage;
	
	/** The wall image. */
	Image wallImage;
	
	/** The win image. */
	Image winImage;
	
	/** The array 2 d. */
	int[][] array2d;

	/**
	 * Instantiates a new maze 2 d display.
	 *
	 * @param parent the parent
	 * @param style the style
	 */
	public Maze2dDisplay(Composite parent, int style) {
		super(parent, style);

		startImage = new Image(getDisplay(), "./resources/start.jpg");
		goalImage = new Image(getDisplay(), "./resources/goal.jpg");
		characterImage = new Image(getDisplay(), "./resources/character.jpg");
		wallImage = new Image(getDisplay(), "./resources/wall.jpg");
		winImage = new Image(getDisplay(), "./resources/win.jpg");
		winner = false;

		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_YELLOW));

				if (array2d != null) {
					int width = getSize().x;
					int height = getSize().y;

					int w = width / array2d[0].length;
					int h = height / array2d.length;

					for (int i = 0; i < array2d.length; i++) {
						for (int j = 0; j < array2d[i].length; j++) {
							int x = j * w;
							int y = i * h;
							if (array2d[i][j] != 0)
								e.gc.drawImage(wallImage, 0, 0, wallImage.getBounds().width,
										wallImage.getBounds().height, x, y, w, h);
						}
					}
					setStart(maze.getStartPosition());
					setGoal(maze.getGoalPosition());
					
					if (currentPosition.getZ() == start.getZ()) {
					e.gc.drawImage(startImage, 0, 0, startImage.getBounds().width, startImage.getBounds().height,
							start.getX() * w, start.getY() * h, w, h);
					}

					if (currentPosition.getZ() == goal.getZ())
						e.gc.drawImage(goalImage, 0, 0, goalImage.getBounds().width, goalImage.getBounds().height,
								goal.getX() * w, goal.getY() * h, w, h);

					if (currentPosition.equals(goal)) {
						e.gc.drawImage(winImage, 0, 0, winImage.getBounds().width, winImage.getBounds().height, 0, 0,
								width, height);
						characterImage.dispose();
						winner = true;
					}
					
					printCharacter(e, currentPosition);

				}
			}
		});
	}

	/**
	 * Prints the character.
	 *
	 * @param e the e
	 * @param charPos the char pos
	 */
	public void printCharacter(PaintEvent e, Position charPos) {
		int width = getSize().x;
		int height = getSize().y;

		int w = width / array2d[0].length;
		int h = height / array2d.length;
		
		if(!winner) {
		e.gc.drawImage(characterImage, 0, 0, goalImage.getBounds().width, goalImage.getBounds().width,
				charPos.getX() * w, charPos.getY() * h, w, h);
		}
	}

	/**
	 * Sets the maze data.
	 *
	 * @param array2d the new maze data
	 */
	public void setMazeData(int[][] array2d) {
		this.array2d = array2d;
		try {
			getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					redraw();
				}
			});
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#moveUp()
	 */
	@Override
	public void moveUp() {
		if (currentPosition.getY() > 0) {
			if (array3d[currentPosition.getZ()][currentPosition.getY() - 1][currentPosition.getX()] == 0) {
				currentPosition.setY(currentPosition.getY() - 1);
				redraw();
			}
		}
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#moveDown()
	 */
	@Override
	public void moveDown() {
		if (currentPosition.getY() < array3d[0].length - 1) {
			if (array3d[currentPosition.getZ()][currentPosition.getY() + 1][currentPosition.getX()] == 0) {
				currentPosition.setY(currentPosition.getY() + 1);
				redraw();
			}
		}
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#moveLeft()
	 */
	@Override
	public void moveLeft() {
		if (currentPosition.getX() > 0) {
			if (array3d[currentPosition.getZ()][currentPosition.getY()][currentPosition.getX() - 1] == 0) {
				currentPosition.setX(currentPosition.getX() - 1);
				redraw();
			}
		}

	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#moveRight()
	 */
	@Override
	public void moveRight() {
		if (currentPosition.getX() < array3d[0][0].length - 1) {
			if (array3d[currentPosition.getZ()][currentPosition.getY()][currentPosition.getX() + 1] == 0) {
				currentPosition.setX(currentPosition.getX() + 1);
				redraw();
			}
		}
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#moveFloorUp()
	 */
	@Override
	public void moveFloorUp() {
		if (currentPosition.getZ() < array3d.length - 1) {
			if (array3d[currentPosition.getZ() + 1][currentPosition.getY()][currentPosition.getX()] == 0) {
				currentPosition.setZ(currentPosition.getZ() + 1);
				setMazeData(array3d[currentPosition.getZ()]);
				redraw();
			}
		}
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#moveFloorDown()
	 */
	@Override
	public void moveFloorDown() {
		if (currentPosition.getZ() > 0) {
			if (array3d[currentPosition.getZ() - 1][currentPosition.getY()][currentPosition.getX()] == 0) {
				currentPosition.setZ(currentPosition.getZ() - 1);
				setMazeData(array3d[currentPosition.getZ()]);
				redraw();
			}
		}
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#moveStart()
	 */
	@Override
	public void moveStart() {
		move(start);
	}

	/* (non-Javadoc)
	 * @see view.MazeDisplay#move(algorithms.mazeGenerators.Position)
	 */
	@Override
	public void move(Position p) {
		currentPosition = new Position(p);
		redraw();
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Position getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(Position start) {
		this.start = start;
	}

	/**
	 * Gets the goal.
	 *
	 * @return the goal
	 */
	public Position getGoal() {
		return goal;
	}

	/**
	 * Sets the goal.
	 *
	 * @param goal the new goal
	 */
	public void setGoal(Position goal) {
		this.goal = goal;
	}
	
	/**
	 * Solve maze.
	 *
	 * @param solution the solution
	 */
	public void solveMaze(Solution<Position> solution) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {Thread.sleep(500); } catch (Exception e) {}
					Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							if(!currentPosition.equals(goal)) {
								currentPosition = solution.getPath().pop().getValue();
								move(currentPosition);
								setMazeData(array3d[currentPosition.getZ()]);	
							}
//							for(int i=0;i<solution.getPath().size();i++) {
//								move(solution.getPath().get(i).getValue());								
//							}							
						}
					});
				}
			}
		}).start();
	}
}
