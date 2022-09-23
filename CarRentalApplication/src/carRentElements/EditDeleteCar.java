package carRentElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EditDeleteCar extends JFrame {

	private JPanel cpAutoModosit;
	private JTextField tfRendszam;
	private JTextField tfTipus;
	private JTextField tfMarka;
	private JTextField tfNapiAr;
	@SuppressWarnings("rawtypes")
	private JComboBox cbAllapot;
	private JButton btnMentes;
	private JButton btnKilepes;
	private JButton btnTorles;
	private JLabel lblUjAuto;
	private JLabel lblMarka;
	private JLabel lblTipus;
	private JLabel lblRendszam;
	private JLabel lblNapiAr;
	private JLabel lblAllapot;
	private Statement stm = null;
	private Connection con = new Db().getCon();

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditDeleteCar(Cars MainWindow, int carId) {
		setTitle("Aut\u00F3 m\u00F3dos\u00EDt\u00E1sa \u00E9s t\u00F6rl\u00E9se, id: " + carId);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 375, 426);
		cpAutoModosit = new JPanel();
		cpAutoModosit.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpAutoModosit);
		
		lblUjAuto = new JLabel("Aut\u00F3 m\u00F3dos\u00EDt\u00E1sa");
		lblUjAuto.setBounds(25, 22, 258, 22);
		lblUjAuto.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblUjAuto.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMarka = new JLabel("M\u00E1rka:");
		lblMarka.setBounds(25, 81, 81, 22);
		lblMarka.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblMarka.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblTipus = new JLabel("T\u00EDpus:");
		lblTipus.setBounds(25, 114, 81, 22);
		lblTipus.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipus.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		
		lblRendszam = new JLabel("Rendsz\u00E1m:");
		lblRendszam.setBounds(25, 147, 103, 22);
		lblRendszam.setHorizontalAlignment(SwingConstants.LEFT);
		lblRendszam.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		
		lblNapiAr = new JLabel("Napi \u00E1r:");
		lblNapiAr.setBounds(25, 180, 103, 22);
		lblNapiAr.setHorizontalAlignment(SwingConstants.LEFT);
		lblNapiAr.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		
		lblAllapot = new JLabel("\u00C1llapot:");
		lblAllapot.setBounds(25, 213, 103, 22);
		lblAllapot.setHorizontalAlignment(SwingConstants.LEFT);
		lblAllapot.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		
		tfRendszam = new JTextField();
		tfRendszam.setBounds(123, 151, 118, 20);
		tfRendszam.setColumns(10);
		
		tfTipus = new JTextField();
		tfTipus.setEnabled(false);
		tfTipus.setBounds(123, 116, 118, 20);
		tfTipus.setColumns(10);
		
		tfMarka = new JTextField();
		tfMarka.setEnabled(false);
		tfMarka.setBounds(123, 85, 118, 20);
		tfMarka.setColumns(10);
		
		tfNapiAr = new JTextField();
		tfNapiAr.setBounds(123, 184, 118, 20);
		tfNapiAr.setColumns(10);
		
		cbAllapot = new JComboBox();
		cbAllapot.setEnabled(false);
		cbAllapot.setBounds(123, 213, 118, 25);
		cbAllapot.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		cbAllapot.setModel(new DefaultComboBoxModel(new String[] {"Nem el\u00E9rhet\u0151", "El\u00E9rhet\u0151"}));
		cbAllapot.setSelectedIndex(1);
		
		btnMentes = new JButton("Ment\u00E9s");
		btnMentes.setBackground(Color.GREEN);
		btnMentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MentesGombraKattintas(MainWindow, carId);
			}
		});
		
		btnMentes.setBounds(25, 265, 145, 43);
		btnMentes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		cpAutoModosit.setLayout(null);
		cpAutoModosit.add(lblUjAuto);
		cpAutoModosit.add(lblNapiAr);
		cpAutoModosit.add(tfNapiAr);
		cpAutoModosit.add(lblRendszam);
		cpAutoModosit.add(lblTipus);
		cpAutoModosit.add(lblMarka);
		cpAutoModosit.add(tfMarka);
		cpAutoModosit.add(tfTipus);
		cpAutoModosit.add(tfRendszam);
		cpAutoModosit.add(lblAllapot);
		cpAutoModosit.add(btnMentes);
		cpAutoModosit.add(cbAllapot);
		
		btnKilepes = new JButton("Kil\u00E9p\u00E9s");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnKilepes.setForeground(Color.RED);
		btnKilepes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnKilepes.setBounds(223, 347, 118, 29);
		cpAutoModosit.add(btnKilepes);
		
		btnTorles = new JButton("T\u00F6rl\u00E9s");
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				torlesGombraKattintas(MainWindow, carId);
				
			}
		});
		btnTorles.setBackground(Color.RED);
		btnTorles.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnTorles.setBounds(196, 265, 145, 43);
		cpAutoModosit.add(btnTorles);
		autoAdatokBetoltIdAlapjan(carId);
		setVisible(true);
	}
	
	public void MentesGombraKattintas(Cars MainWindow, int carId) {
		
		if(tfMarka.getText().isEmpty() || tfTipus.getText().isEmpty() ||
				tfRendszam.getText().isEmpty() || tfTipus.getText().isEmpty() || cbAllapot.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Kérem töltse ki az összes adatot!", "hiba a felvitelben!", JOptionPane.WARNING_MESSAGE);
		} else {
				String sql = "UPDATE rental_cars SET car_brand='"+tfMarka.getText()+"',"
				+ "car_type = '"+tfTipus.getText()+"',"
				+ "lic_plate = '"+tfRendszam.getText()+"',"
				+ "daily_cost = '"+tfNapiAr.getText()+"',"
				+ "status = '"+cbAllapot.getSelectedIndex()+"' WHERE car_id = '"+carId+"';";
		try {
			stm.executeUpdate(sql);
			JOptionPane.showMessageDialog(null , "Sikeres frissítés", "Táblázat frissítve", JOptionPane.INFORMATION_MESSAGE);
			MainWindow.tablazatBetolt();
			dispose();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null , "Sikertelen hozzáadás", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}}
	
	
	public void torlesGombraKattintas(Cars MainWindow, int carId) {
		int valasz = JOptionPane.showConfirmDialog(null, "Biztosan törölni akarja az autót az adatbázisból?", "Biztos a dolgában?",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		String sql = "DELETE FROM rental_cars WHERE car_id = '"+carId+"';";
		if(valasz==0) {
		try {
			stm.executeUpdate(sql);
			MainWindow.tablazatBetolt();
			JOptionPane.showMessageDialog(null , "Autó sikeresen törölve az adatbázisból", "Táblázat frissítve", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "SQL hiba", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
		} else if(valasz==1) {
			dispose();
		} 
	}
	
	
	public void autoAdatokBetoltIdAlapjan(int carId) {
		
		try {
			stm = con.createStatement();
			String sql = "SELECT * FROM rental_cars WHERE car_id = '"+carId+"';";
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next()) {
				tfMarka.setText(rs.getString("car_brand"));
				tfTipus.setText(rs.getString("car_type"));
				tfRendszam.setText(rs.getString("lic_plate"));
				tfNapiAr.setText(rs.getString("daily_cost"));
				cbAllapot.setSelectedIndex(rs.getInt("status"));
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "SQL hiba", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
