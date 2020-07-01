
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// Impossible.java
// Made by: Kellan Delaney
// Creates a bot that always follows the right path
// and is very difficult to beat
//***************************************************

import java.util.ArrayList;
import java.util.Stack;

public class Impossible extends Entity implements Movement {

	private int count = 0; // number of updates; for movement
	private ArrayList<String> path = new ArrayList<>(); // path to solve the Maze

	/**
	 * constructor for the Bot
	 * 
	 * @param newMaze - Maze where the Bot will be
	 */
	public Impossible(Maze newMaze) {
		super(newMaze);
		solveMaze();
	}

	/**
	 * solves the Maze for the bot
	 * 
	 * @return - a list of moves that form a path to solve the maze
	 */
	public void solveMaze() {
		int x = 1, y = 1;
		boolean solved = false;
		String previous = "";
		Stack<String> stack = new Stack<>();
		ArrayList<Integer> visited = new ArrayList<>();
		Maze maze = getMaze();

		// runs while the maze is still not solved
		while (!solved) {

			// adds the current coordinates to the visited list
			visited.add(Integer.parseInt(x + "0" + y));

			// creates and fills a list of options for movement directions
			ArrayList<String> options = new ArrayList<>();
			if (!maze.getCell(x, y - 1).isWall()) {
				if (!visited.contains(Integer.parseInt(x + "0" + (y - 1)))) {
					options.add("UP");
				}
			}
			if (!maze.getCell(x, y + 1).isWall()) {
				if (!visited.contains(Integer.parseInt(x + "0" + (y + 1)))) {
					options.add("DOWN");
				}
			}
			if (!maze.getCell(x - 1, y).isWall()) {
				if (!visited.contains(Integer.parseInt((x - 1) + "0" + y))) {
					options.add("LEFT");
				}
			}
			if (!maze.getCell(x + 1, y).isWall()) {
				if (!visited.contains(Integer.parseInt((x + 1) + "0" + y))) {
					options.add("RIGHT");
				}
			}

			// picks a random choice if there are options
			if (options.size() != 0) {
				String choice = options.get((int) (Math.random() * options.size()));
				if (choice.equals("UP")) {
					y--;
					stack.push("UP");
					previous = "UP";
				} else if (choice.equals("DOWN")) {
					y++;
					stack.push("DOWN");
					previous = "DOWN";
				} else if (choice.equals("LEFT")) {
					x--;
					stack.push("LEFT");
					previous = "LEFT";
				} else if (choice.equals("RIGHT")) {
					x++;
					stack.push("RIGHT");
					previous = "RIGHT";
				} else {
					System.out.println("Impossible 71");
				}

				if (Cell.end().equals(maze.getCell(x, y))) {
					solved = true;
				}

				// backtraces to the last intersection if there are no options
			} else {
				previous = stack.pop();
				if (previous.equals("UP")) {
					y++;
				} else if (previous.equals("DOWN")) {
					y--;
				} else if (previous.equals("LEFT")) {
					x++;
				} else if (previous.equals("RIGHT")) {
					x--;
				}
			}
		}

		// creates the path ArrayList based off the stack
		while (!stack.isEmpty()) {
			path.add(0, stack.pop());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Movement#move()
	 */
	@Override
	public void move() {
		String direction = path.remove(0);
		if (direction.equals("UP")) {
			decY();
		} else if (direction.equals("DOWN")) {
			incY();
		} else if (direction.equals("LEFT")) {
			decX();
		} else if (direction.equals("RIGHT")) {
			incX();
		} else {
			System.out.println("Impossible 126");
		}
	}

	/**
	 * performs movement at regular intervals
	 */
	public void update() {
		if (count % Entity.getSpeed() == 0) {
			move();
		}
		if (Cell.end().equals(cellAtCurrent())) {
			endGame("Impossible Bot");
		}
		count++;
	}

}
