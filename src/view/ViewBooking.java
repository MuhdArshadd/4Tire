package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CustomerController;
import controller.BookingController;
import model.Booking;
import model.Customer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
public static Customer customer;
	private JTable table;
	private int row;
	private int opt = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				  try { 
					  ViewBooking frame = new ViewBooking(customer);
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
	public ViewBooking(Customer customer) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 747, 739);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnViewBookingRequest = new JButton("VIEW BOOKING REQUEST");
        btnViewBookingRequest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	opt = 1; // option 1
            	JOptionPane.showMessageDialog(btnViewBookingRequest, "Please Choose The Options for your booking request.");
                BookingController bookingController = new BookingController();
                try {
                    List<Booking> carServiceList = bookingController.carServicePending(customer);

                    // Create the JTable with DefaultTableModel and column names
                    DefaultTableModel model = new DefaultTableModel();
                    model.setColumnIdentifiers(new Object[]{"SlotID", "Service Type", "Car Name", "Car Model", "Car Type", "Status"});

                    // Set the model for the JTable
                    table.setModel(model);

                    // Add rows to the model
                    for (Booking carServiceInfo : carServiceList) {
                        model.addRow(new Object[]{carServiceInfo.getSlotID(), carServiceInfo.getServiceType(),
                                carServiceInfo.getCarName(), carServiceInfo.getCarModel(), carServiceInfo.getCarType(),
                                carServiceInfo.getStatus()});
                    }

                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnViewBookingRequest.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnViewBookingRequest.setBounds(10, 593, 219, 39);
        contentPane.add(btnViewBookingRequest);

        JButton btnViewApprovedRequest = new JButton("VIEW APPROVED REQUEST");
        btnViewApprovedRequest.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 opt = 2; // option 2
        		 JOptionPane.showMessageDialog(btnViewApprovedRequest, "Please Choose The Options for approved request.");
                 BookingController bookingController = new BookingController();
                 try {
                     List<Booking> carServiceList = bookingController.carServiceApproved(customer);

                     // Create the JTable with DefaultTableModel and column names
                     DefaultTableModel model = new DefaultTableModel();
                     model.setColumnIdentifiers(new Object[]{"SlotID", "Service Type", "Car Name", "Car Model", "Car Type", "Date Of Service" ,"Status"});

                     // Set the model for the JTable
                     table.setModel(model);

                     // Add rows to the model
                     for (Booking carServiceInfo : carServiceList) {
                         model.addRow(new Object[]{carServiceInfo.getSlotID(), carServiceInfo.getServiceType(),
                                 carServiceInfo.getCarName(), carServiceInfo.getCarModel(), carServiceInfo.getCarType(), carServiceInfo.getDateOfAppointment(),
                                 carServiceInfo.getStatus()});
                     }

                 } catch (ClassNotFoundException | SQLException e1) {
                     e1.printStackTrace();
                 }
             }
         });
        btnViewApprovedRequest.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnViewApprovedRequest.setBounds(10, 642, 219, 39);
        contentPane.add(btnViewApprovedRequest);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 57, 689, 438);
        contentPane.add(scrollPane);

        // Create the JTable without setting the model initially
        table = new JTable();
        // to get the selected row 
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) { 
        		row = table.getSelectedRow();        	}
        });

        // Set the JTable as the view for the JScrollPane
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
        
        JLabel lblPleaseChoose = new JLabel("1. Please Choose:");
        lblPleaseChoose.setForeground(new Color(255, 255, 255));
        lblPleaseChoose.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
        lblPleaseChoose.setBounds(10, 505, 219, 31);
        contentPane.add(lblPleaseChoose);
        
        JLabel lblOptions = new JLabel("2. Options:");
        lblOptions.setForeground(new Color(255, 255, 255));
        lblOptions.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
        lblOptions.setBounds(441, 535, 119, 31);
        contentPane.add(lblOptions);
        
        JButton btnConfirmed = new JButton("CONFIRMED");
        btnConfirmed.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if ( opt == 1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    if (row >= 0 && row < model.getRowCount()) {
                     JOptionPane.showMessageDialog(btnConfirmed, "Your booking request is confirmed. Waiting for staff approval");
                    }
                    else {
                        // Handle the case where the row index is out of bounds
                        JOptionPane.showMessageDialog(btnConfirmed, "Invalid row index: " + row);
                    }
        		}
        		else if (opt == 2) {
                    // Use the existing model associated with the table
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    

                    // Make sure the row index is within the valid range
                    if (row >= 0 && row < model.getRowCount()) {
                        Booking booking = new Booking();
                        booking.setSlotID((int) model.getValueAt(row, 0));
                        int ID = booking.getSlotID();

                        int confirmation = JOptionPane.showConfirmDialog(btnConfirmed, "Do you want to confirm this booking?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirmation == JOptionPane.YES_OPTION) {
                            BookingController controller = new BookingController();
                            try {
                                //if (controller.bookingConfirmed(booking)) {
                                    JOptionPane.showMessageDialog(btnConfirmed, "Your booking request is confirmed. You will be directed to payment");
                                    //-----------------------------
                                    PaymentMenu frame = new PaymentMenu(customer, ID);
                                    frame.setVisible(true);
                    				dispose();
                                    //--------------------------------
                                //} else {
                                    //JOptionPane.showMessageDialog(btnConfirmed, "Already On Going Car Services");
                                //}
                            } catch (ClassNotFoundException | SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } else {
                        // Handle the case where the row index is out of bounds
                        JOptionPane.showMessageDialog(btnConfirmed, "Invalid row index: " + row);
                    }
                }else if (opt == 3)
                {
                    JOptionPane.showMessageDialog(btnConfirmed, "Already On Going Car Services.");
                }
            }
        });
        btnConfirmed.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnConfirmed.setBounds(509, 576, 142, 39);
        contentPane.add(btnConfirmed);
        
        JButton btnCancel = new JButton("CANCEL");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (opt == 1) {
        			int confirmation = JOptionPane.showConfirmDialog(btnCancel, "Do you want to cancel this booking?", "Confirmation", JOptionPane.YES_NO_OPTION);
        			if (confirmation == JOptionPane.YES_OPTION) {
        				// Use the existing model associated with the table
        				DefaultTableModel model = (DefaultTableModel) table.getModel();

        				// Make sure the row index is within the valid range
        				if (row >= 0 && row < model.getRowCount()) {
        					Booking booking = new Booking();
        					booking.setSlotID((int) model.getValueAt(row, 0));

        					BookingController controller = new BookingController();
        					try {
        						if (controller.bookingCancel(booking)) {
        							JOptionPane.showMessageDialog(btnCancel, "Your booking has been canceled");
        						} else {
                            JOptionPane.showMessageDialog(btnCancel, "SlotID is not found.");
                        }
                    } catch (ClassNotFoundException | SQLException e1) {
                        // Log the exception or show a user-friendly error message
                        e1.printStackTrace();
                    }
                } else {
                    // Handle the case where the row index is out of bounds
                    JOptionPane.showMessageDialog(btnCancel, "Invalid row index: " + row);
                }
            }
        } else if (opt == 2) {
            int confirmation = JOptionPane.showConfirmDialog(btnCancel, "Do you want to cancel this booking?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
				// Use the existing model associated with the table
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				// Make sure the row index is within the valid range
				if (row >= 0 && row < model.getRowCount()) {
					Booking booking = new Booking();
					booking.setSlotID((int) model.getValueAt(row, 0));

					BookingController controller = new BookingController();
					try {
						if (controller.bookingCancel(booking)) {
							JOptionPane.showMessageDialog(btnCancel, "Your booking has been canceled");
						} else {
                    JOptionPane.showMessageDialog(btnCancel, "Sorry, you cannot cancel this ongoing car service.");
                }
            } catch (ClassNotFoundException | SQLException e1) {
                // Log the exception or show a user-friendly error message
                e1.printStackTrace();
            }
        } else {
            // Handle the case where the row index is out of bounds
            JOptionPane.showMessageDialog(btnCancel, "Invalid row index: " + row);
        }
    }
        }
        else if (opt == 3)
        {
            JOptionPane.showMessageDialog(btnCancel, "Sorry, you cannot cancel this ongoing car service.");
        }
    }
});

btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
btnCancel.setBounds(321, 576, 142, 39);
contentPane.add(btnCancel);
JButton btnViewOngoingService = new JButton("VIEW ONGOING SERVICE");
btnViewOngoingService.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		opt = 3;
        BookingController bookingController = new BookingController();
        try {
            List<Booking> carServiceList = bookingController.carServiceOnGoing(customer);

            // Create the JTable with DefaultTableModel and column names
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"SlotID", "Service Type", "Car Name", "Car Model", "Car Type", "Date Of Service" ,"Status"});

            // Set the model for the JTable
            table.setModel(model);

            // Add rows to the model
            for (Booking carServiceInfo : carServiceList) {
                model.addRow(new Object[]{carServiceInfo.getSlotID(), carServiceInfo.getServiceType(),
                        carServiceInfo.getCarName(), carServiceInfo.getCarModel(), carServiceInfo.getCarType(), carServiceInfo.getDateOfAppointment(),
                        carServiceInfo.getStatus()});
            }

        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
	}
});
btnViewOngoingService.setFont(new Font("Tahoma", Font.PLAIN, 15));
btnViewOngoingService.setBounds(10, 544, 219, 39);
contentPane.add(btnViewOngoingService);
JLabel lblCarServiceDetails = new JLabel("CAR SERVICE DETAILS");
lblCarServiceDetails.setForeground(new Color(255, 255, 255));
lblCarServiceDetails.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
lblCarServiceDetails.setBounds(261, 16, 219, 31);
contentPane.add(lblCarServiceDetails);

JLabel lblNewLabel1 = new JLabel("New label");
lblNewLabel1.setIcon(new ImageIcon(ViewBooking.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
lblNewLabel1.setBounds(0, 0, 743, 702);
contentPane.add(lblNewLabel1);
    }
}