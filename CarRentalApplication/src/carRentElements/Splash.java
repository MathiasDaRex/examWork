package carRentElements;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Splash extends JFrame {

	private JPanel contentPane;
	private JProgressBar progBar1;

	public static void main(String[] args) {
		Splash sp1 = new Splash();
		sp1.setVisible(true);
		
		try {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(20);
				sp1.progBar1.setValue(i);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null , "Hiba a betöltésben", "Hiba", JOptionPane.ERROR_MESSAGE);

		}	
		new Login();
		sp1.dispose();
	}

	public Splash() {
		setTitle("Bet\u00F6lt\u00E9s");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car Rental Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 47, 434, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lbldvzlintA = new JLabel("\u00DCdv\u00F6zli \u00F6nt a");
		lbldvzlintA.setHorizontalAlignment(SwingConstants.CENTER);
		lbldvzlintA.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbldvzlintA.setBounds(0, 7, 434, 44);
		contentPane.add(lbldvzlintA);
		
		JLabel lblBetlts = new JLabel("Bet\u00F6lt\u00E9s");
		lblBetlts.setHorizontalAlignment(SwingConstants.CENTER);
		lblBetlts.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblBetlts.setBounds(0, 150, 434, 44);
		contentPane.add(lblBetlts);
		
		progBar1 = new JProgressBar();
		progBar1.setBounds(0, 204, 434, 14);
		contentPane.add(progBar1);
	}
}
