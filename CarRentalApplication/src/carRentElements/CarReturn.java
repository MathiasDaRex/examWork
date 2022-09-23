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

import javax.swing.JButton;
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
public class CarReturn extends JFrame {

	private JPanel contentPane;
	private JTextField tfRendszam;
	private JTextField tfMarka;
	private JTextField tfTipus;
	private JTextField tfUgyfel;
	private JTextField tfKeses;
	private JTextField tfFelAr;
	private JTextField tfNapiAr;
	private JTable tbFoglalt;
	private JLabel lblAutoVissza;
	private JLabel lblRendszam;
	private JLabel lblMarka;
	private JLabel lblTipus;
	private JLabel lblUgyfel;
	private JLabel lblVissza;
	private JLabel lblKeses;
	private JLabel lblKibreltAutk;
	private JDateChooser dcVisszaDatum;
	private JLabel lblAr;
	private JScrollPane spKiberelt;
	private JLabel lblBentLvAutk;
	private JScrollPane spSzabad;
	private JTable tbSzabad;
	private JButton btnNyomtats;
	private JButton btnVisszavetel;
	private int ar = 0;
	private Statement stm = null;
	private Connection con = new Db().getCon();
	private java.util.Date ReturnDat;
	private java.sql.Date SqlReturnDat;
	private JTextField tfSzabadId;
	private JTextField tfFoglaltId;
	private int rentId;
	private JTextField tfTeljesAr;
	
