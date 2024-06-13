package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.w3c.dom.ranges.RangeException;

import view.View;

/**
 * The world is our model. It saves the bare minimum of information required to
 * accurately reflect the state of the game. Note how this does not know
 * anything about graphics.
 */
public class World {

	/** The world's width. */
	private final int width;
	/** The world's height. */
	private final int height;
	/** The player's x position in the world. */
	private int playerX = 0;
	/** The player's y position in the world. */
	private int playerY = 0;

	private Integer[] pattern = {   0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
									1, 1, 0, 1, 0, 1, 1, 0, 1, 0,
									0, 0, 0, 1, 0, 1, 0, 0, 1, 0,
									0, 1, 1, 1, 0, 1, 0, 0, 1, 0,
									0, 0, 1, 0, 0, 1, 0, 1, 1, 0,
									1, 0, 1, 0, 1, 0, 0, 1, 0, 0,
									1, 0, 0, 0, 1, 0, 1, 1, 1, 0,
									1, 1, 1, 0, 1, 0, 0, 1, 0, 0,
									0, 0, 1, 0, 1, 1, 0, 1, 1, 1,
									0, 0, 0, 0, 1, 0, 0, 0, 0, 0
	  };

	private ArrayList<Block> blocklist;

	/** Set of views registered to be notified of world updates. */
	private final ArrayList<View> views = new ArrayList<>();

	/**
	 * Creates a new world with the given size.t
	 */
	public World(int width, int height) {
		// Normally, we would check the arguments for proper values
		this.width = width;
		this.height = height;
		this.blocklist = generateWorld();

		
	}

	//////////////////////////////////////////////////////////////////////////
	// World gen
	/**
	 * function for placing blocks in the world
	 * 
	 * @return arraylist with placed blocks
	 */

	private ArrayList<Block> generateWorld(){
		// build arraylist of blocks which makeup the world.
		blocklist = new ArrayList<Block>();
		
		for (int i=0; i<width*height; i++){
			// fill world with blocks
			if (this.pattern[i] == 1){
				blocklist.add(new Wall());
				}
			if (this.pattern[i] == 0){
				blocklist.add(new Path());
			}
		// not needed??
		//updateViews();
		}
		return blocklist;
		

	} 
	/**
	 * generates a path through the maze
	 * 
	 * @return
	 */
	private Direction wallpather(){
		;
	}


	///////////////////////////////////////////////////////////////////////////
	// Getters and Setters

	/*
	 * Static method for converting an (x,y) coordinate pair into an index of an Array mapped to a width and height
	 */
	public static int xyConvert(int x, int y, int width, int height){
		int index = y*width+x;
		return index;
	}


	/**
	 * @return a copy of the pattern used to build the world
	 */
	public Integer[] wallCopy(){
		return this.pattern.clone();
	}

	/**
	 *
	 * Returns the width of the world.
	 * 
	 * @return the width of the world.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the world.
	 * 
	 * @return the height of the world.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns block at given coordinates
	 * 
	 * @return the block at a given Position
	 */
	
	public Block getBlock(int x, int y){
		if (y<this.height && x<this.width){
			int blocknumber = y*this.width+x;
			return this.blocklist.get(blocknumber);
		}
		else{
			throw new ArrayIndexOutOfBoundsException("Block is out of range");
		}
	} 

	/**
	 * Returns the player's x position.
	 * 
	 * @return the player's x position.
	 */
	public int getPlayerX() {
		return playerX;
	}

	/**
	 * Sets the player's x position.
	 * 
	 * @param playerX the player's x position.
	 */
	public void setPlayerX(int playerX) {
		playerX = Math.max(0, playerX);
		playerX = Math.min(getWidth() - 1, playerX);
		this.playerX = playerX;
		
		updateViews();
	}

	/**
	 * Returns the player's y position.
	 * 
	 * @return the player's y position.
	 */
	public int getPlayerY() {
		return playerY;
	}

	/**
	 * Sets the player's y position.
	 * 
	 * @param playerY the player's y position.
	 */
	public void setPlayerY(int playerY) {
		playerY = Math.max(0, playerY);
		playerY = Math.min(getHeight() - 1, playerY);
		this.playerY = playerY;
		
		updateViews();
	}

	///////////////////////////////////////////////////////////////////////////
	// Player Management
	
	/**
	 * Moves the player along the given direction.
	 * ->checks if block is "passable" before moving
	 * 
	 * @param direction where to move.
	 */
	public void movePlayer(Direction direction) {

		// check if we're trying to move into a wall
		try {
			if (getBlock(getPlayerX() + direction.deltaX, getPlayerY()+direction.deltaY).isPassable()){

				// The direction tells us exactly how much we need to move along
				// every direction
	
				setPlayerX(getPlayerX() + direction.deltaX);
				setPlayerY(getPlayerY() + direction.deltaY);
				
			}
			else{ 
			;// don't
			}	
		} catch (Exception ArrayIndexOutOfBoundsException) {
			;// don't
		}
	}


	///////////////////////////////////////////////////////////////////////////
	// View Management

	/**
	 * Adds the given view of the world and updates it once. Once registered through
	 * this method, the view will receive updates whenever the world changes.
	 * 
	 * @param view the view to be registered.
	 */
	public void registerView(View view) {
		views.add(view);
		view.update(this);
	}

	/**
	 * Updates all views by calling their {@link View#update(World)} methods.
	 */
	private void updateViews() {
		for (int i = 0; i < views.size(); i++) {
			views.get(i).update(this);
		}
	}

}
