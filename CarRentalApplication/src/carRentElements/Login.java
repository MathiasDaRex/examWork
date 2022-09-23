package carRentElements;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Login extends JFrame{

	private JFrame frmLogin;
	private JTextField tfUserName;
	private JLabel lblMain;
	private JLabel lblUserName;
	private JLabel lblPwd;
	private JButton btnBejelentkezes;
	private JPasswordField pfPwd;


	public Login() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setVisible(true);
		frmLogin.setTitle("Car rental system");
		frmLogin.setBounds(200, 200, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		 lblMain = new JLabel("K\u00E9rem jelentkezzen be!");
		lblMain.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setBounds(102, 11, 206, 20);
		frmLogin.getContentPane().add(lblMain);
		
		 lblUserName = new JLabel("Felhaszn\u00E1l\u00F3n\u00E9v:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(10, 72, 135, 25);
		frmLogin.getContentPane().add(lblUserName);
		
		 lblPwd = new JLabel("Jelsz\u00F3:");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPwd.setBounds(10, 138, 135, 25);
		frmLogin.getContentPane().add(lblPwd);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(155, 72, 168, 24);
		frmLogin.getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		 btnBejelentkezes = new JButton("Bejelentkez\u00E9s");
		btnBejelentkezes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				carsAblakMegjelenit();

			}
		});
		btnBejelentkezes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBejelentkezes.setBounds(155, 196, 127, 42);
		frmLogin.getContentPane().add(btnBejelentkezes);
		
		pfPwd = new JPasswordField();
		pfPwd.setBounds(155, 141, 168, 22);
		frmLogin.getContentPane().add(pfPwd);	
	}
	
	private void carsAblakMegjelenit() {
		Connection con = new Db().getCon();
		String sql = "SELECT * FROM rental_admins WHERE admin_name=? AND admin_pwd=?;";
		String userName = tfUserName.getText();
        String password = String.valueOf(pfPwd.getPassword());
		if(tfUserName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Kérem adja meg a felhasználónevet!", "Hiba", JOptionPane.WARNING_MESSAGE);
		} else if (password.isEmpty()){
			JOptionPane.showMessageDialog(null, "Kérem adja meg a jelszavat!", "Hiba", JOptionPane.WARNING_MESSAGE);
		} else {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, userName);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					frmLogin.setVisible(false);
					dispose();
					new Cars();
				} else {
					JOptionPane.showMessageDialog(null, "Hibás bejelentkezési adatok!", "Hiba", JOptionPane.WARNING_MESSAGE);
					tfUserName.setText("");
					pfPwd.setText("");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "SQL hiba!", "Hiba", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
