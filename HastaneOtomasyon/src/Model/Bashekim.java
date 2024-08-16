package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Bashekim extends User 
{

	Connection con=conn.connDb();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
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
	
	return list;
	
	}
//----------------------------------------------------------

	public ArrayList<User> getClinicDoctorList(int clinic_id)//throws SQLException
	
	{
		ArrayList<User> list=new ArrayList<User>();
		User obj;
		String query="SELECT u.id,u.tcno,u.type,u.name FROM worker w LEFT JOIN user u ON w.user_id=u.id WHERE clinic_id="+clinic_id;
		try {
			st=con.createStatement();
			rs=st.executeQuery(query);
			
			while (rs.next()) 
			{
				obj=new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}
//-----------------------------------------------------------
	
	public boolean addDoctor(String tcno, String pass, String name)// throws SQLException
	{
		String query="INSERT INTO user (tcno,password,name,type) values(?,?,?,?)";
		boolean key=false;
		try 
		{
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, pass);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key=true;
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key) 
			return true;
		else 
			return false;
	}
//--------------------------------------------------------------------------------------------
	
	public boolean deleteDoctor(int id) //throws SQLException
	{
		String query="DELETE  FROM user WHERE id=?";
		boolean key=false;
		try 
		{
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key) 
			return true;
		else 
			return false;
	
		}
	
	
//---------------------------------------------------------------------------------
	public boolean updateDoctor(int id, String tcno,String name, String pass) //throws SQLException
	{
		String query="UPDATE user SET tcno=?, password=?,name=? WHERE id=?";
		boolean key=false;
		
		try 
		{
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, pass);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key=true;
		} catch (SQLException e)
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (key) 
			return true;
		return false;
	}
	
	
//----------------------------
	
	public boolean addWorker(int user_id,int clinic_id) throws SQLException
	{
		
		String query="INSERT INTO worker (user_id,clinic_id) VALUES(?,?)";
				
		boolean key=false;
		int count=0;
		Connection con=conn.connDb();
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM worker WHERE clinic_id="+clinic_id+" AND user_id="+user_id);
			while (rs.next())
			{
				count++;
			}
			if (count==0) {
				preparedStatement=con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);
				preparedStatement.executeUpdate();
			}
		
			key=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key) 
			return true;
		else
			return false;
		
	}
	
	
//-----------------------------------
}
