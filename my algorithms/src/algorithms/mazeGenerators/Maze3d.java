package algorithms.mazeGenerators;

import java.util.Random;

/**
 * The Class Maze3d.
 * Defines a 3D maze.
 */
public class Maze3d {
	
	/** The array3d. */
	int array3d[][][] = null;
	
	/** The start position. */
	Position startPosition = null;
	
	/** The current position. */
	Position currentPosition = null;
	
	/** The goal position. */
	Position goalPosition = null;
	
	/** The height. */
	int height;
	
	/** The width. */
	int width;
	
	/** The floors. */
	int floors;
	
	/**
	 * Instantiates a new maze3d.
	 *
	 * @param floors the floors
	 * @param height the height
	 * @param width the width
	 */
	public Maze3d(int floors, int height, int width) {
		array3d = new int[floors][height][width];
		this.width=width;
		this.height=height;
		this.floors = floors;
	}
	
	/**
	 * Sets the start position.
	 *
	 * @param z the z
	 * @param y the y
	 * @param x the x
	 */
	void setStartPosition(int z, int y,int x) {
		startPosition = new Position(z, y, x);
	}
	
	/**
	 * Sets the goal position.
	 *
	 * @param z the z
	 * @param y the y
	 * @param x the x
	 */
	void setGoalPosition(int z, int y,int x) {
		goalPosition = new Position(z, y, x);
		array3d[z][y][x]=0;
	}
		
/**
 * Gets the start position.
 *
 * @return the start position
 */
public Position getStartPosition() {
		return startPosition;
	}
	
	/**
	 * Gets the floors.
	 *
	 * @return the floors
	 */
	public int getFloors() {
		return floors;
	}

	/**
	 * Sets the floors.
	 *
	 * @param floors the new floors
	 */
	public void setFloors(int floors) {
		this.floors = floors;
	}

	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * Gets the goal position.
	 *
	 * @return the goal position
	 */
	public Position getGoalPosition() {
		return goalPosition;
	}
	
	/**
	 * Gets the array3d.
	 *
	 * @return the array3d
	 */
	public int[][][] getArray3d() {
		return array3d;
	}	

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the possible moves.
	 *
	 * @param p the Position
	 * @return the possible moves
	 */
	public String[] getPossibleMoves(Position p) {
		String[] possibleMoves = new String[6];
		
		int i=0;
		if(p.getX()<width-1 && array3d[p.getZ()][p.getY()][p.getX()+1] == 0)
			possibleMoves[i++] = "Right";
		if(p.getX()>0 && array3d[p.getZ()][p.getY()][p.getX()-1] == 0)
			possibleMoves[i++] = "Left";
		if(p.getY()<height-1 && array3d[p.getZ()][p.getY()+1][p.getX()] == 0)
			possibleMoves[i++] = "Down";
		if(p.getY()>0 && array3d[p.getZ()][p.getY()-1][p.getX()] == 0)
			possibleMoves[i++] = "Up";
		if(p.getZ()<floors-1 && array3d[p.getZ()+1][p.getY()][p.getX()] == 0)
			possibleMoves[i++] = "Above";
		if(p.getZ()>0 && array3d[p.getZ()-1][p.getY()][p.getX()] == 0)
			possibleMoves[i++] = "Below";
		
		return possibleMoves;
	}
	
	/**
	 * Gets the cross section by X.
	 *
	 * @param pos the Position
	 * @return the cross section by X
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByX(int pos) throws IndexOutOfBoundsException {
		if(pos<=width && pos>=0) {
		int arrayX[][] = new int[height][width-pos+1];
		
			for(int i=0;i<height;i++) {
				for(int j=0, k=pos-1;j<width-pos+1;j++,k++) {
					arrayX[i][j] = array3d[1][i][k];
				}
			}
			return arrayX;
		}
		else
			throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Gets the cross section by Y.
	 *
	 * @param pos the Position
	 * @return the cross section by Y
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByY(int pos) throws IndexOutOfBoundsException {
		if(pos<=height && pos>=0) {
			int arrayY[][] = new int[height-pos+1][width];
			
			for(int i=0, k=pos-1;i<height-pos+1;i++, k++) {
				for(int j=0;j<width;j++) {
					arrayY[i][j] = array3d[1][k][j];
				}
			}
			
			return arrayY;
		}
		else
			throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Gets the cross section by Z.
	 *
	 * @param pos the Position
	 * @return the cross section by Z
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByZ(int pos) throws IndexOutOfBoundsException {
		if(pos<floors-1 && pos>0) {
			int arrayZ[][] = new int[height][width];
			
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					arrayZ[i][j] = array3d[pos][i][j];
				}
			}
			
			return arrayZ;
		}
		else
			throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Randomize even rows.
	 * For test only, not in use.
	 *
	 * @param maze3d the maze3d
	 */
	public void RandomizeEvenRows(Maze3d maze3d) {
		Random random = new Random();
		int[][][] array3d = maze3d.getArray3d();
		int height=maze3d.getHeight();
		int width=maze3d.getWidth();
		
		if(height % 2 == 0) {
			for(int i=0;i<floors;i++)
				for(int j=0;j<width; j++)
					array3d[i][height-1][j] = random.nextInt(2);  
		}
		
		if(width % 2 == 0) {
			for(int i=0;i<floors;i++)
				for(int j=0;j<height; j++)
					array3d[i][j][width-1] = random.nextInt(2);  
		}
	}
	
}