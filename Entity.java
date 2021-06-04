import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class Entity extends JComponent
{
	public static final int PLAYER = 0;
	public static final int ENEMY = 1;
	public static final int BULLET = 2;
	public static final int OBSTACLE = 3;

	private int type;
	private int dx, dy;

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
		switch (type)
		{
		case PLAYER:
			this.setSize(new Dimension(65, 33));
			break;
		case ENEMY:
			this.setSize(new Dimension(65, 33));
			break;
		case BULLET:
			this.setSize(new Dimension(6, 15));
			break;
		case OBSTACLE:
			this.setSize(new Dimension(61, 41));
			break;
		default:
		}
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


	public boolean isTouching(Entity e)
    {
        boolean inX;
        boolean inY;
        
        inX = (getX() > e.getX() && getX() < e.getX() + e.getWidth()) || (getX() + getWidth() > e.getX() && getX() + getWidth() < e.getX() + e.getWidth());
        inY = (getY() > e.getY() && getY() < e.getY() + e.getHeight()) || (getY() + getHeight() > e.getY() && getY() + getHeight() < e.getY() + e.getHeight());
        
        return (inX && inY);
    }

	public void update()
	{
		this.setLocation(this.getX() + dx, this.getY() + dy);
	}
}
