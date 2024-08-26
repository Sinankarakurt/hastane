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

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaName;
	private JTextField fld_hastaTcno;
	private JPasswordField pfld_passHasta;
	private JButton btrn_backto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-8, -39, 300, 387);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_hastaName = new JLabel("Adı Soyadı");
		lbl_hastaName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_hastaName.setBounds(42, 11, 186, 29);
		w_pane.add(lbl_hastaName);
		
		fld_hastaName = new JTextField();
		fld_hastaName.setColumns(10);
		fld_hastaName.setBounds(42, 46, 186, 29);
		w_pane.add(fld_hastaName);
		
		JLabel lbl_hastaTcno = new JLabel("Tc No");
		lbl_hastaTcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_hastaTcno.setBounds(42, 86, 186, 29);
		w_pane.add(lbl_hastaTcno);
		
		fld_hastaTcno = new JTextField();
		fld_hastaTcno.setColumns(10);
		fld_hastaTcno.setBounds(42, 121, 186, 29);
		w_pane.add(fld_hastaTcno);
		
		JLabel lbl_doctorPass = new JLabel("Şifre");
		lbl_doctorPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doctorPass.setBounds(42, 161, 186, 29);
		w_pane.add(lbl_doctorPass);
		
		pfld_passHasta = new JPasswordField();
		pfld_passHasta.setBounds(42, 201, 186, 29);
		w_pane.add(pfld_passHasta);
		
		JButton btn_registerHasta = new JButton("Kayıt Ol");
		btn_registerHasta.setBounds(42, 236, 186, 29);
		w_pane.add(btn_registerHasta);
		
		btrn_backto = new JButton("Geri Dön");
		btrn_backto.setBounds(42, 277, 186, 29);
		w_pane.add(btrn_backto);
	}
}
