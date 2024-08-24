package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Doctor doctor=new Doctor();

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
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}                       );
	}

	/**
	 * Create the frame.
	 */
	public DoctorGUI(Doctor doctor) 
	{
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setForeground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_bashekimName = new JLabel("Hoşgeliniz Sayın  : "+doctor.getName());
		lbl_bashekimName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_bashekimName.setBounds(51, 5, 198, 26);
		w_pane.add(lbl_bashekimName);
		
		JButton btn_cikis = new JButton("Çıkış");
		btn_cikis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_cikis.setBounds(566, 5, 110, 26);
		w_pane.add(btn_cikis);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 53, 698, 397);
		w_pane.add(w_tab);
		
		JPanel w_hour = new JPanel();
		w_tab.addTab("Çalışma Saatleri", null, w_hour, null);
		w_hour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(73, 32, 145, 20);
		w_hour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30"}));
		select_time.setBounds(228, 32, 69, 22);
		w_hour.add(select_time);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener
		
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String date=sdf.format(select_date.getDate());
				
				if (date.length()==0) 
				{
				Helper.showMsg("Lütfen geçerli bir tarih giriniz");
				}
				
				String time= " "+select_time.getSelectedItem().toString()+":00";
				String selectDate=date+time;
				try {
					boolean control=doctor.addWhour(doctor.getId(),doctor.getName(), selectDate);
					if (control)
					{
						Helper.showMsg("success");
					} else 
					{
						Helper.showMsg("error");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		}
		);
		btn_addWhour.setBounds(305, 29, 74, 23);
		w_hour.add(btn_addWhour);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 673, 295);
		w_hour.add(scrollPane);
	}
}
