
//***************************************************
// Group: Kellan Delaney, Andy Hardt, Aidan Giles
// MazeGame
// KeyListener.java
// Made by: Kellan Delaney
// Performs actions based on keyboard events. Only 
// necessary because Java does not support multiple
// inheritance
//***************************************************

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	private Player player; // Player whose keyboard inputs matter

	/**
	 * constructor for the KeyListener
	 * 
	 * @param p - Player whose keyboard inputs matter
	 */
	public KeyListener(Player p) {
		player = p;
	}

	/**
	 * performs actions based on a keyboard event
	 */
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

}
