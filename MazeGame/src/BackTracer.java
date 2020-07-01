
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// BackTracer.java
// Made by: Kellan Delaney
// Creates a bot that solves the maze through
// backtracing methods
//***************************************************

import java.util.ArrayList;
import java.util.Stack;

public class BackTracer extends Entity implements Movement {

	private ArrayList<Cell> visited = new ArrayList<>(); // list of visited Cells
	private Stack<String> stack = new Stack<>(); // stack for back tracing when the bot gets to a dead end
	private int count = 0; // number of updates; for movement

	/**
	 * constructor for a Backtracer
	 * 
	 * @param newMaze - Maze where the bot will be
	 */
	public BackTracer(Maze newMaze) {
		super(newMaze);
	}

	/**
	 * performs movement at regular intervals
	 */
	public void update() {
		if (count % Entity.getSpeed() == 0) {
			move();
		}
		if (Cell.end().equals(cellAtCurrent())) {
			endGame("Backtracer");
		}
		count++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Movement#move()
	 */
	@Override
	public void move() {

		// gets current x and y coordinate and maze of bot
		int x = getX();
		int y = getY();
		Maze maze = getMaze();

		// marks current Cell as visited
		visited.add(cellAtCurrent());

		// checks if neighboring Cells have been visited and/or are walls
		// if not, adds them to a list of options
		ArrayList<String> Options = new ArrayList<>();

		// up
		if (!maze.getCell(x, y - 1).isWall()) {
			if (!visited.contains(maze.getCell(x, y - 1))) {
				Options.add("UP");
			}
		}
		// down
		if (!maze.getCell(x, y + 1).isWall()) {
			if (!visited.contains(maze.getCell(x, y + 1))) {
				Options.add("DOWN");
			}
		}
		// left
		if (!maze.getCell(x - 1, y).isWall()) {
			if (!visited.contains(maze.getCell(x - 1, y))) {
				Options.add("LEFT");
			}
		}
		// right
		if (!maze.getCell(x + 1, y).isWall()) {
			if (!visited.contains(maze.getCell(x + 1, y))) {
				Options.add("RIGHT");
			}
		}

		// if there are any neighbors who have not been visited
		if (Options.size() != 0) {
			// chooses randomly one of the options for movement
			String randomChoice = Options.get((int) (Math.random() * Options.size()));
			// pushes the reverse of that direction to the stack and changes the coordinates
			// according to the direction
			if (randomChoice.equals("UP")) {
				decY();
				stack.push("DOWN");
			} else if (randomChoice.equals("DOWN")) {
				incY();
				stack.push("UP");
			} else if (randomChoice.equals("LEFT")) {
				decX();
				stack.push("RIGHT");
			} else if (randomChoice.equals("RIGHT")) {
				incX();
				stack.push("LEFT");
			} else {
				System.out.println("ERROR");
			}
		}

		// else if the stack is not empty
		else if (!stack.isEmpty()) {
			String backTrace = stack.pop();
			// works backwards through the stack to reach a point where it can move away
			// again
			if (backTrace.equals("UP")) {
				decY();
			} else if (backTrace.equals("DOWN")) {
				incY();
			} else if (backTrace.equals("LEFT")) {
				decX();
			} else if (backTrace.equals("RIGHT")) {
				incX();
			} else {
				System.out.println("ERROR");
			}
		}

	}

}
