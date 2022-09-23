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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Cars extends JFrame{

	private JFrame frmCarRentalManager;
	private JTable tblAutok;
	private JScrollPane scrollPane;
	private JButton btnUjAuto;
	private JButton btnKilps;
	private JButton btnBerles;
	private JButton btnUgyfelek;
	private JButton btnVissza;

	public Cars() {
		initialize();
	}

	private void initialize() {
		frmCarRentalManager = new JFrame();
		frmCarRentalManager.setVisible(true);
		frmCarRentalManager.getContentPane().setBackground(UIManager.getColor("Viewport.background"));
		frmCarRentalManager.getContentPane().setForeground(UIManager.getColor("Viewport.foreground"));
		frmCarRentalManager.setTitle("Car rental manager");
		frmCarRentalManager.setBounds(100, 100, 897, 572);
		frmCarRentalManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCarRentalManager.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 861, 362);
		frmCarRentalManager.getContentPane().add(scrollPane);
		
		tblAutok = new JTable();
		tblAutok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tableRowIndex = tblAutok.rowAtPoint(e.getPoint());
				modositEsTorolAblakMegjelenit(tableRowIndex);
			}
		});
		scrollPane.setViewportView(tblAutok);
		tblAutok.setModel(new DefaultTableModel(
				new String[] {"ID",  "Márka", "Típus", "Rendszám", "Státusz", "Ár"}, 0
				));
		btnUjAuto = new JButton("\u00DAj aut\u00F3 felvitele");
		btnUjAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			ujAutoAblakMegjelenit();
			}
		});
		btnUjAuto.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUjAuto.setBounds(682, 409, 175, 41);
		frmCarRentalManager.getContentPane().add(btnUjAuto);
		
		btnKilps = new JButton("Kijelentkez\u00E9s");
		btnKilps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new Login();
			frmCarRentalManager.setVisible(false);
			dispose();
			}
		});
		btnKilps.setForeground(new Color(0, 0, 0));
		btnKilps.setBackground(new Color(255, 0, 0));
		btnKilps.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKilps.setBounds(682, 474, 175, 41);
		frmCarRentalManager.getContentPane().add(btnKilps);
		
		btnBerles = new JButton("Aut\u00F3 b\u00E9rl\u00E9se");
		btnBerles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ujBerlesAblakMegjelenit();
			}
		});
		btnBerles.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBerles.setBounds(10, 409, 204, 41);
		frmCarRentalManager.getContentPane().add(btnBerles);
		
		btnUgyfelek = new JButton("\u00DCgyfelek");
		btnUgyfelek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ugyfelekAblakMegjelenit();
			}
		});
		btnUgyfelek.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUgyfelek.setBounds(452, 409, 204, 41);
		frmCarRentalManager.getContentPane().add(btnUgyfelek);
		
		btnVissza = new JButton("Aut\u00F3 visszav\u00E9tele");
		btnVissza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoVisszaAblakMegjelenit();
			}
		});
		btnVissza.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVissza.setBounds(224, 409, 204, 41);
		frmCarRentalManager.getContentPane().add(btnVissza);
		tablazatBetolt();
	}
	
	public void tablazatBetolt() {
		Db dbObj = new Db();
		String sql = "SELECT * FROM rental_cars ORDER BY car_id;";
		ResultSet rs = dbObj.lekerdez(sql);
		tablazatSorokTorol();
		try {
			while(rs.next()) {
				String allapot = rs.getBoolean("status") ? "elérhetõ" : "foglalt";
				((DefaultTableModel)tblAutok.getModel()).addRow(
						new Object[] {
								rs.getString("car_id"),
								rs.getString("car_brand"),
								rs.getString("car_type"),
								rs.getString("lic_plate"),
								allapot,
								rs.getString("daily_cost"),
						});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null , "Hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	public void tablazatSorokTorol() {
		int sorokSzama = tblAutok.getModel().getRowCount();
		for (int i = sorokSzama-1; i >= 0; i--) {
			((DefaultTableModel)tblAutok.getModel()).removeRow(i);
		}
	}
	
	public void modositEsTorolAblakMegjelenit(int tableRowIndex) {
		int id = Integer.parseInt(((DefaultTableModel)tblAutok.getModel()).getValueAt(tableRowIndex, 0).toString());
		new EditDeleteCar(this, id);
	}
	
	public void ujAutoAblakMegjelenit() {
		new NewCar(this);
	}
	
	public void ugyfelekAblakMegjelenit() {
		new Customers(this);
	}
	
	public void ujBerlesAblakMegjelenit() {
		new CarRent(this);
	}
	
	public void autoVisszaAblakMegjelenit() {
		new CarReturn(this);
	}
}
