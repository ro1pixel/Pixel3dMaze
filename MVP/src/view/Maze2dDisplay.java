package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Position;

public class Maze2dDisplay extends MazeDisplay {
	final Color black;
	final Color white;
	
	Image startImage;
	Image goalImage;
	Image characterImage;
	Image wallImage;
	
	Position charachterPosition;
	
	public Maze2dDisplay(Composite parent, int style) {
		super(parent,style);
		
		startImage = new Image(getDisplay(),"./resources/start.jpg");
		goalImage = new Image(getDisplay(),"./resources/goal.jpg");
		characterImage = new Image(getDisplay(),"./resources/character.jpg");
		wallImage = new Image(getDisplay(),"./resources/wall.jpg");
		
		white = new Color(null,255,255,255);
		black = new Color(null,0,0,0);
		setBackground(white);
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent p) {
				if(maze!=null) {
					setCharacterPosition(maze.getStartPosition());
					
					p.gc.setForeground(black);
					p.gc.setBackground(new Color(null,35,50,120));
					
					int[][][] array3d = maze.getArray3d();
					
					int width = getSize().x;
					int depth = getSize().y;
					
					int w = width / array3d[0][0].length;
					int h = depth / array3d[0].length;
					
					int lengthWidth = array3d[0][0].length;
					int lengthDepth = array3d[0].length;
					
					for(int i=0;i<lengthDepth; i++) {
						for(int j=0;j<lengthWidth;j++) {
							int pixelX = w * j;
							int pixelY = h * i;
							p.gc.drawImage(wallImage, 0, 0,wallImage.getBounds().width,wallImage.getBounds().height,pixelX,pixelY,w,h);
						}
					}
					
					if(charachterPosition.equals(maze.getStartPosition())) {
						p.gc.drawImage(startImage, 0, 0, startImage.getBounds().width,startImage.getBounds().height,maze.getStartPosition().getY(),maze.getStartPosition().getZ(),w,h);
						p.gc.setBackground(black);
					}
					else if(characterPosition.equals(maze.getGoalPosition())){
						p.gc.drawImage(goalImage, 0, 0, goalImage.getBounds().width,goalImage.getBounds().height,maze.getGoalPosition().getY(),maze.getGoalPosition().getZ(),w,h);
						p.gc.setBackground(black);
					}
					else {
						p.gc.drawImage(characterImage, 0, 0, characterImage.getBounds().width,characterImage.getBounds().height,characterPosition.getY(),characterPosition.getZ(),w,h);
					}
				
				}
				
			}
		});
	}
}
