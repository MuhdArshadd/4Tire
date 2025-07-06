package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import database.MyDatabase;
import model.Customer;
import model.Payment;

public class PaymentController {

	public boolean DoPayment(Payment payment, Customer customer, int ID) throws ClassNotFoundException, SQLException
	{
		Connection conn = MyDatabase.doConnection();
		
		PreparedStatement insertInvoice = conn.prepareStatement("INSERT INTO invoice (NormalAmount, TotalAmount, Discount, SlotID) VALUES (?, ?, ?, ?)");
		insertInvoice.setDouble(1,actual(ID));
		insertInvoice.setDouble(2, totalAmount(payment, customer, ID));
		insertInvoice.setDouble(3, discount(payment, customer));
		insertInvoice.setInt(4, ID);
		int rowsAffected = insertInvoice.executeUpdate();
        if (rowsAffected > 0) {
        	
            //conn.close();
            PreparedStatement statement = conn.prepareStatement("UPDATE car_service SET Status='CONFIRMED' WHERE SlotID=? AND (Status='APPROVED' OR Status='PENDING')");
    	    statement.setInt(1, ID); // Assuming getSlotID() returns the SlotID

    	    int rowsUpdated = statement.executeUpdate();

    	    // Check the number of rows updated to see if the update was successful
    	    if (rowsUpdated > 0) {
    	    	 statement.close();
    	    	    conn.close();
    	    	return true;
    	    } else {
    	    	 statement.close();
    	    	    conn.close();
    	        return false;
    	    }
        }
        else
        	return false;
	}
	
	
	public double discount(Payment payment, Customer customer){
		
		int point = payment.getUsedLoyalty();
		double discount = point /10;
		
		return discount;
		
	}
	
	public double actual(int ID) throws ClassNotFoundException, SQLException{

		Connection conn = MyDatabase.doConnection();
		
		PreparedStatement checkAmount = conn.prepareStatement("SELECT ServiceID FROM car_service WHERE SlotID = ?");
		checkAmount.setInt(1, ID);
		ResultSet resultSet = checkAmount.executeQuery();
		if(resultSet.next())
		{
			int serviceID = resultSet.getInt("ServiceID");
			PreparedStatement actualAmount = conn.prepareStatement("SELECT * FROM service WHERE ServiceID = ?");
			actualAmount.setInt(1, serviceID);
			ResultSet resultSet2 = actualAmount.executeQuery();
			if(resultSet2.next())
			{
				double cost = resultSet2.getDouble("Cost");
				return cost;
			}
		}
		return 0;
		
	}
	
	public double total(double ori , double dc)
	{
		
		double total = ori - dc;
		return total;
		
	}
	
	public double totalAmount(Payment payment, Customer customer, int ID) throws ClassNotFoundException, SQLException
	{
		double orig = actual(ID);
		double dic = discount(payment, customer);
		
		return orig - dic;
	}
	
	public void UpdatePoint(Payment payment, Customer customer) throws ClassNotFoundException, SQLException
	{

		Connection conn = MyDatabase.doConnection();
		
		PreparedStatement updatePoint = conn.prepareStatement("UPDATE customer SET LoyaltyPointEarned = LoyaltyPointEarned - ? WHERE CustID = ?");
		updatePoint.setInt(1, payment.getUsedLoyalty());
		updatePoint.setInt(2, customer.getCustID());
		int rowsAffected = updatePoint.executeUpdate();
        if (rowsAffected > 0) {
        	PreparedStatement newPoint = conn.prepareStatement("SELECT * FROM customer WHERE CustID = ?");
			newPoint.setInt(1, customer.getCustID());
			ResultSet resultSet2 = newPoint.executeQuery();
			if(resultSet2.next())
			{
				int np = resultSet2.getInt("LoyaltyPointEarned");
				customer.setLoyaltypoint(np);
			}
        }
	}
	
	public void PlusPoint(Payment payment , Customer customer, int ID) throws ClassNotFoundException, SQLException
	{
		Connection conn = MyDatabase.doConnection();
		PreparedStatement checkService = conn.prepareStatement("SELECT * FROM car_service WHERE SlotID = ?");
		checkService.setInt(1, ID);
		ResultSet resultSet = checkService.executeQuery();
		if(resultSet.next())
		{
			int serviceID = resultSet.getInt("ServiceID");
			PreparedStatement checkPoint = conn.prepareStatement("SELECT * FROM service WHERE ServiceID = ?");
			checkPoint.setInt(1, serviceID);
			ResultSet resultSet2 = checkPoint.executeQuery();
			if(resultSet2.next())
			{
				int point = resultSet2.getInt("LoyaltyPoint");
				PreparedStatement plusPoint = conn.prepareStatement("UPDATE customer SET LoyaltyPointEarned = LoyaltyPointEarned + ? WHERE CustID = ?");
				plusPoint.setInt(1, point);
				plusPoint.setInt(2, customer.getCustID());
				int rowsAffected = plusPoint.executeUpdate();
		        if (rowsAffected > 0) {
		        	PreparedStatement newPoint = conn.prepareStatement("SELECT * FROM customer WHERE CustID = ?");
					newPoint.setInt(1, customer.getCustID());
					ResultSet resultSet3 = newPoint.executeQuery();
					if(resultSet3.next())
					{
						int np = resultSet3.getInt("LoyaltyPointEarned");
						customer.setLoyaltypoint(np);
					}
		        }
				
			}
			
		}
	}
	
	public String getServiceName ( int ID ) throws ClassNotFoundException, SQLException 
	{
		String name = null;
		Connection conn = MyDatabase.doConnection();
		PreparedStatement name1 = conn.prepareStatement("SELECT service.ServiceType FROM service JOIN car_service ON car_service.ServiceID = service.ServiceID WHERE car_service.SlotID = ?");		
		name1.setInt(1, ID);
		ResultSet resultSet = name1.executeQuery();
		
		if(resultSet.next())
		{
			name = resultSet.getString("ServiceType");
		}
		conn.close();
		return name;
	}
}
