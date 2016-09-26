package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

public class Maze2dDisplay extends MazeDisplay {	
	Position start;
	Position goal;
	
	Image startImage;
	Image goalImage;
	Image characterImage;
	Image wallImage;
	int[][] array2d;
	
	Position charachterPosition;
	
	public Maze2dDisplay(Composite parent, int style) {
		super(parent,style);
		
		startImage = new Image(getDisplay(),"./resources/start.jpg");
		goalImage = new Image(getDisplay(),"./resources/goal.jpg");
		characterImage = new Image(getDisplay(),"./resources/character.jpg");
		wallImage = new Image(getDisplay(),"./resources/wall.jpg");
				
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
				   setCharacterPosition(maze.getStartPosition());
				   setStart(maze.getStartPosition());
				   setGoal(maze.getGoalPosition());
				   e.gc.drawImage(startImage, 0, 0, startImage.getBounds().width,startImage.getBounds().height, start.getY()*w, start.getZ()*h,w,h);
				   e.gc.drawImage(goalImage, 0, 0, goalImage.getBounds().width,goalImage.getBounds().height,goal.getY()*w,goal.getZ()*h,w,h);
				}
			}
		});
	}
	
	public void printCharacter(PaintEvent e,Position characterP)
	{
		int width=getSize().x;
		int height=getSize().y;

	   int w=width/array2d[0].length;
	   int h=height/array2d.length;
		e.gc.drawImage(characterImage, 0, 0, goalImage.getBounds().width,goalImage.getBounds().height,characterP.getY()*w,characterP.getZ()*h,w,h);
	}
	
	public void setMazeData(int[][] array2d) {
		this.array2d = array2d;
		try
		{
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePageUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePageDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveStart() {
		// TODO Auto-generated method stub
		
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
