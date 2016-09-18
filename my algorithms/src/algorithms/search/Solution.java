package algorithms.search;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import algorithms.mazeGenerators.Position;

/**
 * The Class Solution.
 * Defines the solution that will be returned to the user.
 * 
 * @param <T> the state being checked
 */
public class Solution<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2422118270064724521L;
	
	/** The path of the solution. */
	private LinkedList<State<T>> path = new LinkedList<>();
	
	public Solution() {
	}
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public LinkedList<State<T>> getPath() {
		return path;
	}
	
	/**
	 * Sets the path.
	 * 
	 * @param path the new path
	 */
	public void setPath(LinkedList<State<T>> path) {
		this.path = path;
	}
	
	/**
	 * Size of linked list.
	 * Returns the path size
	 *
	 * @return the size of the path list
	 */
	public int pathSize() {
		return path.size();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : path) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
	
	public byte[] toByteArray() {
		ArrayList<Byte> byteArray = new ArrayList<>();
		
		for(int i=0;i<path.size();i++) {
			Position currpos = (Position) path.get(i).getValue();
			byte[] currByte = currpos.toByte();
			for(Byte b : currByte) {
				byteArray.add(b);
			}
		}
		
		byte[] finalArray = new byte[byteArray.size()];
		for(int i=0;i<byteArray.size();i++) {
			finalArray[i] = byteArray.get(i);
		}
		
		return finalArray;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
}
