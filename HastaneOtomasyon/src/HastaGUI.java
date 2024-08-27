import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Item;
import Model.Clinic;
import Model.Hasta;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Hasta hasta=new Hasta();
	private Clinic clinic=new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object [] doctorData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public HastaGUI(Hasta hasta) throws SQLException
	{
		
		doctorModel=new DefaultTableModel();
		Object [] colDoctor =new Object [2];
		colDoctor [0]="ID";
		colDoctor[1]="Adı Soyadı";
		doctorModel.setColumnIdentifiers(colDoctor);
//-------------------------------------------------------
		doctorData= new Object[2];
		
		
		
		
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JButton btn_cikis = new JButton("Çıkış Yap");
		btn_cikis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_cikis.setBounds(585, 11, 110, 26);
		w_pane.add(btn_cikis);
		
		JLabel lbl_bashekimName = new JLabel("Hoşgeliniz Sayın   : "+hasta.getName());
		lbl_bashekimName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_bashekimName.setBounds(70, 11, 198, 26);
		w_pane.add(lbl_bashekimName);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 69, 714, 381);
		w_pane.add(w_tab);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane scrollDoctor = new JScrollPane();
		scrollDoctor.setBounds(10, 42, 264, 300);
		w_appointment.add(scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		scrollDoctor.setViewportView(table_doctor);
		
		JLabel lbl_doctorListesi = new JLabel("Doktor Listesi");
		lbl_doctorListesi.setBounds(26, 17, 113, 24);
		w_appointment.add(lbl_doctorListesi);
		
		JLabel lbl_policlinic = new JLabel("Poliklinik Adı");
		lbl_policlinic.setBounds(281, 42, 133, 24);
		w_appointment.add(lbl_policlinic);
		
		JComboBox cbx_selectClinic = new JComboBox();
		cbx_selectClinic.setBounds(281, 68, 133, 29);
		
		cbx_selectClinic.addItem("---Poliklinik Seç----");
		
		for (int i = 0; i < clinic.getClinicList().size(); i++) 
		{
			cbx_selectClinic.addItem(new Item(clinic.getClinicList().get(i).getId(),clinic.getClinicList().get(i).getName()));
		}

		cbx_selectClinic.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				
				if (cbx_selectClinic.getSelectedIndex()!=0) 
				{
					JComboBox c=(JComboBox) e.getSource();
					Item item= (Item) c.getSelectedItem();
					
		
					DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
			
					for (int i = 0; i < colDoctor.length; i++) {
						
					}
					
				}
				
			}
		});
		
		w_appointment.add(cbx_selectClinic);
	}
}
