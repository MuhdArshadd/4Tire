package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLastName;
	private JTextField txtPhoneNumber;
	private JTextField txtEmailAddress;
	private JTextField txtPassword;
	private JTextField txtFirstName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegistration frame = new CustomerRegistration();
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
	public CustomerRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel.setBounds(122, 198, 108, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CUSTOMER REGISTRATION");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 37));
		lblNewLabel_1.setBounds(122, 48, 478, 55);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPleaseInsertThe = new JLabel("Please insert the following information:");
		lblPleaseInsertThe.setForeground(new Color(255, 255, 255));
		lblPleaseInsertThe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblPleaseInsertThe.setBounds(122, 139, 319, 49);
		contentPane.add(lblPleaseInsertThe);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(396, 198, 108, 39);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setForeground(new Color(255, 255, 255));
		lblPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPhoneNumber.setBounds(122, 269, 149, 39);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setForeground(new Color(255, 255, 255));
		lblEmailAddress.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmailAddress.setBounds(122, 342, 149, 39);
		contentPane.add(lblEmailAddress);
		
		JLabel lblNewAccountPassword = new JLabel("New Account \r\nPassword:");
		lblNewAccountPassword.setForeground(new Color(255, 255, 255));
		lblNewAccountPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewAccountPassword.setBounds(122, 405, 213, 49);
		contentPane.add(lblNewAccountPassword);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(501, 211, 143, 24);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(266, 282, 224, 24);
		contentPane.add(txtPhoneNumber);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setColumns(10);
		txtEmailAddress.setBounds(255, 355, 224, 24);
		contentPane.add(txtEmailAddress);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(345, 423, 224, 24);
		contentPane.add(txtPassword);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(228, 211, 143, 24);
		contentPane.add(txtFirstName);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFirstName.setText("");
				txtLastName.setText("");
				txtPhoneNumber.setText("");
				txtEmailAddress.setText("");
				txtPassword.setText("");
			}
		});
		btnClear.setBounds(315, 482, 130, 39);
		contentPane.add(btnClear);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Fname = txtFirstName.getText();
				String Lname = txtLastName.getText();
				String PhoneNum = new String(txtPhoneNumber.getText()).trim();
				String emailAddress = txtEmailAddress.getText();
				String password = new String(txtPassword.getText()).trim();
				
				Customer customer = new Customer();
				customer.setFirstName(Fname);
				customer.setLastName(Lname);
				customer.setContactNo(PhoneNum);
				customer.setEmailAddress(emailAddress);
				customer.setCustPass(password);
				
				CustomerController customercontroller = new CustomerController();
				try {
					if(customercontroller.insertCustomer(customer) == true) {
						JOptionPane.showMessageDialog(btnSignUp, "Your new account has been successfully registered !");
						CustomerLogin frame = new CustomerLogin();
						frame.setVisible(true);
						dispose();
						
					}
					else if(customercontroller.insertCustomer(customer) == false) {
						JOptionPane.showMessageDialog(btnSignUp, "This account is already registered.");
					}
				} catch(ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignUp.setBounds(514, 482, 130, 39);
		contentPane.add(btnSignUp);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLogin frame = new CustomerLogin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(122, 482, 130, 39);
		contentPane.add(btnBack);

		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setIcon(new ImageIcon(CustomerRegistration.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel1.setBounds(0, 0, 733, 537);
		contentPane.add(lblNewLabel1);
	}
}
