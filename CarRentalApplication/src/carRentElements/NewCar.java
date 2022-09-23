package carRentElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
public class NewCar extends JFrame {

	private JPanel cpUjAuto;
	private JTextField tfRendszam;
	private JTextField tfTipus;
	private JTextField tfMarka;
	private JTextField tfNapiAr;
	@SuppressWarnings("rawtypes")
	private JComboBox cbAllapot;
	private JButton btnMentes;
	private JButton btnKilepes;
	private JLabel lblUjAuto;
	private JLabel lblMarka;
	private JLabel lblTipus;
	private JLabel lblRendszam;
	private JLabel lblNapiAr;
	private JLabel lblAllapot;

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewCar(Cars MainWindow) {
		setTitle("\u00DAj aut\u00F3 felvitele");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 375, 426);
		cpUjAuto = new JPanel();
		cpUjAuto.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpUjAuto);
		
		lblUjAuto = new JLabel("\u00DAj aut\u00F3 felvitele");
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
		tfRendszam.setBounds(123, 147, 145, 20);
		tfRendszam.setColumns(10);
		
		tfTipus = new JTextField();
		tfTipus.setBounds(123, 116, 145, 20);
		tfTipus.setColumns(10);
		
		tfMarka = new JTextField();
		tfMarka.setBounds(123, 85, 145, 20);
		tfMarka.setColumns(10);
		
		tfNapiAr = new JTextField();
		tfNapiAr.setBounds(123, 184, 145, 20);
		tfNapiAr.setColumns(10);
		
		cbAllapot = new JComboBox();
		cbAllapot.setBounds(123, 213, 145, 25);
		cbAllapot.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		cbAllapot.setModel(new DefaultComboBoxModel(new String[] {"Nem el\u00E9rhet\u0151", "El\u00E9rhet\u0151"}));
		cbAllapot.setSelectedIndex(1);
		
		btnMentes = new JButton("Ment\u00E9s");
		btnMentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MentesGombraKattintas(MainWindow);	
			}
		
		});
		btnMentes.setBounds(123, 262, 145, 43);
		btnMentes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		cpUjAuto.setLayout(null);
		cpUjAuto.add(lblUjAuto);
		cpUjAuto.add(lblNapiAr);
		cpUjAuto.add(tfNapiAr);
		cpUjAuto.add(lblRendszam);
		cpUjAuto.add(lblTipus);
		cpUjAuto.add(lblMarka);
		cpUjAuto.add(tfMarka);
		cpUjAuto.add(tfTipus);
		cpUjAuto.add(tfRendszam);
		cpUjAuto.add(lblAllapot);
		cpUjAuto.add(btnMentes);
		cpUjAuto.add(cbAllapot);
		
		btnKilepes = new JButton("Kil\u00E9p\u00E9s");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnKilepes.setForeground(Color.RED);
		btnKilepes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnKilepes.setBounds(231, 347, 118, 29);
		cpUjAuto.add(btnKilepes);
		setVisible(true);
	}
	
	private void MentesGombraKattintas(Cars MainWindow) {
		if(tfMarka.getText().isEmpty() || tfTipus.getText().isEmpty() ||
				tfRendszam.getText().isEmpty() || tfTipus.getText().isEmpty() || cbAllapot.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Kérem töltse ki az összes adatot!", "hiba a felvitelben!", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				Connection con = new Db().getCon();
				String sql = "INSERT INTO rental_cars"
						+ "(car_brand, car_type, lic_plate, daily_cost, status)"
						+ "VALUES (?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, tfMarka.getText());
				ps.setString(2, tfTipus.getText());
				ps.setString(3, tfRendszam.getText());
				ps.setString(4, tfNapiAr.getText());
				ps.setInt(5, cbAllapot.getSelectedIndex());
				ps.execute();
				MainWindow.tablazatBetolt();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
