import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Corner extends Tile
{
    private Polygon triangle;
    private int type;

    public static final int TL = 0;
    public static final int TR = 1;
    public static final int BL = 2;
    public static final int BR = 3;

    public Corner(int x, int y, int type, int r, int c)
    {
        super(x, y, r, c);
        this.type = type;
        switch (type)
        {
        case TL:
            triangle = new Polygon(new int[] {0, 20, 20}, new int[] {10, 0, 10}, 3);
            break;
        case TR:
            triangle = new Polygon(new int[] {0, 0, 20}, new int[] {0, 10, 10}, 3);
            break;
        case BL:
            triangle = new Polygon(new int[] {0, 0, 20}, new int[] {0, 10, 0}, 3);
            break;
        case BR:
            triangle = new Polygon(new int[] {0, 20, 20}, new int[] {0, 0, 10}, 3);
            break;
        }
        setS(triangle);
    }

    public boolean contains(Point p)
    {
        Polygon temp = new Polygon(triangle.xpoints, triangle.ypoints, triangle.npoints);
        temp.translate(getX(), getY());
        return temp.contains(p);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GREEN);
        g2.fill(triangle);
    }
}
