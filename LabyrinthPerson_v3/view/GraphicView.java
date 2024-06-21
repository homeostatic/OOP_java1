package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Direction;
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
	private boolean victoryPlayer;
	private boolean victoryFollower;
	private Direction playerfacing;
	private int animationclock = 0;

	// splash screens for end-game
	private BufferedImage win_screen;
	private BufferedImage lose_screen;
	private BufferedImage bg_img;
	private BufferedImage jelly;
	private BufferedImage block;
	private ArrayList<BufferedImage> fishBufferedImages;


	public GraphicView(int width, int height, Dimension fieldDimension, World world) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.fieldDimension = fieldDimension;
		this.bg = new Rectangle(WIDTH, HEIGHT);

		// find walls
		this.worldHeight = world.getHeight();
		this.worldWidth = world.getWidth();
		this.walls = world.wallCopy();
		this.playerfacing = world.getPlayerfacing();
		this.victoryFollower = world.isVictoryPlayer();
		this.victoryFollower = world.isVictoryFollower();


		try
            {
				// open and buffer the splash screens and sprites
                win_screen = ImageIO.read(new File("view\\resources\\win.png"));
				lose_screen = ImageIO.read(new File("view\\resources\\lose.png"));
				bg_img = ImageIO.read(new File("view\\resources\\bg.png"));
				jelly = ImageIO.read(new File("view\\resources\\jelly.png"));
				block = ImageIO.read(new File("view\\resources\\block.png"));

				// animations for fish live in an arraylist
				this.fishBufferedImages = new ArrayList<BufferedImage>();
				String[] paths = {"view\\resources\\fish0.png","view\\resources\\fish0i.png",
									"view\\resources\\fish1.png","view\\resources\\fish1i.png",
										"view\\resources\\fish2.png","view\\resources\\fish2i.png",
											"view\\resources\\fish3.png","view\\resources\\fish3i.png"};
				for (int i = 0; i<8; i++){
					BufferedImage frame = ImageIO.read(new File(paths[i]));
					fishBufferedImages.add(frame);
				}
				
            }catch(Exception e){e.printStackTrace();}

		

	}

	/** The background rectangle. */
	private final Rectangle bg;
	/** The *dot* we're moving. */
	private final Rectangle player = new Rectangle(1, 1);
	
	private final Rectangle follower = new Rectangle(1, 1);
	
	private final Rectangle start = new Rectangle(1, 1);
	
	private final Rectangle finish = new Rectangle(1, 1);

	private BufferedImage fishanimation(Direction facing){
		// uses the direction a character is facing to select an appropriate sprite for movement
		animationclock++;
		 int mod = (animationclock%2);
		if (facing == Direction.LEFT){
			return fishBufferedImages.get(4+mod);

		}
		if (facing == Direction.DOWN){
			return fishBufferedImages.get(2+mod);

		}
		if (facing == Direction.RIGHT){
			return fishBufferedImages.get(0+mod);

		}
		else {
			return fishBufferedImages.get(6+mod);

		}
	}

	/**
	 * Creates a new instance.
	 */
	@Override
	public void paint(Graphics g) {
		// Paint background
		//g.setColor(Color.BLUE);
		//g.fillRect(bg.x, bg.y, bg.width, bg.height);
		g.drawImage(bg_img, bg.x,bg.y,null);

		// Paint walls
		
		g.setColor(Color.BLUE);
		for (int y = 0; y<this.worldHeight; y++){
			for (int x = 0; x<this.worldWidth; x++){
				if (this.walls[World.xyConvert(x, y, this.worldWidth, this.worldHeight)]==1){
					g.drawImage(block, x*fieldDimension.width, y*fieldDimension.height, null);
					//g.fillRect(x*fieldDimension.width, y*fieldDimension.height, fieldDimension.width, fieldDimension.height);
				}
			}
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(start.x, start.y, start.width, start.height);
		
		g.setColor(Color.BLUE);
		g.fillRect(finish.x, finish.y, finish.width, finish.height);
		
		// Paint player
		//g.setColor(Color.GREEN);
		//g.fillOval(player.x, player.y, player.width, player.height);
		BufferedImage fish = fishanimation(playerfacing);
		g.drawImage(fish, player.x, player.y, null);
			
		// paint follower
		g.drawImage(jelly, follower.x, follower.y, null);
		//g.setColor(Color.RED);
		//g.fillOval(follower.x, follower.y, follower.width, follower.height);	
			
		
		if (victoryFollower == true) {
			g.setColor(Color.RED);
			g.fillRect(bg.x, bg.y, bg.width, bg.height);
			g.drawImage(lose_screen, bg.x, bg.y, null);
			
			
		}
		
		if (victoryPlayer == true) {
			g.setColor(Color.GREEN);
			g.fillRect(bg.x, bg.y, bg.width, bg.height);
			g.drawImage(win_screen, bg.x, bg.y,null);
			
		}
		
		
		
		
	}
		
	

	@Override
	public void update(World world) {

		// Update players size and location
		player.setSize(fieldDimension);
		player.setLocation(
			(int) (world.getPlayerX() * fieldDimension.width),
			(int) (world.getPlayerY() * fieldDimension.height)
		);
		playerfacing = world.getPlayerfacing();

		;
		
		start.setSize(fieldDimension);
		start.setLocation(
			(int) (world.getStartX() * fieldDimension.width),
			(int) (world.getStartY() * fieldDimension.height)
		);

		;
		
		finish.setSize(fieldDimension);
		finish.setLocation(
			(int) (world.getFinishX() * fieldDimension.width),
			(int) (world.getFinishY() * fieldDimension.height)
		);
		
		
		follower.setSize(fieldDimension);
		follower.setLocation(
			(int) (world.getFollowerX() * fieldDimension.width),
			(int) (world.getFollowerY() * fieldDimension.height)
		
		);
		// check on state of game
		
		victoryFollower = world.isVictoryFollower();
		victoryPlayer = world.isVictoryPlayer();
		


		repaint();
	}

}
