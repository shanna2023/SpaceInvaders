import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Block extends Tile
{
    public Block(int x, int y, int r, int c)
    {
        super(x, y, r, c);
        setS(new Polygon(new int[] {0, 0, 20, 20}, new int[] {0, 10, 0, 10}, 4));
    }

    public boolean contains(Point p)
    {
        return (new Rectangle(getX(), getY(), 20, 10)).contains(p);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GREEN);
        g2.fillRect(0, 0, 20, 10);
    }
}
