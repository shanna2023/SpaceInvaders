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
	private int power;

	private Color c;

	private Rectangle pixel;
	private boolean[][] current = null;
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
			this.setSize(new Dimension(81, 41));
			break;
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
	 * Constructor for a rectangular obstacle
	 * @param x - x value of the location of the entity
	 * @param y - y value of the location of the entity
	 * @param width - width of the entity
	 * @param height - height of the entity
	 */
	public Entity(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width + 1, height + 1);
		type = OBSTACLE;
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

	/**
	 * Constructor for an enemy
	 * @param x - x value of the location of the enemy
	 * @param y - y value of the location of the enemy
	 * @param s - name of the enemy type
	 */
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

	/**
	 * Constructor for a player
	 * @param x - x value of the location of the enemy
	 * @param y - y value of the location of the enemy
	 */
	public Entity(int x, int y)
	{
		this.setLocation(x, y);
		type = PLAYER;
		this.setSize(new Dimension(81, 41));
		power = 0;
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

	public int getPower()
	{
		return power;
	}

	public void setPower(int power)
	{
		this.power = power;
	}

	/**
	 * Changes the animation frame of an enemy
	 */
	public void change()
	{
		switch (s)
		{
		case "Frog":
			current[1][0] = !current[1][0];
			current[1][10] = !current[1][10];

			current[2][0] = !current[2][0];
			current[2][10] = !current[2][10];

			current[3][0] = !current[3][0];
			current[3][10] = !current[3][10];

			current[5][0] = !current[5][0];
			current[5][1] = !current[5][1];
			current[5][9] = !current[5][9];
			current[5][10] = !current[5][10];

			current[6][0] = !current[6][0];
			current[6][10] = !current[6][10];

			current[7][1] = !current[7][1];
			current[7][3] = !current[7][3];
			current[7][4] = !current[7][4];
			current[7][6] = !current[7][6];
			current[7][7] = !current[7][7];
			current[7][9] = !current[7][9];
			break;
		case "Squid":
			current[5][1] = !current[5][1];
			current[5][2] = !current[5][2];
			current[5][3] = !current[5][3];
			current[5][4] = !current[5][4];
			current[5][5] = !current[5][5];
			current[5][6] = !current[5][6];

			current[6][0] = !current[6][0];
			current[6][1] = !current[6][1];
			current[6][3] = !current[6][3];
			current[6][4] = !current[6][4];
			current[6][6] = !current[6][6];
			current[6][7] = !current[6][7];

			current[7][0] = !current[7][0];
			current[7][1] = !current[7][1];
			current[7][2] = !current[7][2];
			current[7][5] = !current[7][5];
			current[7][6] = !current[7][6];
			current[7][7] = !current[7][7];
			break;
		case "Bob":
			current[5][2] = !current[5][2];
			current[5][9] = !current[5][9];

			current[6][1] = !current[6][1];
			current[6][3] = !current[6][3];
			current[6][8] = !current[6][8];
			current[6][10] = !current[6][10];

			current[7][0] = !current[7][0];
			current[7][1] = !current[7][1];
			current[7][2] = !current[7][2];
			current[7][3] = !current[7][3];
			current[7][8] = !current[7][8];
			current[7][9] = !current[7][9];
			current[7][10] = !current[7][10];
			current[7][11] = !current[7][11];
		}
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
			g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 40, 40);
			g2.setColor(Color.BLACK);
			g2.fillPolygon(new int[] {20, 60, 30, 50}, new int[] {0, 0, 20, 20}, 4);
			g2.setColor(Color.WHITE);
			g2.fillPolygon(new int[] {30, 40, 50}, new int[] {20, 0, 20}, 3);
			g2.setColor(Color.BLACK);
			g2.drawRect(15, 15, 50, 5);
			g2.drawRect(15, 23, 50, 5);
			g2.drawRect(15, 31, 50, 5);
			g2.setColor(Color.BLUE);
			if (power > 0)
			{
				g2.fillRect(15, 31, 50, 5);
			}
			if (power > 1)
			{
				g2.fillRect(15, 23, 50, 5);
			}
			if (power > 2)
			{
				g2.fillRect(15, 15, 50, 5);
			}
			break;

		case ENEMY: //Draw an enemy
			g2.setColor(Color.WHITE);
			if (s != null)
			{
				if (current == null)
				{
					switch (s)
					{
					case "Frog": //Draw a frog enemy
						current = new boolean[][] {
							{false, false, true , false, false, false, false, false, true , false, false},
							{false, false, false, true , false, false, false, true , false, false, false},
							{false, false, true , true , true , true , true , true , true , false, false},
							{false, true , true , false, true , true , true , false, true , true , false},
							{true , true , true , true , true , true , true , true , true , true , true },
							{true , false, true , true , true , true , true , true , true , false, true },
							{true , false, true , false, false, false, false, false, true , false, true },
							{false, false, false, true , true , false, true , true , false, false, false}
						};
						break;
					case "Squid": //Draw a squid enemy
						current = new boolean[][] {
							{false, false, false, true , true , false, false, false},
							{false, false, true , true , true , true , false, false},
							{false, true , true , true , true , true , true , false},
							{true , true , false, true , true , false, true , true },
							{true , true , true , true , true , true , true , true },
							{false, true , false, true , true , false, true , false},
							{true , false, false, false, false, false, false, true },
							{false, true , false, false, false, false, true , false}
						};
						break;
					case "Ship": //Draw a ship enemy
						current = new boolean[][] {
							{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
							{false, false, false, false, false, true , true , true , true , true , true , false, false, false, false, false},
							{false, false, false, true , true , true , true , true , true , true , true , true , true , false, false, false},
							{false, false, true , true , true , true , true , true , true , true , true , true , true , true , false, false},
							{false, true , true , false, true , true , false, true , true , false, true , true , false, true , true , false},
							{true , true , true , true , true , true , true , true , true , true , true , true , true , true , true , true },
							{false, false, true , true , true , false, false, true , true , false, false, true , true , true , false, false},
							{false, false, false, true , false, false, false, false, false, false, false, false, true , false, false, false}
						};
						break;
					case "Bob": //Bob
						current = new boolean[][] {
							{false, false, false, false, true , true , true , true , false, false, false, false},
							{false, true , true , true , true , true , true , true , true , true , true , false},
							{true , true , true , true , true , true , true , true , true , true , true , true },
							{true , true , true , false, false, true , true , false, false, true , true , true },
							{true , true , true , true , true , true , true , true , true , true , true , true },
							{false, false, false, true , true , false, false, true , true , false, false, false},
							{false, false, true , true , false, true , true , false, true , true , false, false},
							{true , true , false, false, false, false, false, false, false, false, true , true }
						};
						if (Math.random()*1000 + 1 == 1)
						{
							current = new boolean[][] {
								{true , true , false, false, false, true , true , false, false, true , true , false},
								{true , false, true , false, true , false, false, true , false, true , false, true },
								{true , false, true , false, true , false, false, true , false, true , false, true },
								{true , true , false, false, true , false, false, true , false, true , true , false},
								{true , false, true , false, true , false, false, true , false, true , false, true },
								{true , false, true , false, true , false, false, true , false, true , false, true },
								{true , false, true , false, true , false, false, true , false, true , false, true },
								{true , true , false, false, false, true , true , false, false, true , true , false}
							};
						}
						break;
					}
				}

				for (int r = 0; r < current.length; r++)
				{
					for (int c = 0; c < current[r].length; c++)
					{
						if (current[r][c])
						{
							pixel.setLocation(c * 5, r * 5);
							g2.fill(pixel);
						}
					}
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
