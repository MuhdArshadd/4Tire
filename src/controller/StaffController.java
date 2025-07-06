package controller;

import view.CarServiceCancel;
import view.CarServiceCompleted;
import view.StaffHomepageGui;
import view.StaffLoginGui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.MyDatabase;

public class StaffController {
	Connection conn = null;
	
	public void updateTablePending(DefaultTableModel model) {
	     int rowCount = model.getRowCount();

		   // Iterate through each row
	        for (int i = 0; i < rowCount; i++) {
	            // Check if the status column (index 7) has been modified
	            String newStatus = (String) model.getValueAt(i, 6);
	 

	            if (!newStatus.equals("PENDING")) {  // Only update if status is not "Pending"
	                // If the status is different from "Pending", update the corresponding row in the database
	                String serviceID = (String) model.getValueAt(i, 0);
	                
	                String sql = "UPDATE car_service SET Status = ?, DateOfAppointment = CURRENT_DATE WHERE ServiceID = ?";
	      
	
	                
	                try {
	                    updateStatusInDatabase(serviceID, newStatus,sql);
	                } catch (ClassNotFoundException e1) {
	                    e1.printStackTrace();
	                }
	            } 
	       
	        }
         // Refresh the table after updating the database
         //refreshTable();

	}
	
	public void updateTableOngoing(DefaultTableModel model)
	{
		 int rowCount = model.getRowCount();

		   // Iterate through each row
	        for (int i = 0; i < rowCount; i++) {
	            // Check if the status column (index 7) has been modified
	            String newStatus = (String) model.getValueAt(i, 6);
	 

	            if (!newStatus.equals("ON GOING")) {  // Only update if status is not "Pending"
	                // If the status is different from "Pending", update the corresponding row in the database
	                String serviceID = (String) model.getValueAt(i, 0);
	                
	                
	                String sql = "UPDATE car_service SET Status = ?, DateOfAppointment = CURRENT_DATE WHERE ServiceID = ?";
	
	                
	                try {
	                    updateStatusInDatabase(serviceID, newStatus,sql);
	                } catch (ClassNotFoundException e1) {
	                    e1.printStackTrace();
	                }
	            } 
	       
	        }
         // Refresh the table after updating the database
         //refreshTable();

	}
	public void updateTableComplete(DefaultTableModel model)
	{
		 int rowCount = model.getRowCount();

		   // Iterate through each row
	        for (int i = 0; i < rowCount; i++) {
	            // Check if the status column (index 7) has been modified
	            String newStatus = (String) model.getValueAt(i, 6);
	 

	            if (!newStatus.equals("COMPLETED")) {  // Only update if status is not "Pending"
	                // If the status is different from "Pending", update the corresponding row in the database
	                String serviceID = (String) model.getValueAt(i, 0);
	                
	                String sql = "UPDATE car_service SET Status = ? WHERE ServiceID = ?";
	      
	
	                
	                try {
	                    updateStatusInDatabase(serviceID, newStatus,sql);
	              
	                } catch (ClassNotFoundException e1) {
	                    e1.printStackTrace();
	                }
	            } 
	       
	        }
         // Refresh the table after updating the database
         //refreshTable();
	}
	
	public void updateTableCancel(DefaultTableModel model)
	{
		
        int rowCount = model.getRowCount();

		   // Iterate through each row
	        for (int i = 0; i < rowCount; i++) {
	            // Check if the status column (index 7) has been modified
	            String newStatus = (String) model.getValueAt(i, 6);
	 

	            if (!newStatus.equals("CANCELLED")) {  // Only update if status is not "cancelled"
	                // If the status is different from "cancelled", update the corresponding row in the database
	                String serviceID = (String) model.getValueAt(i, 0);
	                String sql = "UPDATE car_service SET Status = ? WHERE ServiceID = ?";
	      
	                try {
	                    updateStatusInDatabase(serviceID, newStatus,sql);
	               
	                } catch (ClassNotFoundException e1) {
	                    e1.printStackTrace();
	                }
	            } 
	       
	        }
           // Refresh the table after updating the database
           //refreshTable();
	       
	}
	
	  private void updateStatusInDatabase(String serviceID, String newStatus,String sql ) throws ClassNotFoundException {
		  String updateQuery = sql;



	        try (Connection conn = MyDatabase.doConnection();
	             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {

	            preparedStatement.setString(1, newStatus);
	            preparedStatement.setString(2, serviceID);
	 

	            int rowsUpdated = preparedStatement.executeUpdate();

	            if (rowsUpdated > 0) {
	               JOptionPane.showMessageDialog(null,"Status updated successfully for ServiceID: " + serviceID);
	               
	            } else {
	                JOptionPane.showMessageDialog(null,"Failed to update status for ServiceID: " + serviceID);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  
	

}



