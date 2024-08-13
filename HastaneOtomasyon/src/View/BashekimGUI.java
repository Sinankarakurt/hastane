package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.xml.sax.helpers.ParserAdapter;

import Helper.*;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectionKey;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BashekimGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	//--------------------------------
	Clinic clinic=new Clinic();
	
	// ----------
	public static Bashekim bashekim=new Bashekim();
	private JTextField fld_doctorName;
	private JTextField fld_doctorTcno;
	private JTextField fld_doctorId;
	private JPasswordField pfld_doctorPass;
	private JTable table_doctor;
	//-----------------------------------------------------------
	private DefaultTableModel doctorModel=null;
	private Object [] doctorData=null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
//--------------------------------------------------
	private DefaultTableModel clinicModel=null;
	private Object[] clinicData=null;
	
	//--------------------------------------
	
	private JPopupMenu clinicMenu;
	//-------------------------------------------
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) 
	{
		doctorModel=new DefaultTableModel();
		Object [] colDoctorName= new Object[4];
		colDoctorName[0]="ID";
		colDoctorName[1]="Tc No";
		colDoctorName[2]="Ad Soyad";
		colDoctorName[3]="Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
//-------------------------------------------------------
		doctorData=new Object[4];
		
		try 
		{
			for (int i = 0; i < bashekim.getDoctorList().size(); i++)
			{
				doctorData[0]=bashekim.getDoctorList().get(i).getId();
				doctorData[1]=bashekim.getDoctorList().get(i).getTcno();
				doctorData[2]=bashekim.getDoctorList().get(i).getName();
				doctorData[3]=bashekim.getDoctorList().get(i).getPass();
				doctorModel.addRow(doctorData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//---------------------------------------------------------------------------
		clinicModel=new DefaultTableModel();
		Object[] colClinic=new Object[2];
		
		colClinic[0]="ID";
		colClinic[1]="Poliklinik";
		clinicModel.setColumnIdentifiers(colClinic);
//------------------------------------------------

		clinicData=new Object[2];
		
		try {
			for (int i = 0; i < clinic.getClinicList().size(); i++)
			{
				clinicData[0]=clinic.getClinicList().get(i).getId();
				clinicData[1]=clinic.getClinicList().get(i).getName();
				clinicModel.addRow(clinicData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//-----------------------------------------------
		
		setTitle("Hastane Yönetim Sistemi");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_bashekimName = new JLabel("Hoşgeliniz Sayın   :  "+bashekim.getName());
		lbl_bashekimName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_bashekimName.setBounds(10, 11, 198, 26);
		w_pane.add(lbl_bashekimName);
		
		JButton btn_cikis = new JButton("Çıkış");
		btn_cikis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_cikis.setBounds(525, 11, 110, 26);
		w_pane.add(btn_cikis);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 38, 664, 412);
		w_pane.add(w_tabpane);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Yönetimi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lbl_doctorName = new JLabel("Adı Soyadı");
		lbl_doctorName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doctorName.setBounds(452, 11, 186, 29);
		w_doctorLogin.add(lbl_doctorName);
		
		fld_doctorName = new JTextField();
		fld_doctorName.setBounds(452, 46, 186, 29);
		w_doctorLogin.add(fld_doctorName);
		fld_doctorName.setColumns(10);
		
		JLabel lbl_doctorTcno = new JLabel("Tc No");
		lbl_doctorTcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doctorTcno.setBounds(452, 86, 186, 29);
		w_doctorLogin.add(lbl_doctorTcno);
		
		fld_doctorTcno = new JTextField();
		fld_doctorTcno.setColumns(10);
		fld_doctorTcno.setBounds(452, 121, 186, 29);
		w_doctorLogin.add(fld_doctorTcno);
		
		JLabel lbl_doctorPass = new JLabel("Şifre");
		lbl_doctorPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doctorPass.setBounds(452, 161, 186, 29);
		w_doctorLogin.add(lbl_doctorPass);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (fld_doctorName.getText().length()==0|| 
						fld_doctorTcno.getText().length()==0||
						pfld_doctorPass.getText().length()==0)
						
				{
					Helper.showMsg("fill");
				}
				else 
				{
					try 
					{
		boolean control=bashekim.addDoctor(fld_doctorTcno.getText(), pfld_doctorPass.getText(), fld_doctorName.getText());

		if (control)
		{
			Helper.showMsg("success");
			fld_doctorName.setText(null);
			pfld_doctorPass.setText(null);
			fld_doctorTcno.setText(null);
			updateDoctorModel();
			
		}
					} catch (Exception e2)
					{
						// TODO: handle exception
					}
				}
				
			}
		});
		btn_addDoctor.setBounds(452, 236, 186, 29);
		w_doctorLogin.add(btn_addDoctor);
		
		JLabel lbl_doctorId = new JLabel("kullanıcı ID");
		lbl_doctorId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doctorId.setBounds(452, 270, 186, 29);
		w_doctorLogin.add(lbl_doctorId);
		
		fld_doctorId = new JTextField();
		fld_doctorId.setColumns(10);
		fld_doctorId.setBounds(452, 305, 186, 29);
		w_doctorLogin.add(fld_doctorId);
//-----------------------------------------------------------------------------------		
		JButton btn_deleteDoctor = new JButton("Sil");
		btn_deleteDoctor.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (fld_doctorId.getText().length()==0)
				{
					Helper.showMsg("Lütfen geçerli bir doktor tanımlayınız");
				}
				else
				{
					if (Helper.confirm("sure"))
					{
						int selecId=Integer.parseInt(fld_doctorId.getText());
						try
						{
							
							boolean control=bashekim.deleteDoctor(selecId);
							if (control) 
							{
								Helper.showMsg("success");
								fld_doctorId.setText(null);
								updateDoctorModel();
							}
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
					
					
					
					
					
				}
			
			}
		}
		);

		btn_deleteDoctor.setBounds(452, 345, 186, 29);
		w_doctorLogin.add(btn_deleteDoctor);
//-------------------------------------------------------------------------------------		
		pfld_doctorPass = new JPasswordField();
		pfld_doctorPass.setBounds(452, 201, 186, 29);
		w_doctorLogin.add(pfld_doctorPass);
		
		JScrollPane scrollPane_doctor = new JScrollPane();
		scrollPane_doctor.setBounds(10, 11, 420, 362);
		w_doctorLogin.add(scrollPane_doctor);
		
		table_doctor = new JTable(doctorModel);
		scrollPane_doctor.setViewportView(table_doctor);
		
		
		table_doctor.getSelectionModel().addListSelectionListener
		(new ListSelectionListener(	) 
		{
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
	
				try {
					String drId=table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString();
					fld_doctorId.setText(drId);
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			} 
		}
		);
		
//------------------------------------------------------

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tabpane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 11, 233, 362);
		w_clinic.add(w_scrollClinic);
//-------------------------------------------------------
		clinicMenu=new JPopupMenu();
		
		JMenuItem updateMenu=new JMenuItem("Güncelle");
		JMenuItem deleteMenu=new JMenuItem("sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
	//---------------------------------------------
		updateMenu.addActionListener
		(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			int selID=Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
		   Clinic selectClinic=clinic.getFetch(selID);
		   UpdateClinicGUI updateGUI=new UpdateClinicGUI(selectClinic);
		   updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);			
			}
		}
		);

		
		//---------------------------------------------
		
		
		table_clinic = new JTable(clinicModel);
		
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				Point point=e.getPoint();
				int selectedRow=table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			
				
			}
		}
		);
		
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lbl_clinicName = new JLabel("Poliklinik Adı");
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_clinicName.setBounds(247, 11, 157, 29);
		w_clinic.add(lbl_clinicName);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(247, 46, 157, 29);
		w_clinic.add(fld_clinicName);
