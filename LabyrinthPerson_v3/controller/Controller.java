package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JFrame;

import model.Direction;
import model.World;

/**
 * Our controller listens for key events on the main window.
 */
public class Controller extends JFrame implements KeyListener {

	/** The world that is updated upon every key press. */
	private World world;
	//private List<View> views;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param world the world to be updated whenever the player should move.
	 * @param caged the {@link GraphicsProgram} we want to listen for key presses
	 *              on.
	 */
	public Controller(World world) {
		// Remember the world
		this.world = world;
		
		// Listen for key events
		addKeyListener(this);
		// Listen for mouse events.
		// Not used in the current implementation.
		//addMouseListener(this);
		
	}


	/////////////////// Key Events ////////////////////////////////

	@Override
	public void keyPressed(KeyEvent e) {
		// Check if we need to do something. Tells the world to move the player.
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			world.movePlayer(Direction.UP);
			world.moveFollower();
	
			break;

		case KeyEvent.VK_DOWN:
			world.movePlayer(Direction.DOWN);
			world.moveFollower();
			
			break;

		case KeyEvent.VK_LEFT:
			world.movePlayer(Direction.LEFT);
			world.moveFollower();
			
			
			break;

		case KeyEvent.VK_RIGHT:
			world.movePlayer(Direction.RIGHT);
			world.moveFollower();
			
			break;
			
		case KeyEvent.VK_1:
			world.reset(1);
			break;
			
		
		case KeyEvent.VK_2:
			world.reset(2);
			break;
		
		case KeyEvent.VK_3:
			world.reset(3);
			break;
			
			
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
			
		}
		
	
		
			
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
	}
	
	
}