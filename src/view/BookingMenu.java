package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BookingController;
import controller.CustomerController;
import model.Booking;
import model.Customer;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;



public class BookingMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField carTypeText;
	private JTextField carNameText;
	private JTextField carModelText;
	private JTextField descText;
	private static Customer customer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingMenu frame = new BookingMenu(customer);
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
	public BookingMenu(Customer customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		carTypeText = new JTextField();
		carTypeText.setBounds(191, 156, 172, 29);
		contentPane.add(carTypeText);
		carTypeText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Car Type :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel.setBounds(66, 153, 85, 19);
		contentPane.add(lblNewLabel);
		
		carNameText = new JTextField();
		carNameText.setColumns(10);
		carNameText.setBounds(191, 277, 172, 29);
		contentPane.add(carNameText);
		
		JLabel lblCarName = new JLabel("Car Name :");
		lblCarName.setForeground(new Color(255, 255, 255));
		lblCarName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblCarName.setBounds(66, 276, 99, 25);
		contentPane.add(lblCarName);
		
		carModelText = new JTextField();
		carModelText.setColumns(10);
		carModelText.setBounds(191, 214, 172, 29);
		contentPane.add(carModelText);
		
		JLabel lblCarModel = new JLabel("Description");
		lblCarModel.setForeground(new Color(255, 255, 255));
		lblCarModel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblCarModel.setBounds(493, 144, 107, 36);
		contentPane.add(lblCarModel);
		
		descText = new JTextField();
		descText.setColumns(10);
		descText.setBounds(424, 216, 262, 141);
		contentPane.add(descText);
		
		JLabel lblCarModel_1 = new JLabel("Service Type :");
		lblCarModel_1.setForeground(new Color(255, 255, 255));
		lblCarModel_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblCarModel_1.setBounds(66, 339, 125, 25);
		contentPane.add(lblCarModel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Booking Car Service");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_1.setBounds(240, 27, 265, 45);
		contentPane.add(lblNewLabel_1);
		
		BookingController bookingController = new BookingController();
		ArrayList<String> categories = new ArrayList<>();
		
		try {
			for(int i = 2209; i < 2222;i++)
			{
				String servicetype = bookingController.ListService(i);
				categories.add(servicetype);
			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox<String> comboBox = new JComboBox<>(categories.toArray(new String[0]));
		comboBox.setBounds(66, 374, 297, 36);
		contentPane.add(comboBox);
		
		JLabel lblCarModel_1_1 = new JLabel("Car Model :");
		lblCarModel_1_1.setForeground(new Color(255, 255, 255));
		lblCarModel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblCarModel_1_1.setBounds(66, 211, 99, 29);
		contentPane.add(lblCarModel_1_1);
		
		
		JButton requestBtn = new JButton("Request");
		requestBtn.setForeground(new Color(0, 0, 0));
		requestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String carName = carNameText.getText();
				String carModel = carModelText.getText();
				String carType = carTypeText.getText();
				String description = descText.getText();
				String category = comboBox.getSelectedItem().toString();
				
				Booking booking = new Booking();
				booking.setCarName(carName);
				booking.setCarModel(carModel);
				booking.setCarType(carType);
				booking.setDescription(description);
				booking.setServiceType(category);
				
				BookingController bookingController = new BookingController();
				try {
					if(bookingController.insertBooking(booking, customer) == true) {
						JOptionPane.showMessageDialog(requestBtn, "Your booking has been record!");
						
					}
					else if(bookingController.insertBooking(booking, customer) == false) {
						JOptionPane.showMessageDialog(requestBtn, "Booking unsuccessfull.");
					}
				} catch(ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		requestBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
		requestBtn.setBounds(299, 449, 138, 45);
		contentPane.add(requestBtn);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerHomeMenu frame = new CustomerHomeMenu(customer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBack.setBounds(10, 10, 116, 36);
		contentPane.add(btnBack);
		
		JLabel lblPleaseFillUp = new JLabel("Please fill up the form below :");
		lblPleaseFillUp.setForeground(Color.WHITE);
		lblPleaseFillUp.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblPleaseFillUp.setBounds(224, 62, 300, 61);
		contentPane.add(lblPleaseFillUp);
		
		JLabel lblexplainationOnCar = new JLabel("(Explaination On Car Condition)");
		lblexplainationOnCar.setForeground(Color.WHITE);
		lblexplainationOnCar.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblexplainationOnCar.setBounds(424, 180, 262, 36);
		contentPane.add(lblexplainationOnCar);

		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setIcon(new ImageIcon(BookingMenu.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel1.setBounds(0, 0, 733, 537);
		contentPane.add(lblNewLabel1);
		
	}
}
