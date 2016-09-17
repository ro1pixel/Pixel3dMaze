package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * The Class Position.
 * Defines a Position for maze.
 */
public class Position {
	
	/** Is up Position already visited. */
	private boolean upVisited = false;
	
	/** Is down Position already visited. */
	private boolean downVisited = false;
	
	/** Is right Position already visited. */
	private boolean rightVisited = false;
	
	/** Is left Position already visited. */
	private boolean leftVisited = false;
	
	/** Is above Position already visited. */
	private boolean aboveVisited = false;
	
	/** Is below Position already visited. */
	private boolean belowVisited = false;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The z. */
	private int z;
	
	/**
	 * Instantiates a new position.
	 *
	 * @param z the z
	 * @param y the y
	 * @param x the x
	 */
	public Position(int z, int y, int x) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param z the z
	 * @param y the y
	 * @param x the x
	 */
	public void setPosition(int z,int y,int x) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Sets the z.
	 *
	 * @param z the new z
	 */
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "{"+z+","+y+","+x+"}";
	}

	/**
	 * Checks if is up visited.
	 *
	 * @return true, if is up visited
	 */
	public boolean isUpVisited() {
		return upVisited;
	}

	/**
	 * Sets the up visited.
	 *
	 * @param upVisited the new up visited
	 */
	public void setUpVisited(boolean upVisited) {
		this.upVisited = upVisited;
	}

	/**
	 * Checks if is down visited.
	 *
	 * @return true, if is down visited
	 */
	public boolean isDownVisited() {
		return downVisited;
	}

	/**
	 * Sets the down visited.
	 *
	 * @param downVisited the new down visited
	 */
	public void setDownVisited(boolean downVisited) {
		this.downVisited = downVisited;
	}

	/**
	 * Checks if is right visited.
	 *
	 * @return true, if is right visited
	 */
	public boolean isRightVisited() {
		return rightVisited;
	}

	/**
	 * Sets the right visited.
	 *
	 * @param rightVisited the new right visited
	 */
	public void setRightVisited(boolean rightVisited) {
		this.rightVisited = rightVisited;
	}

	/**
	 * Checks if is left visited.
	 *
	 * @return true, if is left visited
	 */
	public boolean isLeftVisited() {
		return leftVisited;
	}

	/**
	 * Sets the left visited.
	 *
	 * @param leftVisited the new left visited
	 */
	public void setLeftVisited(boolean leftVisited) {
		this.leftVisited = leftVisited;
	}

	/**
	 * Checks if is above visited.
	 *
	 * @return true, if is above visited
	 */
	public boolean isAboveVisited() {
		return aboveVisited;
	}

	/**
	 * Sets the above visited.
	 *
	 * @param aboveVisited the new above visited
	 */
	public void setAboveVisited(boolean aboveVisited) {
		this.aboveVisited = aboveVisited;
	}

	/**
	 * Checks if is below visited.
	 *
	 * @return true, if is below visited
	 */
	public boolean isBelowVisited() {
		return belowVisited;
	}

	/**
	 * Sets the below visited.
	 *
	 * @param belowVisited the new below visited
	 */
	public void setBelowVisited(boolean belowVisited) {
		this.belowVisited = belowVisited;
	}

	/**
	 * Check neighbors.
	 * Checks what neighbors have been visited.
	 *
	 * @param maze3d the maze 3 d
	 */
	public void checkNeighbors(Maze3d maze3d) {
		int[][][] array3d = maze3d.getArray3d();
		
		if((x<maze3d.getWidth()-3 && array3d[z][y][x+2] == 0) || x>=maze3d.getWidth()-3)
			rightVisited = true;
		if((x>2 && array3d[z][y][x-2] == 0) || x<=2)
			leftVisited = true;
		if((y<maze3d.getHeight()-3 && array3d[z][y+2][x] == 0) || y>=maze3d.getHeight()-3)
			downVisited = true;
		if((y>2 && array3d[z][y-2][x] == 0) || y<=2)
			upVisited = true;
		if((z<maze3d.getFloors()-2 && array3d[z+1][y][x] == 0) || z>=maze3d.getFloors()-2)
			aboveVisited = true;
		if((z>1 && array3d[z-1][y][x] == 0) || z<=1)
			belowVisited = true;
	}

	/**
	 * All visited.
	 * Checks if all neighbors have been visited.
	 *
	 * @return true, if successful
	 */
	public boolean allVisited() {
		if(rightVisited == true && leftVisited == true && downVisited == true && upVisited == true && aboveVisited == true && belowVisited == true)
			return true;
		else
			return false;
	}

	@Override
	public boolean equals(Object obj) {
		Position position = (Position) obj;
		
		if(this.x==position.getX() && this.y==position.getY() && this.z==position.getZ())
			return true;
		else
			return false;
	}
	
	public byte[] toByte() {
		byte[] byteArray = new byte[3];
		
		//add Position
		byteArray[0] = (byte)z;
		byteArray[1] = (byte)y;
		byteArray[2] = (byte)x;
		
		return byteArray;
	}
	
	
}
