import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player extends Entity
{
	private int power;
	
	public Player(int x, int y)
	{
		super(x, y, Entity.PLAYER);
		power = 0;
	}
	
	public int getPower()
	{
		return power;
	}

	public void setPower(int power)
	{
		this.power = power;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		g2.setColor(Color.BLACK);
		g2.fillPolygon(new int[] {16, 48, 24, 40}, new int[] {0, 0, 15, 15}, 4);
		g2.setColor(Color.WHITE);
		g2.fillPolygon(new int[] {24, 32, 40}, new int[] {15, 0, 15}, 3);
		g2.setColor(Color.BLACK);
		g2.drawRect(12, 12, 40, 4);
		g2.drawRect(12, 19, 40, 4);
		g2.drawRect(12, 26, 40, 4);
		g2.setColor(Color.BLUE);
		if (power > 0)
		{
			g2.fillRect(12, 26, 40, 4);
		}
		if (power > 1)
		{
			g2.fillRect(12, 19, 40, 4);
		}
		if (power > 2)
		{
			g2.fillRect(12, 12, 40, 4);
		}
	}
}
