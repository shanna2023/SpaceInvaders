import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Divider extends JComponent
{
	private Rectangle Divider;
	private ArrayList<Rectangle> recs;

	public Divider(int x, int y, int w, int h)
	{
		recs = new ArrayList<Rectangle>();
		Divider = new Rectangle(w, h);
		setLocation(x, y);
		setSize(w, h);

		for(int i = 0; i < 11; i++)
		{
			recs.add(new Rectangle(39, h));
			recs.get(i).setLocation(i * 65, 0);			
		}
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.LIGHT_GRAY);
		for(Rectangle r : recs)
		{
			g2.fill(r);
		}
	}
}

