import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Bullet extends Entity
{
    private Color c;
    
    public Bullet(int x, int y, Color color)
    {
        super(x, y, Entity.BULLET);
        this.c = color;
        if (c.equals(Color.BLUE))
        {
            setDy(-10);
        }
        else
        {
            setDy(5);
        }
        setDx(0);
    }
    
    public boolean isTouching(Obstacle e)
    {
        boolean inX;
        boolean inY;
        
        inX = (getX() > e.getX() && getX() < e.getX() + e.getWidth()) || (getX() + getWidth() > e.getX() && getX() + getWidth() < e.getX() + e.getWidth());
        inY = (getY() > e.getY() && getY() < e.getY() + e.getHeight()) || (getY() + getHeight() > e.getY() && getY() + getHeight() < e.getY() + e.getHeight());
        
        return (inX && inY);
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
    }
}