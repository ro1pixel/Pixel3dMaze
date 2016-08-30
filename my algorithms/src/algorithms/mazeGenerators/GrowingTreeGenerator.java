package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.Random;

/**
 * The Class GrowingTreeGenerator.
 * The main maze generator
 */
public class GrowingTreeGenerator extends GeneralMaze3dGenerator {

	/**
	 * Instantiates a new growing tree generator.
	 *
	 * @param chooser the chooser type
	 */
	public GrowingTreeGenerator(PositionChooser chooser) {
		this.chooser = chooser;
	}
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int floors, int height, int width) {	
		floors=floors+2;
		Random random = new Random();
		Maze3d maze3d = new Maze3d(floors, height, width);
		int[][][] array3d = maze3d.getArray3d();
		cells = new LinkedList<>();
		
		initializeMaze(maze3d);
		
		//choose random Position and set to 0
		int zAxis = random.nextInt((floors/2))+1;
		int xAxis = random.nextInt((width/2)-3)*2+1;
		int yAxis = random.nextInt((height/2)-3)*2+1;
		
//		//Choose odd zAxis
//		int zAxis = random.nextInt(height-2)+1;
//		//Choose odd xAxis
//		int xAxis = random.nextInt(width-1);
//		while (xAxis % 2 == 0)
//			xAxis = random.nextInt(width-1);
//		//Choose odd yAxis
//		int yAxis = random.nextInt(height-1);
//		while (yAxis % 2 == 0)
//			yAxis = random.nextInt(height-1);
//		//Set start to 0
		array3d[zAxis][yAxis][xAxis] = 0;
		
		//set start position to the random Position
		maze3d.setStartPosition(zAxis, yAxis, xAxis);
		
		//add start position to list
        cells.add(maze3d.getStartPosition());

        //generates a random path
        while (!cells.isEmpty()){
            Position cell = chooser.choose(cells);
            cell.checkNeighbors(maze3d);
            yAxis = cell.getY();
            xAxis = cell.getX();
            zAxis = cell.getZ();
            
            if(!cell.allVisited()) {
            	while(true) {
            		int r = random.nextInt(6);
		        	if(r==0 && (!cell.isUpVisited())) {
		        		cell.setUpVisited(true);
		            	array3d[zAxis][yAxis-1][xAxis] = 0;
		            	array3d[zAxis][yAxis-2][xAxis] = 0;
		            	cells.add(new Position(zAxis, yAxis-2, xAxis));
		            	break;
		        	}
		        	else if(r==1 && (!cell.isDownVisited())) {
		        		cell.setDownVisited(true);
		            	array3d[zAxis][yAxis+1][xAxis] = 0;
		            	array3d[zAxis][yAxis+2][xAxis] = 0;
		            	cells.add(new Position(zAxis, yAxis+2, xAxis));
		            	break;
		            }
		        	else if(r==2 && (!cell.isRightVisited())) {
		        		cell.setRightVisited(true);
		            	array3d[zAxis][yAxis][xAxis+1] = 0;
		            	array3d[zAxis][yAxis][xAxis+2] = 0;
		            	cells.add(new Position(zAxis, yAxis, xAxis+2));
		            	break;
		            }
		        	else if(r==3 && (!cell.isLeftVisited())) {
		        		cell.setLeftVisited(true);
		            	array3d[zAxis][yAxis][xAxis-1] = 0;
		            	array3d[zAxis][yAxis][xAxis-2] = 0;
		            	cells.add(new Position(zAxis, yAxis, xAxis-2));
		            	break;
		            }
		        	else if(r==4 && (!cell.isBelowVisited())) {
		        		cell.setBelowVisited(true);
		            	array3d[zAxis-1][yAxis][xAxis] = 0;
		            	cells.add(new Position(zAxis-1, yAxis, xAxis));
		            	break;
		            } 
		        	else if(r==5 && (!cell.isAboveVisited())) {
		        		cell.setAboveVisited(true);
		            	array3d[zAxis+1][yAxis][xAxis] = 0;
		            	cells.add(new Position(zAxis+1, yAxis, xAxis));
		            	break;
		            } 
            	}
            }

        	else {
            	cells.remove(cell);
            }
         }
        
        //Randomize goalPosition
        while(true) {
    		zAxis = random.nextInt(floors-2)+1;
    		xAxis = random.nextInt(width);
    		yAxis = random.nextInt(height);
    		
    		if(array3d[zAxis][yAxis][xAxis] == 0 
    				&& (xAxis!=maze3d.getStartPosition().getX() 
    				&& yAxis!=maze3d.getStartPosition().getY())
    				&& zAxis!=maze3d.getStartPosition().getZ()) {
    			maze3d.setGoalPosition(zAxis,yAxis,xAxis);
    			break;
    		}
        }
        
        //check if Start or Goal position is on bottom/top and mark exit with 0
        if(maze3d.getStartPosition().getZ() == 1) {
        	array3d[0][maze3d.getStartPosition().getY()][maze3d.getStartPosition().getX()] = 0;
        }
        if(maze3d.getStartPosition().getZ() == floors-2) {
        	array3d[floors-2][maze3d.getStartPosition().getY()][maze3d.getStartPosition().getX()] = 0;
        }
        if(maze3d.getGoalPosition().getZ() == 1) {
        	array3d[0][maze3d.getGoalPosition().getY()][maze3d.getGoalPosition().getX()] = 0;
        }
        if(maze3d.getGoalPosition().getZ() == floors-2) {
        	array3d[floors-1][maze3d.getGoalPosition().getY()][maze3d.getGoalPosition().getX()] = 0;
        }
        
        maze3d.RandomizeEvenRows(maze3d);
        
        //for debug only
      //  array3d[maze3d.getStartPosition().getZ()][maze3d.getStartPosition().getY()][maze3d.getStartPosition().getX()] = 2;
      // array3d[maze3d.getGoalPosition().getZ()][maze3d.getGoalPosition().getY()][maze3d.getGoalPosition().getX()] = 9;
        
		return maze3d;
	}

}
