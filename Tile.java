import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class Tile extends JComponent
{
    private boolean hit;
    private int state;
    private ArrayList<Tile> neighbors;
    private boolean[][] broken;

    private Polygon s;

    private int r;
    private int c;

    public Tile(int x, int y, int r, int c)
    {
        this.r = r;
        this.c = c;
        this.setBounds(x, y, 20, 10);
        state = Obstacle.SOLID;
        hit = false;
        neighbors = new ArrayList<Tile>();
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public void addNeighbor(Tile t)
    {
        neighbors.add(t);
    }

    public void setBroken(boolean[][] b)
    {
        broken = b;
    }

    public boolean[][] getBroken()
    {
        return broken;
    }

    public ArrayList<Tile> getNeighbors()
    {
        return neighbors;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public Polygon getS() {
        return s;
    }

    public void setS(Polygon s) {
        this.s = s;
    }

    public abstract boolean contains(Point p);

    public boolean isGone()
    {
        for (Tile t : neighbors)
        {
            if (t != null && t.getState() == Obstacle.SOLID)
            {
                return false;
            }
        }
        return !(this.getState() == Obstacle.SOLID);
    }
    
    public boolean isGone(Tile ti)
    {
        for (Tile t : neighbors)
        {
            if (t != null)
            {
                if (!t.equals(ti))
                {
                    if (t.getState() == Obstacle.SOLID)
                    {
                        return false;
                    }
                }
            }
        }
        return !(this.getState() == Obstacle.SOLID);
    }
}
