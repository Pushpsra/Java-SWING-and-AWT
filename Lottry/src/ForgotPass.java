import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

//forgotPassword
public class ForgotPass extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	users u=new users();
	JButton ForgotPass;
	JTextField Uid,Sques,Sans;
	JLabel ULabel,SecQue,Ans,Head;
	Border blackline;
	Connection con;
	PreparedStatement pstmt,pstmt2;
	String UserArr[],fil[],s3;
	
	JComboBox ud,sc;
	int i,k;
	
	
	ForgotPass()
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
		
		JPanel pnl2=new JPanel();
		pnl2.setLayout(null);
		pnl2.setBorder(BorderFactory.createTitledBorder(""));
		pnl2.setBounds(30,100,420,250);
		C.add(pnl2);
		
		JPanel pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBorder(BorderFactory.createTitledBorder("Get Password..."));
		pnl.setBounds(10,10,460,440);
		C.add(pnl);
		
		//----
		try
		{
		pstmt2=con.prepareStatement("Select * from userTable");
		ResultSet rs2=pstmt2.executeQuery();
		int count=0;
		while(rs2.next())
		{
			String s=rs2.getString("Uid");
			count++;
			System.out.println(s);
		}
		UserArr=new String[count];
		//fil=new String[count];
		System.out.println(UserArr.length);
		pstmt2=con.prepareStatement("Select * from userTable");
		ResultSet rs3=pstmt2.executeQuery();
		while(rs3.next())
		{
			String s=rs3.getString("Uid");
			UserArr[i++]=s;
			//String s1=rs3.getString("SecQue");
			//fil[i++]=s1;
		}
		for(int j=0;j<count;j++)
		{
		System.out.println(UserArr[j]);
		
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		try
		{
		pstmt2=con.prepareStatement("Select * from userTable");
		ResultSet rs2=pstmt2.executeQuery();
		int count=0;
		while(rs2.next())
		{
			String s=rs2.getString("Uid");
			count++;
			System.out.println(s);
		}
		fil=new String[count];
		System.out.println(fil.length);
		pstmt2=con.prepareStatement("Select * from userTable");
		ResultSet rs3=pstmt2.executeQuery();
		while(rs3.next())
		{
			String s1=rs3.getString("SecQue");
			fil[k++]=s1;
		}
		for(int l=0;l<count;l++)
		{
		System.out.println(fil[l]);
		
		}
		
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		ud=new JComboBox(UserArr);
		sc=new JComboBox(fil);
		ud.setBounds(150,70, 130, 20);
		sc.setBounds(150,100, 130, 20);
		ud.addActionListener(new ActionListener()
		  {	@Override
				public void actionPerformed(ActionEvent arg0) 
		  		{
			  String lng=ud.getSelectedItem().toString();
		    	Uid.setText(lng);
				
					int indx= ud.getSelectedIndex();	
					sc.setSelectedIndex(indx);
				
		  		}
			});
		
		sc.addActionListener(new ActionListener() 
			  {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					int indx= sc.getSelectedIndex();	
					s3=fil[indx];
					
					
				}
			});
		pnl2.add(ud);
		pnl2.add(sc);
		//----
		Head=new JLabel("Get Password");
		Head.setSize(200,100);
		Head.setLocation(200,0);
		Head.setFont(new Font("Arial",Font.BOLD , 20));
		pnl.add(Head);
		//------
	
		ULabel=new JLabel("UserId");
		ULabel.setSize(100,20);
		ULabel.setLocation(50,50);
		pnl2.add(ULabel);
		
		Uid=new JTextField();
		Uid.setSize(100,20);
		Uid.setLocation(150,50);
		pnl2.add(Uid);
		//-------
		
		SecQue=new JLabel("SecurityQues");
		SecQue.setSize(100,20);
		SecQue.setLocation(50,100);
		pnl2.add(SecQue);
		
		/*Sques=new JTextField();
		Sques.setSize(100,20);
		Sques.setLocation(150,100);
		pnl2.add(Sques);*/
		//-------
		
		Ans=new JLabel("Answer");
		Ans.setSize(100,20);
		Ans.setLocation(50,150);
		pnl2.add(Ans);
		
		Sans=new JTextField();
		Sans.setSize(100,20);
		Sans.setLocation(150,150);
		pnl2.add(Sans);
		//--------
		
		ForgotPass=new JButton("GetPassword");
        ForgotPass.setSize(150,20);
        ForgotPass.setLocation(200,400);
        ForgotPass.addActionListener(
        		new ActionListener()
        		{
        		public void actionPerformed(ActionEvent ev)
        	    {
        	    	
        	    	String un=Uid.getText();
        	    	String s=s3;
        	    	String a=Sans.getText();
        	    	
        	    	if(ev.getActionCommand().equals("GetPassword"))
        	    	{
        	    	
        	    		String ps=u.forgotPassword(un,s,a);
        	    		if(ps!=null)
        	    		JOptionPane.showMessageDialog(ForgotPass, ps);
        	    		else
        	    	    JOptionPane.showMessageDialog(ForgotPass,"Account Does Not Exist!");
        	    	}
        	    }
        	    });
        
        blackline = BorderFactory.createLineBorder(Color.black);
		ForgotPass.setBorder(blackline);
        pnl.add(ForgotPass);
        //--------

        setVisible(true);
        setSize(500,500);
        //--------
	}
	
	
    

}

