package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bashekim extends User 
{

	Connection con=conn.connDb();
	Statement st=null;
	ResultSet rs=null;
//------------------------------------------------------------------------------
	public Bashekim(int id, String tcno, String name, String pass, String type)
	{
		super(id, tcno, name, pass, type);
		// TODO Auto-generated constructor stub
	}
//--------------------------------------------------------------------
	public Bashekim() 
	{
		// TODO Auto-generated constructor stub
	}
//-----------------------------------------------------------	
	

	public ArrayList<User> getDoctorList() throws SQLException
	{
		ArrayList<User> list=new ArrayList<User>();
		User obj;
		try 
		{
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM user WHERE type='doktor'");
			while (rs.next()) 
			{
			obj=new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"), rs.getString("type"));
			list.add(obj);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			st.close();
			rs.close();
			con.close();
		}
	return list;
	
	}
	
}
