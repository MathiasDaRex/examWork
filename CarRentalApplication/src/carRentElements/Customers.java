package carRentElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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

@SuppressWarnings("serial")
public class Customers extends JFrame {

	private JPanel cpUgyfelek;
	private JScrollPane spUgyfelek;
	private JTable tblUgyfelek;
	private JTextField tfKeres;
	private JButton btnKilepes;
	private JLabel lblNev;
	private JButton btnKeres;
	private JButton btnUjUgyfel;
	private JButton btnOsszesUgyfel;

	public Customers(Cars MainWindow) {
		setTitle("\u00DCgyfelek kezel\u00E9se");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 913, 585);
		cpUgyfelek = new JPanel();
		cpUgyfelek.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpUgyfelek);
		cpUgyfelek.setLayout(null);
		
		spUgyfelek = new JScrollPane();
		spUgyfelek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		spUgyfelek.setBounds(10, 11, 864, 362);
		cpUgyfelek.add(spUgyfelek);
		
		tblUgyfelek = new JTable();
		tblUgyfelek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tableRowIndex = tblUgyfelek.rowAtPoint(e.getPoint());
				ugyfelekModositAblakMegjelenit(tableRowIndex);
			}
		});
		spUgyfelek.setViewportView(tblUgyfelek);
		tblUgyfelek.setModel(new DefaultTableModel(
				new String[] {"Ügyfél ID","Név", "Cím", "Telefonszám"},0
				));
		
		btnKilepes = new JButton("Kil\u00E9p\u00E9s");
		btnKilepes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnKilepes.setForeground(Color.BLACK);
		btnKilepes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKilepes.setBackground(Color.RED);
		btnKilepes.setBounds(696, 494, 175, 41);
		cpUgyfelek.add(btnKilepes);
		
		lblNev = new JLabel("N\u00E9v:");
		lblNev.setHorizontalAlignment(SwingConstants.CENTER);
		lblNev.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNev.setBounds(234, 410, 136, 26);
		cpUgyfelek.add(lblNev);
		
		tfKeres = new JTextField();
		tfKeres.setBounds(333, 415, 191, 20);
		cpUgyfelek.add(tfKeres);
		tfKeres.setColumns(10);
		
		btnKeres = new JButton("Keres");
		btnKeres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nevAlapjanKeres();
			}
		});
		btnKeres.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKeres.setBounds(534, 414, 89, 23);
		cpUgyfelek.add(btnKeres);
		
		btnUjUgyfel = new JButton("\u00DAj \u00FCgyf\u00E9l felvitele");
		btnUjUgyfel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UjUgyfelAblakMegjelenit();
			}
		});
		btnUjUgyfel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUjUgyfel.setBounds(10, 403, 204, 41);
		cpUgyfelek.add(btnUjUgyfel);
		
		btnOsszesUgyfel = new JButton("\u00D6sszes \u00FCgyf\u00E9l");
		btnOsszesUgyfel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablazatBetolt();
			}
		});
		btnOsszesUgyfel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOsszesUgyfel.setBounds(649, 414, 225, 23);
		cpUgyfelek.add(btnOsszesUgyfel);
		setVisible(true);
		tablazatBetolt();
		}

	public void UjUgyfelAblakMegjelenit() {
		new NewCustomer(this);
	}
	
	public void tablazatBetolt() {
		Db dbObj = new Db();
		String sql = "SELECT * FROM rental_users ORDER BY user_id;";
		ResultSet rs = dbObj.lekerdez(sql);
		tablazatSorokTorol();
			try {
				while (rs.next()) {
				((DefaultTableModel)tblUgyfelek.getModel()).addRow(
						new Object[] {
							rs.getString("user_id"),
							rs.getString("user_name"),
							rs.getString("user_address"),
							rs.getString("user_phone"),
						});
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	public void tablazatSorokTorol() {
		int sorokSzama = tblUgyfelek.getModel().getRowCount();
		for (int i = sorokSzama-1; i >= 0; i--) {
			((DefaultTableModel)tblUgyfelek.getModel()).removeRow(i);
		}
	}
	
	public void ugyfelekModositAblakMegjelenit(int tableRowIndex) {
		int id = Integer.parseInt(((DefaultTableModel)tblUgyfelek.getModel()).getValueAt(tableRowIndex, 0).toString());
		new EditDeleteCustomer(this, id);
	}
	
	public void nevAlapjanKeres() {
		Db dbObj = new Db();
		tablazatSorokTorol();
		String sql = "SELECT * FROM rental_users WHERE user_name LIKE '%"+tfKeres.getText()+"%';";
		ResultSet rs = dbObj.lekerdez(sql);
		try {
			while(rs.next()) {
				((DefaultTableModel)tblUgyfelek.getModel()).addRow(
						new Object [] {
								rs.getString("user_id"),
								rs.getString("user_name"),
								rs.getString("user_address"),
								rs.getString("user_phone"),
						}
				);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
		
	}	
}
