package view;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controller.StaffController;
import database.MyDatabase;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CarServiceCancel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    JComboBox<String> combo;
    JComboBox<String> combo1;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	CarServiceCancel frame = new CarServiceCancel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     Connection conn=null;
     
     public void refreshTable() throws ClassNotFoundException, SQLException {
 	    String[] header = { "ServiceID", "CustID", "CarModel", "CarName", "Description","AppointmentDate",
 	           "Status"  };
 	    DefaultTableModel dm = new DefaultTableModel() {
 	        @Override
 	        public Class<?> getColumnClass(int column) { 
 	            return (column == 6 ) ? String.class : Object.class;
 	        }
 	    };
 	    
        String sqlQuery = "SELECT ServiceID, CustID, CarModel, CarName,  Description,DateOfAppointment,  Status FROM car_service Where Status = 'CANCELLED'";

 	    try (Connection conn = MyDatabase.doConnection();
 	         PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
 	         ResultSet resultSet = preparedStatement.executeQuery()) {

 	        List<Object[]> resultList = new ArrayList<>();

 	       while (resultSet.next()) { 
               String serviceID = resultSet.getString("ServiceID");
               String custID = resultSet.getString("CustID");
               String carModel = resultSet.getString("CarModel");
               String carName = resultSet.getString("CarName");
               String description = resultSet.getString("Description");
               String dateOfAppointment = resultSet.getString("DateOfAppointment");
               //String staffID = resultSet.getString("StaffID");
               String status = resultSet.getString("Status");

    
               
               if (dateOfAppointment == null) {
   	            dateOfAppointment = "NOT SET";
   	        }
   	        
   	        //if (staffID == null) {
   	          //  staffID = "NONE";
   	      //  }

   	        Object[] rowData = { serviceID, custID, carModel, carName, 
                       description,dateOfAppointment, status, true };


 	            resultList.add(rowData);
 	        }

 	        Object[][] data = resultList.toArray(new Object[0][]);
 	        dm.setDataVector(data, header);

 	        // Get the existing JTable and update its model
 	        JTable table = ((JTable)((JScrollPane) getContentPane().getComponent(0)).getViewport().getView());
 	        table.setModel(dm);
 	        
 	    } catch (Exception ex) {
 	        ex.printStackTrace();
 	    }
 	}
   
    public CarServiceCancel() throws ClassNotFoundException, SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 744, 501);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        getContentPane().setLayout(null);

        DefaultTableModel dm = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 7) ? Boolean.class : String.class;
            }
        };
       
        String[] header = { "ServiceID", "CustID", "CarModel", "CarName", "Description","AppointmentDate",
  	           "Status"  };

        String sqlQuery = "SELECT ServiceID, CustID, CarModel, CarName,  Description,DateOfAppointment,  Status FROM car_service Where Status = 'CANCELLED'";

        try (
        		Connection conn = MyDatabase.doConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()){
        

            List<Object[]> resultList = new ArrayList<>();

            while (resultSet.next()) { 
                String serviceID = resultSet.getString("ServiceID");
                String custID = resultSet.getString("CustID");
                String carModel = resultSet.getString("CarModel");
                String carName = resultSet.getString("CarName");
                String description = resultSet.getString("Description");
                String dateOfAppointment = resultSet.getString("DateOfAppointment");
                //String staffID = resultSet.getString("StaffID");
                String status = resultSet.getString("Status");

     
                
                if (dateOfAppointment == null) {
    	            dateOfAppointment = "NOT SET";
    	        }
    	        
    	        //if (staffID == null) {
    	          //  staffID = "NONE";
    	      //  }

    	        Object[] rowData = { serviceID, custID, carModel, carName, 
                        description,dateOfAppointment, status, true };

                resultList.add(rowData);
            }

            Object[][] data = resultList.toArray(new Object[0][]);
            dm.setDataVector(data, header);

            JTable table = new JTable(dm);
            combo = new JComboBox<>();
          
            combo.addItem("PENDING");
            combo.addItem("APPROVED");
            combo.addItem("ON GOING");
            combo.addItem("COMPLETED");
            combo.addItem("CANCELLED");
            
            TableColumn col = table.getColumnModel().getColumn(6);
            col.setCellEditor(new DefaultCellEditor(combo));
           

            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(17, 69, 695, 311);
            pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            getContentPane().add(pane);
            
            JLabel lblNewLabel = new JLabel("Cancelled Repair And Service Jobs");
            lblNewLabel.setForeground(new Color(255, 255, 255));
            lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setBounds(197, 31, 336, 28);
            contentPane.add(lblNewLabel);
            
            JButton btnUpdate = new JButton("Update\r\n");
            btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
            btnUpdate.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		DefaultTableModel model = (DefaultTableModel) table.getModel();
            		StaffController staffController = new StaffController();
            		staffController.updateTableCancel(model);
            		try {
						refreshTable();
				
					} catch (ClassNotFoundException | SQLException e1) {

						e1.printStackTrace();
					}

            	}
            });
            btnUpdate.setBounds(381, 391, 103, 37);
            contentPane.add(btnUpdate);
            
            JButton btnBack = new JButton("Back\r\n");
            btnBack.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            			StaffHomepageGui frame = new StaffHomepageGui();
						frame.setVisible(true);
						dispose();
            	}
            });
            btnBack.setFont(new Font("Times New Roman", Font.BOLD, 15));
            btnBack.setBounds(241, 391, 103, 37);
            contentPane.add(btnBack);
            setVisible(true);

    		JLabel lblNewLabel1 = new JLabel("New label");
    		lblNewLabel1.setIcon(new ImageIcon(CarServiceCancel.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
    		lblNewLabel1.setBounds(0, 0, 741, 548);
    		contentPane.add(lblNewLabel1);
     
        }
    }
  
}
