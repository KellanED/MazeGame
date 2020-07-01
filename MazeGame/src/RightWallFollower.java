
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// RightWallFollower.java
// Made by: Kellan Delaney
// Creates a bot that solves the maze through
// following the right wall 
//***************************************************

public class RightWallFollower extends Entity implements Movement {

	private String facing = "DOWN"; // direction the Bot is facing
	private Cell rightCell; // Cell to the left of the bot
	private Cell frontCell; // Cell in front of the bot
	private int count = 0; // number of updates; for movement

	/**
	 * constructor for the Bot
	 * 
	 * @param newMaze - Maze where the bot will be
	 */
	public RightWallFollower(Maze newMaze) {
		super(newMaze);
	}

	/**
	 * randomly performs movement
	 */
	public void update() {
		if (count % Entity.getSpeed() == 0) {
			move();
		}
		if (Cell.end().equals(cellAtCurrent())) {
			endGame("Right Wall Follower");
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
		rightCell = getRight();
		frontCell = getFront();
		if (rightCell.isWall()) {
			if (frontCell.isWall()) {
				turnLeft();
			} else {
				advance();
			}
		} else {
			turnRight();
			advance();
		}
	}

	/**
	 * turns the bot to the right
	 */
	public void turnRight() {
		if (facing.equals("UP")) {
			facing = "RIGHT";
		} else if (facing.equals("RIGHT")) {
			facing = "DOWN";
		} else if (facing.equals("DOWN")) {
			facing = "LEFT";
		} else if (facing.equals("LEFT")) {
			facing = "UP";
		}

	}

	/**
	 * turns the bot to the left
	 */
	public void turnLeft() {
		if (facing.equals("UP")) {
			facing = "LEFT";
		} else if (facing.equals("RIGHT")) {
			facing = "UP";
		} else if (facing.equals("DOWN")) {
			facing = "RIGHT";
		} else if (facing.equals("LEFT")) {
			facing = "DOWN";
		}
	}

	/**
	 * moves the bot forward in the direction it is facing
	 */
	public void advance() {
		if (facing.equals("UP")) {
			decY();
		} else if (facing.equals("RIGHT")) {
			incX();
		} else if (facing.equals("DOWN")) {
			incY();
		} else if (facing.equals("LEFT")) {
			decX();
		}
	}

	/**
	 * accesses the Cell to the right of the bot, relative to the direction it's
	 * facing
	 * 
	 * @return - the right Cell
	 */
	public Cell getRight() {
		int x = getX();
		int y = getY();
		Maze maze = getMaze();
		switch (facing) {
		case "UP":
			return maze.getCell(x + 1, y);
		case "RIGHT":
			return maze.getCell(x, y + 1);
		case "DOWN":
			return maze.getCell(x - 1, y);
		case "LEFT":
			return maze.getCell(x, y - 1);
		default:
			return null;
		}
	}

	/**
	 * accesses the Cell in front of the bot, relative to the direction it's facing
	 * 
	 * @return - the front Cell
	 */
	public Cell getFront() {
		int x = getX();
		int y = getY();
		Maze maze = getMaze();
		switch (facing) {
		case "UP":
			return maze.getCell(x, y - 1);
		case "RIGHT":
			return maze.getCell(x + 1, y);
		case "DOWN":
			return maze.getCell(x, y + 1);
		case "LEFT":
			return maze.getCell(x - 1, y);
		default:
			return null;
		}
	}
}
