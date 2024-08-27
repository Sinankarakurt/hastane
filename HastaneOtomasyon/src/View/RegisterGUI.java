package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Model.*;
import Helper.*;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaName;
	private JTextField fld_hastaTcno;
	private JPasswordField pfld_passHasta;
	private JButton btn_backto;
	private Hasta hasta=new Hasta();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 300, 339);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_hastaName = new JLabel("Adı Soyadı");
		lbl_hastaName.setBounds(42, 11, 186, 29);
		lbl_hastaName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		w_pane.add(lbl_hastaName);
		
		fld_hastaName = new JTextField();
		fld_hastaName.setBounds(42, 42, 186, 29);
		fld_hastaName.setColumns(10);
		w_pane.add(fld_hastaName);
		
		JLabel lbl_hastaTcno = new JLabel("Tc No");
		lbl_hastaTcno.setBounds(42, 82, 186, 29);
		lbl_hastaTcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		w_pane.add(lbl_hastaTcno);
		
		fld_hastaTcno = new JTextField();
		fld_hastaTcno.setBounds(42, 110, 186, 29);
		fld_hastaTcno.setColumns(10);
		w_pane.add(fld_hastaTcno);
		
		JLabel lbl_doctorPass = new JLabel("Şifre");
		lbl_doctorPass.setBounds(42, 149, 186, 29);
		lbl_doctorPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		w_pane.add(lbl_doctorPass);
		
		pfld_passHasta = new JPasswordField();
		pfld_passHasta.setBounds(42, 192, 186, 29);
		w_pane.add(pfld_passHasta);
		
		JButton btn_registerHasta = new JButton("Kayıt Ol");
		btn_registerHasta.setBounds(42, 227, 186, 29);
		btn_registerHasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (fld_hastaTcno.getText().length()==0||pfld_passHasta.getText().length()==0||fld_hastaName.getText().length()==0) 
				{
					Helper.showMsg("fill");
					
				} else
				{
					try 
					{
						boolean control=hasta.register(fld_hastaTcno.getText(), pfld_passHasta.getText(),fld_hastaName.getText());
						if (control) 
						{							
						Helper.showMsg("success");
						LoginGUI login=new LoginGUI();
						login.setVisible(true);
						dispose();}
						else
						{
							Helper.showMsg("error");
						}
						
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
			}
		});
		w_pane.add(btn_registerHasta);
		
		btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setBounds(42, 263, 186, 29);
		w_pane.add(btn_backto);
	}


}
