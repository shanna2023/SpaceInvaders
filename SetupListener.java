import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class SetupListener implements ActionListener
{
	private JFrame j;
	private ArrayList<ArrayList<Entity>> list;
	private int count;
	
	public SetupListener(JFrame j, ArrayList<ArrayList<Entity>> e)
	{
		count = 0;
		this.j = j;
		list = e;
	}
	
	public int getCount()
	{
		return count;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		j.add(list.get(0).get(count));
		System.out.println("Hi");
		j.repaint();
		count++;
	}

}
