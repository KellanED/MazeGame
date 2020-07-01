
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// MazeGameFrame.java
// Made by: Kellan Delaney
// Creates the Frame in which the game will be played
// and draws all components, including the actual maze,
// the player, and the bot
//***************************************************

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JPanel;

public class MazeGameFrame extends JPanel  {

	private static final long serialVersionUID = -2021797320845950680L;
	private Maze maze; // Maze for the game
	private Player player; // Player for the game
	private Entity bot; // the Bot for the game
	private String botType = ""; // the type of bot
	private boolean isSicko; // indicates whether it is sicko mode
	// colors to be used in the game
	private Color wallColor = Color.BLACK; // color for the wall
	private Color hallColor = Color.WHITE; // color for the empty spaces
	private Color playerColor = Color.BLUE; // color for the player
	private Color botColor = Color.RED; // color for the bot
	private int count = 0; // count of updates

	/**
	 * constructor for a Frame
	 */
	public MazeGameFrame(int size, String botType, boolean sicko) {

		isSicko = sicko;
		setFocusable(true);

		this.botType = botType;
		maze = new Maze(size);
		player = new Player(maze);
		if (botType.equals("BackTracer")) {
			bot = new BackTracer(maze);
		} else if (botType.equals("LeftWallFollower")) {
			bot = new LeftWallFollower(maze);
		} else if (botType.equals("RightWallFollower")) {
			bot = new RightWallFollower(maze);
		} else if (botType.equals("Impossible")) {
			bot = new Impossible(maze);
		} else {
			System.out.println("MazeGameFrame 48");
		}

		addKeyListener(new KeyListener(player));

		startTimer();
		
	}
	
	/**
	 * starts the timer which consistenly updates the game frame
	 */
	public void startTimer() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				updateGame();
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 10);
	}

	/**
	 * sets the size of the component according to the size of the maze
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(maze.getSize() * 15, maze.getSize() * 15);
	}

	/**
	 * paints the Component on the Frame
	 * 
	 * @param g - Graphics
	 */
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g; // changes to Graphics2D because it works better for this
		if (count % 10 == 0) {
			if (isSicko) {
				if (wallColor.equals(Color.BLACK)) {
					wallColor = Color.WHITE;
					hallColor = Color.BLACK;
					playerColor = Color.RED;
					botColor = Color.BLUE;
				} else {
					wallColor = Color.BLACK;
					hallColor = Color.WHITE;
					playerColor = Color.BLUE;
					botColor = Color.RED;
				}
			}
		}

		// fills the frame with the Maze based on walls
		for (int row = 0; row < maze.getSize(); row++) {
			for (int col = 0; col < maze.getSize(); col++) {
				if (maze.getCell(row, col).isWall()) {
					g2d.setColor(wallColor);
				} else if (Cell.end().equals(maze.getCell(row, col))) {
					g2d.setColor(Color.GREEN);
				} else {
					g2d.setColor(hallColor);
				}
				g2d.fillRect(15 * row, 15 * col, 15, 15);
			}
		}

		// draws the Player
		g2d.setColor(playerColor);
		g2d.fillRect(player.getX() * 15 + 1, player.getY() * 15 + 1, 13, 13);

		// draws the Bot
		g2d.setColor(botColor);
		g2d.fillRect(bot.getX() * 15 + 1, bot.getY() * 15 + 1, 13, 13);

		count++;
	}

	/**
	 * updates the entity locations and redraws the game
	 */
	public void updateGame() {
		if (!Cell.end().equals(player.cellAtCurrent()) && !Cell.end().equals(bot.cellAtCurrent())) {
			player.update(); // updates player movement
			// updates bot movement
			if (botType.equals("BackTracer")) {
				((BackTracer) bot).update();
			} else if (botType.equals("LeftWallFollower")) {
				((LeftWallFollower) bot).update();
			} else if (botType.equals("RightWallFollower")) {
				((RightWallFollower) bot).update();
			} else if (botType.equals("Impossible")) {
				((Impossible) bot).update();
			} else {
				System.out.println("MazeGameFrame 128");
			}
			repaint(); // redraws the game in the frame
		}
	}
}
