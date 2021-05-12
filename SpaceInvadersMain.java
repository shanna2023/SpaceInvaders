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
	private ArrayList<Entity> enemyFire;
	private ArrayList<Entity> friendlyFire;
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
	
	public SpaceInvadersMain()
	{
		this.setBounds(0, 0, 1250, 650);
		this.setLayout(null);
		this.setTitle("Space Invaders");
		this.getContentPane().setBackground(Color.BLACK);
		
		frog = new Entity(50, 50, "Frog");
		this.add(frog);
		
		squid = new Entity(150, 50, "Squid");
		this.add(squid);
		
		Entity hehe = new Entity(250, 50, "Ship");
		this.add(hehe);
		
		bob = new Entity(400, 50, "Bob");
		this.add(bob);
		
//		s = new Character(600, 200, 3, 'k');
//		this.add(s);
//		
//		char c = 'a';
//		for (int i = 0; i < 26; i++)
//		{
//			this.add(new Character(i * 25, 400, 3, c));
//			c++;
//		}
		
		String str1 = "space invaders";
		String str2 = "press b to begin";
		for (int i = 0; i < str1.length(); i++)
		{
			this.add(new Character(i * 60 + 250, 200, 10, str1.charAt(i)));
		}
		for (int i = 0; i < str2.length(); i++)
		{
			this.add(new Character(i * 20 + 500, 300, 3, str2.charAt(i)));
		}
		
		count = 0;
		fire = 0;

		enemyFire = new ArrayList<Entity>();
		friendlyFire = new ArrayList<Entity>();
		obstacles = new ArrayList<Entity>();
		enemies = new ArrayList<ArrayList<Entity>>();
		player = new Entity(600, 550, Entity.PLAYER);
		
		SpaceInvadersMain jawn = this;
		
		

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
						friendlyFire.add(new Entity(player.getX() + 34, player.getY() - 15, Color.BLUE));
						jawn.add(friendlyFire.get(friendlyFire.size() - 1));
						player.setPower(0);
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_M)
				{
					enemyFire.add(new Entity(bob.getX() + 34, bob.getY() + 50, Color.RED));
					jawn.add(enemyFire.get(enemyFire.size() - 1));
				}
				
				if (e.getKeyCode() == KeyEvent.VK_B && !began)
				{
					began = true;
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
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					
				}
			}

		});
		
		jawn.add(player);

		Timer t = new Timer(25, this);
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
		for (Entity b : friendlyFire)
		{
			b.update();
		}
		for (Entity b : enemyFire)
		{
			b.update();
		}
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
			}
		}
		
		if (count % 20 == 0)
		{
			frog.change();
			squid.change();
			bob.change();
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
		repaint();
	}
}
