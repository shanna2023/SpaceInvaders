import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Character extends JComponent
{
	private Rectangle pixel;
	private int size;
	private boolean[][] image;
	
	public Character(int x, int y, int size, char c)
	{
		this.setLocation(x, y);
		this.setSize(new Dimension(5 * size + 1, 7 * size + 1));
		pixel = new Rectangle(size, size);
		this.size = size;
		image = null;
		switch (c)
		{
		case 'a': //Letter A
			image = new boolean[][] {
				{false, true , true , true , false},
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true }
			};
			break;
		case 'b': //Letter B
			image = new boolean[][] {
				{true , true , true , true , false},
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , false},
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , false}
			};
			break;
		case 'c': //Letter C
			image = new boolean[][] {
				{false, true , true , true , true },
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{false, true , true , true , true }
			};
			break;
		case 'd': //Letter D
			image = new boolean[][] {
				{true , true , true , true , false},
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , false}
			};
			break;
		case 'e': //Letter E
			image = new boolean[][] {
				{true , true , true , true , true },
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , true , true , true , false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , true , true , true , true }
			};
			break;
		case 'f': //Letter F
			image = new boolean[][] {
				{true , true , true , true , true },
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , true , true , true , false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false}
			};
			break;
		case 'g': //Letter G
			image = new boolean[][] {
				{true , true , true , true , true },
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, true , true , true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , true }
			};
			break;
		case 'h': //Letter H
			image = new boolean[][] {
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true }
			};
			break;
		case 'i': //Letter I
			image = new boolean[][] {
				{true , true , true , true , true },
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{true , true , true , true , true }
			};
			break;
		case 'j': //Letter J
			image = new boolean[][] {
				{true , true , true , true , true },
				{false, false, false, false, true },
				{false, false, false, false, true },
				{false, false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{false, true , true , true , false}
			};
			break;
		case 'k': //Letter K
			image = new boolean[][] {
				{true , false, false, false, true },
				{true , false, false, true , false},
				{true , false, true , false, false},
				{true , true , false, false, false},
				{true , false, true , false, false},
				{true , false, false, true , false},
				{true , false, false, false, true }
			};
			break;
		case 'l': //Letter L
			image = new boolean[][] {
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , true , true , true , true }
			};
			break;
		case 'm': //Letter M
			image = new boolean[][] {
				{true , false, false, false, true },
				{true , true , false, true , true },
				{true , false, true , false, true },
				{true , false, true , false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true }
			};
			break;
		case 'n': //Letter N
			image = new boolean[][] {
				{true , false, false, false, true },
				{true , true , false, false, true },
				{true , true , false, false, true },
				{true , false, true , false, true },
				{true , false, false, true , true },
				{true , false, false, true , true },
				{true , false, false, false, true }
			};
			break;
		case 'o': //Letter O
			image = new boolean[][] {
				{false, true , true , true , false},
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{false, true , true , true , false}
			};
			break;
		case 'p': //Letter P
			image = new boolean[][] {
				{true , true , true , true , false},
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , false},
				{true , false, false, false, false},
				{true , false, false, false, false},
				{true , false, false, false, false}
			};
			break;
		case 'q': //Letter Q
			image = new boolean[][] {
				{false, true , true , false, false},
				{true , false, false, true , false},
				{true , false, false, true , false},
				{true , false, false, true , false},
				{true , false, false, true , false},
				{false, true , true , false, false},
				{false, false, false, true , true }
			};
			break;
		case 'r': //Letter R
			image = new boolean[][] {
				{true , true , true , true , false},
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , true , true , true , false},
				{true , false, true , false, false},
				{true , false, false, true , false},
				{true , false, false, false, true }
			};
			break;
		case 's': //Letter S
			image = new boolean[][] {
				{false, true , true , true , true },
				{true , false, false, false, false},
				{true , false, false, false, false},
				{false, true , true , true , false},
				{false, false, false, false, true },
				{false, false, false, false, true },
				{true , true , true , true , false}
			};
			break;
		case 't': //Letter T
			image = new boolean[][] {
				{true , true , true , true , true },
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false}
			};
			break;
		case 'u': //Letter U
			image = new boolean[][] {
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{false, true , true , true , false}
			};
			break;
		case 'v': //Letter V
			image = new boolean[][] {
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{false, true , false, true , false},
				{false, true , false, true , false},
				{false, true , false, true , false},
				{false, false, true , false, false}
			};
			break;
		case 'w': //Letter W
			image = new boolean[][] {
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, false, false, true },
				{true , false, true , false, true },
				{true , false, true , false, true },
				{true , false, true , false, true },
				{false, true , false, true , false}
			};
			break;
		case 'x': //Letter X
			image = new boolean[][] {
				{true , false, false, false, true },
				{false, true , false, true , false},
				{false, true , false, true , false},
				{false, false, true , false, false},
				{false, true , false, true , false},
				{false, true , false, true , false},
				{true , false, false, false, true }
			};
			break;
		case 'y': //Letter Y
			image = new boolean[][] {
				{true , false, false, false, true },
				{false, true , false, true , false},
				{false, true , false, true , false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false},
				{false, false, true , false, false}
			};
			break;
		case 'z': //Letter Z
			image = new boolean[][] {
				{true , true , true , true , true },
				{false, false, false, false, true },
				{false, false, false, true , false},
				{false, false, true , false, false},
				{false, true , false, false, false},
				{true , false, false, false, false},
				{true , true , true , true , true }
			};
			break;
		}
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.WHITE);
		if (image != null)
		{
			for (int r = 0; r < image.length; r++)
			{
				for (int c = 0; c < image[r].length; c++)
				{
					if (image[r][c])
					{
						pixel.setLocation(c * size, r * size);
						g2.fill(pixel);
					}
				}
			}
		}
	}
}