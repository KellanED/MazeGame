
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// Entity.java
// Made by: Kellan Delaney
// Superclass for any "living" thing in the game including
// Players and Bots
//***************************************************

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Entity implements ActionListener {

	private Maze maze; // Maze
	private int x; // x coordinate of Entity
	private int y; // y coordinate of Entity
	private JButton quitButton = new JButton("Quit"); // button to quit the game after playing
	private JFrame end = new JFrame("End Game"); // end game frame
	private static final int SPEED = 13;

	/**
	 * Entity constructor
	 * 
	 * @param newMaze - Maze in which the entity will reside
	 */
	public Entity(Maze newMaze) {
		maze = newMaze;
		x = 1;
		y = 1;
	}

	/**
	 * accesses the Cell where the Entity currently is
	 * 
	 * @return the current Cell
	 */
	public Cell cellAtCurrent() {
		return maze.getCell(x, y);
	}

	/**
	 * accessor method for x coordinate of Entity
	 * 
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * accessor method for y coordinate of Entity
	 * 
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * increases x coordinate of Entity by 1
	 */
	public void incX() {
		x++;
	}

	/**
	 * increases y coordinate of Entity by 1
	 */
	public void incY() {
		y++;
	}

	/**
	 * decreases x coordinate of Entity by 1
	 */
	public void decX() {
		x--;
	}

	/**
	 * decreases y coordinate of Entity by 1
	 */
	public void decY() {
		y--;
	}

	/**
	 * ends the game
	 * 
	 * @param type - type of entity which won the game
	 */
	public void endGame(String type) {
		JFrame mainFrame = Main.getFrame();
		end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		end.setSize(400, 400);
		end.setResizable(false);
		end.setLocationRelativeTo(null);
		end.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel gameEnd = new JLabel();
		if (type.equals("Player")) {
			gameEnd.setText("You beat the Bot!");
			gameEnd.setForeground(Color.BLUE);
		} else {
			gameEnd.setText("You lost to the " + type + "!");
			gameEnd.setForeground(Color.RED);
		}
		gameEnd.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		gameEnd.setHorizontalAlignment(JLabel.CENTER);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		end.add(gameEnd, gbc);
		gbc.gridy = 2;
		end.add(quitButton, gbc);
		quitButton.addActionListener(this);
		end.setVisible(true);
		mainFrame.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(quitButton)) {
			System.exit(0);
		}
	}

	/**
	 * accesses static speed variable for entities
	 * 
	 * @return - speed
	 */
	public static int getSpeed() {
		return SPEED;
	}

	/**
	 * accesses the maze in which the Entity is
	 * 
	 * @return - maze
	 */
	public Maze getMaze() {
		return maze;
	}
}
