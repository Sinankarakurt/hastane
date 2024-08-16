package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import Helper.*;

public class Clinic
{
	
	private int id;
	private String name;
//-------------------------------

	DBConnection conn=new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	
	PreparedStatement preparedStatement =null;
//-------------------------------------------
	
	public Clinic() {};
	
	//----------------------------------------------
	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
//---------------------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//------------------------------
	public ArrayList<Clinic> getClinicList() throws SQLException
	{
	
		ArrayList<Clinic> list=new ArrayList<Clinic>();
		Clinic obj;
		Connection con=conn.connDb();
		try 
		{
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM clinic");
			while(rs.next())
			{
				obj=new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	finally 
	{
		st.close();
		rs.close();
		con.close();
	}
		return list;
	}
		
//------------------------------------------------
	public boolean addClinic(String name)
	{
		Connection con=conn.connDb();
		String query="INSERT INTO clinic  (name) VALUES(?)";
		try {
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}

//-------------------------------------------------------
	
	public boolean deleteClinic(int id)
	{
		Connection con=conn.connDb();
		String query="DELETE FROM clinic WHERE id=?";
		boolean key=false;
		
		try
		{
			st=con.createStatement();
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

	
//--------------------------------------------
	
	public boolean updateClinic(int id,String name)
	{
		String query="UPDATE clinic SET name=? WHERE id=?";
		boolean key=false;
		Connection con=conn.connDb();
		
		try 
		{
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (key) 
			return true;
		else return false;
	}
		
	//------------------------------------------
	
	public Clinic getFetch(int id)
	{
		Connection con=conn.connDb();
		Clinic c=new Clinic();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM clinic WHERE id="+id);
			while (rs.next())
			{
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return c;
		
	}
	
	//-------------------------------------------------------------------

}
