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
	Connection con=conn.connDb();
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
		
		try 
		{
			st=con.createStatement();
			rs=st.executeQuery("SELECGT * FROM clinic");
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
	

}
