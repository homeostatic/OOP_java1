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
	/** The start x position in the world. */
	/**the last direction the player moved */
	private Direction playerfacing;
	private int startX = 0;
	/** The start y position in the world. */
	private int startY = 0;
	
	/** The start x position in the world. */
	private int finishX = 9;
	/** The start y position in the world. */
	private int finishY = 9;
	/** The followers X position in the world. */
	private int followerX = 0;
	/** The followers Y position in the world. */
	private int followerY = 8;
	/** Steps counts the steps of the player */
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
	
	
	/** this is the best way the follower can go */
	private String[] followerWay = {"","D","", "R","","R","", "R","", "U","", "U", "","U","", "U","", "U","", "R","", "U","", "U","", "U","", "U","", "R","","R","", "R","", "D","", "D","", "D","", "L","", "D", "","D","","D","","D","","D","","D","","R","","R","", "R",};

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
	// World generation and reset
	/**
	 * function for placing blocks in the world
	 * 
	 * @return arraylist with placed blocks
	 */

	private ArrayList<Block> generateWorld(){
		
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
	
	/**
	 * Resets the world so it can be played from the beginning again. 
	 * @param newModus
	 */
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


	///////////////////////////////////////////////////////////////////////////
	// Getters and Setters

	/**
	 * Static method for converting an (x,y) coordinate pair into an index of an Array mapped to a width and height
	 */
	public static int xyConvert(int x, int y, int width, int height){
		int index = y*width+x;
		return index;
	}


	/**
	 * Returns a copy of the pattern used to build the world.
	 * @return copy of pattern.
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
	 * Returns the x_coordinate of the start.
	 * 
	 * @return the startX
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Sets the x_coordinate of the start to startX.
	 * 
	 * @param startX the startX to set
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}

	/**
	 * Returns the y_coordinate of the start.
	 * 
	 * @return the startY
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Sets the y_coordinate of the start to startY.
	 * 
	 * @param startY the startY to set
	 */
	public void setStartY(int startY) {
		this.startY = startY;
	}

	/**
	 * Returns the x_coordinate of the finish.
	 * 
	 * @return the finishX
	 */
	public int getFinishX() {
		return finishX;
	}

	/**
	 * Sets the x_coordinate of the finish to finishX.
	 * 
	 * @param finishX the finishX to set
	 */
	public void setFinishX(int finishX) {
		this.finishX = finishX;
	}

	/**
	 * Returns the y_coordinate of the finish.
	 * 
	 * @return the finishY
	 */
	public int getFinishY() {
		return finishY;
	}

	/**
	 * Sets the y_coordinate of the finish to finishY.
	 * 
	 * @param finishY, the finishY to set
	 */
	public void setFinishY(int finishY) {
		this.finishY = finishY;
	}

	
	/**
	 * Returns the x-coordinate of followers position.
	 * 
	 * @return followerX
	 */
	public int getFollowerX() {
		return followerX;
	}
	
	/**
	 * Sets the x-coordinate of the follower to followerX. Updates view after that.
	 * 
	 * @param followerX, followerX to set.
	 */

	public void setFollowerX(int followerX) {

			followerX = Math.max(0, followerX);
			followerX = Math.min(getWidth() - 1, followerX);
			this.followerX = followerX;
			
			updateViews();

	}
	
	/**
	 * Returns the y-coordinate of followers position.
	 * 
	 * @return followerY
	 */

	public int getFollowerY() {
		return followerY;
	}
	
	/**
	 * Sets the y-coordinate of the follower to followerY. Updates view after that.
	 * 
	 * @param followerY, followerY to set.
	 */

	public void setFollowerY(int followerY) {
		followerY = Math.max(0, followerY);
		followerY = Math.min(getWidth() - 1, followerY);
		this.followerY = followerY;
		
		updateViews();


	}

	/**
	 * Returns VictoryPlayer.
	 * @return the victoryPlayer
	 */
	public boolean isVictoryPlayer() {
		return victoryPlayer;
	}

	/**
	 * Sets the victoryPlayer to victoryPlayer.
	 * @param victoryPlayer the victoryPlayer to set
	 */
	public void setVictoryPlayer(boolean victoryPlayer) {
		this.victoryPlayer = victoryPlayer;
	}

	/**
	 * Returns VictoryFollower.
	 * 
	 * @return the victoryFollower
	 */
	public boolean isVictoryFollower() {
		return victoryFollower;
	}

	/**
	 * Sets the victoryFollower to victoryFollower.
	 * 
	 * @param victoryFollower the victoryFollower to set
	 */
	public void setVictoryFollower(boolean victoryFollower) {
		this.victoryFollower = victoryFollower;
	}

	/**
	 * Returns the counted steps of the player.
	 * @return the steps
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * Sets steps of the player to steps.
	 * 
	 * @param steps, the steps to set
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}

	/**
	 * Returns the modus of the game.
	 * 
	 * @return the modus
	 */
	public int getModus() {
		return modus;
	}

	/**
	 * Sets the modus of the game to modus.
	 * 
	 * @param modus, the modus to set
	 */
	public void setModus(int modus) {
		this.modus = modus;
	}

	/**
	 * Returns block at given coordinates
	 * 
	 * @param x position, position
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
	 * @param playerX, the player's x position.
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
	 * Gets the way of the follower
	 * @return the followerWay
	 */
	public String[] getFollowerWay() {
		return followerWay;
	}

	/**
	 * Returns the Playerfacing.
	 * 
	 * @return the playerfacing
	 */
	public Direction getPlayerfacing() {
		return playerfacing;
	}

	/**
	 * Sets the playerfacing to playerfcing.
	 * 
	 * @param playerfacing the playerfacing to set
	 */
	public void setPlayerfacing(Direction playerfacing) {
		this.playerfacing = playerfacing;
	}

	/**
	 * Moves the player along the given direction.
	 * ->checks if block is "passable" before moving
	 * Changes victoryPlayer to true if Player reaches the finish.
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
		
		// checks if player reaches the finish
		if (getPlayerX() == finishX && getPlayerY() == finishY) {
			
			
			victoryPlayer = true;
			updateViews();
			
		}
			steps = steps + 1;
			
	
	}
	
	
	/**
	 * Moves the follower depending of the mode.
	 * Changes victoryFollower to true if Follower enters the finish or catches the player.
	 */
	public void moveFollower() {

		Direction direction = Direction.NONE;
		
		switch(modus) {
		//easy mode: follower moves randomly
		case 1:
			Direction[] directionList = {Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
			direction = directionList[new Random().nextInt(directionList.length)];
			break;
			
		// medium mode: follower moves every second playerstep a predetermint path.
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
		
		// hard mode: follower moves every playerstep.
			
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
			
		
		// checks if follower reaches the finish or catches the player
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

	


	

	
	

	
}



