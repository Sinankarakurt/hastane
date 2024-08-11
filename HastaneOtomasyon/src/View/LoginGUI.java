package View;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
//--------------------------------------
import Helper.*;
import Model.*;

public class LoginGUI extends JFrame
{
//-----------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JPasswordField pfld_hastaPass;
	private JTextField fld_doctorTc;
	private JPasswordField pfld_doctorPass;

//---------------------------------------------------------------
	private DBConnection con=new DBConnection();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try
				{
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//--------------------------------------------------------------------------
	public LoginGUI() 
	{
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
//--------------------------------------------------------------		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hast.jpg")));
		lbl_logo.setBounds(191, 0, 100, 50);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setBounds(122, 56, 250, 29);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBackground(Color.WHITE);
		w_tabpane.setBounds(10, 117, 464, 233);
		w_pane.add(w_tabpane);
		
		JPanel w_hasta = new JPanel();
		w_hasta.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hasta, null);
		w_hasta.setLayout(null);
		
		JLabel lbl_hastaTc = new JLabel("T.C.   :");
		lbl_hastaTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_hastaTc.setBounds(60, 21, 87, 30);
		w_hasta.add(lbl_hastaTc);
		
		JLabel lbl_hastaPass = new JLabel("Şifre  :");
		lbl_hastaPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_hastaPass.setBounds(60, 70, 87, 30);
		w_hasta.add(lbl_hastaPass);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(168, 21, 250, 30);
		w_hasta.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		pfld_hastaPass = new JPasswordField();
		pfld_hastaPass.setBounds(168, 70, 250, 30);
		w_hasta.add(pfld_hastaPass);
		
		JButton btn_hastaRegister = new JButton("Kayıt Ol");
		btn_hastaRegister.setBounds(58, 140, 150, 30);
		w_hasta.add(btn_hastaRegister);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		btn_hastaLogin.setBounds(268, 140, 150, 30);
		w_hasta.add(btn_hastaLogin);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setLayout(null);
		w_doctor.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doctor, null);
		
		JLabel lbl_doctorTc = new JLabel("T.C.   :");
		lbl_doctorTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doctorTc.setBounds(60, 21, 87, 30);
		w_doctor.add(lbl_doctorTc);
		
		JLabel lbl_doctorPass = new JLabel("Şifre  :");
		lbl_doctorPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doctorPass.setBounds(60, 70, 87, 30);
		w_doctor.add(lbl_doctorPass);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(168, 21, 250, 30);
		w_doctor.add(fld_doctorTc);
		
		pfld_doctorPass = new JPasswordField();
		pfld_doctorPass.setBounds(168, 70, 250, 30);
		w_doctor.add(pfld_doctorPass);
		
		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			if (fld_doctorTc.getText().length()==0 || pfld_doctorPass.getText().length()==0) 
			{
				Helper.showMsg("fill");
			} else
			{
				
				try
				{
					Connection conn=con.connDb();
					Statement st;
					st = conn.createStatement();
					ResultSet rs=st.executeQuery("SELECT * FROM user");
					while (rs.next())
					{
						if (fld_doctorTc.getText().equals(rs.getString("tcno"))&& pfld_doctorPass.getText().equals(rs.getString("password"))) 
						{
							Bashekim bhekim=new Bashekim();
							
							bhekim.setId(rs.getInt("id"));
							bhekim.setTcno(rs.getString("tcno"));
							bhekim.setPass(rs.getString("password"));
							bhekim.setName(rs.getString("name"));
							bhekim.setType(rs.getString("type"));
							
							BashekimGUI bGUI=new BashekimGUI(bhekim);
							bGUI.setVisible(true);
							dispose();							
						} else {

						}
						
					}
					
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
				
			}
		}
		);
		btn_doctorLogin.setBounds(168, 139, 250, 30);
		w_doctor.add(btn_doctorLogin);
//----------------------------------------------------
	}
}
