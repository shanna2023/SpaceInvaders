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

	public SpaceInvadersMain()
	{
		this.setResizable(false);
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
		String str2 = "press b to begin 0123456789";
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

		Obstacle obstacle = new Obstacle(180, 425);
		obstacles.add(obstacle);

		Obstacle obstacle1 = new Obstacle(451, 425);
		obstacles.add(obstacle1);

		Obstacle obstacle2 = new Obstacle(722, 425);
		obstacles.add(obstacle2);

		Obstacle obstacle3 = new Obstacle(993, 425);
		obstacles.add(obstacle3);



		for(Obstacle o : obstacles)
		{
			this.add(o);
			o.setVisible(false);
		}

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
				/* ****************************************************************************************************
				 * vv PLAYER MOVEMENT AND SHOOTING vv
				 * ****************************************************************************************************
				 */
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
				/* ****************************************************************************************************
				 * ^^ PLAYER MOVEMENT AND SHOOTING ^^
				 * ****************************************************************************************************
				 */

				/* ****************************************************************************************************
				 * vv BEGIN SETTING UP vv
				 * ****************************************************************************************************
				 */
				if (e.getKeyCode() == KeyEvent.VK_B && !began && !settingUp)
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
					bullets = new ArrayList<Bullet>();

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

					count = 0;
					settingUp = true;
					began = false;
					t.start();
				}
				/* ****************************************************************************************************
				 * ^^ BEGIN SETTING UP ^^
				 * ****************************************************************************************************
				 */

				/* ****************************************************************************************************
				 * vv TEMPORARY TESTING OBSTACLE vv
				 * ****************************************************************************************************
				 */
