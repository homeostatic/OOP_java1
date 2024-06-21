package model;

import java.util.ArrayList;
import java.util.Random;

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
	/**the last direction the player moved */
	private Direction playerfacing;

	/** The start x position in the world. */
	private int startX = 0;
	/** The start y position in the world. */
	private int startY = 0;
	
	/** The finish x position in the world. */
	private int finishX = 9;
	/** The finish y position in the world. */
	private int finishY = 9;

	/** The follower's x position in the world. */
	private int followerX = 0;
	/** The follower's y position in the world. */
	private int followerY = 8;
	
	private int steps = 0;
	
	/** game state variables */
	private boolean victoryPlayer = false;
	private boolean victoryFollower = false;
	private int modus = 1;

	/** list used to map out the placement of wall and path blocks */
	private Integer[] pattern = {   0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
									1, 1, 0, 1, 0, 1, 1, 0, 1, 0,
									0, 0, 0, 1, 0, 1, 0, 0, 1, 0,
									0, 1, 1, 1, 0, 1, 0, 0, 1, 0,
									0, 0, 1, 0, 0, 1, 0, 1, 1, 0,
									1, 0, 0, 0, 1, 1, 0, 1, 0, 0,
									1, 1, 0, 0, 1, 1, 0, 1, 1, 0,
									1, 1, 1, 0, 1, 0, 0, 1, 0, 0,
									0, 1, 1, 0, 1, 1, 0, 1, 1, 1,
									0, 0, 0, 0, 1, 0, 0, 0, 0, 0
	  };
	  private ArrayList<Block> blocklist;
	
	
	/** this is the best way the follower can go */
	private String[] followerWay = {"","D","", "R","","R","", "R","", "U","", "U", "","U","", "U","", "U","", "R","", "U","", "U","", "U","", "U","", "R","","R","", "R","", "D","", "D","", "D","", "L","", "D", "","D","","D","","D","","D","","D","","R","","R","", "R",};

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
		
		}
		return blocklist;
		

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

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getFinishX() {
		return finishX;
	}

	public void setFinishX(int finishX) {
		this.finishX = finishX;
	}

	public int getFinishY() {
		return finishY;
	}

	public void setFinishY(int finishY) {
		this.finishY = finishY;
	}	
	

	public int getFollowerX() {
		return followerX;
	}

	public void setFollowerX(int followerX) {

			followerX = Math.max(0, followerX);
			followerX = Math.min(getWidth() - 1, followerX);
			this.followerX = followerX;
			
			updateViews();

	}

	public int getFollowerY() {
		return followerY;
	}
	
	

	public void setFollowerY(int followerY) {
		followerY = Math.max(0, followerY);
		followerY = Math.min(getWidth() - 1, followerY);
		this.followerY = followerY;
		
		updateViews();


	}

	public Direction getPlayerfacing(){
		return this.playerfacing;
	}

	/**
	 * Returns block at given coordinates
	 * 
	 * @param x x position
	 * @param y y positon
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
	
	public boolean isVictoryPlayer() {
		return victoryPlayer;
	}

	public void setVictoryPlayer(boolean victoryPlayer) {
		this.victoryPlayer = victoryPlayer;
	}

	public boolean isVictoryFollower() {
		return victoryFollower;
	}

	public void setVictoryFollower(boolean victoryFollower) {
		this.victoryFollower = victoryFollower;
	}

	public int getModus() {
		return modus;
	}

	public void setModus(int modus) {
		this.modus = modus;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	
	

	///////////////////////////////////////////////////////////////////////////
	// Player Management
	
	public String[] getFollowerWay() {
		return followerWay;
	}

	/**
	 * Moves the player along the given direction.
	 * ->checks if block is "passable" before moving
	 * 
	 * @param direction where to move.
	 */
	public void movePlayer(Direction direction) {

		this.playerfacing = direction;
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
		
		
		if (getPlayerX() == finishX && getPlayerY() == finishY) {
			
			
			victoryPlayer = true;
			updateViews();
			
		}
			steps = steps + 1;
			
	
	}
	
	
	public void moveFollower() {

		Direction direction = Direction.NONE;
		
		switch(modus) {
		
		case 1:
			Direction[] directionList = {Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
			direction = directionList[new Random().nextInt(directionList.length)];
			break;
		case 2:
		
			if (steps % 2 != 0 && steps < followerWay.length) {
				
				if (followerWay[steps] == "U") {
					direction = Direction.UP;
				}
				
				if (followerWay[steps] == "D") {
					direction = Direction.DOWN;;
				}
				
				if (followerWay[steps] == "L") {
					direction = Direction.LEFT;;
				}
				
				if (followerWay[steps] == "R") {
					direction = Direction.RIGHT;
				}
				
			}
			break;
			
		case 3:
			if (steps < followerWay.length) {
				
				if (followerWay[steps] == "") {
					
					steps = steps + 1;
				}
				
				if (followerWay[steps] == "U") {
					direction = Direction.UP;
				}
				
				if (followerWay[steps] == "D") {
					direction = Direction.DOWN;;
				}
				
				if (followerWay[steps] == "L") {
					direction = Direction.LEFT;;
				}
				
				if (followerWay[steps] == "R") {
					direction = Direction.RIGHT;
				}
				
			}
			break;
		}
		try {
			if (getBlock(getFollowerX() + direction.deltaX, getFollowerY()+direction.deltaY).isPassable()){

				// The direction tells us exactly how much we need to move along
				// every direction
				this.setFollowerX(this.getFollowerX() + direction.deltaX);
				this.setFollowerY(this.getFollowerY() + direction.deltaY);
	
					
			}
			else{ 
			;// don't
			}	
		} catch (Exception ArrayIndexOutOfBoundsException) {
			;// don't
		}
			
		
		
		if (getFollowerX() == finishX && getFollowerY() == finishY 
				|| getFollowerX() == getPlayerX() && getFollowerY() == getPlayerY() ) {
			
			
			victoryFollower = true;
			updateViews();
			
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

	public void reset(int newModus) {
		setModus(newModus);
		setPlayerX(0);
		setPlayerY(0);
		setFollowerX(0);
		setFollowerY(8);
		setSteps(0);
		victoryFollower = false;
		victoryPlayer = false;
		
		updateViews();
		
	}
	
	
	

	
}



