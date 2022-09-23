package carRentElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class NewCustomer extends JFrame {

	private JPanel cpUjUgyfel;
	private JTextField tfNev;
	private JTextField tfCim;
	private JTextField tfTelefonSzam;
	private JLabel lblUjUgyfel; 
	private JLabel lblNev ;
	private JLabel lblCim;
	private JLabel lblTelefonszam;
	private JButton btnMentes;
	private JButton btnKilepes;



	public NewCustomer(Customers MainWindow) {
		setTitle("\u00DAj \u00FCgyf\u00E9l felvitele");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 347);
		cpUjUgyfel = new JPanel();
		cpUjUgyfel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpUjUgyfel);
		cpUjUgyfel.setLayout(null);
		
		lblUjUgyfel = new JLabel("\u00DAj \u00FCgyf\u00E9l felvitele");
		lblUjUgyfel.setHorizontalAlignment(SwingConstants.CENTER);
		lblUjUgyfel.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblUjUgyfel.setBounds(20, 31, 280, 22);
		cpUjUgyfel.add(lblUjUgyfel);
		
		lblNev = new JLabel("N\u00E9v:");
		lblNev.setHorizontalAlignment(SwingConstants.LEFT);
		lblNev.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblNev.setBounds(20, 81, 81, 22);
		cpUjUgyfel.add(lblNev);
		
		lblCim = new JLabel("C\u00EDm:");
		lblCim.setHorizontalAlignment(SwingConstants.LEFT);
		lblCim.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblCim.setBounds(20, 114, 81, 22);
		cpUjUgyfel.add(lblCim);
		
		lblTelefonszam = new JLabel("Telefonsz\u00E1m:");
		lblTelefonszam.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefonszam.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblTelefonszam.setBounds(20, 147, 108, 22);
		cpUjUgyfel.add(lblTelefonszam);
		
		btnMentes = new JButton("Ment\u00E9s");
		btnMentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MentesGombraKattintas(MainWindow);
			}
		});
		btnMentes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnMentes.setBounds(20, 202, 129, 29);
		cpUjUgyfel.add(btnMentes);
		
		btnKilepes = new JButton("Kil\u00E9p\u00E9s");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilepes.setForeground(Color.RED);
		btnKilepes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnKilepes.setBounds(187, 267, 118, 29);
		cpUjUgyfel.add(btnKilepes);
		
		tfNev = new JTextField();
		tfNev.setColumns(10);
		tfNev.setBounds(155, 85, 145, 20);
		cpUjUgyfel.add(tfNev);
		
		tfCim = new JTextField();
		tfCim.setColumns(10);
		tfCim.setBounds(155, 118, 145, 20);
		cpUjUgyfel.add(tfCim);
		
		tfTelefonSzam = new JTextField();
		tfTelefonSzam.setColumns(10);
		tfTelefonSzam.setBounds(155, 151, 145, 20);
		cpUjUgyfel.add(tfTelefonSzam);
		setVisible(true);
	}
	
	private void MentesGombraKattintas(Customers MainWindow) {
		try {
			Connection con = new Db().getCon();
			String sql = "INSERT INTO rental_users"
					+ "(user_name, user_address, user_phone)"
					+ "VALUES (?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tfNev.getText());
			ps.setString(2, tfCim.getText());
			ps.setString(3, tfTelefonSzam.getText());
			ps.execute();
			ps.close();
			JOptionPane.showMessageDialog(null , "Sikeres hozzáadás", "Táblázat frissítve", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			MainWindow.tablazatBetolt();	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
 
}
