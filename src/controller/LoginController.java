package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.MyDatabase;
import model.Customer;
import view.StaffHomepageGui;
import view.StaffLoginGui;

public class LoginController {
	Connection conn = null;

	public void staffLogin(String staffID, String staffPassword)
	{
		try {
			conn = MyDatabase.doConnection();
			String sql = "SELECT StaffID FROM staff WHERE StaffID=? AND StaffPass=? AND AccountStatus='ACTIVE'";				  
		    PreparedStatement preparedStatement = conn.prepareStatement(sql);
			    
		    preparedStatement.setString(1, staffID);
		    preparedStatement.setString(2, staffPassword);
    
		    // Execute your query and process the result set if needed
		    ResultSet resultSet = preparedStatement.executeQuery();
			int count = 0 ;
			while(resultSet.next()){
				count = count + 1;
				}
			if(count == 1)
			{
				// when login button is press. it will go to staff Home page
				StaffHomepageGui frame = new StaffHomepageGui();
				frame.setVisible(true);
//				StaffLoginGui staffLoginGui = new StaffLoginGui();
//				staffLoginGui.dispose();
			}else if(count > 1)								{
				JOptionPane.showMessageDialog(null,"Duplicate StaffId and password ");
			}else
			{
				JOptionPane.showMessageDialog(null,"StaffId or password is incorrect");
			}
			   
				resultSet.close();
				preparedStatement.close();

	} catch (SQLException u) {
	    JOptionPane.showMessageDialog(null, u);
	} catch (Exception u) {
	    JOptionPane.showMessageDialog(null, u);
	}	
	}
	
}