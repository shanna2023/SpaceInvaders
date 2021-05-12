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
	private ArrayList<ArrayList<Entity>> enemies;
	private ArrayList<Entity> obstacles;
	private Entity player;
	private int count;
	private int fire;
	private Entity frog;
	private Entity squid;
	private Entity bob;
	private Character s;
	private boolean began;
	private ArrayList<Character> title;
	private ArrayList<Character> subtitle;
	private ArrayList<Entity> bullets;
	
	public SpaceInvadersMain()
	{
		enemies = new ArrayList<ArrayList<Entity>>();
		this.setBounds(0, 0, 1250, 650);
		this.setLayout(null);
		this.setTitle("Space Invaders");
		this.getContentPane().setBackground(Color.BLACK);
		this.setResizable(false);

		
//		frog = new Entity(50, 150, "Frog");
//		this.add(frog);
//		
//		squid = new Entity(150, 150, "Squid");
//		this.add(squid);
//		
//		Entity hehe = new Entity(250, 150, "Ship");
//		this.add(hehe);
//		
//		bob = new Entity(400, 50, "Bob");
//		this.add(bob);
//		
//		s = new Character(600, 200, 3, 'k');
//		this.add(s);
//		
//		char c = 'a';
//		for (int i = 0; i < 26; i++)
//		{
//			this.add(new Character(i * 25, 400, 3, c));
//			c++;
//		}

		
		title = new ArrayList<Character>();
		subtitle = new ArrayList<Character>();
		bullets = new ArrayList<Entity>();
		
		String str1 = "space invaders";
		String str2 = "press b to begin";
		for (int i = 0; i < str1.length(); i++)
		{
			title.add(new Character(i * 60 + 250, 200, 10, str1.charAt(i)));
			this.add(title.get(i));
		}
		for (int i = 0; i < str2.length(); i++)
		{
			subtitle.add(new Character(i * 20 + 500, 300, 3, str2.charAt(i)));
			this.add(subtitle.get(i));
		}
		
		count = 0;
		fire = 0;

		obstacles = new ArrayList<Entity>();
		enemies = new ArrayList<ArrayList<Entity>>();
		player = new Entity(600, 550, Entity.PLAYER);
		
		SpaceInvadersMain jawn = this;
		
		Timer t = new Timer(25, this);

		this.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setDx(-5);
				}
				
				if (e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setDx(5);
				}
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					if (player.getPower() == 3)
					{
						player.setPower(0);
						bullets.add(new Entity(player.getX() + 33, player.getY() - 20, Color.BLUE));
						jawn.add(bullets.get(bullets.size() - 1));
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_B && !began)
				{
					began = true;
					for(Character str: title)
						str.setVisible(false);
					for(Character str: subtitle)
						str.setVisible(false);
					for(Entity o: obstacles)
						add(o);
					jawn.add(player);
					for(ArrayList<Entity> en: enemies)
					{
						for(Entity obj: en)
							add(obj);
					}
					t.start();
					for(int i = 0; i < 4; i++)
					{
						for(int j = 0; j < 18; j++)
						{
							Entity pillo = new Entity(72 * j + 20, 10, "Squid");
							enemies.add(new ArrayList<Entity>());
							enemies.get(i).add(pillo);
							jawn.add(pillo);
						}
					}
					
					for(int i = 0; i < 4; i++)
					{
						for(int j = 0; j < 18; j++)
						{
							Entity pillo = new Entity(72 * j + 12, 60, "Frog");
							enemies.add(new ArrayList<Entity>());
							enemies.get(i).add(pillo);
							jawn.add(pillo);
						}
					}
					
					for(int i = 0; i < 4; i++)
					{
						for(int j = 0; j < 18; j++)
						{
							Entity pillo = new Entity(72 * j + 12, 110, "Frog");
							enemies.add(new ArrayList<Entity>());
							enemies.get(i).add(pillo);
							jawn.add(pillo);
						}
					}
					
					for(int i = 0; i < 4; i++)
					{
						for(int j = 0; j < 18; j++)
						{
							Entity pillo = new Entity(72 * j + 10, 160, "Bob");
							enemies.add(new ArrayList<Entity>());
							enemies.get(i).add(pillo);
							jawn.add(pillo);
						}
					}
					
					for(int i = 0; i < 4; i++)
					{
						for(int j = 0; j < 18; j++)
						{
							Entity pillo = new Entity(72 * j + 10, 210, "Bob");
							enemies.add(new ArrayList<Entity>());
							enemies.get(i).add(pillo);
							jawn.add(pillo);
						}
					}
					
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setDx(0);
				}
				
				if (e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setDx(0);
				}
			}

		});
		
		jawn.add(player);
		
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
		if (player.getX() >= 0 && player.getX() + player.getWidth() < this.getWidth() + this.getInsets().left + this.getInsets().right)
		{
			if (player.getX() + player.getDx() < 0)
			{
				player.setLocation(0, player.getY());
			}
			else
			{
				player.update();
			}
		}
		
		for (ArrayList<Entity> arr : enemies)
		{
			for (Entity enemy : arr)
			{
				enemy.update();
				if(count % 20 == 0)
				{
					System.out.println("Hi");
					enemy.change();
					
				}
			}
		}
		
		for (Entity b : bullets)
		{
			b.update();
		}
		
//		if (count % 20 == 0)
//		{
//			frog.change();
//			squid.change();
//			bob.change();
//		}
		
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
		repaint();
	}
	
}
