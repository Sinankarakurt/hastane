package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	
	
	
	
}
