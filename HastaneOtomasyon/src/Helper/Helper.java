package Helper;

import javax.swing.JOptionPane;

public class Helper 
{
	public static void showMsg(String str)
	{
		String msg;
		
		switch (str)
		{
//-----------------------------------
		case "fill":
			msg="Lütfen tüm alanları doldurunjuz";
						break;
//-----------------------------------------		
		case "success":
			msg="İşlem başarılı";
			break;
//---------------------------
		default:
			msg=str;
			break;
//---------------------------------
		}
		
		
		JOptionPane.showMessageDialog(null, msg, "mesage", JOptionPane.INFORMATION_MESSAGE);
	}
//------------------------------------------
	public static boolean confirm(String str)
	{
		String msg;
		switch (str) {
		case "sure":
			msg="Bu işlemi gerçekleştirmek istiyor musunuz?";
			
			break;

		default:
			msg=str;
			break;
		}
		int res=JOptionPane.showConfirmDialog(null, msg	, "Dikkat!", JOptionPane.YES_NO_OPTION);
		
		if (res==0) {
			return true	;
		}
		else {
			return false;
		}
	}
	//----------------------------------------
	
	
}
