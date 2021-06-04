import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Bullet extends Entity
{
	private boolean isE;
    private Rectangle noob;
    private Rectangle noob1;
    
    public Bullet(int x, int y, boolean isEnemy)
    {
        super(x, y, Entity.BULLET);
        noob = new Rectangle(0, 0, 5, 14);
        noob1 = new Rectangle(2, 2, 1, 10);
        isE = isEnemy;

        if (isE == false)
        {
            setDy(-20);
        }
        else
        {
            setDy(12);
        }
        setDx(0);
    }
    
    public boolean isE() 
    {
		return isE;
	}

	public void setE(boolean isE) 
	{
		this.isE = isE;
	}


    
    public boolean isOutsideOf(JFrame c)
    {
        boolean outY = false;
        boolean outX = false;
        if (getY() < 0 || getY() + getHeight() > c.getHeight() - c.getInsets().top - 5)
        {
            outY = true;
        }
        if (getX() < 0 || getX() + getWidth() > c.getWidth() - c.getInsets().right - 5)
        {
            outX = true;
        }
        return outX && outY;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        if (isE == false)
        {
        	g2.setColor(Color.BLUE);
        	g2.draw(noob);
        	g2.fill(noob);
        	g2.setColor(Color.MAGENTA);
        	g2.draw(noob1);
        	g2.fill(noob1);
        }
        else
        {
        	g2.setColor(Color.RED);
        	g2.draw(noob);
        	g2.fill(noob);
        	g2.setColor(Color.ORANGE);
        	g2.draw(noob1);
        	g2.fill(noob1);
        }

        	
        
    }
}
