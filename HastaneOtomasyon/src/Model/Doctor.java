package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	

	
}
