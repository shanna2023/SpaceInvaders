import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class PowerUp extends JComponent
{
	private Ellipse2D.Double circle;
	private String type;
	private int dy;
	
	public PowerUp(int x, int y, String type)
	{
		dy = 10;
		this.setLocation(x, y);
		this.type = type;
		switch (type)
		{
		case "Med":
			this.setSize(new Dimension(27, 21));
			break;
			
		case "Freeze":
			this.setSize(new Dimension(22, 22));
			break;
			
		case "Fire Speed":
			this.setSize(new Dimension(10, 25));
			break;
			
		case "Player Speed":
			this.setSize(new Dimension(21, 21));
			break;
		}
	}
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		switch (type)
		{
		case "Med":
			g2.setColor(Color.WHITE);
			g2.fillRoundRect(0, 5, 26, 15, 5, 5);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(5, 0, 16, 10, 5, 5);
			g2.setColor(Color.RED);
			g2.fillRect(8, 11, 10, 3);
			g2.fillRect(11, 8, 3, 10);
			break;
			
		case "Freeze":
			g2.setColor(new Color(110, 240, 255));
			g2.fillRect(0, 0, 7, 7);
			g2.fillRect(7, 14, 7, 7);
			g2.setColor(new Color(0, 118, 191));
			g2.fillRect(14, 0, 7, 7);
			g2.fillRect(0, 7, 7, 7);
			g2.fillRect(0, 14, 7, 7);
			g2.setColor(new Color(2, 178, 209));
			g2.fillRect(14, 7, 7, 7);
			g2.setColor(new Color(46, 169, 191));
			g2.fillRect(14, 14, 7, 7);
			g2.setColor(new Color(75, 120, 219));
			g2.fillRect(7, 7, 7, 7);
			g2.setColor(new Color(0, 74, 237));
			g2.fillRect(7, 0, 7, 7);
			break;
			
		case "Fire Speed":
			g2.setColor(new Color(194, 162, 0));
			g2.fillRect(4, 0, 2, 2);
			g2.fillRect(2, 2, 6, 4);
			g2.fillRect(0, 6, 10, 4);
			g2.setColor(new Color(237, 203, 31));
			g2.fillRect(0, 10, 10, 10);
			g2.fillRect(2, 20, 6, 2);
			g2.fillRect(0, 22, 10, 2);
			break;
			
			
		case "Player Speed":
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(2));
			g2.drawLine(5, 5, 10, 10);
			g2.drawLine(10, 10, 5, 15);
			g2.drawLine(10, 5, 15, 10);
			g2.drawLine(15, 10, 10, 15);
			break;
		}
	}
	
	public void update()
	{
		setLocation(getX(), getY() + dy);
	}
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
}