//-----------------------------------------------------------------		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener
		(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (fld_clinicName.getText().length()==0)
				{
					Helper.showMsg("fill");
					
				} 
				else
				{
					if (clinic.addClinic(fld_clinicName.getText()))
					{
						Helper.showMsg("success");
						fld_clinicName.setText(null);
						updateClinicModel();
					}
				}
			}
		}
		);
		
//--------------------------------------------------------------------		
		btn_addClinic.setBounds(247, 86, 157, 29);
		w_clinic.add(btn_addClinic);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 11, 251, 362);
		w_clinic.add(scrollPane);
//------------------------------------------------------------------------
		
		table_doctor.getModel().addTableModelListener(new TableModelListener()
		{
			
			@Override
			public void tableChanged(TableModelEvent e)
			{
				if (e.getType()==TableModelEvent.UPDATE) 
				{
					int selId=Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selTcno=table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selName=table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selPass=table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					
					
			boolean control=	bashekim.updateDoctor(selId, selTcno, selPass, selName);
		
					
				}
				
				
			}
		});
		
		
		
	}
//-------------------------------------------

 

  
	
	
	
//-------------------------------
	public void updateDoctorModel()
	{
		DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		try 
		{
			for (int i = 0; i < bashekim.getDoctorList().size(); i++)
			{
				doctorData[0]=bashekim.getDoctorList().get(i).getId();
				doctorData[1]=bashekim.getDoctorList().get(i).getTcno();
				doctorData[2]=bashekim.getDoctorList().get(i).getName();
				doctorData[3]=bashekim.getDoctorList().get(i).getPass();
				doctorModel.addRow(doctorData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//----------------------------------------------------------------------------
	
	public void updateClinicModel()
	{
		DefaultTableModel clearModel=(DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		
		try 
		{
			for (int i = 0; i < clinic.getClinicList().size(); i++)
			{
				clinicData[0]=clinic.getClinicList().get(i).getId();
				clinicData[1]=clinic.getClinicList().get(i).getName();
				clinicModel.addRow(clinicData);
			}
			
		} catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	//----------------------------------------------
	
	
}
