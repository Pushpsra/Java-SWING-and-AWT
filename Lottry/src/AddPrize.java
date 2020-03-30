import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AddPrize extends JFrame implements ListSelectionListener
{
	JLabel Position,Prize,Count,Head,PicLabel;
	JTextField Pos,Pze,Cnt;
	JButton New,Update,Close,Delete,Save;
	JPanel pnl1,pnl2,pnl3;
	JList<?> langs;
	JComboBox pss;
	PreparedStatement pstmt;
	Border blackline;
	Connection con;
	ImageIcon ico1,ico2,ico3,ico4,ico5,Pic;
	String a[],b[];
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	AddPrize()
	{
	
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3308/lottery","root","bce");
	System.out.println("---------");
	}
	catch(Exception ex)
	{
		System.out.println(ex.toString());
	}
    
	Container C= getContentPane();
	C.setLayout(null);
	
	pnl3=new JPanel();
	pnl3.setLayout(null);
	pnl3.setBorder(BorderFactory.createTitledBorder(""));
	pnl3.setBounds(390,40,250,310);
	C.add(pnl3);
	
	pnl2=new JPanel();
	pnl2.setLayout(null);
	pnl2.setBorder(BorderFactory.createTitledBorder(""));
	pnl2.setBounds(30,370,625,160);
	C.add(pnl2);
	
	pnl1=new JPanel();
	pnl1.setLayout(null);
	pnl1.setBorder(BorderFactory.createTitledBorder("AddPrize"));
	pnl1.setBounds(10,10,665,540);
	C.add(pnl1);
	//-------
	ico1=new ImageIcon("login.gif");
	ico2=new ImageIcon("create.gif");
	ico3=new ImageIcon("forgot.gif");
	ico4=new ImageIcon("find.gif");
	ico5=new ImageIcon("save.gif");
	Pic=new ImageIcon("gift_voucher.jpg");
	PicLabel=new JLabel("herepic");
	PicLabel.setSize(230,300);
	PicLabel.setLocation(10,10);
	PicLabel.setIcon(Pic);
	pnl3.add(PicLabel);
	
	
	//-----
	Head=new JLabel("Prize Details");
	Head.setSize(200,100);
	Head.setLocation(200,0);
	Head.setFont(new Font("Arial",Font.BOLD , 20));
	pnl1.add(Head);
	//------

	Position=new JLabel("Position");
	Position.setSize(100,20);
	Position.setLocation(30,80);
	pnl1.add(Position);
	
	Pos=new JTextField();
	Pos.setSize(120,20);
	Pos.setLocation(200,80);
	pnl1.add(Pos);
	
	Prize=new JLabel("Prize");
	Prize.setSize(100,20);
	Prize.setLocation(30,130);
	pnl1.add(Prize);
	
    Pze=new JTextField();
    Pze.setSize(120,20);
    Pze.setLocation(200,130);
	pnl1.add(Pze);
	
	Count=new JLabel("Count");
	Count.setSize(100,20);
	Count.setLocation(30,180);
	pnl1.add(Count);
	
	Cnt=new JTextField();
	Cnt.setSize(120,20);
	Cnt.setLocation(200,180);
	pnl1.add(Cnt);
	//-----
	addScroll();
	//------

	New=new JButton("New");
	New.setSize(130,20);
	New.setLocation(50,30);
	blackline = BorderFactory.createLineBorder(Color.black);
	New.setBorder(blackline);
	New.setIcon(ico1);
	New.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			NewP();
		}
	});
    pnl2.add(New);
	
    Update=new JButton("Update");
    Update.setSize(130,20);
    Update.setLocation(250,30);
	blackline = BorderFactory.createLineBorder(Color.black);
	Update.setBorder(blackline);
	Update.setIcon(ico2);
	Update.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String i1=Pos.getText();
			String i2=Pze.getText();
			String i3=Cnt.getText();
			//System.out.println(i1+""+i2+""+i3);
			UpdateP(i1,i2,i3);
			addScroll();
		}
	});
    pnl2.add(Update);
    
    Close=new JButton("Close");
    Close.setSize(130,20);
    Close.setLocation(450,30);
	blackline = BorderFactory.createLineBorder(Color.black);
	Close.setBorder(blackline);
	Close.setIcon(ico3);
	Close.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			CloseP();
			addScroll();
		}
	});
    pnl2.add(Close);
	
    Delete=new JButton("Delete");
    Delete.setSize(130,20);
	Delete.setLocation(50,100);
	blackline = BorderFactory.createLineBorder(Color.black);
	Delete.setBorder(blackline);
	Delete.setIcon(ico4);
	Delete.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String i1=Pos.getText();
			//String i2=Pze.getText();
			//String i3=Cnt.getText();
			//System.out.println(i1+""+i2+""+i3);
			DeleteP(i1);
			b=FetchAll();
		    langs=new JList(b);
		    addScroll();
		}
	});
    pnl2.add(Delete);
    
    Save=new JButton("Save");
    Save.setSize(130,20);
    Save.setLocation(250,100);
	blackline = BorderFactory.createLineBorder(Color.black);
    Save.setBorder(blackline);
    Save.setIcon(ico5);
    Save.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String i1=Pos.getText();
			String i2=Pze.getText();
			String i3=Cnt.getText();
			System.out.println(i1+""+i2+""+i3);
			SaveP(i1,i2,i3);
			//addScroll();
		}
	});
    pnl2.add(Save);
    //------
    
    setSize(700,600);
    setVisible(true);
	}
	
