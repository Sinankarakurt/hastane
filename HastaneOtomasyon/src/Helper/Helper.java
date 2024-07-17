package Helper;

import javax.swing.JOptionPane;

public class Helper 
{
	public static void showMsg(String str)
	{
		String msg;
		
		switch (str)
		{
		case "fill":
			msg="Lütfen tüm alanları doldurunjuz";
			
			break;
		

		default:
			msg=str;
			break;
		}
		
		
		JOptionPane.showMessageDialog(null, msg, "mesage", JOptionPane.INFORMATION_MESSAGE);
	}

}
