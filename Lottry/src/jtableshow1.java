
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
class showInTable1 extends JFrame
{	
Connection con;
PreparedStatement pstmt;
ResultSet rs;
	showInTable1()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		 con =DriverManager.getConnection("jdbc:mysql://localhost:3308/lottery","root","bce");
		 fillTable();
		}
		catch(Exception ex){ex.printStackTrace();}
		
	}
   void fillTable() throws Exception
      {	   
	   String sql = "Select * from ticketdetails";
	    pstmt = con.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	   ResultSetMetaData md = rs.getMetaData();
	   
	   Vector columnNames = new Vector();
	   Vector data = new Vector();

	   int columns = md.getColumnCount();
	   for (int i = 1; i <= columns; i++) {
	   columnNames.addElement( md.getColumnName(i) );
	   }
	   while (rs.next())
	   {
	   Vector row = new Vector(columns);
	   for (int i = 1; i <= columns; i++){
	   row.addElement( rs.getObject(i) );
	   }
	   data.addElement( row );
	   }
	   rs.close();
	   pstmt.close();
	   
	   
	   JTable table = new JTable(data, columnNames);
	
	   JScrollPane scrollPane = new JScrollPane( table );
	   table.setBackground(Color.ORANGE);
	   table.setSelectionBackground(Color.white);
	  
	   Container c=getContentPane();
	   JPanel pnl=new JPanel();
	   pnl.setLayout(null);
	   pnl.setBackground(Color.pink);
	   
	   c.add(pnl);
	   
	   scrollPane.setBounds(50, 50,500,400);
	   scrollPane.setBackground(Color.magenta);
	   pnl.add( scrollPane );
	   
	   
	   setSize(600,500);
	   setVisible(true);
      }
}

public class jtableshow1 extends showInTable1
{
	public static void main(String args[])
{
	new showInTable1();
}
}

  


