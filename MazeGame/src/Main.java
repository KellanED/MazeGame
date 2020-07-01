
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// Main.java
// Made by: Kellan Delaney and Andy Hardt
// Main application class for the maze game. Includes a start menu.
//***************************************************

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Main implements ActionListener {

	private int mazeSize = 15; // size of the maze
	private String botType = "LeftWallFollower";
	private JLabel description;
	private JLabel title;
	private String[] botStrings = { "Left Wall Follower", "Right Wall Follower", "Backtracer", "Impossible" }; // options for bot combo box
	private String[] sizeStrings = { "Small", "Medium", "Large" }; // options for size combo box
	private JComboBox<String> botList = new JComboBox<String>(botStrings);
	private JComboBox<String> sizeList = new JComboBox<String>(sizeStrings);
	private JButton startButton = new JButton("Start");
	private JButton h2pButton = new JButton("How to Play");
	private JButton sickoButton = new JButton();
	private JButton quitButton = new JButton("Quit");
	private static JFrame frame = new JFrame("Random Maze"); // creates the frame
	private JFrame startFrame = new JFrame("Maze Game Start Menu");

	/**
	 * constructor for the main game start menu   
	 */
	public Main() {

		// sets up the combo boxes and buttons
		botList.setSelectedIndex(0);
		botList.addActionListener(this);
		sizeList.setSelectedIndex(0);
		sizeList.addActionListener(this);
		startButton.addActionListener(this);
		quitButton.addActionListener(this);
		h2pButton.addActionListener(this);
		sickoButton.addActionListener(this);

		// sets up bot description
		description = new JLabel();
		description.setHorizontalAlignment(JLabel.CENTER);
		updateLabel(botStrings[botList.getSelectedIndex()]);
		description.setPreferredSize(new Dimension(350, 50));

		// sets up title
		title = new JLabel("Maze Game                 ");
		title.setHorizontalAlignment(JLabel.CENTER);

		// sets up grid layout and fills the frame with components
		GridBagConstraints gbc = new GridBagConstraints();
		startFrame.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		startFrame.add(sickoButton, gbc);
		sickoButton.setOpaque(false);
		sickoButton.setContentAreaFilled(false);
		sickoButton.setBorderPainted(false);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		startFrame.add(title, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		startFrame.add(new JLabel("Bot: "), gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		startFrame.add(botList, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		startFrame.add(description, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		startFrame.add(new JLabel("Size: "), gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		startFrame.add(sizeList, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		startFrame.add(quitButton, gbc);
		gbc.gridx = 1;
		startFrame.add(h2pButton, gbc);
		gbc.gridx = 2;
		startFrame.add(startButton, gbc);

		// finishing touches for the frame
		startFrame.setSize(430, 300);
		startFrame.setResizable(false);
		startFrame.setLocationRelativeTo(null);
		startFrame.setVisible(true);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(botList)) {
			String botName = (String) botList.getSelectedItem();
			updateLabel(botName);
			if (botName.equals("Backtracer")) {
				botType = "BackTracer";
			} else if (botName.equals("Left Wall Follower")) {
				botType = "LeftWallFollower";
			} else if (botName.equals("Right Wall Follower")) {
				botType = "RightWallFollower";
			} else if (botName.equals("Impossible")) {
				botType = "Impossible";
			} else {
				System.out.println("Main 103");
			}
		} else if (e.getSource().equals(sizeList)) {
			String size = (String) sizeList.getSelectedItem();
			if (size.equals("Small")) {
				mazeSize = 15;
			} else if (size.equals("Medium")) {
				mazeSize = 25;
			} else if (size.equals("Large")) {
				mazeSize = 35;
			} else {
				System.out.println("Main 130");
			}
		} else if (e.getSource().equals(startButton)) {
			startGame(false);
			startFrame.dispose();
		} else if (e.getSource().equals(quitButton)) {
			System.exit(0);
		} else if (e.getSource().equals(h2pButton)) {
			howToPlay();
		} else if (e.getSource().equals(sickoButton)) {
			startGame(true);
			startFrame.dispose();
		}
	}

	/**
	 * updates the description of the selected bot
	 * 
	 * @param name - new name of selected bot
	 */
	public void updateLabel(String name) {
		String text = "";
		if (name.equals("Backtracer")) {
			text = "<html><p>The Backtracer bot makes random decisions at every junction, and traces its path back to that junction if it ends up hitting a dead end.</p><html>";
		} else if (name.equals("Left Wall Follower")) {
			text = "<html><p>The Left Wall Follower bot follows the wall to its left, which depends on the direction it is currently heading in.</p><html>";
		} else if (name.equals("Right Wall Follower")) {
			text = "<html><p>The Right Wall Follower bot follows the wall to its right, which depends on the direction is is currently heading in.</p><html>";
		} else if (name.equals("Impossible")) {
			text = "<html><p>The Impossible bot makes no wrong decisions.</p><html>";
		} else {
			System.out.println("Main 158");
		}
		description.setText(text);
	}

	/**
	 * creates a frame with the game instructions
	 */
	public void howToPlay() {
		JFrame frame = new JFrame("How to Play");
		GridBagConstraints gbc = new GridBagConstraints();
		frame.setLayout(new GridBagLayout());
		JLabel title = new JLabel("Maze Game Instructions");
		title.setHorizontalAlignment(JLabel.CENTER);
		JLabel instructions = new JLabel(
				"<html><p>You are the blue square. Your goal is to race the red square bot the to end of the maze, as denoted by a green marker. You may choose from 4 different bots, "
						+ "each with their own unique behaviors. You may also choose a size for the maze. Every time you play there will be a new randomly generated maze.</p><html>");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		frame.add(new JLabel("                      "));
		gbc.gridx = 1;
		frame.add(title, gbc);
		gbc.gridx = 2;
		frame.add(new JLabel("                      "));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		frame.add(instructions, gbc);

		frame.setSize(335, 250);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// starts the game
	public void startGame(boolean sicko) {
		// The actual maze.
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame when application end
		frame.add(new MazeGameFrame(mazeSize, botType, sicko)); // adds the maze game to the frame
		frame.pack(); // sets the size relative to the size of the maze
		frame.setLocationRelativeTo(null); // opens the window in the center of the screen
		frame.setResizable(true); // makes the size of the frame non-adjustable
		frame.setVisible(true); // makes the frame visible
	}
	
	/**accesses the main menu frame
	 * @return - frame
	 */
	public static JFrame getFrame() {
		return frame;
	}
	
	//Runs the actual game.
	public static void main(String[] args) {
		new Main();
	}
	
}
