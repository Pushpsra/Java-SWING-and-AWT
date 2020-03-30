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
import java.util.Collections;
import java.util.List;

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
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Caret;


public class LuckyDraw extends JFrame implements ActionListener,ListSelectionListener
{
	JLabel Head1,Position,NPrizes;
	JTextField Pos,NP,Res;
	JPanel pnl1,pnl2,pnl3;
	JButton Shuffle,Dump;
	JComboBox langs;
	JList langs2;
	PreparedStatement pstmt;
	Border blackline;
	Connection con;
	ImageIcon ico1,ico2;
	String a[],d[],c[];
	String ar[];
	ArrayList<String> b;
	int l=0;
	
	LuckyDraw()
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
		pnl3.setBounds(30,50,415,210);
		C.add(pnl3);
		
		pnl2=new JPanel();
		pnl2.setLayout(null);
		pnl2.setBorder(BorderFactory.createTitledBorder("Result..."));
		pnl2.setBounds(30,265,415,180);
		C.add(pnl2);
		
		pnl1=new JPanel();
		pnl1.setLayout(null);
		pnl1.setBorder(BorderFactory.createTitledBorder(""));
		pnl1.setBounds(10,10,460,465);
		C.add(pnl1);
		//----
		    langs=new JComboBox();
			langs.addActionListener(this);
			langs.setBounds(20,50,150,30);
			pnl2.add(langs);
			addScroll();
			addScroll2();
		//----
		ico1=new ImageIcon("login.gif");
		ico2=new ImageIcon("create.gif");
		//-----
		
		Head1=new JLabel("Lucky Draw!");
		Head1.setSize(200,50);
		Head1.setLocation(200,0);
		Head1.setFont(new Font("Arial",Font.BOLD , 20));
		pnl1.add(Head1);
		
		Position=new JLabel("Position");
		Position.setSize(100,20);
		Position.setLocation(30,30);
		pnl3.add(Position);
		
		Pos=new JTextField();
		Pos.setSize(120,20);
		Pos.setLocation(200,30);
		pnl3.add(Pos);
		
		NPrizes=new JLabel("PrizeCount");
		NPrizes.setSize(100,20);
		NPrizes.setLocation(30,80);
		pnl3.add(NPrizes);
		
		NP=new JTextField();
		NP.setSize(120,20);
		NP.setLocation(200,80);
		pnl3.add(NP);
		
