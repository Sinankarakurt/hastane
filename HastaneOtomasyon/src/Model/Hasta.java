package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.*;

public class Hasta extends User 
{
	DBConnection conn=new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
//-----------------------------------------------------------------
	public Hasta() {
	
		// TODO Auto-generated constructor stub
	}
//-------------------------------------------------------------------
	public Hasta(int id, String tcno, String name, String pass, String type) {
		super(id, tcno, name, pass, type);
		// TODO Auto-generated constructor stub
	}
//----------------------------------------------------------------------------
	
	
	public boolean register(String tcno,String pass,String name) throws SQLException
	{
		Connection con=conn.connDb();
		
		int key=0;
		boolean duplicate=false;
		
		String query="INSERT INTO user (tcno,password,name,type)  VALUES(?,?,?,?)";
		
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM user WHERE tcno='"+tcno+"'");
			
			while (rs.next()) 
			{
				duplicate=true;
				Helper.showMsg("Bu tc numarasına ait bir kayıt bulunmaktadır.");
				break;
			}
			
			if (!duplicate)
			{
					  preparedStatement=con.prepareStatement(query);
					  preparedStatement.setString(1, tcno);
					  preparedStatement.setString(2, pass);
					  preparedStatement.setString(3, name);
					  preparedStatement.setString(4,"hasta");
					  preparedStatement.executeUpdate();
					  key=1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		if (key==1)
			return true;
		else 
			return false;
	}
//-----------------------------------------------------------------------------------------------

	public boolean addAppointmennt(int doctor_id,int hasta_id,String doctor_name,String hasta_name,String appdate) throws SQLException
	{
		Connection con=conn.connDb();
		
		int key=0;
		boolean duplicate=false;
		
		String query="INSERT INTO appointment (doctor_id,doctor_name,hasta_id,hasta_name,app_date)  VALUES(?,?,?,?,?)";
		
		
		try {
			          st=con.createStatement();
					  preparedStatement=con.prepareStatement(query);
					  preparedStatement.setInt(1, doctor_id);
					  preparedStatement.setString(2, doctor_name);
					  preparedStatement.setInt(3, hasta_id);
					  preparedStatement.setString(4, hasta_name);
					  preparedStatement.setString(5, appdate);
					  preparedStatement.executeUpdate();
					 
					  key=1;
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		if (key==1)
			return true;
		else 
			return false;
	}
//---------------------------------------------------------------------------------------------
	public boolean updateWhourStatus(int doctor_id,String wdate) throws SQLException
	{
		Connection con=conn.connDb();
		
		int key=0;
		boolean duplicate=false;
		
		String query="UPDATE whour SET status =? WHERE doctor_id=? AND wdate=?";
		
		
		try {
			          st=con.createStatement();
					  preparedStatement=con.prepareStatement(query);
					  preparedStatement.setString(1, "p");
					  preparedStatement.setInt(2, doctor_id);
					  preparedStatement.setString(3, wdate);
					  
					  preparedStatement.executeUpdate();
					 
					  key=1;
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		if (key==1)
			return true;
		else 
			return false;
	}
	
}