//------------------------------------------------------------------------	
void NewP()
{
	Pos.setText("");
	Pze.setText("");
	Cnt.setText("");
}

void UpdateP(String ps,String pz,String c)
{
	try
	{
	
	//--------
	/*pstmt=con.prepareStatement("select * from addprize where Position=?");
	pstmt.setString(1,ps);
	ResultSet rs=pstmt.executeQuery();
	String s1=rs.getString("Position");
	String s2=rs.getString("Prize");
	String s3=rs.getString("Count");
	System.out.println(s1+""+s2+""+s3);
	if(rs.next())
	{*/
	pstmt=con.prepareStatement("Update addprize set Prize=?,Count=? where Position=?");
	pstmt.setString(3,ps);
	pstmt.setString(1,pz);
	pstmt.setString(2,c);
	int r=pstmt.executeUpdate();
	System.out.println(r);
	if(r==1)
	JOptionPane.showMessageDialog(Update,"Record Updated!");
	else
	JOptionPane.showMessageDialog(Update,"Record Not Updated..");
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}

void CloseP()
{
	System.exit(0);
}

void DeleteP(String ps)
{
	try
	{ 	pstmt=con.prepareStatement("Delete from addprize where Position=?");
		pstmt.setString(1,ps);
		int r=pstmt.executeUpdate();
		System.out.println(r);
		if(r==1)
		JOptionPane.showMessageDialog(Delete,"Record Deleted!");
		else
		JOptionPane.showMessageDialog(Delete,"Record Not Deleted..");
		
	}
	catch(Exception ex)
	{
		System.out.println(ex.toString());
	}
	
}

void SaveP(String ps,String pz,String c)
{
	try
	{   
		pstmt=con.prepareStatement("insert into addprize values (?,?,?)");
		pstmt.setString(1,ps);
		pstmt.setString(2,pz);
		pstmt.setString(3,c);
		int r=pstmt.executeUpdate();
		//System.out.println(r);
		if(r==1)
		{
		JOptionPane.showMessageDialog(Save,"Record Saved!");
		}
		else
		JOptionPane.showMessageDialog(Save,"Record Not Saved..");
		
	}
	catch(Exception ex)
	{
			ex.printStackTrace();
	}
	
}

/*ArrayList<String> FetchAll()
{
	ArrayList<String> idd=new ArrayList<String>();
	try
	{
		pstmt=con.prepareStatement("select Position from addprize");
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			idd.add(rs.getString("Position"));
		}
		rs.close();
		pstmt.close();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return(idd);
}*/

String[] FetchAll()
{
	int count=0,i=0;
	try
	{
		pstmt=con.prepareStatement("select * from addprize");
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			count++;
		}
		//System.out.println(count);
		a=new String[count];

		pstmt=con.prepareStatement("select * from addprize");
		ResultSet rs2=pstmt.executeQuery();
		while(rs2.next())
		{
			String s1=rs2.getString("Position");
			a[i++]=s1;
		}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return (a);
}

@SuppressWarnings("rawtypes")
void addScroll()
{
	    b=FetchAll();
	    //System.out.println(b.length);
	    langs=new JList(b);
		
		langs.addListSelectionListener(this);
		//langs.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scroll = new JScrollPane(langs);
		//langs.getMode().add
		scroll.setBounds(200,100, 120,20);
		pnl1.add(scroll);
}

public void valueChanged(ListSelectionEvent ev)
{

String lng=langs.getSelectedValue().toString();
Pos.setText(lng);
		//JOptionPane.showMessageDialog(null, lng);
}


public static void main(String args[])
{
	new AddPrize();
}

}
