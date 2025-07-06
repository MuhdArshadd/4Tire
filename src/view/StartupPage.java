package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StartupPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartupPage frame = new StartupPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartupPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCarServiceAnd = new JLabel("CAR SERVICE AND REPAIR");
		lblCarServiceAnd.setForeground(new Color(255, 255, 255));
		lblCarServiceAnd.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 37));
		lblCarServiceAnd.setBounds(114, 0, 475, 127);
		contentPane.add(lblCarServiceAnd);
		
		JLabel lblPleaseChooseYour = new JLabel("Please Choose Your Category :");
		lblPleaseChooseYour.setForeground(new Color(255, 255, 255));
		lblPleaseChooseYour.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblPleaseChooseYour.setBounds(206, 98, 295, 82);
		contentPane.add(lblPleaseChooseYour);
		
		JButton btnCustomer = new JButton("CUSTOMER");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLogin frame = new CustomerLogin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCustomer.setBounds(253, 190, 185, 62);
		contentPane.add(btnCustomer);
		
		JButton btnStaff = new JButton("STAFF");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffLoginGui frame = null;
				try {
					frame = new StaffLoginGui();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);
				dispose();
			}
		});
		btnStaff.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnStaff.setBounds(253, 278, 185, 62);
		contentPane.add(btnStaff);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(StartupPage.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel.setBounds(0, 0, 704, 537);
		contentPane.add(lblNewLabel);
		
		
	}
}
