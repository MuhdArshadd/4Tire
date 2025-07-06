package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class CustomerLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmailAddress;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLogin frame = new CustomerLogin();
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
	public CustomerLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER LOG IN");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 37));
		lblNewLabel.setBounds(175, 10, 356, 127);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseInsertThe = new JLabel("Please insert the following information:");
		lblPleaseInsertThe.setForeground(new Color(255, 255, 255));
		lblPleaseInsertThe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblPleaseInsertThe.setBounds(205, 98, 319, 49);
		contentPane.add(lblPleaseInsertThe);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setForeground(new Color(255, 255, 255));
		lblEmailAddress.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmailAddress.setBounds(118, 180, 149, 39);
		contentPane.add(lblEmailAddress);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setColumns(10);
		txtEmailAddress.setBounds(257, 193, 224, 24);
		contentPane.add(txtEmailAddress);
		
		 JLabel lblPassword = new JLabel("Password:");
		 lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblPassword.setBounds(118, 245, 213, 49);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(257, 263, 224, 24);
        contentPane.add(txtPassword);

        // Other components and initialization code can be added here

        setVisible(true);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartupPage frame = new StartupPage();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(118, 322, 130, 39);
		contentPane.add(btnBack);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmailAddress.setText("");
				txtPassword.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(311, 322, 130, 39);
		contentPane.add(btnClear);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String emailAddress = txtEmailAddress.getText();
				String password = new String(txtPassword.getText()).trim();
				
				Customer customer = new Customer();
				customer.setEmailAddress(emailAddress);
				customer.setCustPass(password);
				
				CustomerController customercontroller = new CustomerController();
				try {
					if(customercontroller.loginCustomer(customer) == true) {
						JOptionPane.showMessageDialog(btnLogIn, "You are log in !");
						CustomerHomeMenu frame = new CustomerHomeMenu(customer);
						frame.setVisible(true);
						dispose();
						
					}
					else if(customercontroller.loginCustomer(customer) == false) {
						JOptionPane.showMessageDialog(btnLogIn, "Invalid Email or Password");
					}
				} catch(ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogIn.setBounds(510, 322, 130, 39);
		contentPane.add(btnLogIn);
		
		JLabel lblFirstTimer = new JLabel("First Timer?");
		lblFirstTimer.setForeground(new Color(255, 255, 255));
		lblFirstTimer.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblFirstTimer.setBounds(516, 434, 159, 49);
		contentPane.add(lblFirstTimer);
		
		JButton btnRegisterNow = new JButton("REGISTER NOW");
		btnRegisterNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerRegistration frame = new CustomerRegistration();
				frame.setVisible(true);
				dispose();
			}
		});
		btnRegisterNow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegisterNow.setBounds(510, 488, 213, 39);
		contentPane.add(btnRegisterNow);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(CustomerLogin.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel_2.setBounds(0, 0, 743, 548);
		contentPane.add(lblNewLabel_2);
	}
}
