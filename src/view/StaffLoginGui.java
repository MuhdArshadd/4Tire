package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.StaffController;
import database.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.MyDatabase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StaffLoginGui extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffLoginGui frame = new StaffLoginGui();
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
	
	public StaffLoginGui() throws ClassNotFoundException, SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 744, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField txtStaffId = new JTextField();
		txtStaffId.setBounds(247, 198, 220, 32);
		contentPane.add(txtStaffId);
		txtStaffId.setColumns(10);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(247, 252, 220, 32);
		contentPane.add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String staffID = txtStaffId.getText();
				String staffPassword = txtPassword.getText();
        		LoginController loginController = new LoginController();
        		loginController.staffLogin(staffID, staffPassword);
        		dispose();
        		
			
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLogin.setBounds(371, 311, 115, 39);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartupPage frame1 = new StartupPage();
				frame1.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBack.setBounds(232, 311, 115, 39);
		contentPane.add(btnBack);
		
		JLabel lblStaffLogIn = new JLabel("STAFF LOG IN");
		lblStaffLogIn.setForeground(Color.WHITE);
		lblStaffLogIn.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 37));
		lblStaffLogIn.setBounds(232, 0, 254, 127);
		contentPane.add(lblStaffLogIn);
		
		JLabel lblPleaseInsertThe = new JLabel("Please insert the following information:");
		lblPleaseInsertThe.setForeground(Color.WHITE);
		lblPleaseInsertThe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblPleaseInsertThe.setBounds(205, 93, 319, 49);
		contentPane.add(lblPleaseInsertThe);
		
		JLabel lblEmailAddress = new JLabel("Staff ID:");
		lblEmailAddress.setForeground(Color.WHITE);
		lblEmailAddress.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmailAddress.setBounds(121, 189, 89, 39);
		contentPane.add(lblEmailAddress);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPassword.setBounds(121, 238, 115, 49);
		contentPane.add(lblPassword);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(StaffLoginGui.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel.setBounds(0, 0, 741, 548);
		contentPane.add(lblNewLabel);

		
	}
}