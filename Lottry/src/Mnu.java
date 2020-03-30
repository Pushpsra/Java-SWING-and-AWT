import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;


public class Mnu extends JFrame
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JMenuBar bar=new JMenuBar();
JMenu users=new JMenu("Users");
JMenuItem an=new JMenuItem("AddNew");
JMenuItem sa=new JMenuItem("ShowAll");
JMenuItem ex=new JMenuItem("Exit");
JTextArea ar;
Mnu()
{
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200,100);
    Container C= getContentPane();
	C.setLayout(null);
	setJMenuBar(bar);
	bar.add(users);
	an.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
	users.add(an);
	an.setToolTipText("Add new users here");
	an.setForeground(Color.orange);
	an.setBackground(Color.white);
	users.add(sa);
	users.add(ex);
	an.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	});
	
	ar=new JTextArea();
	ar.setLocation(100,200);
	ar.setSize(200,200);
	C.add(ar);
	ar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	setVisible(true);
	setSize(500,500);
	
}
		
	public static void main(String args[])
	{
		new Mnu();
	}
	
}
	
