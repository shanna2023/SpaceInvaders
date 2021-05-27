import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class SpaceInvadersMain extends JFrame implements ActionListener
{
	private ArrayList<ArrayList<Enemy>> enemies;
	private ArrayList<Obstacle> obstacles;
	private Player player;
	private int count;
	private int fire;
	private Enemy frog;
	private Enemy squid;
	private Enemy bob;
	private Enemy frog2;
	private Enemy squid2;
	private Enemy bob2;
	private Enemy hehe;
	private boolean began;
	private boolean settingUp;
	private ArrayList<Character> title;
	private ArrayList<Character> subtitle;
	private ArrayList<Bullet> bullets;

	//Temp
	private Obstacle obstacle;

	public SpaceInvadersMain()
	{
		this.setBounds(0, 0, 1280, 650);
		this.setLayout(null);
		this.setTitle("Space Invaders");
		this.getContentPane().setBackground(Color.BLACK);

		frog = new Enemy(70, 300, "Frog");
		this.add(frog);

		squid = new Enemy(78, 100, "Squid");
		this.add(squid);

		bob = new Enemy(68, 500, "Bob");
		this.add(bob);

		frog2 = new Enemy(1100, 300, "Frog");
		this.add(frog2);

		squid2 = new Enemy(1108, 100, "Squid");
		this.add(squid2);

		bob2 = new Enemy(1098, 500, "Bob");
		this.add(bob2);

		hehe = new Enemy(590, 50, "Ship");
		this.add(hehe);

		title = new ArrayList<Character>();
		subtitle = new ArrayList<Character>();
		bullets = new ArrayList<Bullet>();

		String str1 = "space invaders";
		String str2 = "press b to begin";
		for (int i = 0; i < str1.length(); i++)
		{
			title.add(new Character(i * 60 + 210, 200, 10, str1.charAt(i)));
			this.add(title.get(i));
		}
		for (int i = 0; i < str2.length(); i++)
		{
			subtitle.add(new Character(i * 20 + 470, 300, 3, str2.charAt(i)));
			this.add(subtitle.get(i));
		}

		count = 0;
		fire = 0;

		began = false;
		settingUp = false;

		obstacles = new ArrayList<Obstacle>();
		enemies = new ArrayList<ArrayList<Enemy>>();
		player = new Player(600, 550);
		obstacle = new Obstacle(500, 150);
		this.add(obstacle);

		SpaceInvadersMain jawn = this;

		Timer t = new Timer(25, this);

		this.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					player.setDx(-5);
				}

				if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					player.setDx(5);
				}

				if (e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					if (player.getPower() == 3)
					{
						player.setPower(0);
						bullets.add(new Bullet(player.getX() + 33, player.getY() - 20, false));
						jawn.add(bullets.get(bullets.size() - 1));
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_B && !began)
				{
					t.stop();
					for(Character str: title)
						jawn.remove(str);
					//str.setVisible(false);
					for(Character str: subtitle)
						jawn.remove(str);
					//						str.setVisible(false);
					for(Entity o: obstacles)
						jawn.add(o);
					for (Entity b : bullets)
						jawn.remove(b);
					//					removeAll();
					revalidate();
					repaint();
					jawn.add(player);

					jawn.remove(frog);
					jawn.remove(frog2);
					jawn.remove(squid);
					jawn.remove(squid2);
					jawn.remove(bob);
					jawn.remove(bob2);
					jawn.remove(hehe);

					for(ArrayList<Enemy> en: enemies)
					{
						for(Entity obj: en)
						{
							jawn.add(obj);
							System.out.println("Hi");
							repaint();
							try {
								Thread.sleep(250);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
//					SetupListener s = new SetupListener(jawn, enemies);					
//					Timer t2 = new Timer(250, s);
//					t2.start();
//
//					while (s.getCount() < enemies.get(0).size())
//					{
//						System.out.println(s.getCount());
//					}
//
//					t2.stop();

					count = 0;
					settingUp = true;
					t.start();
				}

				if (e.getKeyCode() == KeyEvent.VK_1)
				{
					bullets.add(new Bullet(obstacle.getX() + 10, obstacle.getY() - 100, true));
					jawn.add(bullets.get(bullets.size() - 1));
				}
				if (e.getKeyCode() == KeyEvent.VK_2)
				{
					bullets.add(new Bullet(obstacle.getX() + 40, obstacle.getY() - 100, true));
					jawn.add(bullets.get(bullets.size() - 1));
				}
				if (e.getKeyCode() == KeyEvent.VK_3)
				{
					bullets.add(new Bullet(obstacle.getX() + 75, obstacle.getY() - 100, true));
					jawn.add(bullets.get(bullets.size() - 1));
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					player.setDx(0);
				}

				if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					player.setDx(0);
				}
			}

		});

		hehe.setDx(-3);

		t.start();

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		new SpaceInvadersMain();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		count++;
		if (began)
		{
			if (player.getX() >= 0 && player.getX() + player.getWidth() <= this.getWidth() - this.getInsets().right - this.getInsets().left)
			{
				if (player.getX() + player.getDx() < 0)
				{
					player.setLocation(0, player.getY());
				}
				else if (player.getX() + player.getWidth() + player.getDx() > this.getWidth() - this.getInsets().right - this.getInsets().left)
				{
					player.setLocation(this.getWidth() - this.getInsets().right - this.getInsets().left - player.getWidth(), player.getY());
				}
				else
				{
					player.update();
				}
			}

			for (ArrayList<Enemy> arr : enemies)
			{
				for (Enemy enemy : arr)
				{
					enemy.update();
					if (count % 20 == 0)
					{
						enemy.change();
					}
				}
			}

			for (Entity b : bullets)
			{
				b.update();
			}

			if (player.getPower() < 3)
			{
				fire++;
				if (fire % 10 == 0)
				{
					player.setPower(player.getPower() + 1);
				}
			}

			if (player.getPower() == 3)
			{
				fire = 0;
			}

			if(left() < 0)
			{
				for(ArrayList<Enemy> h : enemies)
				{
					for(Enemy bruh : h)
					{
						bruh.setDx(bruh.getDx() * -1);
						bruh.setLocation(bruh.getX(), bruh.getY() + 20);
					}
				}
			}
			if(right() > this.getContentPane().getWidth())
			{
				for(ArrayList<Enemy> h : enemies)
				{
					for(Enemy bruh : h)
					{
						bruh.setDx(bruh.getDx() * -1);
						bruh.setLocation(bruh.getX(), bruh.getY() + 20);
					}
				}
			}

			for(int i = 0; i < enemies.size(); i++)
			{
				if(enemies.get(i).isEmpty())
				{
					enemies.remove(i);
					i--;
				}
			}

//			for(int i = 0; i < enemies.size(); i++)
//			{
//				for(int j = 0; j < enemies.get(i).size(); j++)
//				{
//					if(enemies.get(i).get(1).getY() + enemies.get(i).get(1).getHeight() > )
//					{
//						
//					}
//				}
//			}

			for(int i = 0; i < enemies.size(); i++)
			{
				for(int j = 0; j < enemies.get(i).size(); j++)
				{
					if(enemies.get(i).get(j).getBounds().intersects(player.getBounds()))
					{
						JOptionPane.showMessageDialog(this, "Game Over", getTitle(), 0);
						System.exit(0);
					}
				}
			}
		}
		else if (settingUp)
		{
						int j = (count - 1) % 16;
						if (j == 0)
						{
							enemies.add(new ArrayList<Enemy>());
						}
			
						if (count > 64)
						{
							Enemy pillo = new Enemy(69 * j + 69, 210, "Bob");
							enemies.get(4).add(pillo);
							this.add(pillo);
							if (count == 80)
							{
								settingUp = false;
								began = true;
							}
						}
						else if(count > 48)
						{
							Enemy pillo = new Enemy(69 * j + 69, 160, "Bob");
							enemies.get(3).add(pillo);
							this.add(pillo);
						}
						else if(count > 32)
						{
							Enemy pillo = new Enemy(69 * j + 71, 110, "Frog");
							enemies.get(2).add(pillo);
							this.add(pillo);
						}
						else if(count > 16)
						{
							Enemy pillo = new Enemy(69 * j + 71, 60, "Frog");
							enemies.get(1).add(pillo);
							this.add(pillo);
						}
						else if(count > 0)
						{
							Enemy pillo = new Enemy(69 * j + 79, 10, "Squid");
							enemies.get(0).add(pillo);
							this.add(pillo);
						}
		}
		else
		{
						if (count % 20 == 0)
						{
							for (Character c : subtitle)
							{
								c.setVisible(!c.isShowing());
							}
						}
						
						if (count % 20 == 0)
						{
							bullets.add(new Bullet(hehe.getX() + 11, hehe.getY() + 30, true));
							this.add(bullets.get(bullets.size() - 1));
						}
						
						if ((count + 10) % 20 == 0)
						{
							bullets.add(new Bullet(hehe.getX() + hehe.getWidth() - 24, hehe.getY() + 30, true));
							this.add(bullets.get(bullets.size() - 1));
						}
						
						for (Entity en : bullets)
						{
							en.update();
						}
						
						hehe.update();
						
						if (hehe.getX() < 210 || hehe.getX() + hehe.getWidth() > 990)
						{
							hehe.setDx(hehe.getDx() * -1);
						}
						
						if (count % 20 == 0)
						{
							frog.change();
							squid.change();
							bob.change();
							frog2.change();
							squid2.change();
							bob2.change();
						}
		}
		if(count == 81)
		{
			for(ArrayList<Enemy> h : enemies)
			{
				for(Enemy bruh : h)
				{
					bruh.setDx(-1);
				}
			}
		}

		for (Obstacle o : obstacles)
		{
			for (int i = 0; i < bullets.size(); i++)
			{
				if (o.hitBy(bullets.get(i)) != null)
				{
					this.remove(bullets.get(i));
					bullets.remove(i);
				}
			}
		}

		for (int i = 0; i < bullets.size(); i++)
		{
			Tile temp = obstacle.hitBy(bullets.get(i));
			if (temp != null)
			{
				obstacle.test(temp);
				this.remove(bullets.get(i));
				bullets.remove(i);
				i--;
			}
			else if (bullets.get(i).isOutsideOf(this))
			{
				this.remove(bullets.get(i));
				bullets.remove(i);
				i--;
			}
		}

		repaint();
	}

	/**
	 * Adds an entire String to the frame as Characters
	 * @param s - The String to be added to the frame
	 * @param x - The x value of the top-left corner of the String
	 * @param y - The y value of the top-left corner of the String
	 * @param size - The pixel size used to draw each character
	 * @param width - The pixel width between each character
	 */
	public void add(String s, int x, int y, int size, int width)
	{
		Character c = new Character(x, y, size, s.charAt(0));
		int curX = x + c.getWidth() + width;
		this.add(c);
		for (int i = 1; i < s.length(); i++)
		{
			c = new Character(curX, y, size, s.charAt(i));
			curX += c.getWidth() + width;
			this.add(c);
		}
	}

	/**
	 * Adds an entire String to the frame as Characters, as well as adds them to an ArrayList
	 * @param s - The String to be added to the frame
	 * @param x - The x value of the top-left corner of the String
	 * @param y - The y value of the top-left corner of the String
	 * @param size - The pixel size used to draw each character
	 * @param width - The pixel width between each character
	 * @param arr - 
	 */
	public void add(String s, int x, int y, int size, int width, ArrayList<Character> arr)
	{
		Character c = new Character(x, y, size, s.charAt(0));
		int curX = x + c.getWidth() + width;
		this.add(c);
		for (int i = 1; i < s.length(); i++)
		{
			c = new Character(curX, y, size, s.charAt(i));
			curX += c.getWidth() + width;
			arr.add(c);
			this.add(c);
		}
	}

	public int left()
	{
		Entity left = enemies.get(0).get(0);
		for(int i = 1; i < 5; i ++)
		{
			if(enemies.get(i).get(0).getX() < left.getX())
			{
				left = enemies.get(i).get(0);
			}
		}
		return left.getX();
	}

	public int right()
	{
		Entity right = enemies.get(0).get(enemies.get(0).size()-1);
		for(int i = 1; i < 5; i ++)
		{
			if(enemies.get(i).get(enemies.get(i).size()-1).getX() + enemies.get(i).get(enemies.get(i).size()-1).getWidth() > right.getX() + right.getWidth())
			{
				right = enemies.get(i).get(enemies.get(i).size()-1);
			}
		}
		return right.getX() + right.getWidth();
	}

}