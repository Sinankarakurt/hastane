package Model;

public class User
{
//-----------------------------------------------------------------
	private int id;
	private String tcno,name,pass,type;
//----------------------------------------------------------------
	public User(int id, String tcno, String name, String pass, String type)
	{
		
		this.id = id;
		this.tcno = tcno;
		this.name = name;
		this.pass = pass;
		this.type = type;
	}
	
	
//--------------------------------------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
//----------------------------------------------------
	
}
