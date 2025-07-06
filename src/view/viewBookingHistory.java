package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BookingController;
import model.Booking;
import model.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class viewBookingHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Customer customer;
	private JTable table;
	private JTextField textDOS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewBookingHistory frame = new viewBookingHistory(customer);
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
	 public viewBookingHistory(Customer customer) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1153, 739);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 57, 1072, 438);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnBack = new JButton("BACK");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerHomeMenu frame = new CustomerHomeMenu(customer);
                frame.setVisible(true);
                dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnBack.setBounds(10, 10, 119, 39);
        contentPane.add(btnBack);

        JLabel lblPleaseChoose = new JLabel("YOUR CAR SERVICE HISTORY");
        lblPleaseChoose.setForeground(new Color(255, 255, 255));
        lblPleaseChoose.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
        lblPleaseChoose.setBounds(417, 13, 292, 31);
        contentPane.add(lblPleaseChoose);

        JLabel lblDateOfService = new JLabel("Date Of Service:\r\n");
        lblDateOfService.setForeground(new Color(255, 255, 255));
        lblDateOfService.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
        lblDateOfService.setBounds(728, 505, 130, 31);
        contentPane.add(lblDateOfService);

        JButton btnSearch = new JButton("SEARCH");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dateStr = textDOS.getText();

                try {
                    BookingController bookingController = new BookingController();
                    List<Booking> carServiceList = bookingController.carServiceCompleted(customer);

                    DefaultTableModel model = new DefaultTableModel();
                    model.setColumnIdentifiers(new Object[]{"SlotID", "Service Type", "Car Name", "Car Model", "Car Type", "Date Of Service", "Status", "Description"});

                    table.setModel(model);

                    if (!dateStr.isEmpty()) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date targetDate = dateFormat.parse(dateStr);

                        for (Booking carServiceInfo : carServiceList) {
                            String rowDateStr = carServiceInfo.getDateOfAppointment();
                            if (rowDateStr != null) {
                                try {
                                    Date rowDate = dateFormat.parse(rowDateStr);

                                    if (rowDate.equals(targetDate)) {
                                        model.addRow(new Object[]{carServiceInfo.getSlotID(), carServiceInfo.getServiceType(), carServiceInfo.getCarName(), carServiceInfo.getCarModel(), carServiceInfo.getCarType(), carServiceInfo.getDateOfAppointment(), carServiceInfo.getStatus(), carServiceInfo.getDescription()});
                                    }
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } else {
                        for (Booking carServiceInfo : carServiceList) {
                            model.addRow(new Object[]{carServiceInfo.getSlotID(), carServiceInfo.getServiceType(), carServiceInfo.getCarName(), carServiceInfo.getCarModel(), carServiceInfo.getCarType(), carServiceInfo.getDateOfAppointment(), carServiceInfo.getStatus(), carServiceInfo.getDescription()});
                        }
                    }

                } catch (ClassNotFoundException | SQLException | ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(contentPane, "Error fetching data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSearch.setBounds(962, 546, 130, 39);
        contentPane.add(btnSearch);

        textDOS = new JTextField();
        textDOS.setColumns(10);
        textDOS.setBounds(868, 512, 224, 24);
        contentPane.add(textDOS);

        // Fetch data and update the table
        try {
            BookingController bookingController = new BookingController();
            List<Booking> carServiceList = bookingController.carServiceCompleted(customer);

            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"SlotID", "Service Type", "Car Name", "Car Model", "Car Type", "Date Of Service", "Status", "Description"});

            table.setModel(model);

            JButton btnReset = new JButton("RESET");
            btnReset.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		try {
            			BookingController bookingController = new BookingController();
            			List<Booking> carServiceList = bookingController.carServiceCompleted(customer);

            			DefaultTableModel model = new DefaultTableModel();
            			model.setColumnIdentifiers(new Object[]{"SlotID", "Service Type", "Car Name", "Car Model", "Car Type", "Date Of Service", "Status", "Description"});

	            table.setModel(model);

	            for (Booking carServiceInfo : carServiceList) {
	            	model.addRow(new Object[]{carServiceInfo.getSlotID(), carServiceInfo.getServiceType(), carServiceInfo.getCarName(), carServiceInfo.getCarModel(), carServiceInfo.getCarType(), carServiceInfo.getDateOfAppointment(), carServiceInfo.getStatus(), carServiceInfo.getDescription()});
	            }

            		} catch (ClassNotFoundException | SQLException ex) {
            			ex.printStackTrace();
            			JOptionPane.showMessageDialog(contentPane, "Error resetting data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            		}
            	}
            });
            btnReset.setVerticalAlignment(SwingConstants.BOTTOM);
            btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
            btnReset.setBounds(20, 513, 119, 39);
            contentPane.add(btnReset);


            for (Booking carServiceInfo : carServiceList) {
                model.addRow(new Object[]{carServiceInfo.getSlotID(), carServiceInfo.getServiceType(), carServiceInfo.getCarName(), carServiceInfo.getCarModel(), carServiceInfo.getCarType(), carServiceInfo.getDateOfAppointment(), carServiceInfo.getStatus(), carServiceInfo.getDescription()});
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	
		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setIcon(new ImageIcon(viewBookingHistory.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel1.setBounds(0, 0, 1139, 702);
		contentPane.add(lblNewLabel1);
		
    }
}
