package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Doctor extends User
{
	Statement st=null;
	ResultSet rs=null;
	Connection con=conn.connDb();
	PreparedStatement preparedStatement =null;
	
//----------------------------------------------------------
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
//----------------------------------------------------------------------------
	public Doctor(int id, String tcno, String name, String pass, String type)
	{
		super(id, tcno, name, pass, type);
		// TODO Auto-generated constructor stub
	}
//-------------------------------------------------------------------
	
	public boolean addWhour(int doctor_id,String doctor_name,String wdate) throws SQLException
	{
		int key=0;
		int count=0;
		String query="INSERT INTO whour ( doctor_id,doctor_name,wdate) 	VALUES (?,?,?) ";
		
		try 
		{
			st=con.createStatement();
			
	rs=st.executeQuery("SELECT * FROM whour WHERE status='a'AND doctor_id="+doctor_id+" AND wdate='"+wdate+"'");
	
			while (rs.next())
			{
				count++;
				break;
				
			}
			if (count==0)
			{
				
			
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1,doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			}
				key=1;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		if (key==1)
			
			return true;
		else
			return false;
		
		
	}

	
}
