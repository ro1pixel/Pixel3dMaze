package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import algorithms.mazeGenerators.Position;

public class Maze2dDisplay extends MazeDisplay {	
	Position start;
	Position goal;
	boolean resetGame;
	
	Image startImage;
	Image goalImage;
	Image characterImage;
	Image wallImage;
	Image winImage;
	int[][] array2d;
		
	public Maze2dDisplay(Composite parent, int style) {
		super(parent,style);
		
		startImage = new Image(getDisplay(),"./resources/start.jpg");
		goalImage = new Image(getDisplay(),"./resources/goal.jpg");
		characterImage = new Image(getDisplay(),"./resources/character.jpg");
		wallImage = new Image(getDisplay(),"./resources/wall.jpg");
		winImage = new Image(getDisplay(),"./resources/win.jpg");
				
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_YELLOW));
				
				if(array2d!=null){
					int width=getSize().x;
					int height=getSize().y;

				   int w=width/array2d[0].length;
				   int h=height/array2d.length;

				   for(int i=0;i<array2d.length;i++){
				      for(int j=0;j<array2d[i].length;j++){
				          int x=j*w;
				          int y=i*h;
				          if(array2d[i][j]!=0)
				        	  e.gc.drawImage(wallImage, 0, 0, wallImage.getBounds().width, wallImage.getBounds().height, x, y, w, h);
				      }
				   }
				   setStart(maze.getStartPosition());
				   setGoal(maze.getGoalPosition());
				   e.gc.drawImage(startImage, 0, 0, startImage.getBounds().width,startImage.getBounds().height, start.getX()*w, start.getY()*h,w,h);
				   
				   if(currentPosition.getZ() == goal.getZ())
					   e.gc.drawImage(goalImage, 0, 0, goalImage.getBounds().width,goalImage.getBounds().height,goal.getX()*w,goal.getY()*h,w,h);
				   
				   //TODO fix winning
					if(currentPosition == goal) {
						   e.gc.drawImage(winImage, 0, 0, winImage.getBounds().width,winImage.getBounds().height,0,0,width,height);
						   characterImage = winImage;
						}
					if(resetGame) {
						resetGame = false;
						currentPosition = start;
						redraw();
					}
				   printCharacter(e, currentPosition);

				}
			}
		});
	}
	
	public void printCharacter(PaintEvent e,Position charPos) {
		int width=getSize().x;
		int height=getSize().y;

	   int w=width/array2d[0].length;
	   int h=height/array2d.length;
	   e.gc.drawImage(characterImage, 0, 0, goalImage.getBounds().width, goalImage.getBounds().width, charPos.getX()*w, charPos.getY()*h, w, h);
	}
	
	public void setMazeData(int[][] array2d) {
		this.array2d = array2d;
		try {
			getDisplay().syncExec(new Runnable() {					

				@Override
				public void run() {	
					redraw();
				}
			});					
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}

	@Override
	public void moveUp() {
		if(currentPosition.getY() > 0) {
			if(array3d[currentPosition.getZ()][currentPosition.getY() - 1][currentPosition.getX()] == 0) {
				currentPosition.setY(currentPosition.getY() - 1);
				redraw();		
			}
		}
	}

	@Override
	public void moveDown() {
		if(currentPosition.getY() < array3d[0].length-1) {
			if(array3d[currentPosition.getZ()][currentPosition.getY() + 1][currentPosition.getX()] == 0) {
				currentPosition.setY(currentPosition.getY() + 1);
				redraw();
			}
		}	
	}

	@Override
	public void moveLeft() {
		if(currentPosition.getX() > 0) {
			if(array3d[currentPosition.getZ()][currentPosition.getY()][currentPosition.getX() - 1] == 0) {
				currentPosition.setX(currentPosition.getX() - 1);
				redraw();
			}
		}
		
	}

	@Override
	public void moveRight() {
		if(currentPosition.getX() < array3d[0][0].length-1) {
			if(array3d[currentPosition.getZ()][currentPosition.getY()][currentPosition.getX() + 1] == 0) {
				currentPosition.setX(currentPosition.getX() + 1);
				redraw();
			}
		}		
	}

	@Override
	public void moveFloorUp() {
		if(currentPosition.getZ() < array3d.length-1) {
			if(array3d[currentPosition.getZ() + 1][currentPosition.getY()][currentPosition.getX()] == 0) {
				currentPosition.setZ(currentPosition.getZ() + 1);
				setMazeData(array3d[currentPosition.getZ()]);
				redraw();
			}
		}		
	}

	@Override
	public void moveFloorDown() {
		if(currentPosition.getZ() > 0) {
			if(array3d[currentPosition.getZ() - 1][currentPosition.getY()][currentPosition.getX()] == 0) {
				currentPosition.setZ(currentPosition.getZ() - 1);
				setMazeData(array3d[currentPosition.getZ()]);
				redraw();
			}
		}			
	}

	@Override
	public void moveStart() {
//		currentPosition.setX(start.getX());
//		currentPosition.setY(start.getY());
//		currentPosition.setZ(start.getZ());
		resetGame = true;
		redraw();

	}

	@Override
	public void move(Position p) {
		// TODO Auto-generated method stub
		
	}

	public Position getStart() {
		return start;
	}

	public void setStart(Position start) {
		this.start = start;
	}

	public Position getGoal() {
		return goal;
	}

	public void setGoal(Position goal) {
		this.goal = goal;
	}	
	
}
