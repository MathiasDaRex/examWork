package carRentElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class CarRent extends JFrame {

	private JPanel contentPane;
	private JTable tbSzabad;
	private JTable tbFoglalt;
	private JTextField tfRendszam;
	private JTextField tfMarka;
	private JTextField tfTipus;
	private JTextField tfNapiAr;
	private JScrollPane spFoglalt;
	private JScrollPane spSzabad;
	private JLabel lblSzabad;
	private JLabel lblFoglaltAutk;
	private JLabel lblAutFoglals;
	private JLabel lblRendszm;
	private JLabel lblMrka;
	private JLabel lblTpus;
	@SuppressWarnings("rawtypes")
	private JComboBox cbUgyfel;
	private JLabel lblgyfl;
	private JLabel lblBrlsKezdete;
	private JLabel lblBrlsVge;
	private JDateChooser dcKezdet;
	private JDateChooser dcVege;
	private JLabel lblAr;
	private JButton btnMentes;
	private JButton btnModosit;
	private JButton btnAlaphelyzet;
	private JButton btnNyomtatas;
	private JButton btnKilepes;
	private Statement stm = null;
	private Connection con = new Db().getCon();
	private java.util.Date RentDat, ReturnDat;
	private java.sql.Date SqlRentDat, SqlReturnDat;
	private JTextField tfRentId;
	private JTextField tfSzabadId;
	private JButton btnSzamitas;
	private JTextField tfAr;
	
	
	@SuppressWarnings("rawtypes")
	public CarRent(Cars MainWindow) {
		setTitle("Aut\u00F3 foglal\u00E1s");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 867, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		spFoglalt = new JScrollPane();
		spFoglalt.setBounds(10, 339, 831, 175);
		contentPane.add(spFoglalt);
		
		tbFoglalt = new JTable();
		tbFoglalt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				@SuppressWarnings("unused")
				int tableRowIndex = tbFoglalt.rowAtPoint(e.getPoint());
				btnModosit.setEnabled(true);
				btnMentes.setEnabled(false);
			}
		});
		tbFoglalt.setEnabled(false);
		
		spFoglalt.setViewportView(tbFoglalt);
		tbFoglalt.setModel(new DefaultTableModel(
				new String[] {"Bérlés ID", "Autó ID", "Bérlõ neve",  "Márka", "Típus", "Rendszám", "Bérlés kezdete", "Bérlés vége", "Teljes ár"}, 0
				));

		spSzabad = new JScrollPane();
		spSzabad.setBounds(30, 123, 792, 145);
		contentPane.add(spSzabad);
		tbSzabad = new JTable();
		tbSzabad.setEnabled(false);
		tbSzabad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tableRowIndex = tbSzabad.rowAtPoint(e.getPoint());
				autoAdatokTextfieldbeIras(tableRowIndex);
				btnModosit.setEnabled(false);
				btnMentes.setEnabled(true);
			}
		});
		spSzabad.setViewportView(tbSzabad);
		tbSzabad.setModel(new DefaultTableModel(
				new String[] {"ID",  "Márka", "Típus", "Rendszám", "Státusz", "Ár"}, 0
				));
		
		lblSzabad = new JLabel("Szabad aut\u00F3k");
		lblSzabad.setHorizontalAlignment(SwingConstants.CENTER);
		lblSzabad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSzabad.setBounds(0, 79, 851, 32);
		contentPane.add(lblSzabad);
		
		lblFoglaltAutk = new JLabel("Foglalt aut\u00F3k");
		lblFoglaltAutk.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoglaltAutk.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFoglaltAutk.setBounds(0, 292, 851, 32);
		contentPane.add(lblFoglaltAutk);
		
		lblAutFoglals = new JLabel("Aut\u00F3 foglal\u00E1s");
		lblAutFoglals.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutFoglals.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAutFoglals.setBounds(0, 11, 851, 32);
		contentPane.add(lblAutFoglals);
		
		tfRendszam = new JTextField();
		tfRendszam.setEditable(false);
		tfRendszam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfRendszam.setBounds(30, 563, 114, 26);
		contentPane.add(tfRendszam);
		tfRendszam.setColumns(10);
		
		lblRendszm = new JLabel("Rendsz\u00E1m");
		lblRendszm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRendszm.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRendszm.setBounds(10, 525, 154, 32);
		contentPane.add(lblRendszm);
		
		tfMarka = new JTextField();
		tfMarka.setEditable(false);
		tfMarka.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMarka.setColumns(10);
		tfMarka.setBounds(165, 563, 114, 26);
		contentPane.add(tfMarka);
		
		lblMrka = new JLabel("M\u00E1rka");
		lblMrka.setHorizontalAlignment(SwingConstants.CENTER);
		lblMrka.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMrka.setBounds(145, 525, 154, 32);
		contentPane.add(lblMrka);
		
		tfTipus = new JTextField();
		tfTipus.setEditable(false);
		tfTipus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfTipus.setColumns(10);
		tfTipus.setBounds(304, 563, 114, 26);
		contentPane.add(tfTipus);
		
		lblTpus = new JLabel("T\u00EDpus");
		lblTpus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTpus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTpus.setBounds(280, 525, 154, 32);
		contentPane.add(lblTpus);
		
		cbUgyfel = new JComboBox();
		cbUgyfel.setBounds(304, 645, 145, 26);
		contentPane.add(cbUgyfel);
		
		lblgyfl = new JLabel("\u00DCgyf\u00E9l");
		lblgyfl.setHorizontalAlignment(SwingConstants.CENTER);
		lblgyfl.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblgyfl.setBounds(301, 601, 145, 32);
		contentPane.add(lblgyfl);
		
		lblBrlsKezdete = new JLabel("B\u00E9rl\u00E9s kezdete");
		lblBrlsKezdete.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrlsKezdete.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBrlsKezdete.setBounds(10, 601, 154, 32);
		contentPane.add(lblBrlsKezdete);
		
		lblBrlsVge = new JLabel("B\u00E9rl\u00E9s v\u00E9ge");
		lblBrlsVge.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrlsVge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBrlsVge.setBounds(145, 601, 154, 32);
		contentPane.add(lblBrlsVge);
		
		dcKezdet = new JDateChooser();
		dcKezdet.setBounds(30, 645, 114, 26);
		contentPane.add(dcKezdet);
		
		dcVege = new JDateChooser();
		dcVege.setBounds(165, 645, 114, 26);
		contentPane.add(dcVege);
		
		lblAr = new JLabel("\u00C1r");
		lblAr.setHorizontalAlignment(SwingConstants.CENTER);
		lblAr.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAr.setBounds(446, 525, 114, 32);
		contentPane.add(lblAr);
		
		tfNapiAr = new JTextField();
		tfNapiAr.setVisible(false);
		tfNapiAr.setEditable(false);
		tfNapiAr.setEnabled(false);
		tfNapiAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNapiAr.setColumns(10);
		tfNapiAr.setBounds(594, 526, 114, 26);
		contentPane.add(tfNapiAr);
		
		 btnMentes = new JButton("Ment\u00E9s");
		 btnMentes.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		MentesGombraKattintas();
		 		MainWindow.tablazatBetolt();
		 	}
		 });
		btnMentes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnMentes.setBounds(464, 628, 145, 43);
		contentPane.add(btnMentes);
		
		 btnModosit = new JButton("M\u00F3dos\u00EDt\u00E1s");
		 btnModosit.setVisible(false);
		 btnModosit.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		MainWindow.tablazatBetolt();
		 	}
		 });
		btnModosit.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnModosit.setBounds(666, 635, 145, 43);
		contentPane.add(btnModosit);
		
		 btnAlaphelyzet = new JButton("Alaphelyzet");
		 btnAlaphelyzet.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		alaphelyzetbeAllitas();
		 	}
		 });
		btnAlaphelyzet.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnAlaphelyzet.setBounds(464, 702, 145, 26);
		contentPane.add(btnAlaphelyzet);
		
		btnNyomtatas = new JButton("Nyomtat\u00E1s");
		btnNyomtatas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printGombraKattintas();
			}
		});
		btnNyomtatas.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnNyomtatas.setBounds(304, 699, 122, 32);
		contentPane.add(btnNyomtatas);
		
		btnKilepes = new JButton("Kil\u00E9p\u00E9s");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilepes.setForeground(Color.BLACK);
		btnKilepes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKilepes.setBackground(Color.RED);
		btnKilepes.setBounds(666, 690, 175, 41);
		contentPane.add(btnKilepes);
		
		tfRentId = new JTextField();
		tfRentId.setVisible(false);
		tfRentId.setEnabled(false);
		tfRentId.setBounds(30, 689, 49, 20);
		contentPane.add(tfRentId);
		tfRentId.setColumns(10);
		
		tfSzabadId = new JTextField();
		tfSzabadId.setVisible(false);
		tfSzabadId.setEnabled(false);
		tfSzabadId.setColumns(10);
		tfSzabadId.setBounds(91, 689, 49, 20);
		contentPane.add(tfSzabadId);
		
		btnSzamitas = new JButton("Sz\u00E1m\u00EDt\u00E1s");
		btnSzamitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				szamitasGombraKattintas();
			}
		});
		btnSzamitas.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnSzamitas.setBounds(575, 563, 145, 26);
		contentPane.add(btnSzamitas);
		tfAr = new JTextField();
		tfAr.setEditable(false);
		tfAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAr.setColumns(10);
		tfAr.setBounds(446, 566, 114, 26);
		contentPane.add(tfAr);
		tablazatokBetolt();
		getCustomer();
		setVisible(true);
	}
	
	public void tablazatokBetolt() {
		Db dbObj = new Db();
		String sql = "SELECT * FROM rental_cars WHERE status = 1 ORDER BY car_id;";
		String sql2 = "SELECT * FROM rental_rents ORDER BY rent_id;";
		ResultSet rs = dbObj.lekerdez(sql);
		ResultSet rs2 = dbObj.lekerdez(sql2);
		szabadTablazatSorokTorol();
		foglaltTablazatSorokTorol();

		try {
			while(rs.next()) {
				String allapot = rs.getBoolean("status") ? "elérhetõ" : "foglalt";
				((DefaultTableModel)tbSzabad.getModel()).addRow(
						new Object[] {
								rs.getString("car_id"),
								rs.getString("car_brand"),
								rs.getString("car_type"),
								rs.getString("lic_plate"),
								allapot,
								rs.getString("daily_cost"),
						});
			}
		
			while(rs2.next()) {
				((DefaultTableModel)tbFoglalt.getModel()).addRow(
						new Object[] {
								rs2.getString("rent_id"),
								rs2.getString("car_id"),
								rs2.getString("user_name"),
								rs2.getString("car_brand"),
								rs2.getString("car_type"),
								rs2.getString("lic_plate"),
								rs2.getString("rent_date"),
								rs2.getString("return_date"),
								rs2.getString("fee")
						});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "SQL Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	public void szabadTablazatSorokTorol() {
		int sorokSzama = tbSzabad.getModel().getRowCount();
		for (int i = sorokSzama-1; i >= 0; i--) {
			((DefaultTableModel)tbSzabad.getModel()).removeRow(i);
		}
	}
	
	public void foglaltTablazatSorokTorol() {
		int sorokSzama = tbFoglalt.getModel().getRowCount();
		for (int i = sorokSzama-1; i >= 0; i--) {
			((DefaultTableModel)tbFoglalt.getModel()).removeRow(i);
		}
	}
	
	public void autoAdatokTextfieldbeIras(int tableRowIndex) {
		int id = Integer.parseInt(((DefaultTableModel)tbSzabad.getModel()).getValueAt(tableRowIndex, 0).toString());
		try {
			stm = con.createStatement();
			String sql = "SELECT * FROM rental_cars WHERE car_id = '"+id+"';";
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next()) {
				tfMarka.setText(rs.getString("car_brand"));
				tfTipus.setText(rs.getString("car_type"));
				tfRendszam.setText(rs.getString("lic_plate"));
				tfNapiAr.setText(rs.getString("daily_cost"));
				tfAr.setText("");
				tfSzabadId.setText(rs.getString("car_id"));
				tfRentId.setText("");
				maiDatumBeallitas();
				dcVege.setDate(null);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "SQL hiba", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void getCustomer() {
		try {
			stm = con.createStatement();
			String sql = "SELECT * FROM rental_users;";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String ugyfel = rs.getString("user_name");
				cbUgyfel.addItem(ugyfel);	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	
	public void MentesGombraKattintas() {
			
			if(tfMarka.getText().isEmpty() || tfTipus.getText().isEmpty() || tfRendszam.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Válasszon ki egy szabad autót!", "hiba a felvitelben!", JOptionPane.WARNING_MESSAGE);
			} else if(dcKezdet.getDate()==null || dcVege.getDate()==null) {
				JOptionPane.showMessageDialog(null, "Adja meg a kívánt idõintervallumot!", "hiba a felvitelben!", JOptionPane.WARNING_MESSAGE);
			
			} else if(tfAr.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Számítsa ki a bérlés teljes árát a számítás gomb segítségével!", "hiba a felvitelben!",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					RentDat = dcKezdet.getDate();
					SqlRentDat = new java.sql.Date(RentDat.getTime());
					ReturnDat = dcVege.getDate();
					SqlReturnDat = new java.sql.Date(ReturnDat.getTime());
					Connection con = new Db().getCon();
					String sql = "INSERT INTO rental_rents "
							+ "(car_id, user_name,car_brand,car_type, lic_plate, rent_date, return_date, fee)"
							+ "VALUES (?,?,?,?,?,?,?,?);";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, tfSzabadId.getText());
					ps.setString(2, cbUgyfel.getSelectedItem().toString());
					ps.setString(3, tfMarka.getText());
					ps.setString(4, tfTipus.getText());
					ps.setString(5, tfRendszam.getText());
					ps.setDate(6, SqlRentDat);
					ps.setDate(7, SqlReturnDat);
					ps.setInt(8, Integer.valueOf(tfAr.getText()));	
					ps.executeUpdate();
					ps.close();
					JOptionPane.showMessageDialog(null , "Az autó sikeresen kibérelve", "Sikeres bérlés", JOptionPane.INFORMATION_MESSAGE);
					tablazatokBetolt();		
					autoStatusUpdate();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null , "Sikertelen hozzáadás", "Hiba", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	
	public void alaphelyzetbeAllitas() {
		cbUgyfel.setSelectedIndex(0);
		tfMarka.setText("");
		tfTipus.setText("");
		tfRendszam.setText("");
		tfAr.setText("");
		dcKezdet.setDate(null);
		dcVege.setDate(null);
	}
	
	public void autoStatusUpdate() {
		
		try {
			String carId = tfSzabadId.getText();
			Boolean status = false;
			String sql = "UPDATE rental_cars SET "
					+ " status=?"
					+ " WHERE car_id = '"+carId+"';";
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, status);
			ps.executeUpdate();
			ps.close();
			tablazatokBetolt();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "Sikertelen hozzáadás", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void maiDatumBeallitas() {
		Date maiDatum = new Date();
		dcKezdet.setDate(maiDatum);
	}
	
	
	private void szamitasGombraKattintas() {
		if(tfNapiAr.getText().isEmpty() || tfMarka.getText().isEmpty() || tfTipus.getText().isEmpty() || tfRendszam.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null , "Kérem válasszon ki egy autót!", "Hiba", JOptionPane.ERROR_MESSAGE);
		} else if (dcVege.getDate()==null){
			JOptionPane.showMessageDialog(null , "Kérem válassza ki a visszahozatal dátumát!", "Hiba", JOptionPane.ERROR_MESSAGE);
		} else {
			long diff = dcVege.getDate().getTime() - dcKezdet.getDate().getTime();
			long napok = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			int napiAr = Integer.valueOf((tfNapiAr.getText())); 
			int teljesAr = (int) (napiAr*napok);
			tfAr.setText(String.valueOf(teljesAr));
		}
	}
	
	
	private void printGombraKattintas() {
		try {
			tbFoglalt.print();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null , "Sikertelen nyomtatás!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
}
