import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JComponent;

public class Entity extends JComponent
{
	public static final int PLAYER = 0;
	public static final int ENEMY = 1;
	public static final int BULLET = 2;
	public static final int OBSTACLE = 3;
	
	private int type;
	private int dx, dy;
	
	private Color c;
	
	private Rectangle pixel;
	
	private String s;
	
	/**
	 * 
	 * @param x - x value of the location of the entity
	 * @param y - y value of the location of the entity
	 * @param type - type of entity (player, bullet, enemy, obstacle)
	 */
	public Entity(int x, int y, int type)
	{
		this.setLocation(x, y);
		this.type = type;
		c = null;
		s = null;
		switch (type)
		{
		case PLAYER:
			
		case ENEMY:
			this.setSize(new Dimension(89, 41));
			break;
		case BULLET:
			this.setSize(new Dimension(13, 26));
			break;
		case OBSTACLE:
			this.setSize(new Dimension(51, 51));
			break;
		default:
		}
	}
	
	/**
	 * 
	 * @param x - x value of the location of the entity
	 * @param y - y value of the location of the entity
	 * @param width - width of the entity
	 * @param height - height of the entity
	 */
	public Entity(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width + 1, height + 1);
		this.type = 3;
		c = null;
		s = null;
	}
	
	/**
	 * Constructor for a bullet
	 * @param x - x value for the location of the bullet
	 * @param y - y value for the location of the bullet
	 * @param color - color of the bullet (either blue or red)
	 */
	public Entity(int x, int y, Color color)
	{
		this.c = color;
		this.setBounds(x, y, 13, 26);
		type = BULLET;
		if (c.equals(Color.BLUE))
		{
			dy = -10;
		}
		else
		{
			dy = 5;
		}
		dx = 0;
		s = null;
	}
	
	public Entity(int x, int y, String s)
	{
		this.setLocation(x, y);
		type = ENEMY;
		pixel = new Rectangle(5, 5);
		this.s = s;
		switch (s)
		{
		case "Frog":
			this.setSize(new Dimension(56, 41));
			break;
		case "Squid":
			this.setSize(new Dimension(41, 41));
			break;
		case "Ship":
			this.setSize(new Dimension(81, 41));
			break;
		case "Bob":
			this.setSize(new Dimension(61, 41));
			break;
		}
	}
	
	public int getDx()
	{
		return dx;
	}
	
	public void setDx(int dx)
	{
		this.dx = dx;
	}
	
	public int getDy()
	{
		return dy;
	}
	
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	
	public void change()
	{
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		//Switch case for what type of entity this object is
		switch (type)
		{
		
		
		case PLAYER: //Draw a player
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2.setColor(Color.BLACK);
			g2.fill(new Ellipse2D.Double(10, 10, 10, 10));
			break;
			
			
		case ENEMY: //Draw an enemy
			g2.setColor(Color.WHITE);
			if (s != null)
			{
				boolean[][] image;
				switch (s)
				{
				case "Frog": //Draw a frog enemy
					image = new boolean[][] {
							{false, false, true, false, false, false, false, false, true, false, false},
							{false, false, false, true, false, false, false, true, false, false, false},
							{false, false, true, true, true, true, true, true, true, false, false},
							{false, true, true, false, true, true, true, false, true, true, false},
							{true, true, true, true, true, true, true, true, true, true, true},
							{true, false, true, true, true, true, true, true, true, false, true},
							{true, false, true, false, false, false, false, false, true, false, true},
							{false, false, false, true, true, false, true, true, false, false, false}
					};
					for (int r = 0; r < 8; r++)
					{
						for (int c = 0; c < 11; c++)
						{
							if (image[r][c])
							{
								pixel.setLocation(c * 5, r * 5);
								g2.fill(pixel);
							}
						}
					}
					break;
				case "Squid": //Draw a squid enemy
					
					break;
				case "Ship": //Draw a ship enemy
					
					break;
				case "Bob": //Bob
					
					break;
				}
			}
			break;
		case BULLET:
			if (c.equals(Color.BLUE))
			{
				g2.setColor(Color.BLACK);
				g2.drawArc(0, 0, 12, 12, 0, 180);
				g2.drawPolygon(new int[] {0, 6, 12}, new int[] {6, 25, 6}, 3);
				g2.setColor(c);
				g2.fillArc(0, 0, 12, 12, 0, 180);
				g2.fillPolygon(new int[] {0, 6, 12}, new int[] {6, 25, 6}, 3);
			}
			else
			{
				g2.setColor(Color.BLACK);
				g2.drawArc(0, 12, 12, 12, 180, 180);
				g2.drawPolygon(new int[] {0, 6, 12}, new int[] {18, 0, 18}, 3);
				g2.setColor(c);
				g2.fillArc(0, 12, 12, 12, 180, 180);
				g2.fillPolygon(new int[] {0, 6, 12}, new int[] {18, 0, 18}, 3);
			}
			break;
		case OBSTACLE:
			g2.setColor(Color.BLACK);
			g2.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
			break;
		}
		
	}
	
	public void update()
	{
		this.setLocation(this.getX() + dx, this.getY() + dy);
	}
}