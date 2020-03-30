import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SignUp extends JFrame implements ActionListener, ListSelectionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton SinUp;
	JTextField Uid,Sques,Sans;
	JLabel ULabel,PassLabel,SecQue,Ans,Head;
	JPasswordField Password;

	JPanel pnl,pnl2;
	@SuppressWarnings("rawtypes")
	JList langs;
	Border blackline;
     @SuppressWarnings({ "unchecked", "rawtypes" })
	SignUp()
	{
		
		Container C= getContentPane();
		C.setLayout(null);
		pnl2=new JPanel();
		pnl2.setLayout(null);
		pnl2.setBorder(BorderFactory.createTitledBorder(""));
		pnl2.setBounds(30,100,420,250);
		C.add(pnl2);
		
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBorder(BorderFactory.createTitledBorder("SignUp..."));
		pnl.setBounds(10,10,460,440);
		C.add(pnl);
		
	    //------
		String []fil={"school name?","sister name?","daughter name?","phone number?","pet name?","brother name?"};
		langs=new JList(fil);

		langs.addListSelectionListener(this);
		//langs.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scroll = new JScrollPane(langs);
		scroll.setBounds(150, 170, 150,50);
		pnl2.add(scroll);
		
		//------
				Head=new JLabel("AddUser");
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
				
				PassLabel=new JLabel("Password");
				PassLabel.setSize(100,20);
				PassLabel.setLocation(50,100);
				pnl2.add(PassLabel);
				
				Password=new JPasswordField();
				Password.setSize(100,20);
				Password.setLocation(150,100);
				pnl2.add(Password);
				//-------
				
				SecQue=new JLabel("SecurityQues");
				SecQue.setSize(100,20);
				SecQue.setLocation(50,150);
				pnl2.add(SecQue);
				
				Sques=new JTextField();
				Sques.setSize(100,20);
				Sques.setLocation(150,150);
				pnl2.add(Sques);
				//-------
				
				Ans=new JLabel("Answer");
				Ans.setSize(100,20);
				Ans.setLocation(50,220);
				pnl2.add(Ans);
				
				Sans=new JTextField();
				Sans.setSize(100,20);
				Sans.setLocation(150,220);
				pnl2.add(Sans);
				//--------
               
				SinUp=new JButton("SignUp");
				SinUp.setSize(100,20);
				SinUp.setLocation(200,400);
				SinUp.addActionListener(this);
				blackline = BorderFactory.createLineBorder(Color.black);
				SinUp.setBorder(blackline);
		        pnl.add(SinUp);
		        //---------
		        
		        setVisible(true);
		        setSize(500,500);
		        //--------
			}
     public void valueChanged(ListSelectionEvent ev)
		
	  {
    	String lng=langs.getSelectedValue().toString();
    	Sques.setText(lng);
		//JOptionPane.showMessageDialog(null, lng);
	  }
     
     public void actionPerformed(ActionEvent ev)
     {
     	
     	String un=Uid.getText();
     	String p=Password.getText();
     	String s=Sques.getText();
     	String a=Sans.getText();
     	if(ev.getActionCommand().equals("SignUp"))
    	{
     		users u=new users();
    		boolean c=u.checkID(un);
			if(c==false)
			{	
    		u.SignUp(un,p,s,a);
    		JOptionPane.showMessageDialog(this,"Account Created!!");
			}
			else
				JOptionPane.showMessageDialog(this,"Account already exists!!");
				
    	}
     }
   	
   }
   		

