package view;

import model.World;

/**
 * A view that prints the current state of the world to the console upon every
package view;

import model.World;

/**
 * A view that prints the current state of the world to the console upon every
 * update.
 */
public class ConsoleView implements View {

	@Override
	public void update(World world) {
		int playerX = world.getPlayerX();
		int playerY = world.getPlayerY();
		int startX = world.getStartX();
		int startY = world.getStartY();
		int finishX = world.getFinishX();
		int finishY = world.getFinishY();
		int followerX = world.getFollowerX();
		int followerY = world.getFollowerY();
		for (int row = 0; row < world.getHeight(); row++) {
			for (int col = 0; col < world.getWidth(); col++) {
				// If the player is here, print #, otherwise print .
				
				if (row == followerY && col == followerX) {
					System.out.print("F");
					
				}
				else if (row == playerY && col == playerX) {
					System.out.print("P");
					
				}
			
				else if (row == startY && col == startX){
					System.out.print("#");
					
				}
				else if (row == finishY && col == finishX){
					System.out.print("#");
					
				}else {
					if (world.getBlock(col, row).isPassable()){
						System.out.print("_");
						}
					else{System.out.print("*");
						}
		
					}
				
			

			}

			// A newline after every row
			System.out.println();
		}

		// A newline between every update
		System.out.println();
		
		if (world.isVictoryFollower()) {
			
			System.out.println("You lose!");
		}
		
		if (world.isVictoryPlayer()) {
			
			System.out.println("You win!");
		}
	}

}
