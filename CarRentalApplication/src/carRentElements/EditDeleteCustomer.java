package carRentElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class EditDeleteCustomer extends JFrame {

	private JPanel cpUgyfMod;
	private JTextField tfNev;
	private JTextField tfCim;
	private JTextField tfTelefonSzam;
	private JLabel lblUgyfMod;
	private JLabel lblNev;
	private JLabel lblCim;
	private JLabel lblTelefonSzam;
	private JButton btnMentes;
	private JButton btnTorles;
	private JButton btnKilepes;
	private Statement stm = null;
	private Connection con = new Db().getCon();


	public EditDeleteCustomer(Customers MainWindow, int userId) {
		setTitle("\u00DCgyf\u00E9l m\u00F3dos\u00EDt\u00E1sa/t\u00F6rl\u00E9se");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 384);
		cpUgyfMod = new JPanel();
		cpUgyfMod.setToolTipText("");
		cpUgyfMod.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpUgyfMod);
		cpUgyfMod.setLayout(null);
		
		lblUgyfMod = new JLabel("\u00DCgyf\u00E9l m\u00F3dos\u00EDt\u00E1sa/t\u00F6rl\u00E9se");
		lblUgyfMod.setHorizontalAlignment(SwingConstants.CENTER);
		lblUgyfMod.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblUgyfMod.setBounds(24, 26, 280, 22);
		cpUgyfMod.add(lblUgyfMod);
		
		lblNev = new JLabel("N\u00E9v:");
		lblNev.setHorizontalAlignment(SwingConstants.LEFT);
		lblNev.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblNev.setBounds(24, 83, 81, 22);
		cpUgyfMod.add(lblNev);
		
		lblCim = new JLabel("C\u00EDm:");
		lblCim.setHorizontalAlignment(SwingConstants.LEFT);
		lblCim.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblCim.setBounds(24, 116, 81, 22);
		cpUgyfMod.add(lblCim);
		
		lblTelefonSzam = new JLabel("Telefonsz\u00E1m:");
		lblTelefonSzam.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefonSzam.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		lblTelefonSzam.setBounds(24, 149, 108, 22);
		cpUgyfMod.add(lblTelefonSzam);
		
		tfNev = new JTextField();
		tfNev.setColumns(10);
		tfNev.setBounds(159, 87, 145, 20);
		cpUgyfMod.add(tfNev);
		
		tfCim = new JTextField();
		tfCim.setColumns(10);
		tfCim.setBounds(159, 120, 145, 20);
		cpUgyfMod.add(tfCim);
		
		tfTelefonSzam = new JTextField();
		tfTelefonSzam.setColumns(10);
		tfTelefonSzam.setBounds(159, 153, 145, 20);
		cpUgyfMod.add(tfTelefonSzam);
		
		btnMentes = new JButton("Ment\u00E9s");
		btnMentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MentesGombraKattintas(MainWindow, userId);
			}
		});
		btnMentes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnMentes.setBounds(24, 210, 135, 31);
		cpUgyfMod.add(btnMentes);
		
		btnTorles = new JButton("T\u00F6rl\u00E9s");
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ugyfelTorlesGombraKattintas(MainWindow, userId);
			}
		});
		btnTorles.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnTorles.setBounds(187, 210, 135, 31);
		cpUgyfMod.add(btnTorles);
		
		btnKilepes = new JButton("Kil\u00E9p\u00E9s");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilepes.setForeground(Color.RED);
		btnKilepes.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
		btnKilepes.setBounds(204, 291, 118, 29);
		cpUgyfMod.add(btnKilepes);
		ugyfelAdatokBetoltIdAlapjan(userId);
		setVisible(true);
	}
	
	public void MentesGombraKattintas(Customers MainWindow, int userId) {
		
		if(tfNev.getText().isEmpty() || tfCim.getText().isEmpty() || tfTelefonSzam.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Kérem töltse ki az összes adatot!", "hiba a felvitelben!", JOptionPane.WARNING_MESSAGE);
		} else {
		
		try {
			Connection con = new Db().getCon();
			String sql = "UPDATE rental_users SET user_name= ? ,"
					+ "user_address = ? ,"
					+ "user_phone = ? WHERE user_id = '"+userId+"';";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tfNev.getText());
			ps.setString(2, tfCim.getText());
			ps.setString(3, tfTelefonSzam.getText());
			ps.executeUpdate();
			ps.close();
			JOptionPane.showMessageDialog(null , "Sikeres frissítés", "Táblázat frissítve", JOptionPane.INFORMATION_MESSAGE);
			MainWindow.tablazatBetolt();
			dispose();
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null , "Sikertelen hozzáadás", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	
	public void ugyfelTorlesGombraKattintas(Customers MainWindow, int userId) {
		int valasz = JOptionPane.showConfirmDialog(null, "Biztosan törölni akarja az ügyfelet az adatbázisból?", "Biztos a dolgában?",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		String sql = "DELETE FROM rental_users WHERE user_id = '"+userId+"';";
		if(valasz==0) {
			try {
				stm.executeUpdate(sql);
				MainWindow.tablazatBetolt();
				JOptionPane.showMessageDialog(null , "Az ügyfél sikeresen törölve az adatbázisból", "Táblázat frissítve", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
			}
		} else if(valasz==1) {
			dispose();
		} 
	}

	public void ugyfelAdatokBetoltIdAlapjan(int userId) {
	
		try {	
		stm = con.createStatement();
		String sql = "SELECT * FROM rental_users WHERE user_id = '"+userId+"';";
		
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()) {
			tfNev.setText(rs.getString("user_name"));
			tfCim.setText(rs.getString("user_address"));
			tfTelefonSzam.setText(rs.getString("user_phone"));
		}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);

		}
	}

}
