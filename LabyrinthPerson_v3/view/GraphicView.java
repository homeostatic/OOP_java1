package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.World;

/**
 * A graphical view of the world.
 */
public class GraphicView extends JPanel implements View {

	/** The view's width. */
	private final int WIDTH;
	/** The view's height. */
	private final int HEIGHT;

	private Dimension fieldDimension;

	private Integer[] walls;
	private int worldHeight ;
	private int worldWidth; 
	private World world;

	public GraphicView(int width, int height, Dimension fieldDimension, World world) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.fieldDimension = fieldDimension;
		this.bg = new Rectangle(WIDTH, HEIGHT);

		// link the the world being drawn
		this.world = world;
		this.worldHeight = world.getHeight();
		this.worldWidth = world.getWidth();
		//this.walls = world.wallCopy();

	}

	/** The background rectangle. */
	private final Rectangle bg;
	/** The *dot* we're moving. */
	private final Rectangle player = new Rectangle(1, 1);

	/**
	 * Creates a new instance.
	 */
	@Override
	public void paint(Graphics g) {
		// Paint background
		g.setColor(Color.BLUE);
		g.fillRect(bg.x, bg.y, bg.width, bg.height);

		// Paint walls
		g.setColor(Color.GREEN);
		for (int y = 0; y<this.worldHeight; y++){
			for (int x = 0; x<this.worldWidth; x++){
				if (!this.world.getBlock(x,y).isPassable()){
					g.fillRect(x*fieldDimension.width, y*fieldDimension.height, fieldDimension.width, fieldDimension.height);
				}
				else if(this.world.getBlock(x, y).getSpecial().equals("Start")){
					// extra code for painting the start field
					g.setColor(Color.YELLOW);
					g.fillRoundRect(x*fieldDimension.width, y*fieldDimension.height, fieldDimension.width, fieldDimension.height,20,20);
					g.setColor(Color.GREEN);
				}
			}
		}
		// Paint player
		g.setColor(Color.BLACK);
		g.fillOval(player.x, player.y, player.width, player.height);
		
		
	}
	

	@Override
	public void update(World world) {

		// Update players size and location
		player.setSize(fieldDimension);
		player.setLocation(
			(int) (world.getPlayerX() * fieldDimension.width),
			(int) (world.getPlayerY() * fieldDimension.height)
		);

		;


		repaint();
	}

}
