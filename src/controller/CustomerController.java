package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.MyDatabase;
import model.Customer;

public class CustomerController {

	//CUSTOMER LOG IN
	public boolean loginCustomer(Customer customer) throws ClassNotFoundException, SQLException {
    	
		boolean success;
    	Connection conn = MyDatabase.doConnection();
		
		PreparedStatement loginCust = conn.prepareStatement("SELECT * FROM customer WHERE EmailAddress = ?");
		loginCust.setString(1, customer.getEmailAddress());
        ResultSet resultSet = loginCust.executeQuery();

        if (resultSet.next()) {
        	int custID = resultSet.getInt("CustID");
            String custPass = resultSet.getString("CustPass");
            String custFName = resultSet.getString("FirstName");
            String custLName = resultSet.getString("LastName");
            String custContNum = resultSet.getString("ContactNo");
            String custEmAd = resultSet.getString("EmailAddress");
            int custLoyalPoint = resultSet.getInt("LoyaltyPointEarned");
            
            customer.setCustID(custID);
            customer.setCustPass(custPass);
            customer.setFirstName(custFName);
            customer.setLastName(custLName);
            customer.setContactNo(custContNum);
            customer.setEmailAddress(custEmAd);
            customer.setLoyaltypoint(custLoyalPoint);
            
            success = true;
            conn.close();            
        } else {
        	success = false;
        	conn.close();
        }
        
        return success;
        
    }
	//CUSTOMER REGISTRATION
    public boolean insertCustomer(Customer customer) throws ClassNotFoundException, SQLException {
    	
    		boolean success = false;
        	Connection conn = MyDatabase.doConnection();
			
			PreparedStatement checkIfExists = conn.prepareStatement("SELECT EmailAddress FROM customer WHERE EmailAddress = ?");
			checkIfExists.setString(1, customer.getEmailAddress());
            ResultSet resultSet = checkIfExists.executeQuery();

            if (resultSet.next()) {
            	
            	success = false;
                conn.close();
                
            } else {
                PreparedStatement insertCustomer = conn.prepareStatement("INSERT INTO customer (CustPass, FirstName, LastName, ContactNo, EmailAddress) VALUES (?, ?, ?, ?, ?)");
                insertCustomer.setString(1, customer.getCustPass());
                insertCustomer.setString(2, customer.getFirstName());
                insertCustomer.setString(3, customer.getLastName());
                insertCustomer.setString(4, customer.getContactNo());
                insertCustomer.setString(5, customer.getEmailAddress());

                int rowsAffected = insertCustomer.executeUpdate();
                if (rowsAffected > 0) {
                	
                	success = true;
                    conn.close();
         
                }
            }
            return success;
        }

    //MAIN
    public static void main(String[] args) {
    }
}
