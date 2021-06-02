import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class HealthBar extends JComponent
{
	private int health;
	public HealthBar(int x, int y)
	{
		this.setBounds(x, y, 201, 26);
		health = 200;
	}
	
	
	
	public int getHealth() {
		return health;
	}



	public void setHealth(int health) {
		this.health = health;
	}


	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.drawRoundRect(1, 1, 198, 23, 10, 10);
		g2.setColor(Color.RED);
		g2.fillRoundRect(1, 1, health - 2, 23, 10, 10);
	}
}
