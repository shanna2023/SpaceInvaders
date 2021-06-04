import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy extends Entity
{
	private Rectangle pixel;
	private boolean[][] current = null;
	private boolean[][] temp;
	private String s;
	
	
	public Enemy(int x, int y, String s)
	{
		super(x, y, Entity.ENEMY);
		pixel = new Rectangle(4, 4);
		this.s = s;
		switch (s)
		{
		case "Frog":
			this.setSize(new Dimension(45, 33));
			break;
		case "Squid":
			this.setSize(new Dimension(33, 33));
			break;
		case "Ship":
			this.setSize(new Dimension(65, 33));
			break;
		case "Bob":
			this.setSize(new Dimension(49, 33));
			break;
		case "Dead":
			this.setSize(new Dimension(49, 33));
			break;
		case "RedDead":
			this.setSize(new Dimension(49, 33));
			break;
		}
	}
	
	public String getName()
	{
		return s;
	}
	
	public void setName(String str)
	{
		s = str;
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
	
	
	
	public boolean[][] getCurrent()
	{
		return current;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
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
					break;
				case "Dead": //Dead
					current = new boolean[][] {
						{true , false, false, true , false, false, false, false, true , false, false, true },
						{false, true , false, false, true , false, false, true , false, false, true , false},
						{false, false, true , false, false, false, false, false, false, true , false, false},
						{false, false, false, false, false, false, false, false, false, false, false, false},
						{false, false, false, false, false, false, false, false, false, false, false, false},
						{false, false, true , false, false, false, false, false, false, true , false, false},
						{false, true , false, false, true , false, false, true , false, false, true , false},
						{true , false, false, true , false, false, false, false, true , false, false, true }
					};
					break;
				case "RedDead": //RedDead
					current = new boolean[][] {
						{true , false, false, true , false, false, false, false, true , false, false, true },
						{false, true , false, false, true , false, false, true , false, false, true , false},
						{false, false, true , false, false, false, false, false, false, true , false, false},
						{false, false, false, false, false, false, false, false, false, false, false, false},
						{false, false, false, false, false, false, false, false, false, false, false, false},
						{false, false, true , false, false, false, false, false, false, true , false, false},
						{false, true , false, false, true , false, false, true , false, false, true , false},
						{true , false, false, true , false, false, false, false, true , false, false, true }
					};
					break;
					
				}
			}
			
			if (s.equals("Bob") && current[0][0])
			{
				for (int r = 0; r < current.length; r++)
				{
					for (int c = 0; c < current[r].length; c++)
					{
						current[r][c] = temp[r][c];
					}
				}
			}
			
			if (s.equals("Bob") && (int)(Math.random()*10000 + 1) == 1)
			{
				temp = new boolean[current.length][current[0].length];
				for (int r = 0; r < current.length; r++)
				{
					for (int c = 0; c < current[r].length; c++)
					{
						temp[r][c] = current[r][c];
					}
				}
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
			
			for (int r = 0; r < current.length; r++)
			{
				for (int c = 0; c < current[r].length; c++)
				{
					if (current[r][c])
					{
						if(s.equals("Ship") || s.equals("RedDead"))
						{
							g2.setColor(Color.RED);
						}
						pixel.setLocation(c * 4, r * 4);
						g2.fill(pixel);
					}
				}
			}
		}
	}
}


