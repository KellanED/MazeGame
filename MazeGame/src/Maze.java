
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// Maze.java
// Made by: Kellan Delaney
// Maze generator and appropriate accessor methods
// for use in other classes
//***************************************************

import java.util.ArrayList;
import java.util.Stack;

public class Maze {

	private final int SIZE; // size of the maze, must be an odd number
	private Cell[][] maze; // the actual maze, a 2D array of Cells

	/**
	 * Maze default constructor SIZE = 51
	 */
	public Maze(int size) {
		SIZE = size;
		maze = new Cell[SIZE][SIZE];
		generateMaze();
	}

	/**
	 * accesses the size of the Maze
	 * 
	 * @return - the size of the Maze
	 */
	public int getSize() {
		return SIZE;
	}

	/**
	 * accesses a Cell at given coordinates in the Maze
	 * 
	 * @param x - x coordinate of the Cell you want to access
	 * @param y - y coordinate of the Cell you want to access
	 * @return - the Cell at the given coordinates
	 */
	public Cell getCell(int x, int y) {
		return maze[x][y];
	}

	/**
	 * Generates a random maze
	 * https://en.wikipedia.org/wiki/Maze_generation_algorithm Depth-first search
	 */
	public void generateMaze() {

		Cell current; // the current Cell which the generator is looking at
		int x; // x coordinate of the current Cell
		int y; // y coordinate of the current Cell

		// creates a Maze of a given SIZE
		// *****TOP LEFT IS 0, 0*****
		// *****COORDINATES INCREASE THE FARTHER RIGHT AND DOWN*****
		// filled with Cells which are walls by default
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[row].length; col++) {
				maze[row][col] = new Cell(row, col);
			}
		}

		// creates the border of the Maze
		for (int row = 0; row < SIZE; row++) {
			maze[row][0].setBorder();
			maze[row][SIZE - 1].setBorder();
		}
		for (int col = 0; col < SIZE; col++) {
			maze[0][col].setBorder();
			maze[SIZE - 1][col].setBorder();
		}

		// ***********************************************************
		// actual maze generation
		// depth-first search
		// ***********************************************************

		// starts maze generation at upper left corner
		// marks the start as visited, removes the wall at it, and adds it to the stack
		current = maze[1][1];
		current.markVisited();
		Stack<Cell> stack = new Stack<>();
		int pathLength = 0;
		int maxLength = 0;

		// while there are unvisited Cells
		while (Cell.areUnvisited()) {

			x = current.getX();
			y = current.getY();

			// checks if neighboring Cells have been visited
			// if not, adds them to a list of options
			ArrayList<Cell> Options = new ArrayList<>();

			// right
			if (x != SIZE - 2) {
				if (!maze[x + 2][y].isVisited()) {
					Options.add(maze[x + 2][y]);
				}
			}

			// left
			if (x != 1) {
				if (!maze[x - 2][y].isVisited()) {
					Options.add(maze[x - 2][y]);
				}
			}

			// up
			if (y != SIZE - 2) {
				if (!maze[x][y + 2].isVisited()) {
					Options.add(maze[x][y + 2]);
				}
			}

			// down
			if (y != 1) {
				if (!maze[x][y - 2].isVisited()) {
					Options.add(maze[x][y - 2]);
				}
			}

			// if the current Cell has any neighbors which have not been visited
			if (Options.size() != 0) {
				// chooses randomly one of the unvisited neighbors
				Cell randomChoice = Options.get((int) (Math.random() * Options.size()));
				// push the current Cell to the stack
				stack.push(current);
				// removes wall between the current Cell and chosen Cell
				if (x != randomChoice.getX()) {
					maze[(x + randomChoice.getX()) / 2][y].markVisited();
				} else if (y != randomChoice.getY()) {
					maze[x][(y + randomChoice.getY()) / 2].markVisited();
				}
				// makes the chosen Cell the current Cell and marks it as visited
				current = randomChoice;
				current.markVisited();
				pathLength++;
			}

			// else if the stack is not empty
			else if (!stack.isEmpty()) {
				if (pathLength > maxLength) {
					maxLength = pathLength;
					Cell.setEnd(current);
				}
				// pop a Cell from the stack and make it the current Cell
				// traverses back through the stack until a Cell is reached that has
				// neighbors which have not been visited, the starts over
				current = stack.pop();
				pathLength--;
			}

		}

	}

}
