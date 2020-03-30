import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AddAgent extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel AgentId,Name,City,Phone,Address,Head,PicLabel;
	JTextField AId,Nm,Cty,Ph,Add;
	JButton New,Update,Delete,Save,Search;
	JPanel pnl1,pnl2,pnl3;
	@SuppressWarnings("rawtypes")
	JComboBox langs;
	PreparedStatement pstmt;
	Border blackline;
	Connection con;
	ImageIcon ico1,ico2,ico3,ico4,ico5,Pic;
	String a[],b[];
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	AddAgent()
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
	pnl1.setBorder(BorderFactory.createTitledBorder("AddAgent"));
	pnl1.setBounds(10,10,665,540);
	C.add(pnl1);
	//-------
	ico1=new ImageIcon("login.gif");
	ico2=new ImageIcon("create.gif");
	ico3=new ImageIcon("forgot.gif");
	ico4=new ImageIcon("find.gif");
	ico5=new ImageIcon("save.gif");
	Pic=new ImageIcon("images1.jpg");
	PicLabel=new JLabel("");
	PicLabel.setSize(300,300);
	PicLabel.setLocation(20,10);
	PicLabel.setIcon(Pic);
	pnl3.add(PicLabel);
	
	
	//-----
	Head=new JLabel("Add Agent Details");
	Head.setSize(200,100);
	Head.setLocation(170,0);
	Head.setFont(new Font("Arial",Font.BOLD , 20));
	pnl1.add(Head);
	//------

	AgentId=new JLabel("AgentId");
	AgentId.setSize(100,20);
	AgentId.setLocation(30,80);
	pnl1.add(AgentId);
	
	AId=new JTextField();
	AId.setSize(120,20);
	AId.setLocation(200,80);
	pnl1.add(AId);
	
	Name=new JLabel("Name");
	Name.setSize(100,20);
	Name.setLocation(30,130);
	pnl1.add(Name);
	
    Nm=new JTextField();
    Nm.setSize(120,20);
    Nm.setLocation(200,130);
	pnl1.add(Nm);
	
	City=new JLabel("City");
	City.setSize(100,20);
	City.setLocation(30,180);
	pnl1.add(City);
	
	Cty=new JTextField();
	Cty.setSize(120,20);
	Cty.setLocation(200,180);
	pnl1.add(Cty);
	
	Phone=new JLabel("Phone");
	Phone.setSize(100,20);
	Phone.setLocation(30,230);
	pnl1.add(Phone);
	
	Ph=new JTextField();
	Ph.setSize(120,20);
	Ph.setLocation(200,230);
	pnl1.add(Ph);
	
	Address=new JLabel("Address");
	Address.setSize(100,20);
	Address.setLocation(30,280);
	pnl1.add(Address);
	
	Add=new JTextField();
	Add.setSize(120,20);
	Add.setLocation(200,280);
	pnl1.add(Add);
	//-----
	langs=new JComboBox();
	langs.addActionListener(this);
	//JScrollPane scroll = new JScrollPane(langs);
	langs.setBounds(200,100, 120,20);
	pnl1.add(langs);
	addScroll();
	
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
			String i1=AId.getText();
			String i2=Nm.getText();
			String i3=Cty.getText();
			String i4=Ph.getText();
			String i5=Add.getText();
			//System.out.println(i1+""+i2+""+i3);
			UpdateP(i1,i2,i3,i4,i5);
		}
	});
    pnl2.add(Update);
    
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
			String i1=AId.getText();
			//String i2=Nm.getText();
			//String i3=Cty.getText();
			//System.out.println(i1+""+i2+""+i3);
			DeleteP(i1);
			//b=FetchAll();
		    //langs=new JList(b);
		    //addScroll();
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
			String i1=AId.getText();
			String i2=Nm.getText();
			String i3=Cty.getText();
			String i4=Ph.getText();
			String i5=Add.getText();
			//System.out.println(i1+""+i2+""+i3);
			SaveP(i1,i2,i3,i4,i5);
			//addScroll();
		}
	});
    pnl2.add(Save);
    
    Search=new JButton("Search");
    Search.setSize(130,20);
    Search.setLocation(450,30);
	blackline = BorderFactory.createLineBorder(Color.black);
	Search.setBorder(blackline);
	Search.setIcon(ico3);
	Search.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String i=AId.getText();
			SearchP(i);
			addScroll();
		}
	});
    pnl2.add(Search);
    //------
    
    setSize(700,600);
    setVisible(true);
	}
	
