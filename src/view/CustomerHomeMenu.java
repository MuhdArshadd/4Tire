package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Customer;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerHomeMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
public static Customer customer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerHomeMenu frame = new CustomerHomeMenu(customer);
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
	public CustomerHomeMenu(Customer customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblWelcome = new JLabel("WELCOME " + customer.getFirstName()+ " " +customer.getLastName());
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblWelcome.setBounds(197, 100, 414, 49);
		contentPane.add(lblWelcome);
		
		JLabel lblYourLoyaltyPoint = new JLabel("Your Loyalty Point : " + customer.getLoyaltypoint());
		lblYourLoyaltyPoint.setForeground(Color.WHITE);
		lblYourLoyaltyPoint.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblYourLoyaltyPoint.setBounds(181, 158, 414, 49);
		contentPane.add(lblYourLoyaltyPoint);
		
		
		JButton btnBook = new JButton("BOOK SERVICE");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingMenu frame = new BookingMenu(customer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBook.setBounds(210, 263, 285, 39);
		contentPane.add(btnBook);
		
		JButton btnViewSchedule = new JButton("VIEW SCHEDULE");
		btnViewSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooking frame = new ViewBooking(customer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewSchedule.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnViewSchedule.setBounds(210, 320, 281, 39);
		contentPane.add(btnViewSchedule);
		
		JButton btnHistory = new JButton("VIEW SERVICE HISTORY");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewBookingHistory frame = new viewBookingHistory(customer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHistory.setBounds(212, 382, 283, 39);
		contentPane.add(btnHistory);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnLogOut, "You successfully log out !");
				CustomerLogin frame = new CustomerLogin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogOut.setBounds(554, 468, 169, 39);
		contentPane.add(btnLogOut);
		
		JLabel lblCustomerHomeMenu = new JLabel("CUSTOMER HOME MENU");
		lblCustomerHomeMenu.setForeground(Color.WHITE);
		lblCustomerHomeMenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 37));
		lblCustomerHomeMenu.setBounds(121, 0, 475, 90);
		contentPane.add(lblCustomerHomeMenu);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to do today?");
		lblWhatWouldYou.setForeground(Color.WHITE);
		lblWhatWouldYou.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblWhatWouldYou.setBounds(181, 192, 344, 61);
		contentPane.add(lblWhatWouldYou);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(CustomerHomeMenu.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel.setBounds(0, 0, 733, 537);
		contentPane.add(lblNewLabel);
		

	}
}