		Res=new JTextField();
		Res.setSize(120,20);
		Res.setLocation(220,50);
		pnl2.add(Res);
		//-------
		b=FetchAll();
		Shuffle=new JButton("Shuffle");
		Shuffle.setSize(130,20);
		Shuffle.setLocation(120,150);
		blackline = BorderFactory.createLineBorder(Color.black);
		Shuffle.setBorder(blackline);
		Shuffle.setIcon(ico1);
		Shuffle.addActionListener(new ActionListener() 
		  {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int s2=0;
				String s1=NP.getText();
				s2=Integer.parseInt(s1);
				Collections.shuffle(b);
				List<String> f = b.subList(0,s2);
				Res.setText(f.toString());
				for(int i=0;i<s2;i++)
				{
					
					
					try
					{
						pstmt=con.prepareStatement("select * from ticketdetails where LotteryNumber=?");
						pstmt.setString(1,b.get(i));
						ResultSet rs=pstmt.executeQuery();
						while(rs.next())
						{
							String i1=Pos.getText();
							String getaid=rs.getString("AgentId");
							String getnm=rs.getString("CustName");
							String getadd=rs.getString("CustAddress");
							String getcity=rs.getString("CustCity");
							String getph=rs.getString("CustPhone");
						
						System.out.println(getaid+""+getnm+""+getadd+""+getcity+""+getph);
						try
						{
							pstmt=con.prepareStatement("insert into custwin values (?,?,?,?,?,?,?)");
							pstmt.setString(1,i1);
							pstmt.setString(2,b.get(i));
							pstmt.setString(3,getaid);
							pstmt.setString(4,getnm);
							pstmt.setString(5,getcity);
							pstmt.setString(6,getadd);
							pstmt.setString(7,getph);
							pstmt.executeUpdate();
						}
						catch(Exception ex)
						{
							System.out.println(ex.toString());
						}
						}
					}
					catch(Exception ex)
					{
						ex.toString();
					}
					
				}
				b.removeAll(f);
				System.out.println(b);
			}
		});
		
	    pnl3.add(Shuffle);
	    
	    Dump=new JButton("Dump");
		Dump.setSize(130,20);
		Dump.setLocation(220,100);
		blackline = BorderFactory.createLineBorder(Color.black);
		Dump.setBorder(blackline);
		Dump.setIcon(ico2);
		Dump.addActionListener(new ActionListener() 
		  {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String i1=Pos.getText();
				String i2=NP.getText();
				String i3 = Res.getText();
				String i4="";
				try
				{
					pstmt=con.prepareStatement("select Prize from addprize where Position=?");
					pstmt.setString(1,i1);
					ResultSet rs=pstmt.executeQuery();
					if(rs.next())
					{
					i4=rs.getString("Prize");
					}
					else
					{
						System.out.println("Error");
					}
					System.out.println(i1+""+i2+""+i3+""+i4);
				}
				catch(Exception ex)
				{
					ex.toString();
				}
				
				try
				{
					pstmt=con.prepareStatement("Insert into CustomerWinner values (?,?,?,?)");
					pstmt.setString(1,i1);
					pstmt.setString(2,i4);
					pstmt.setString(3,i2);
					pstmt.setString(4,i3);
					int rs=pstmt.executeUpdate();
					System.out.println(rs);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
				new jtableShow();
				
				
				
			}
		});
	    pnl2.add(Dump);
		//---------------------
	    setSize(500,530);
		setVisible(true);
	}
    //----------
	
	ArrayList<String> FetchAll()
	{
		ArrayList<String> idd=new ArrayList<String>();
		try
		{
			pstmt=con.prepareStatement("select LotteryNumber from TicketDetails");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				idd.add(rs.getString("LotteryNumber"));
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		return(idd);
	}
	//----------
	/*String[] FetchAll()
	{
		int Count=0,i=0;
		try
		{
			pstmt=con.prepareStatement("select * from TicketDetails");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				Count++;
			}
			System.out.println(Count);
			a=new String[Count];

			pstmt=con.prepareStatement("select * from TicketDetails");
			ResultSet rs2=pstmt.executeQuery();
			while(rs2.next())
			{
				String s1=rs2.getString("LotteryNumber");
				a[i++]=s1;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return (a);
	}*/
	//----------
	void addScroll()
	{
		    b=FetchAll();
		    langs.removeAll();
		    for(String s:b)
		    {
		    	langs.addItem(s);
		    }
		    
	}
	//----------
	public void actionPerformed(ActionEvent arg0) 
	{
		String lng=langs.getSelectedItem().toString();

	}
	//---------
	String[] FetchAll2()
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
			c=new String[count];

			pstmt=con.prepareStatement("select * from addprize");
			ResultSet rs2=pstmt.executeQuery();
			while(rs2.next())
			{
				String s1=rs2.getString("Position");
				c[i++]=s1;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return (c);
	}

	@SuppressWarnings("rawtypes")
	void addScroll2()
	{
		    d=FetchAll2();
		    langs2=new JList(d);
		    langs2.setBounds(200,50,120,50);
			langs2.addListSelectionListener(this);
			JScrollPane scroll = new JScrollPane(langs2);
			scroll.setBounds(200,50, 120,20);
			pnl3.add(scroll);
	}

	public void valueChanged(ListSelectionEvent ev)
	{

	String lng=langs2.getSelectedValue().toString();
	Pos.setText(lng);
	String s2=Pos.getText();
	
	try
	{
		pstmt=con.prepareStatement("select Count from addprize where Position=?");
		pstmt.setString(1,s2);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())
		{
		String s1=rs.getString("Count");
		System.out.println(s1);
		NP.setText(s1);
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Inavlid attempt!!!..");
		}
	}
	catch(Exception ex)
	{
		System.out.println(ex.toString());
	}
	
	}
	//------
	public static void main(String args[])
	{
		new LuckyDraw();
	}
    //-------------
	
}