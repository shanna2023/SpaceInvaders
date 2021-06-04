import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
	private Enemy redShip;
	private boolean began;
	private boolean settingUp;
	private ArrayList<Character> title;
	private ArrayList<Character> subtitle;
	private ArrayList<Bullet> bullets;
	private int score;
	private ArrayList<Character> scoreDisplay;
	private ArrayList<Enemy> deaths; 
	private ArrayList<PowerUp> powerUps;
	private HealthBar health;
	private boolean flag;
	private boolean nextLevel;
	private int eDx; 
	private int eFire;
	private String[] types;
	private int time;
	private int playerSpeed;
	private int fireSpeed;
	private boolean frozen;
	private int highScore;
	private ArrayList<Character> highScoreDisplay;
	private Divider divider;
	
	public SpaceInvadersMain()
	{
		nextLevel = false;
		this.setResizable(false);
		this.setBounds(0, 0, 640, 650);
		this.setLayout(null);
		this.setTitle("Space Invaders");
		this.getContentPane().setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);

		frog = new Enemy(90, 300, "Frog");
		this.add(frog);

		squid = new Enemy(96, 100, "Squid");
		this.add(squid);

		bob = new Enemy(88, 500, "Bob");
		this.add(bob);

		frog2 = new Enemy(490, 300, "Frog");
		this.add(frog2);

		squid2 = new Enemy(496, 100, "Squid");
		this.add(squid2);

		bob2 = new Enemy(488, 500, "Bob");
		this.add(bob2);

		redShip = new Enemy(280, 50, "Ship");
		this.add(redShip);

		title = new ArrayList<Character>();
		subtitle = new ArrayList<Character>();
		bullets = new ArrayList<Bullet>();
		scoreDisplay = new ArrayList<Character>();
		powerUps = new ArrayList<PowerUp>();
		deaths = new ArrayList<Enemy>();
		highScoreDisplay = new ArrayList<Character>();
		types = new String[] {"Med", "Freeze", "Fire Speed", "Player Speed"};
		time = 150;
		playerSpeed = 10;
		fireSpeed = 3;
		frozen = false;
		highScore = 0;
		divider = new Divider(0, 530, 700, 5);
		


		String str1 = "space invaders";
		String str2 = "press b to begin";

		for (int i = 0; i < str1.length(); i++)
		{
			title.add(new Character(i * 24 + 147, 220, 4, str1.charAt(i)));
			this.add(title.get(i));
		}
		for (int i = 0; i < str2.length(); i++)
		{
			subtitle.add(new Character(i * 12 + 217, 280, 2, str2.charAt(i)));
			this.add(subtitle.get(i));
		}

		health = new HealthBar(415, 10);
		count = 0;
		fire = 0;
		eDx = 1;
		eFire = 27;
		began = false;
		settingUp = false;

		obstacles = new ArrayList<Obstacle>();
		enemies = new ArrayList<ArrayList<Enemy>>();
		player = new Player(280, 550);



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
					player.setDx(-1 * playerSpeed); //-speed is throw
				}

				if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					player.setDx(1 * playerSpeed); //speed is throw
				}

				if (e.getKeyCode() == KeyEvent.VK_F)
				{
					if (player.getPower() == 3)
					{
						player.setPower(0);
						bullets.add(new Bullet(player.getX() + 29, player.getY() - 20, false));
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

					jawn.remove(frog);
					jawn.remove(frog2);
					jawn.remove(squid);
					jawn.remove(squid2);
					jawn.remove(bob);
					jawn.remove(bob2);
					jawn.remove(redShip);

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
				for(Obstacle o : obstacles)
				{
					if (e.getKeyCode() == KeyEvent.VK_1)
					{
						bullets.add(new Bullet(o.getX() + 10, o.getY() - 100, true));
						jawn.add(bullets.get(bullets.size() - 1));
					}
					if (e.getKeyCode() == KeyEvent.VK_2)
					{
						bullets.add(new Bullet(o.getX() + 40, o.getY() - 100, true));
						jawn.add(bullets.get(bullets.size() - 1));
					}
					if (e.getKeyCode() == KeyEvent.VK_3)
					{
						bullets.add(new Bullet(o.getX() + 65, o.getY() - 100, true));
						jawn.add(bullets.get(bullets.size() - 1));
					}
				}
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

		redShip.setDx(-3);

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
		if(began && !nextLevel)
		{
			again(eFire);

		}
		else if(settingUp)
		{
			settingUp(eDx);
		}

		else //Title screen
		{
			if(count == 1)
			{	
				String str = "high score:";
				for (int i = 0; i < 11; i++)
				{
					Character c = new Character(10 + 19*i, 10, 3, str.charAt(i));
					this.add(c);
					highScoreDisplay.add(c);

				}
				File file = new File("HighScore.txt");
				try 
				{
					file.createNewFile();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				try
				{
					boolean temp = true;
					Scanner in = new Scanner(file);
					while(in.hasNext())
					{
						String num = in.next();
						highScore = Integer.parseInt(num);
						int numZeros = 7 - num.length();
						for(int k = 0; k < 7; k++)
						{
							if(k < numZeros)
							{
								Character c = new Character(220 + 20*k, 10, 3, '0');
								scoreDisplay.add(k, c);
							}
							else
							{
								Character c = new Character(220 + 20*k, 10, 3, num.charAt(k - numZeros));
								scoreDisplay.add(k, c);
							}			
						}

						for(int k = 0; k < scoreDisplay.size(); k++)
						{
							this.add(scoreDisplay.get(k));
						}
						temp = false;
					}
					if(temp)
					{
						String num = "0";
						int numZeros = 7 - num.length();
						for(int k = 0; k < 7; k++)
						{
							if(k < numZeros)
							{
								Character c = new Character(220 + 20*k, 10, 3, '0');
								scoreDisplay.add(k, c);
							}
							else
							{
								Character c = new Character(220 + 20*k, 10, 3, num.charAt(k - numZeros));
								scoreDisplay.add(k, c);
							}		
						}
						for(int k = 0; k < scoreDisplay.size(); k++)
						{
							this.add(scoreDisplay.get(k));
						}
					}
					in.close();
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
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
				bullets.add(new Bullet(redShip.getX() + 11, redShip.getY() + 30, true));
				this.add(bullets.get(bullets.size() - 1));
			}
			if ((count + 10) % 20 == 0)
			{
				bullets.add(new Bullet(redShip.getX() + redShip.getWidth() - 24, redShip.getY() + 30, true));
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

			redShip.update();

			if (redShip.getX() < 147 || redShip.getX() + redShip.getWidth() > 470)
			{
				redShip.setDx(redShip.getDx() * -1);
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

	public int total()
	{
		int total = 0;
		for(ArrayList<Enemy> en : enemies)
		{
			for(Enemy enemy : en)
			{
				total++;
			}
		}
		return total;
	}

	public void updateScore()
	{
		for(int k = 0; k < scoreDisplay.size(); k++)
		{
			this.remove(scoreDisplay.get(k));
		}


		String score2 = "" + this.score;
		int numZeros = 7 - score2.length();

		for(int k = 0; k < 7; k++)
		{
			if(k < numZeros)
			{
				Character c = new Character(130 + 20*k, 10, 3, '0');
				scoreDisplay.set(k, c);
			}
			else
			{
				Character c = new Character(130 + 20*k, 10, 3, score2.charAt(k - numZeros));
				scoreDisplay.set(k, c);
			}			
		}

		for(int k = 0; k < scoreDisplay.size(); k++)
		{
			this.add(scoreDisplay.get(k));
		}
	}
	
	public void reset()
	{
		count = 0;
		health.reset();
		enemies = new ArrayList<ArrayList<Enemy>>();
		obstacles = new ArrayList<Obstacle>();
		bullets = new ArrayList<Bullet>();
		scoreDisplay = new ArrayList<Character>();
		deaths = new ArrayList<Enemy>(); 
		this.getContentPane().removeAll();
		repaint();

	}

	public void settingUp(int eDx)
	{
		
		if(count == 1)
		{
			for(int i = 0; i < highScoreDisplay.size(); i++)
			{
				this.remove(highScoreDisplay.get(i));
			}
			for(int i = 0; i < scoreDisplay.size(); i++)
			{
				this.remove(scoreDisplay.get(i));
			}

			Obstacle obstacle = new Obstacle(76, 425);
			obstacles.add(obstacle);

			Obstacle obstacle1 = new Obstacle(213, 425);
			obstacles.add(obstacle1);

			Obstacle obstacle2 = new Obstacle(350, 425);
			obstacles.add(obstacle2);

			Obstacle obstacle3 = new Obstacle(487, 425);
			obstacles.add(obstacle3);
			for(Obstacle o : obstacles)
			{
				this.add(o);
				o.setVisible(false);
			}
		}
		player.setDx(0);
		player.setLocation(280, 550);

		int j = (count - 1) % 10;

		if (j == 0)
		{
			enemies.add(new ArrayList<Enemy>());
		}


		/* ****************************************************************************************************
		 * vv PLACING EACH ENEMY ACCORDING TO COUNT vv
		 * ****************************************************************************************************
		 */
		if (count > 40)
		{
			Enemy pillo = new Enemy(54 * j + 45, 300, "Bob");
			enemies.get(4).add(pillo);
			this.add(pillo);
			if (count == 50)
			{
				settingUp = false;
				nextLevel = false;
				began = true;
				this.add(redShip); /////////////////////////
				redShip.setLocation(-125, 50);
				redShip.setDx(0);
				this.add(health);

				String str = "score:";
				String s = this.score + "";
				int numZeros = 7 - s.length();
				for (int i = 0; i < 6; i++)
				{
					Character c = new Character(10 + 20*i, 10, 3, str.charAt(i));
					this.add(c);
				}

				for(int i = scoreDisplay.size() - 1; i >= 0 ; i--)
				{
					scoreDisplay.remove(i);
				}
				
				for(int k = 0; k < 7; k++)
				{
					if(k < numZeros)
					{
						Character c = new Character(130 + 20*k, 10, 3, '0');
						scoreDisplay.add(k, c);
					}
					else
					{
						Character c = new Character(130 + 20*k, 10, 3, s.charAt(k - numZeros));
						scoreDisplay.add(k, c);
					}			
				}

				for(int k = 0; k < scoreDisplay.size(); k++)
				{
					this.add(scoreDisplay.get(k));
				}

				String healthLabel = "hp ";
				for(int i = 0; i < healthLabel.length(); i++)
				{
					this.add(new Character(375 + 19*i, 10, 3, healthLabel.charAt(i)));
				}


				this.add(player);

				//Starts enemies going if left direction
				for(ArrayList<Enemy> h : enemies)
				{
					for(Enemy bruh : h)
					{
						bruh.setDx(-1 * eDx);
					}
				}
				this.eDx++;
				if (this.eFire > 2)
				{
					this.eFire -= 2;
				}
				
				this.add(divider);
				
				for(Obstacle o : obstacles)
				{
					o.setVisible(true);
				}
			}
		}
		else if(count > 30)
		{
			Enemy pillo = new Enemy(54 * j + 45, 250, "Bob");
			enemies.get(3).add(pillo);
			this.add(pillo);
		}
		else if(count > 20)
		{
			Enemy pillo = new Enemy(54 * j + 47, 200, "Frog");
			enemies.get(2).add(pillo);
			this.add(pillo);
		}
		else if(count > 10)
		{
			Enemy pillo = new Enemy(54 * j + 47, 150, "Frog");
			enemies.get(1).add(pillo);
			this.add(pillo);
		}
		else if(count > 0)
		{
			Enemy pillo = new Enemy(54 * j + 53, 100, "Squid");
			enemies.get(0).add(pillo);
			this.add(pillo);
		}
		
		
		//			/* ****************************************************************************************************
		//			 * ^^ PLACING EACH ENEMY ACCORDING TO COUNT ^^
		//			 * ****************************************************************************************************
		//			 */
	}



	public void again(int eFire)
	{
		for(int i = powerUps.size() - 1; i >= 0; i--)
		{
			powerUps.get(i).update();
			if(powerUps.get(i).getY() + powerUps.get(i).getHeight() > this.getContentPane().getHeight())
			{
				this.remove(powerUps.get(i));
				powerUps.remove(i);
			}
		}

		if(time == 0)
		{
			frozen = false;
			playerSpeed = 10;
			fireSpeed = 3;		
		}
		else 
		{
			time --;
		}

		if(!frozen)
		{
			for(int i = powerUps.size() - 1; i >= 0; i--)
			{	
				if(player.getBounds().intersects(powerUps.get(i).getBounds()))
				{
					frozen = false;
					time = 150;
					playerSpeed = 10;
					fireSpeed = 3;
					switch(powerUps.get(i).getType())
					{
					case "Med": 
						health.reset();
						this.remove(powerUps.get(i));
						powerUps.remove(i);
						break;
					case "Freeze":
						frozen = true;
						this.remove(powerUps.get(i));
						powerUps.remove(i);
						break;
					case "Fire Speed":
						fireSpeed = 1;
						this.remove(powerUps.get(i));
						powerUps.remove(i);
						break;
					case "Player Speed":			
						playerSpeed = 20;
						this.remove(powerUps.get(i));
						powerUps.remove(i);
						break;
					}
				}

			}
		}

		if(redShip.getX() == -125)
		{
			if((total() <= 30 && total() > 10) && !flag)
			{
				redShip.setDx(5);
			}
			else if(total() <= 10 && flag)
			{
				redShip.setDx(5);
			}
		}

		if(redShip.getX() > this.getContentPane().getWidth())
		{
			redShip.setDx(0);
			redShip.setLocation(1500, 50);
		}
		if(((total() <= 30 && total() > 10) && !flag && redShip.getX() == 1500) || (total() <= 10 && flag && redShip.getX() == 1500))
		{
			flag = !flag;
			redShip.setLocation(-125, 50);
		}

		for(int i = 0; i < deaths.size(); i++)
		{
			this.remove(deaths.get(i));
			deaths.remove(i);
			i--;
		}
		int tempScore = score;
		boolean hit = false;
		for(int i = bullets.size()-1; i >= 0; i--)
		{
			if(!bullets.get(i).isE())
			{

				if(redShip.getBounds().intersects(bullets.get(i).getBounds()))
				{
					deaths.add(new Enemy(redShip.getX(), redShip.getY(), "RedDead"));
					this.add(deaths.get(deaths.size()-1));
					redShip.setLocation(1500, 50);
					this.remove(bullets.get(i));
					hit = true;
					score += 200;
				}
			}

			if(hit)
			{
				bullets.remove(i);
				hit = false;
			}
		}



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

		hit = false;
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
							Enemy en = enemies.get(h).get(j);
							if(enemies.get(h).get(j).getName().equals("Bob"))
							{
								score += 10;
							}
							else if(enemies.get(h).get(j).getName().equals("Frog"))
							{
								score += 20;
							}
							else if(enemies.get(h).get(j).getName().equals("Squid"))
							{
								score += 30;
							}
							this.remove(enemies.get(h).get(j));
							enemies.get(h).remove(j);
							this.remove(bullets.get(i));
							hit = true;
							j--;	

							deaths.add(new Enemy(en.getX(), en.getY(), "Dead"));
							this.add(deaths.get(deaths.size() - 1));

							if(!frozen)
							{
								if((int) (Math.random() * 15) == 0)
								{
									String s = types[(int) (Math.random() * 4)];
									powerUps.add(new PowerUp(en.getX() + 13, en.getY(), s));
									this.add(powerUps.get(powerUps.size() - 1));
								}
							}
						}	
					}
				}
			}
			if(hit)
			{
				bullets.remove(i);
				updateScore();
				hit = false;
			}
			else if(tempScore != score)
			{
				updateScore();
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

		for(int i = 0; i < enemies.size(); i++)
		{
			if(enemies.get(i).isEmpty())
			{
				enemies.remove(i);
				i--;
			}
		}

		if(!frozen)
		{
			if(count % eFire == 0 &&  enemies.size() > 0)
			{
				int r = (int)(Math.random()*enemies.size());
				int c = (int)(Math.random()*enemies.get(r).size());
				Enemy pillo = enemies.get(r).get(c);
				bullets.add(new Bullet(pillo.getX() + pillo.getWidth()/2, pillo.getY() + pillo.getHeight(), true));
				this.add(bullets.get(bullets.size()-1));	
			}
		}



		/* ****************************************************************************************************
		 * UPDATING ENEMIES AND BULLETS, GAME OVER IF ENEMY TOUCHES PLAYER vv
		 * ****************************************************************************************************
		 */

		if(!frozen)
		{
			for (ArrayList<Enemy> arr : enemies)
			{
				for (Enemy enemy : arr)
				{
					if(count % 2 == 0)
					{
						enemy.update();
					}

					if (count % 20 == 0)
					{
						enemy.change();
					}
					if(enemy.getY() + enemy.getHeight() > divider.getY() + divider.getHeight() || health.getHealth() <= 0 ) 
					{
						String s = "";
						try
						{
							PrintWriter pw = new PrintWriter(new FileWriter("HighScore.txt"));
							if(score > highScore)
							{
								pw.println(score);
								s = "New High Score!";
							}
							else
							{
								pw.println(highScore);
								s = "Game Over";
							}
							pw.flush();
							pw.close();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(this, s, getTitle(), 0);
						System.exit(0);
					}
				}
			}
		}

			for (int i = 0; i < bullets.size(); i++)
			{
				if (bullets.get(i).isE() && bullets.get(i).isTouching(player))
				{
					health.setHealth(health.getHealth() - 40);
					this.remove(bullets.get(i));
					bullets.remove(i);
					i--;
				}
			}

			if(!frozen)
			{
				redShip.update();
			}

			if(enemies.size() == 0)
			{
				frozen = false;
				repaint();
				JOptionPane.showMessageDialog(this, "Level Complete", getTitle(), 1);
				nextLevel = true;
				began = false;
				settingUp = true;
				reset();
				return;
			}


			if(left() < 0)
			{
				for(ArrayList<Enemy> h : enemies)
				{
					for(Enemy bruh : h)
					{
						if(count % 2 == 0)
						{
							if(!frozen)
							{
								bruh.setDx(bruh.getDx() * -1);
								bruh.setLocation(bruh.getX(), bruh.getY() + 20);
							}
						}
					}
				}
			}
			if(right() > this.getContentPane().getWidth())
			{
				for(ArrayList<Enemy> h : enemies)
				{
					for(Enemy bruh : h)
					{
						if(count % 2 == 0)
						{
							if(!frozen)
							{
								bruh.setDx(bruh.getDx() * -1);
								bruh.setLocation(bruh.getX(), bruh.getY() + 20);
							}
						}
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

			for (Bullet b : bullets)
			{
				if((b.isE() && !frozen) || !b.isE()) 
				{
					b.update();
				}
			}

			if (player.getPower() < 3)
			{
				fire++;
				if (fire % fireSpeed == 0)
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
}
