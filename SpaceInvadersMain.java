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
	private ArrayList<Entity> enemies;
	private ArrayList<Entity> obstacles;
	private Entity player;
	private int count;
	
	public SpaceInvadersMain()
	{
		this.setBounds(50, 50, 800, 500);
		this.setLayout(null);
		this.setTitle("Space Invaders");
		this.getContentPane().setBackground(Color.BLACK);
		
		Entity ting = new Entity(50, 50, "Frog");
		this.add(ting);
		
		count = 0;

		enemyFire = new ArrayList<Entity>();
		friendlyFire = new ArrayList<Entity>();
		obstacles = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		player = new Entity(400, 350, Entity.PLAYER);
		
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
					friendlyFire.add(new Entity((player.getX() * 2 + player.getWidth())/2, player.getY() - 26, Color.BLUE));
					jawn.add(friendlyFire.get(friendlyFire.size() - 1));
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
		
		for (Entity enemy : enemies)
		{
			
		}
	}
}
