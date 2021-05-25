import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Obstacle extends Entity
{
	public static final int SOLID = 4;
	public static final int BROKEN = 5;
	public static final int DESTROYED = 6;

	public static boolean[][][] broken;

	private Tile[][] obstacle;

	public Obstacle(int x, int y)
	{
		super(x, y, Entity.OBSTACLE);
		if (broken == null)
		{
			broken = new boolean[5][10][30];
			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 30; j++)
				{
					broken[0][i][j] = (int)(Math.random()*25) == 0;
				}
			}

			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 30; j++)
				{
					broken[1][i][j] = (int)(Math.random()*25) == 0;
				}
			}

			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 30; j++)
				{
					broken[2][i][j] = (int)(Math.random()*25) == 0;
				}
			}

			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 30; j++)
				{
					broken[3][i][j] = (int)(Math.random()*25) == 0;
				}
			}

			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 30; j++)
				{
					broken[4][i][j] = (int)(Math.random()*25) == 0;
				}
			}
		}
		obstacle = new Tile[][] {
			{new Corner(0, 0, Corner.TL, 0, 0), new Block(30, 0, 0, 1), new Corner(60, 0, Corner.TR, 0, 2)},
			{new Block(0, 10, 1, 0), new Block(30, 10, 1, 1), new Block(60, 10, 1, 2)},
			{new Block(0, 20, 2, 0), new Block(30, 20, 2, 1), new Block(60, 20, 2, 2)},
			{new Corner(0, 30, Corner.BL, 3, 0), null, new Corner(60, 30, Corner.BR, 3, 2)}
		};

		for (int i = 0; i < obstacle.length; i++)
		{
			for (int j = 0; j < obstacle[i].length; j++)
			{
				if (obstacle[i][j] != null)
				{
					if (i - 1 >= 0)
					{
						obstacle[i][j].addNeighbor(obstacle[i-1][j]);
					}
					if (j + 1 < obstacle[i].length)
					{
						obstacle[i][j].addNeighbor(obstacle[i][j+1]);
					}
					if (i + 1 < obstacle.length)
					{
						obstacle[i][j].addNeighbor(obstacle[i+1][j]);
					}
					if (j - 1 >= 0)
					{
						obstacle[i][j].addNeighbor(obstacle[i][j-1]);
					}
				}
			}
		}
	}

	public Tile hitBy(Bullet b)
	{
		if (b.isTouching(this))
		{
			System.out.println(1);
			Point contact = new Point((b.getX() * 2 + b.getWidth())/2 - getX(), b.getY() - getY());
			for (int i = 0; i < obstacle.length; i++)
			{
				System.out.println(2);
				for (int j = 0; j < obstacle[i].length; j++)
				{
					System.out.println(3);
					if (obstacle[i][j] != null)
					{
						System.out.println(4);
						if (obstacle[i][j].contains(contact) && obstacle[i][j].getState() == SOLID)
						{
							System.out.println(obstacle[i][j]);
							obstacle[i][j].setState(BROKEN);
							return obstacle[i][j];
						}
					}
				}
			}
		}
		System.out.println("************************************");
		return null;
	}

	public void test(Tile t)
	{
		if (t != null)
		{
			for (int i = 0; i < t.getNeighbors().size(); i++)
			{
				if (t.getNeighbors().get(i) != null)
				{	
					if (t.getNeighbors().get(i).isGone())
					{
						obstacle[t.getNeighbors().get(i).getR()][t.getNeighbors().get(i).getC()] = null;
					}
				}
			}
			if (t.isGone())
			{
				obstacle[t.getR()][t.getC()] = null;
			}
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GREEN);
		for (int r = 0; r < obstacle.length; r++)
		{
			for (int c = 0; c < obstacle[r].length; c++)
			{
				if (obstacle[r][c] != null)
				{
					if (obstacle[r][c].getState() == SOLID)
					{
						this.add(obstacle[r][c]);
					}
					else if (obstacle[r][c].getState() == BROKEN)
					{
						this.remove(obstacle[r][c]);
						repaint();
						if (obstacle[r][c].getBroken() == null)
						{
							int num = (int)(Math.random()*5);
							obstacle[r][c].setBroken(broken[num]);
						}
						for (int i = 0; i < obstacle[r][c].getBroken().length; i++)
						{
							for (int j = 0; j < obstacle[r][c].getBroken()[i].length; j++)
							{
								if (obstacle[r][c].getBroken()[i][j])
								{
									g2.fillRect((int)(obstacle[r][c].getX() + j), (int)(obstacle[r][c].getY() + i), 1, 1);
								}
							}
						}
					}
				}
			}
		}
	}
}