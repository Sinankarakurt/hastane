package View;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

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
	private JTable table_whour;
	private Whour  whour=new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData=null;
	private int selectDoctorID=0;
	private String selectDoctorName=null;
	

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
//-------------------------------------------------------
		whourModel=new DefaultTableModel();
		Object [] colWhour=new Object[2];
		colWhour[0]="ID";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
//---------------------------------------------------------------------------------------
		
		
		
		
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
		cbx_selectClinic.setBounds(281, 68, 149, 29);
		
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
			
					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) 
						{
							doctorData[0]=clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1]=clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
				
			}
		});
		
		w_appointment.add(cbx_selectClinic);
		
		JLabel lbl_seletctDoctor = new JLabel("Doktor Seç");
		lbl_seletctDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_seletctDoctor.setBounds(284, 108, 146, 29);
		w_appointment.add(lbl_seletctDoctor);
		
		JButton btn_selectDoctor = new JButton("Seç");
		btn_selectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int row=table_doctor.getSelectedRow();
				if (row>0)
				{
					String value=table_doctor.getModel().getValueAt(row, 0).toString();
					int id=Integer.parseInt(value);
					DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++)
						{
							whourData[0]=whour.getWhourList(id).get(i).getId();
							whourData[1]=whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID=id;
					selectDoctorName=table_doctor.getModel().getValueAt(row, 1).toString();
					//System.out.println(selectDoctorID+selectDoctorName);
				}
				else {
					Helper.showMsg("Lüften bir doktor seçiniz");
				}
			}
		});
		btn_selectDoctor.setBounds(284, 148, 146, 29);
		w_appointment.add(btn_selectDoctor);
		
		JLabel lbl_whour = new JLabel("Uygun Saatler");
		lbl_whour.setBounds(440, 17, 113, 24);
		w_appointment.add(lbl_whour);
		
		JScrollPane scroll_whour = new JScrollPane();
		scroll_whour.setBounds(440, 42, 264, 300);
		w_appointment.add(scroll_whour);
		
		table_whour = new JTable(whourModel);
		scroll_whour.setViewportView(table_whour);
		
		JLabel lbl_randevuAl = new JLabel("Randevu ");
		lbl_randevuAl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_randevuAl.setBounds(284, 188, 146, 29);
		w_appointment.add(lbl_randevuAl);
		
		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			
				int selRow=table_whour.getSelectedRow();
				
				if (selRow>0)
				{
					String date=table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
			boolean control=hasta.addAppointmennt(selectDoctorID, hasta.getId(), selectDoctorName	,hasta.getName(), date);
			if (control)
			{
				Helper.showMsg("success");
				hasta.updateWhourStatus(selectDoctorID, date);
				updateWhourModel(selectDoctorID);
				
				
				
			}
			else
			{
				Helper.showMsg("error");
			}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					Helper.showMsg("Geçerli bir tarih giriniz.");
				}
				
			}
		});
		btn_addAppoint.setBounds(284, 228, 146, 29);
		w_appointment.add(btn_addAppoint);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
	}
	
	public void updateWhourModel(int doctor_id)
	{
		DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);

		try {
			for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++)
			{
				whourData[0]=whour.getWhourList(doctor_id).get(i).getId();
				whourData[1]=whour.getWhourList(doctor_id).get(i).getWdate();
				whourModel.addRow(whourData);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//
	}
	
}