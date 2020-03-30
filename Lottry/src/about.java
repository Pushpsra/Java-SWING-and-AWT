import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class about extends JFrame
{

	JLabel PicLabel;
	JLabel Abt,Abt1,Abt2;
	JPanel pnl1,pnl2;
	ImageIcon Pic;
	
	about()
	{
	Container C= getContentPane();
	C.setLayout(null);
	
	pnl1=new JPanel();
	pnl1.setLayout(null);
	pnl1.setBorder(BorderFactory.createTitledBorder(""));
	pnl1.setBounds(20,20,440,180);
	C.add(pnl1);
	
	pnl2=new JPanel();
	pnl2.setLayout(null);
	pnl2.setBorder(BorderFactory.createTitledBorder(""));
	pnl2.setBounds(20,220,440,220);
	C.add(pnl2);
	
	Pic=new ImageIcon("here4.jpg");
	PicLabel=new JLabel("");
	PicLabel.setSize(400,200);
	PicLabel.setLocation(20,10);
	PicLabel.setIcon(Pic);
	pnl2.add(PicLabel);
	
	Abt=new JLabel("This project has been made by:");
	Abt.setSize(200,20);
	Abt.setLocation(10,10);
	pnl1.add(Abt);
	
	Abt1=new JLabel("Pushp Sra(11103535)");
	Abt1.setSize(200,20);
	Abt1.setLocation(10,50);
	pnl1.add(Abt1);
	
	Abt2=new JLabel("Under the supereme guidance of--");
	Abt2.setSize(200,20);
	Abt2.setLocation(150,100);
	pnl1.add(Abt2);
	
	setSize(500,500);
	setVisible(true);
	}
	public static void main(String args[])
	{
		new about();
	}
	
}
