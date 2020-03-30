import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;



	public class Mnu2 extends JFrame
	{
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	JMenuBar bar=new JMenuBar();
	JMenu Lottery=new JMenu("Lottery");
	JMenu Agent=new JMenu("Agent");
	JMenu Prize=new JMenu("Prize");
	JMenu Draw=new JMenu("Draw");
	JMenu About=new JMenu("About");
	JMenuItem an1=new JMenuItem("Add");
	JMenuItem an2=new JMenuItem("AddAgent");
	JMenuItem ds=new JMenuItem("Display");
	JMenuItem ds2=new JMenuItem("DisplayAgent");
	JMenuItem an3=new JMenuItem("AddPrize");
	JMenuItem ds3=new JMenuItem("View");
	JMenuItem gd=new JMenuItem("GetDraw");
	JMenuItem vw=new JMenuItem("View");
	JMenuItem about=new JMenuItem("About");
	Border blackline;
	JPanel pnl;
	ImageIcon Pic;
	JLabel PicLabel;
	
Mnu2()
{
		
		blackline = BorderFactory.createLineBorder(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocation(200,100);
	    Container C= getContentPane();
		C.setLayout(null);
		bar.setBorder(blackline);
		setJMenuBar(bar);
		bar.add(Lottery);
		bar.add(Agent);
		bar.add(Prize);
		bar.add(Draw);
		bar.add(About);
		
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBorder(BorderFactory.createTitledBorder(""));
		pnl.setBounds(10,10,460,420);
		C.add(pnl);
		
		Pic=new ImageIcon("Win.gif");
		PicLabel=new JLabel("");
		PicLabel.setSize(500,200);
		PicLabel.setLocation(50,50);
		PicLabel.setIcon(Pic);
		pnl.add(PicLabel);
		
		an1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
		an1.setToolTipText("Add new users here");
		an1.setForeground(Color.black);
		an1.setBackground(Color.white);
		an1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new TicketDetails();
			}
		});
		
		ds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,InputEvent.ALT_DOWN_MASK));
		ds.setToolTipText("Display users here");
		ds.setForeground(Color.black);
		ds.setBackground(Color.white);
		ds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new jtableshow1();
			}
		});
		Lottery.add(an1);
		Lottery.add(ds);
		
		
		an2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
		Agent.add(an2);
		an2.setToolTipText("Add new agents here");
		an2.setForeground(Color.black);
		an2.setBackground(Color.white);
		an2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AddAgent();
			}
		});
		ds2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,InputEvent.ALT_DOWN_MASK));
		Agent.add(ds2);
		ds2.setToolTipText("Display users here");
		ds2.setForeground(Color.black);
		ds2.setBackground(Color.white);
		ds2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new jtableshow2();
			}
		});
		
		
		an3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		Prize.add(an3);
		an3.setToolTipText("Add new Prizes here");
		an3.setForeground(Color.black);
		an3.setBackground(Color.white);
		an3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			 new AddPrize();	
			}
		});
		ds3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,InputEvent.ALT_DOWN_MASK));
		Prize.add(ds3);
		ds3.setToolTipText("Display prizes here");
		ds3.setForeground(Color.black);
		ds3.setBackground(Color.white);
		ds3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new jtableshow3();
			}
		});
		
		gd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
		Draw.add(gd);
		gd.setToolTipText("Add new Prizes here");
		gd.setForeground(Color.black);
		gd.setBackground(Color.white);
		gd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new LuckyDraw();
			}
		});
		vw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,InputEvent.ALT_DOWN_MASK));
		Draw.add(vw);
		vw.setToolTipText("Display prizes here");
		vw.setForeground(Color.black);
		vw.setBackground(Color.white);
		vw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new jtableshow4();
			}
		});
		
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
		About.add(about);
		about.setToolTipText("Add new Prizes here");
		about.setForeground(Color.black);
		about.setBackground(Color.white);
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new about();
			}
		});
		
		
		
		
		setVisible(true);
		setSize(500,500);
	}

	public static void main(String args[])
	{
		new Mnu2();
	}
}
