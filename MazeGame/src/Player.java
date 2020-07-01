
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// Player.java
// Made by: Kellan Delaney
// Class for the actual Player of the game
//***************************************************

import java.awt.event.KeyEvent;

public class Player extends Entity implements Movement {

	private String direction = ""; // direction the Player will move

	/**
	 * Player constructor
	 * 
	 * @param newMaze - Maze in which the player will reside
	 */
	public Player(Maze newMaze) {
		super(newMaze);
	}

	/**
	 * updates the location of the Player according to inputted directions
	 */
	public void update() {
		move();
		// closes the game if the Player reaches the end
		if (Cell.end().equals(cellAtCurrent())) {
			endGame("Player");
		}
		// resets the direction
		direction = "";
	}

	/**
	 * supplies the direction to move in based on keyboard input
	 * 
	 * @param e - KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			direction = "UP";
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			direction = "LEFT";
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			direction = "DOWN";
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			direction = "RIGHT";
		}
	}

	/**
	 * tests if an Entity can move in a certain direction
	 * 
	 * @param direction - "UP", "DOWN", "LEFT", "RIGHT"
	 * @return true if the Entity can move in that direction
	 */
	public boolean canMove(String direction) {
		int x = getX();
		int y = getY();
		Maze maze = getMaze();
		if (direction.equals("UP")) { // up
			if (!maze.getCell(x, y - 1).isWall()) {
				return true;
			} else {
				return false;
			}
		}
		if (direction.equals("DOWN")) { // down
			if (!maze.getCell(x, y + 1).isWall()) {
				return true;
			} else {
				return false;
			}
		}
		if (direction.equals("LEFT")) { // left
			if (!maze.getCell(x - 1, y).isWall()) {
				return true;
			} else {
				return false;
			}
		}
		if (direction.equals("RIGHT")) { // right
			if (!maze.getCell(x + 1, y).isWall()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Movement#move()
	 */
	@Override
	public void move() {
		if (canMove(direction)) {
			if (direction.equals("UP")) { // up
				decY();
			} else if (direction.equals("DOWN")) { // down
				incY();
			} else if (direction.equals("LEFT")) { // left
				decX();
			} else if (direction.equals("RIGHT")) { // right
				incX();
			}
		}
	}
}
