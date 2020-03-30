import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
//import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;


public class Wind2 extends JFrame implements ActionListener
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton SignUp,ForgotPass,LogIn,Fetch;
	JTextField Uid;
	JPasswordField Password;
	JLabel ULabel,PassLabel,Head,PicLabel;
	//Image Pi;
	JPanel pnl,pnl2,pnl3,pnl4;
	Border blackline;
	Connection con;
	PreparedStatement pstmt,pstmt2;
	ImageIcon ico1,ico2,ico3,ico4,Pic;
	String UserArr[];
	@SuppressWarnings("rawtypes")
	JList langs;
	int i,count;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Wind2()
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3308/dbms","root","bce");
		System.out.println("---------");
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		Container C= getContentPane();
		C.setLayout(null);
		
		//-------
		pnl2=new JPanel();
		pnl2.setLayout(null);
		pnl2.setBorder(BorderFactory.createTitledBorder(""));
		pnl2.setBounds(220,60,230,160);
		C.add(pnl2);
		
		pnl4=new JPanel();
		pnl4.setLayout(null);
		pnl4.setBorder(BorderFactory.createTitledBorder(""));
		pnl4.setBounds(30,80,140,100);
		C.add(pnl4);
		
		/*pnl3=new JPanel();
		pnl3.setLayout(null);
		pnl3.setBorder(BorderFactory.createTitledBorder(""));
		pnl3.setBounds(220,250,230,150);
		C.add(pnl3);*/
		
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBorder(BorderFactory.createTitledBorder(""));
		pnl.setBounds(10,10,460,230);
		C.add(pnl);
		//----
		
		try
		{
		pstmt2=con.prepareStatement("Select * from userTable");
		ResultSet rs2=pstmt2.executeQuery();
		while(rs2.next())
		{
			String s=rs2.getString("Uid");
			count++;
			System.out.println(s);
		}
		UserArr=new String[count];
		System.out.println(UserArr.length);
		pstmt2=con.prepareStatement("Select * from userTable");
		ResultSet rs3=pstmt2.executeQuery();
		while(rs3.next())
		{
			String s=rs3.getString("Uid");
			UserArr[i++]=s;
		}
		
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
			
		/*langs=new JList(UserArr);
		
		langs.addListSelectionListener(this);
		//langs.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scroll = new JScrollPane(langs);
		scroll.setBounds(100,30, 100,60);
		pnl2.add(scroll);*/
	
		//----
		ico1=new ImageIcon("login.gif");
		ico2=new ImageIcon("create.gif");
		ico3=new ImageIcon("forgot.gif");
		ico4=new ImageIcon("find.gif");
		
		//----
		
		Head=new JLabel("LOGIN");
		Head.setSize(150,50);
		Head.setLocation(20,0);
		Head.setFont(new Font("ARIAL",Font.BOLD , 20));
		pnl.add(Head);
		//----
		Pic=new ImageIcon("here1.jpg");
		PicLabel=new JLabel("");
		PicLabel.setSize(250,100);
		PicLabel.setLocation(10,0);
		PicLabel.setIcon(Pic);
		pnl4.add(PicLabel);
		
		//----
		
		ULabel=new JLabel("UserId");
		ULabel.setSize(100,20);
		ULabel.setLocation(10,10);
		pnl2.add(ULabel);
		
		Uid=new JTextField();
		Uid.setSize(100,20);
		Uid.setLocation(100,10);
		pnl2.add(Uid);
		//-------
		
		PassLabel=new JLabel("Password");
		PassLabel.setSize(100,20);
		PassLabel.setLocation(10,60);
		pnl2.add(PassLabel);
		
		Password=new JPasswordField();
		Password.setSize(100,20);
		Password.setLocation(100,60);
		pnl2.add(Password);
		
		/*Password=new JTextField();
		Password.setSize(100,20);
		Password.setLocation(100,100);
		pnl2.add(Password);*/
		//-------
		
		LogIn=new JButton("LogIn");
		LogIn.setSize(100,20);
		LogIn.setLocation(100,100);
		//LogIn.setIcon(ico1);
		LogIn.addActionListener(this);
		blackline = BorderFactory.createLineBorder(Color.black);
		LogIn.setBorder(blackline);
		LogIn.setIcon(ico1);
        pnl2.add(LogIn);
        //--------
        ForgotPass=new JButton("ForgotPassword");
        ForgotPass.setIcon(ico2);
        ForgotPass.setSize(150,20);
        ForgotPass.setLocation(300,400);
        ForgotPass.addActionListener(this);
        blackline = BorderFactory.createLineBorder(Color.black);
		ForgotPass.setBorder(blackline);
        pnl.add(ForgotPass);
        //------------
		
		/*SignUp=new JButton("SignUp");
		SignUp.setSize(100,20);
		SignUp.setLocation(60,30);
		//SignUp.setIcon(ico3);
        SignUp.addActionListener(this);
        blackline = BorderFactory.createLineBorder(Color.black);
		SignUp.setBorder(blackline);
		SignUp.setIcon(ico2);
        pnl3.add(SignUp);
        //--------
        
        ForgotPass=new JButton("ForgotPassword");
        ForgotPass.setSize(150,20);
        ForgotPass.setLocation(60,60);
        ForgotPass.addActionListener(this);
        blackline = BorderFactory.createLineBorder(Color.black);
		ForgotPass.setBorder(blackline);
		ForgotPass.setIcon(ico3);
        pnl3.add(ForgotPass);
        //--------
        
        Fetch=new JButton("Fetch");
		Fetch.setSize(100,20);
		Fetch.setLocation(60,90);
		Fetch.addActionListener(this);
		blackline = BorderFactory.createLineBorder(Color.black);
		Fetch.setBorder(blackline);
		Fetch.setIcon(ico4);
        pnl3.add(Fetch);*/
        
        setVisible(true);
        setSize(500,280);
        //--------
	}
	/*public void valueChanged(ListSelectionEvent ev)
	  {String lng=langs.getSelectedValue().toString();
		Uid.setText(lng);
		//JOptionPane.showMessageDialog(null, lng);
	  }*/
	public void actionPerformed(ActionEvent ev)
    {
    	
  
         	if(ev.getActionCommand().equals("LogIn"))
        	{
         		String un=Uid.getText();
             	String p=Password.getText();
         		boolean present=false;
         		try
         		{
         			if(un.equals("admin"))
         			{
         				JOptionPane.showMessageDialog(this,"Logged In!");
         				new SignUp();
         				
         			}
         			else
         			{
         			pstmt=con.prepareStatement("Select * from userTable where Uid=? and Pass=?");
         			pstmt.setString(1,un);
         			pstmt.setString(2,p);
         			ResultSet rs=pstmt.executeQuery();
         			while(rs.next())
         			{
         				present=true;
         			}
         			if(present==true)
         			{
        				JOptionPane.showMessageDialog(this,"Logged In!");
        				new Mnu2();
         			}
        			else
        				JOptionPane.showMessageDialog(this,"Invalid LogIn Attempt");
         			
         			
         			}
         			
         			
         		}
         		catch(Exception ex)
         		{
         			ex.printStackTrace();
         		}
    			
        	}
         	else if(ev.getActionCommand().equals("ForgotPassword"))
        	{
            new ForgotPass();	
        	}
    	/*else if(ev.getActionCommand().equals("SignUp"))
    	{
    		new SignUp();
				
    	}
    	
    	
    	
    	else if(ev.getActionCommand().equals("Fetch"))
    	{
    		new FetchOne();
    	}*/
    	else
    	{
    		JOptionPane.showMessageDialog(this,"Error!");
    	}
    }
	public static void main(String args[])
    {
    	new Wind2();
    	
    }
}
		
	