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


public class TicketDetails extends JFrame implements ListSelectionListener 
{

	JLabel LotteryNumber,AgentId,CustName,CustAdd,CustCity,CustPhone,Head,PicLabel;
	JTextField Ln,AId,CNm,CAdd,CCty,CPh;
	JButton New,Submit,CancelTicket,Close;
	JPanel pnl1,pnl3;
	@SuppressWarnings("rawtypes")
	JList langs;
	PreparedStatement pstmt;
	Border blackline;
	Connection con;
	ImageIcon ico1,ico2,ico3,ico4;
	String a[],b[];
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	TicketDetails()
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
	pnl3.setBounds(390,40,250,320);
	C.add(pnl3);
	
	
	pnl1=new JPanel();
	pnl1.setLayout(null);
	pnl1.setBorder(BorderFactory.createTitledBorder("TicketDetails.."));
	pnl1.setBounds(10,10,665,400);
	C.add(pnl1);
	//-------
	ico1=new ImageIcon("login.gif");
	ico2=new ImageIcon("create.gif");
	ico3=new ImageIcon("forgot.gif");
	ico4=new ImageIcon("find.gif");

	//-----
	Head=new JLabel("Ticket Details");
	Head.setSize(200,100);
	Head.setLocation(200,0);
	Head.setFont(new Font("Arial",Font.BOLD , 20));
	pnl1.add(Head);
	//------
	
	LotteryNumber=new JLabel("LotteryNumber");
	LotteryNumber.setSize(100,20);
	LotteryNumber.setLocation(30,80);
	pnl1.add(LotteryNumber);
	
	Ln=new JTextField();
	Ln.setSize(120,20);
	Ln.setLocation(200,80);
	pnl1.add(Ln);

	AgentId=new JLabel("AgentId");
	AgentId.setSize(100,20);
	AgentId.setLocation(30,130);
	pnl1.add(AgentId);
	
	AId=new JTextField();
	AId.setSize(120,20);
	AId.setLocation(200,130);
	pnl1.add(AId);
	
	CustName=new JLabel("CustName");
	CustName.setSize(100,20);
	CustName.setLocation(30,180);
	pnl1.add(CustName);
	
    CNm=new JTextField();
    CNm.setSize(120,20);
    CNm.setLocation(200,180);
	pnl1.add(CNm);
	
	CustCity=new JLabel("CustCity");
	CustCity.setSize(100,20);
	CustCity.setLocation(30,230);
	pnl1.add(CustCity);
	
	CCty=new JTextField();
	CCty.setSize(120,20);
	CCty.setLocation(200,230);
	pnl1.add(CCty);
	
	CustPhone=new JLabel("CCustPhone");
	CustPhone.setSize(100,20);
	CustPhone.setLocation(30,280);
	pnl1.add(CustPhone);
	
	CPh=new JTextField();
	CPh.setSize(120,20);
	CPh.setLocation(200,280);
	pnl1.add(CPh);
	
	CustAdd=new JLabel("Custadd");
	CustAdd.setSize(100,20);
	CustAdd.setLocation(30,330);
	pnl1.add(CustAdd);
	
	CAdd=new JTextField();
	CAdd.setSize(120,20);
	CAdd.setLocation(200,330);
	pnl1.add(CAdd);
	//-----
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
    pnl3.add(New);
	
    
    CancelTicket=new JButton("CancelTicket");
    CancelTicket.setSize(130,20);
	CancelTicket.setLocation(50,80);
	blackline = BorderFactory.createLineBorder(Color.black);
	CancelTicket.setBorder(blackline);
	CancelTicket.setIcon(ico4);
	CancelTicket.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String i1=Ln.getText();
			//String i2=CNm.getText();
			//String i3=CCty.getText();
			//System.out.println(i1+""+i2+""+i3);
			CancelTicketP(i1);
			b=FetchAll();
		    langs=new JList(b);
		    addScroll();
		}
	});
    pnl3.add(CancelTicket);
    
    Submit=new JButton("Submit");
    Submit.setSize(130,20);
    Submit.setLocation(50,130);
	blackline = BorderFactory.createLineBorder(Color.black);
    Submit.setBorder(blackline);
    Submit.setIcon(ico2);
    Submit.addActionListener(new ActionListener() 
	  {
		@Override
		public void actionPerformed(ActionEvent e) 
		{   
			String i1=Ln.getText();
			String i2=AId.getText();
			String i3=CNm.getText();
			String i4=CCty.getText();
			String i5=CPh.getText();
			String i6=CAdd.getText();
			//System.out.println(i1+""+i2+""+i3);
			SubmitP(i1,i2,i3,i4,i5,i6);
			//addScroll();
		}
	});
    pnl3.add(Submit);
    
    Close=new JButton("Close");
    Close.setSize(130,20);
    Close.setLocation(50,180);
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
    pnl3.add(Close);
    setSize(700,460);
    setVisible(true);
	}

	
	
	
//------------------------------------------------------------------------	
void NewP()
{   
	Ln.setText("");
	AId.setText("");
	CNm.setText("");
	CCty.setText("");
	CPh.setText("");
    CAdd.setText("");
}

void CancelTicketP(String ps)
{
	try
	{ 	pstmt=con.prepareStatement("Delete from TicketDetails where LotteryNumber=?");
		pstmt.setString(1,ps);
		int r=pstmt.executeUpdate();
		System.out.println(r);
		if(r==1)
		JOptionPane.showMessageDialog(CancelTicket,"Record Canceled!");
		else
		JOptionPane.showMessageDialog(CancelTicket,"Record Not Canceled..");
		
	}
	catch(Exception ex)
	{
		System.out.println(ex.toString());
	}
	
}

void SubmitP(String i1,String i2,String i3,String i4,String i5,String i6)
{
	try
	{   
		pstmt=con.prepareStatement("insert into TicketDetails values (?,?,?,?,?,?)");
		pstmt.setString(1,i1);
		pstmt.setString(2,i2);
		pstmt.setString(3,i3);
		pstmt.setString(4,i4);
		pstmt.setString(5,i5);
		pstmt.setString(6,i6);
		int r=pstmt.executeUpdate();
		//System.out.println(r);
		if(r==1)
		{
		JOptionPane.showMessageDialog(Submit,"Record Submited!");
		}
		else
		JOptionPane.showMessageDialog(Submit,"Record Not Submited..");
		
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
		//System.out.println(CustCity);
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


@SuppressWarnings("rawtypes")
void addScroll()
{
	    b=FetchAll();
	    //System.out.println(b.length);
	    langs=new JList(b);
		
		langs.addListSelectionListener(this);
		JScrollPane scroll = new JScrollPane(langs);
		scroll.setBounds(200,150, 120,20);
		pnl1.add(scroll);
}

public void valueChanged(ListSelectionEvent ev)
{
	
String lng=langs.getSelectedValue().toString();
AId.setText(lng);
		//JOptionPane.showMessageDialog(null, lng);
}


public static void main(String args[])
{
	new TicketDetails();
}

}