//------------------------------------------------------------------------	
void NewP()
{
	AId.setText("");
	Nm.setText("");
	Cty.setText("");
	Ph.setText("");
	Add.setText("");
}

void UpdateP(String ad,String n,String c,String ph,String add)
{
	try
	{
	
	//--------
	/*pstmt=con.prepareStatement("select * from addName where AgentId=?");
	pstmt.setString(1,ps);
	ResultSet rs=pstmt.executeQuery();
	String s1=rs.getString("AgentId");
	String s2=rs.getString("Name");
	String s3=rs.getString("City");
	System.out.println(s1+""+s2+""+s3);
	if(rs.next())
	{*/
	pstmt=con.prepareStatement("Update addagent set Name=?,City=?,Phone=?,Address=? where AgentId=?");
	pstmt.setString(5,ad);
	pstmt.setString(1,n);
	pstmt.setString(2,c);
	pstmt.setString(3,ph);
	pstmt.setString(4,add);
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
void DeleteP(String ps)
{
	try
	{ 	pstmt=con.prepareStatement("Delete from addagent where AgentId=?");
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

void SaveP(String i1,String i2,String i3,String i4,String i5)
{
	try
	{   
		pstmt=con.prepareStatement("insert into addagent values (?,?,?,?,?)");
		pstmt.setString(1,i1);
		pstmt.setString(2,i2);
		pstmt.setString(3,i3);
		pstmt.setString(4,i4);
		pstmt.setString(5,i5);
		int r=pstmt.executeUpdate();
		//System.out.println(r);
		if(r==1)
		{
		JOptionPane.showMessageDialog(Save,"Record Saved!");
		addScroll();
		
		}
		else
		JOptionPane.showMessageDialog(Save,"Record Not Saved..");
		
	}
	catch(Exception ex)
	{
			ex.printStackTrace();
	}
	
}
String[] FetchAll()
{
	int Count=0,i=0;
	try
	{
		pstmt=con.prepareStatement("select * from addagent");
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			Count++;
		}
		//System.out.println(City);
		a=new String[Count];

		pstmt=con.prepareStatement("select * from addagent");
		ResultSet rs2=pstmt.executeQuery();
		while(rs2.next())
		{
			String s1=rs2.getString("AgentId");
			a[i++]=s1;
		}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return (a);
}

void SearchP(String i)
{
	try
	{
		pstmt=con.prepareStatement("select * from addagent where AgentId=?");
		pstmt.setString(1,i);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())
		{
			String i1=rs.getString("Name");
			String i2=rs.getString("City");
			String i3=rs.getString("Phone");
			String i4=rs.getString("Address");
			Nm.setText(i1);
			Cty.setText(i2);
			Ph.setText(i3);
			Add.setText(i4);
		}
	}
	catch(Exception ex)
	{
		ex.toString();
	}
	
}

@SuppressWarnings("rawtypes")
void addScroll()
{
	    b=FetchAll();
	    //System.out.println(b.length);
	    //
	    langs.removeAll();
	    for(String s:b)
	    {
	    	langs.addItem(s);
	    }
	    
		
		
}

public void actionPerformed(ActionEvent ev)
{
	
String lng=langs.getSelectedItem().toString();
AId.setText(lng);
		//JOptionPane.showMessageDialog(null, lng);
}


public static void main(String args[])
{
	new AddAgent();
}

}