//				for(Obstacle o : obstacles)
//				{
//					if (e.getKeyCode() == KeyEvent.VK_1)
//					{
//						bullets.add(new Bullet(o.getX() + 10, o.getY() - 100, true));
//						jawn.add(bullets.get(bullets.size() - 1));
//					}
//					if (e.getKeyCode() == KeyEvent.VK_2)
//					{
//						bullets.add(new Bullet(o.getX() + 40, o.getY() - 100, true));
//						jawn.add(bullets.get(bullets.size() - 1));
//					}
//					if (e.getKeyCode() == KeyEvent.VK_3)
//					{
//						bullets.add(new Bullet(o.getX() + 65, o.getY() - 100, true));
//						jawn.add(bullets.get(bullets.size() - 1));
//					}
//				}
				/* ****************************************************************************************************
				 * ^^ TEMPORARY TESTING OBSTACLE ^^
				 * ****************************************************************************************************
				 */

				//Pause game
				if (e.getKeyCode() == KeyEvent.VK_P)
				{
					if (t.isRunning())
					{
						t.stop();
					}
					else
					{
						t.start();
					}
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
		//If all aliens are on screen, then start moving and player start gaining power
		if (began)
		{
			
			for(Obstacle o : obstacles)
			{
				o.setVisible(true);
			}
			
			/* ****************************************************************************************************
			 * vv RUNS THROUGH EACH OBSTACLE AND DETERMINES IF IT WAS HIT, REMOVES BULLETS ACCORDINGLY vv
			 * ****************************************************************************************************
			 */
			for (Obstacle o : obstacles)
			{
				for (int i = 0; i < bullets.size(); i++)
				{
					Tile[] temp = o.hitBy(bullets.get(i));
					if (temp != null)
					{
						for (Tile t : temp)
						{
							o.test(t);
						}
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
			}
			/* ****************************************************************************************************
			 * ^^ RUNS THROUGH EACH OBSTACLE AND DETERMINES IF IT WAS HIT, REMOVES BULLETS ACCORDINGLY ^^
			 * ****************************************************************************************************
			 */
			boolean hitOrNot = false;
			for(int i = bullets.size()-1; i >= 0; i--)
			{
				if(!bullets.get(i).isE())
				{
					for(int h = 0; h < enemies.size(); h++)
					{
						for(int j = 0; j < enemies.get(h).size(); j++)
						{
							if(enemies.get(h).get(j).getBounds().intersects(bullets.get(i).getBounds()))
							{
								this.remove(enemies.get(h).get(j));
								enemies.get(h).remove(j);
								this.remove(bullets.get(i));
								hitOrNot = true;
								j--;
							}
						}
					}
				}
				if(hitOrNot)
				{
					bullets.remove(i);
					hitOrNot = false;
				}
			}
				
			
			/* ****************************************************************************************************
			 * vv LIMITING PLAYER MOVEMENT vv
			 * ****************************************************************************************************
			 */
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
			/* ****************************************************************************************************
			 * ^^ LIMITING PLAYER MOVEMENT ^^
			 * ****************************************************************************************************
			 */


			/* ****************************************************************************************************
			 * vv UPDATING ENEMIES AND BULLETS, GAME OVER IF ENEMY TOUCHES PLAYER vv
			 * ****************************************************************************************************
			 */
			for (ArrayList<Enemy> arr : enemies)
			{
				for (Enemy enemy : arr)
				{
					enemy.update();
					if (count % 20 == 0)
					{
						enemy.change();
					}
					if(enemy.getBounds().intersects(player.getBounds())) //If an enemy is touching the player
					{
						JOptionPane.showMessageDialog(this, "Game Over", getTitle(), 0);
						System.exit(0);
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
			
			if(enemies.size() == 0)
			{
				repaint();				
				JOptionPane.showMessageDialog(this, "You Win", getTitle(), 1);
				System.exit(0);
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

			
			/* ****************************************************************************************************
			 * ^^ UPDATING ENEMIES, GAME OVER IF ENEMY TOUCHES PLAYER ^^
			 * ****************************************************************************************************
			 */

			/* ****************************************************************************************************
			 * vv UPDATING BULLETS AND PLAYER POWER vv
			 * ****************************************************************************************************
			 */
			for (Entity b : bullets)
			{
				b.update();
			}

			if (player.getPower() < 3)
			{
				fire++;
				if (fire % 2 == 0)
				{
					player.setPower(player.getPower() + 1);
				}
			}

			if (player.getPower() == 3)
			{
				fire = 0;
			}
			/* ****************************************************************************************************
			 * ^^ UPDATING BULLETS AND PLAYER POWER ^^
			 * ****************************************************************************************************
			 */
		}
		else if (settingUp) //Just left title screen, enemies not all on screen
		{
			int j = (count - 1) % 16;

			if (j == 0)
			{
				enemies.add(new ArrayList<Enemy>());
			}

			/* ****************************************************************************************************
			 * vv PLACING EACH ENEMY ACCORDING TO COUNT vv
			 * ****************************************************************************************************
			 */
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
			//			/* ****************************************************************************************************
			//			 * ^^ PLACING EACH ENEMY ACCORDING TO COUNT ^^
			//			 * ****************************************************************************************************
			//			 */
		}
		else //Title screen
		{
			//Flashing subtitle
			if (count % 20 == 0)
			{
				for (Character c : subtitle)
				{
					c.setVisible(!c.isShowing());
				}
			}

			//vv Ship at top shooting bullets vv
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
			//^^ Ship at top shooting bullets ^^

			/* ****************************************************************************************************
			 * vv UPDATING BULLETS, SHIP, AND ENEMIES vv
			 * ****************************************************************************************************
			 */
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
			/* ****************************************************************************************************
			 * ^^ UPDATING BULLETS, SHIP, AND ENEMIES ^^
			 * ****************************************************************************************************
			 */

		}

		//Starts enemies going if left direction
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

		

		/* ****************************************************************************************************
		 * vv TEMPORARY, FOR TESTING OF OBSTACLES vv
		 * ****************************************************************************************************
		 */
	
		/* ****************************************************************************************************
		 * ^^ TEMPORARY, FOR TESTING OF OBSTACLES ^^
		 * ****************************************************************************************************
		 */

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
	 * @param arr - The <code>ArrayList</code> to which the <code>Character</code>s are added
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
		for(int i = 1; i < enemies.size(); i ++)
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
		for(int i = 1; i < enemies.size(); i ++)
		{
			if(enemies.get(i).get(enemies.get(i).size()-1).getX() + enemies.get(i).get(enemies.get(i).size()-1).getWidth() > right.getX() + right.getWidth())
			{
				right = enemies.get(i).get(enemies.get(i).size()-1);
			}
		}
		return right.getX() + right.getWidth();
	}

}