	public CarReturn(Cars MainWindow) {
		setTitle("B\u00E9relt aut\u00F3 visszaszolg\u00E1ltat\u00E1sa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 999, 751);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblAutoVissza = new JLabel("Aut\u00F3 visszav\u00E9tele");
		lblAutoVissza.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutoVissza.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAutoVissza.setBounds(0, 11, 983, 32);
		contentPane.add(lblAutoVissza);
		
		tfRendszam = new JTextField();
		tfRendszam.setEditable(false);
		tfRendszam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfRendszam.setColumns(10);
		tfRendszam.setBounds(56, 145, 114, 26);
		contentPane.add(tfRendszam);
		
		 lblRendszam = new JLabel("Rendsz\u00E1m");
		lblRendszam.setHorizontalAlignment(SwingConstants.CENTER);
		lblRendszam.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRendszam.setBounds(56, 101, 114, 32);
		contentPane.add(lblRendszam);
		
		 lblMarka = new JLabel("M\u00E1rka");
		lblMarka.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarka.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMarka.setBounds(56, 174, 114, 32);
		contentPane.add(lblMarka);
		
		tfMarka = new JTextField();
		tfMarka.setEditable(false);
		tfMarka.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMarka.setColumns(10);
		tfMarka.setBounds(56, 212, 114, 26);
		contentPane.add(tfMarka);
		
		 lblTipus = new JLabel("T\u00EDpus");
		lblTipus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipus.setBounds(56, 238, 114, 32);
		contentPane.add(lblTipus);
		
		tfTipus = new JTextField();
		tfTipus.setEditable(false);
		tfTipus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfTipus.setColumns(10);
		tfTipus.setBounds(56, 271, 114, 26);
		contentPane.add(tfTipus);
		
		 lblUgyfel = new JLabel("\u00DCgyf\u00E9l neve");
		lblUgyfel.setHorizontalAlignment(SwingConstants.CENTER);
		lblUgyfel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUgyfel.setBounds(56, 309, 114, 32);
		contentPane.add(lblUgyfel);
		
		tfUgyfel = new JTextField();
		tfUgyfel.setEditable(false);
		tfUgyfel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfUgyfel.setColumns(10);
		tfUgyfel.setBounds(56, 344, 114, 26);
		contentPane.add(tfUgyfel);
		
		 lblVissza = new JLabel("Visszahoz\u00E1s d\u00E1tuma");
		lblVissza.setHorizontalAlignment(SwingConstants.CENTER);
		lblVissza.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVissza.setBounds(31, 382, 171, 32);
		contentPane.add(lblVissza);
		
		 dcVisszaDatum = new JDateChooser();
		dcVisszaDatum.setBounds(56, 424, 114, 32);
		contentPane.add(dcVisszaDatum);
		
		lblKeses = new JLabel("K\u00E9s\u00E9s");
		lblKeses.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeses.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKeses.setBounds(56, 468, 114, 32);
		contentPane.add(lblKeses);
		
		tfKeses = new JTextField();
		tfKeses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfKeses.setColumns(10);
		tfKeses.setBounds(56, 503, 114, 26);
		contentPane.add(tfKeses);
		
		 lblAr = new JLabel("Fel\u00E1r");
		lblAr.setHorizontalAlignment(SwingConstants.CENTER);
		lblAr.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAr.setBounds(56, 541, 114, 32);
		contentPane.add(lblAr);
		
		tfFelAr = new JTextField();
		tfFelAr.setEditable(false);
		tfFelAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfFelAr.setColumns(10);
		tfFelAr.setBounds(56, 570, 114, 26);
		contentPane.add(tfFelAr);
		
		 spKiberelt = new JScrollPane();
		spKiberelt.setBounds(324, 107, 622, 234);
		contentPane.add(spKiberelt);
		
		tbFoglalt = new JTable();
		tbFoglalt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
		 		int tableRowIndex = tbFoglalt.rowAtPoint(e.getPoint());
		 		foglaltAutoAdatokTextfieldbeIras(tableRowIndex);
			}
		});
		spKiberelt.setViewportView(tbFoglalt);
		tbFoglalt.setModel(new DefaultTableModel(
				new String[] {"Bérlés ID", "Autó ID", "Bérlõ neve",  "Márka", "Típus", "Rendszám", "Bérlés kezdete", "Bérlés vége", "Teljes ár"}, 0
				));
		 lblKibreltAutk = new JLabel("Kib\u00E9relt aut\u00F3k");
		lblKibreltAutk.setHorizontalAlignment(SwingConstants.CENTER);
		lblKibreltAutk.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKibreltAutk.setBounds(324, 63, 622, 32);
		contentPane.add(lblKibreltAutk);
		
		 lblBentLvAutk = new JLabel("Visszahozott aut\u00F3k");
		lblBentLvAutk.setHorizontalAlignment(SwingConstants.CENTER);
		lblBentLvAutk.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBentLvAutk.setBounds(324, 361, 622, 32);
		contentPane.add(lblBentLvAutk);
		
		 spSzabad = new JScrollPane();
		spSzabad.setBounds(324, 405, 622, 234);
		contentPane.add(spSzabad);
		
		 tbSzabad = new JTable();
		 tbSzabad.addMouseListener(new MouseAdapter() {
		 	
		 });
		spSzabad.setViewportView(tbSzabad);
		tbSzabad.setModel(new DefaultTableModel(
				new String[] {"ID",  "Rendszám", "Bérlõ neve", "Visszahozás dátuma", "Ár"}, 0
				));
		 btnNyomtats = new JButton("Nyomtat\u00E1s");
		 btnNyomtats.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		printGombraKattintas();
		 	}
		 });
		btnNyomtats.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnNyomtats.setBounds(450, 651, 122, 32);
		contentPane.add(btnNyomtats);
		
		 btnVisszavetel = new JButton("Visszav\u00E9tel");
		 btnVisszavetel.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		visszavetGombraKattintas();
		 		MainWindow.tablazatBetolt();
		 	}
		 });
		btnVisszavetel.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnVisszavetel.setBounds(39, 627, 145, 43);
		contentPane.add(btnVisszavetel);
		
		JButton btnKiszamitas = new JButton("Kisz\u00E1m\u00EDt\u00E1s");
		btnKiszamitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kiszamitasGombraKattintas();
			}
		});
		btnKiszamitas.setFont(new Font("Segoe UI Light", Font.BOLD, 11));
		btnKiszamitas.setBounds(185, 570, 101, 26);
		contentPane.add(btnKiszamitas);
		
		tfSzabadId = new JTextField();
		tfSzabadId.setVisible(false);
		tfSzabadId.setBounds(31, 43, 53, 20);
		contentPane.add(tfSzabadId);
		tfSzabadId.setColumns(10);
		
		tfFoglaltId = new JTextField();
		tfFoglaltId.setVisible(false);
		tfFoglaltId.setColumns(10);
		tfFoglaltId.setBounds(109, 43, 53, 20);
		contentPane.add(tfFoglaltId);
		
		JButton btnVissza = new JButton("Kil\u00E9p\u00E9s");
		btnVissza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVissza.setForeground(Color.BLACK);
		btnVissza.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVissza.setBackground(Color.RED);
		btnVissza.setBounds(796, 656, 175, 41);
		contentPane.add(btnVissza);
		
		tfNapiAr = new JTextField();
		tfNapiAr.setVisible(false);
		tfNapiAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNapiAr.setEditable(false);
		tfNapiAr.setColumns(10);
		tfNapiAr.setBounds(192, 43, 74, 20);
		contentPane.add(tfNapiAr);
		
		tfTeljesAr = new JTextField();
		tfTeljesAr.setVisible(false);
		tfTeljesAr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfTeljesAr.setEditable(false);
		tfTeljesAr.setColumns(10);
		tfTeljesAr.setBounds(192, 108, 74, 20);
		contentPane.add(tfTeljesAr);
		setVisible(true);
		tablazatokBetolt();
	}
	
	private void tablazatokBetolt() {
		Db dbObj = new Db();
		String sql = "SELECT * FROM rental_returns;";
		String sql2 = "SELECT * FROM rental_rents ORDER BY rent_id;";
		ResultSet rs = dbObj.lekerdez(sql);
		ResultSet rs2 = dbObj.lekerdez(sql2);
		szabadTablazatSorokTorol();
		foglaltTablazatSorokTorol();
		try {
			while(rs.next()) {
				((DefaultTableModel)tbSzabad.getModel()).addRow(
						new Object[] {
								rs.getString("return_id"),
								rs.getString("lic_plate"),
								rs.getString("user_name"),
								rs.getString("return_date"),
								rs.getString("fine"),
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
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
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
	
	private void kiszamitasGombraKattintas() {
		int napiAr = Integer.parseInt(tfNapiAr.getText());
		if(tfKeses.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Kérem adja meg hány napot késett az ügyfél.");
		} else if(tfFoglaltId.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Kérem válasszon ki egy autót!");
		} else if(dcVisszaDatum.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Kérem adja meg a visszahozatal dátumát!");
		} else {
			int teljesAr = napiAr * Integer.valueOf(tfKeses.getText());
			tfFelAr.setText(String.valueOf(teljesAr));
		}
		
	}
	
	public void foglaltAutoAdatokTextfieldbeIras(int tableRowIndex) {
			DefaultTableModel model = (DefaultTableModel)tbFoglalt.getModel();
			int i = tbFoglalt.getSelectedRow();
			tfFoglaltId.setText(model.getValueAt(i, 1).toString());
			tfUgyfel.setText(model.getValueAt(i, 2).toString());
			tfMarka.setText(model.getValueAt(i, 3).toString());
			tfTipus.setText(model.getValueAt(i, 4).toString());
			tfRendszam.setText(model.getValueAt(i, 5).toString());
			rentId = Integer.valueOf(model.getValueAt(i, 0).toString());
			ar = Integer.valueOf(model.getValueAt(i, 8).toString());
			napiArMeghataroz();
			}
		 
	public void visszavetGombraKattintas() {
		if(tfMarka.getText().isEmpty() || tfTipus.getText().isEmpty() || tfRendszam.getText().isEmpty() || tfUgyfel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Válasszon ki egy szabad autót!", "hiba a felvitelben!", JOptionPane.WARNING_MESSAGE);
		} else if(dcVisszaDatum.getDate()==null) {
			JOptionPane.showMessageDialog(null, "Adja meg a visszavétel dátumát!", "hiba a felvitelben!", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
			ReturnDat = dcVisszaDatum.getDate();
			SqlReturnDat = new java.sql.Date(ReturnDat.getTime());
			Connection con = new Db().getCon();
			String sql = "INSERT INTO rental_returns "
					+ "( lic_plate,user_name,return_date,fine)"
					+ "VALUES (?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tfRendszam.getText());
			ps.setString(2, tfUgyfel.getText());
			ps.setDate(3, SqlReturnDat);
			ps.setInt(4, Integer.valueOf(tfFelAr.getText())+ar); 
			ps.executeUpdate();
			ps.close();
			autoStatusUpdate();
			foglaltTablabolTorles();
			tablazatokBetolt();	
			JOptionPane.showMessageDialog(null , "Az autó visszahozása sikeresen rögzítve, a tábla frissítve!", "Sikeres visszavét!", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null , "Sikertelen hozzáadás", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	
	public void autoStatusUpdate() {
		try {
			String carId = tfFoglaltId.getText();
			Boolean status = true;
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
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void foglaltTablabolTorles() {
		try {
			String sql = "DELETE FROM rental_rents WHERE rent_id ="+rentId;
			Statement stm = con.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void printGombraKattintas() {
		try {
			tbSzabad.print();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null , "Sikertelen nyomtatás!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void napiArMeghataroz() {
		int carId = Integer.parseInt(tfFoglaltId.getText());
		try {
			stm = con.createStatement();
			String sql = "SELECT * FROM rental_cars WHERE car_id = '"+carId+"';";
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next()) {
				tfNapiAr.setText(rs.getString("daily_cost"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
}
