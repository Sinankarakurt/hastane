package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	public DBConnection() {}
	
	
	Connection c=null;
	
	public Connection connDb()
	{
		try 
		{
			this.c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			return c;
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	

}
