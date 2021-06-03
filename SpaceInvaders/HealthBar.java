import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class HealthBar extends JComponent
{
	private int health;
	public HealthBar(int x, int y)
	{
		this.setBounds(x, y, 201, 21);
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
		g2.setColor(new Color(169, 3, 252));
		g2.fillRoundRect(1, 1, health - 2, 20, 10, 10);
	}
}