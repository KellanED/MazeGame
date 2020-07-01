
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// Cell.java
// Made by: Kellan Delaney
// Cells which will comprise the Maze an appropriate 
// accessor and mutator methods for use in other classes
//***************************************************

import java.util.ArrayList;

public class Cell {

	private static ArrayList<Cell> Unvisited = new ArrayList<>(); // a list of all unvisited Cells
	private boolean isWall; // whether or not a certain Cell is a wall
	private int xCoord; // x coordinate of Cell
	private int yCoord; // y coordinate of Cells
	private static Cell endCell; // the final Cell of the Maze; the goal

	/**
	 * Constructs a Cell with given coordinates
	 * 
	 * @param x - x coordinate of Cell
	 * @param y - y coordinate of Cell
	 */
	public Cell(int x, int y) {
		// only every other Cell is going to be visited, so this adds those
		// cells to the unvisited list
		if (x % 2 == 1 && y % 2 == 1) {
			Unvisited.add(this);
		}
		isWall = true; // all Cells are walls by default
		xCoord = x; // sets coordinates
		yCoord = y;
	}

	/**
	 * accesses the visited status of a Cell
	 * 
	 * @return - true if the Cell has been visited
	 */
	public boolean isVisited() {
		if (Unvisited.contains(this)) {
			return false;
		}
		return true;
	}

	/**
	 * marks Cell as part of the border of the Maze
	 */
	public void setBorder() {
		isWall = true;
		Unvisited.remove(this);
	}

	/**
	 * accesses the wall status of a Cell
	 * 
	 * @return - true if the Cell is a wall
	 */
	public boolean isWall() {
		return isWall;
	}

	/**
	 * marks a Cell as visited and changes it to not a wall
	 */
	public void markVisited() {
		Unvisited.remove(this);
		isWall = false;
	}

	/**
	 * accesses the status of total unvisited cells
	 * 
	 * @return - true if there are still unvisited cells
	 */
	public static boolean areUnvisited() {
		if (Unvisited.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * accessor method for x coordinate of Cell
	 * 
	 * @return - x coordinate of Cell
	 */
	public int getX() {
		return xCoord;
	}

	/**
	 * accessor method for y coordinate of Cell
	 * 
	 * @return - y coordinate of Cell
	 */
	public int getY() {
		return yCoord;
	}

	/**
	 * marks a Cell as the end of the Maze. can only be one
	 */
	public static void setEnd(Cell cell) {
		endCell = cell;
	}

	/**
	 * accesses the ending Cell of the Maze
	 * 
	 * @return the end Cell
	 */
	public static Cell end() {
		return endCell;
	}
}
