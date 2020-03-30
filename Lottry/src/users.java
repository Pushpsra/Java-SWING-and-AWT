import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class users
{
	String UserId;
	String Password,SecurityQuestion,Answer;
	Connection con;
	PreparedStatement pstmt;
	users()
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
    }
	
	void SignUp(String Uid,String Pass,String SecQues,String Ans)
	{
		UserId=Uid;
		Password=Pass;
		SecurityQuestion=SecQues;
		Answer=Ans;
		try
		{
			
			pstmt=con.prepareStatement("insert into userTable values(?,?,?,?)");
			pstmt.setString(1,Uid);
			
			pstmt.setString(2,Pass);
			pstmt.setString(3,SecQues);
			pstmt.setString(4,Ans);
			int r=pstmt.executeUpdate();
			System.out.println("Account created!"+r);
			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	
	boolean logIn(String Uid,String Pass)
	{
	boolean present=false;
	try
	{
		pstmt=con.prepareStatement("Select * from userTable where Uid=? and Pass=?");
		pstmt.setString(1,Uid);
		pstmt.setString(2,Pass);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			present=true;
		}
		rs.close();
		pstmt.close();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return present;
	}
	
	String forgotPassword(String Usid,String SecQues,String Ans)
	{
		try
		{
			pstmt=con.prepareStatement("Select * from userTable where Uid=? and SecQue=? and Ans=?");
			pstmt.setString(1,Usid);
			pstmt.setString(2,SecQues);
			pstmt.setString(3,Ans);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				Password=rs.getString("Pass");
				
			}
			else
			{
				Password=null;
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return Password;
	}
	
	boolean checkID(String Usid)
	{
		boolean p=false;
		try
		{
			pstmt=con.prepareStatement("select * from userTable where Uid=?");
			pstmt.setString(1,Usid);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next())
			{
				p=true;
			}
		rs.close();
		pstmt.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return p;
	}
	
	users FetchOne(String Usid)
	{
		users temp=new users();
		temp.UserId=Usid;
		try
		{
			pstmt=con.prepareStatement("select * from userTable where Uid=?");
			pstmt.setString(1,Usid);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				temp.Password=rs.getString("Pass");
				temp.SecurityQuestion=rs.getString("SecQue");
				temp.Answer=rs.getString("Ans");
			}
			else
			{
				System.out.println("Invalid Account!");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return temp;
	}
	
}






