package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.MyDatabase;
import model.Booking;
import model.Customer;

public class BookingController {

	public String ListService(int i) throws ClassNotFoundException, SQLException
	{
		Connection conn = MyDatabase.doConnection();
		
		PreparedStatement serviceCategory = conn.prepareStatement("SELECT * FROM service WHERE ServiceID = ?");
		serviceCategory.setInt(1, i);
		ResultSet resultSet = serviceCategory.executeQuery();
		
		 if (resultSet.next()) {
			 int serviceID = resultSet.getInt("ServiceID");
			 String serviceType = resultSet.getString("ServiceType");
			 String desc = resultSet.getString("Description");
			 double cost = resultSet.getDouble("Cost");
			 int loyaltyPoint = resultSet.getInt("LoyaltyPoint");
			 
			 return serviceType;
		 }
		 else
			 return null;
	}
	
	public boolean insertBooking(Booking booking, Customer customer) throws ClassNotFoundException, SQLException {
		boolean success = false;
		Connection conn = MyDatabase.doConnection();
		
		 PreparedStatement findCategory = conn.prepareStatement("SELECT ServiceID FROM service WHERE ServiceType = ?");
		 findCategory.setString(1, booking.getServiceType());
		 ResultSet resultSet = findCategory.executeQuery();
		 if(resultSet.next())
		 {
			 int serviceID = resultSet.getInt("ServiceID");
			 PreparedStatement insertBooking = conn.prepareStatement("INSERT INTO car_service (ServiceID, CustID, CarName, CarModel, CarType, Description) VALUES (?, ?, ?, ?, ?, ?)");
			 insertBooking.setInt(1,serviceID);
			 insertBooking.setInt(2, customer.getCustID());
			 insertBooking.setString(3, booking.getCarName());
			 insertBooking.setString(4, booking.getCarModel());
			 insertBooking.setString(5, booking.getCarType());
			 insertBooking.setString(6, booking.getDescription());
			 int rowsAffected = insertBooking.executeUpdate();
             if (rowsAffected > 0) {
             	
             	success = true;
                 conn.close();
      
             }
		 }
		return success;
	}
	
public List<Booking> carServicePending(Customer customer) throws SQLException, ClassNotFoundException {
    	
        List<Booking> carServiceList = new ArrayList<>();
    	Connection conn = MyDatabase.doConnection();

		PreparedStatement list = conn.prepareStatement("SELECT car_service.SlotID, service.ServiceType, car_service.CarName, car_service.CarModel, car_service.CarType, car_service.Status FROM car_service JOIN service ON car_service.ServiceID = service.ServiceID WHERE car_service.Status = 'PENDING' AND car_service.CustID = ?");
		list.setInt(1, customer.getCustID());
        ResultSet resultSet = list.executeQuery();

        while (resultSet.next()) {
        	int slotID = resultSet.getInt("SlotID");
            String serviceType = resultSet.getString("ServiceType");
            String carName = resultSet.getString("CarName");
            String carModel = resultSet.getString("CarModel");
            String carType = resultSet.getString("CarType");
            String status = resultSet.getString("Status");
            
            Booking carService = new Booking();
            carService.setSlotID(slotID);
            carService.setServiceType(serviceType);
            carService.setCarName(carName);
            carService.setCarModel(carModel);
            carService.setCarType(carType);
            carService.setStatus(status);
            
            carServiceList.add(carService);
        }
        conn.close();

        // Return the ArrayList of CarService objects
        return carServiceList;
    }
public List<Booking> carServiceApproved(Customer customer) throws SQLException, ClassNotFoundException {
    	
        List<Booking> carServiceList = new ArrayList<>();
    	Connection conn = MyDatabase.doConnection();

		PreparedStatement list = conn.prepareStatement("SELECT car_service.SlotID, service.ServiceType, car_service.CarName, car_service.CarModel, car_service.CarType, car_service.DateOfAppointment, car_service.Status FROM car_service JOIN service ON car_service.ServiceID = service.ServiceID WHERE car_service.Status = 'APPROVED' AND car_service.CustID = ?");
		list.setInt(1, customer.getCustID());
        ResultSet resultSet = list.executeQuery();

        while (resultSet.next()) {
        	int slotID = resultSet.getInt("SlotID");
            String serviceType = resultSet.getString("ServiceType");
            String carName = resultSet.getString("CarName");
            String carModel = resultSet.getString("CarModel");
            String carType = resultSet.getString("CarType");
            String DOAP = resultSet.getString("DateOfAppointment");
            String status = resultSet.getString("Status");
            
            Booking carService = new Booking();
            carService.setSlotID(slotID);
            carService.setServiceType(serviceType);
            carService.setCarName(carName);
            carService.setCarModel(carModel);
            carService.setCarType(carType);
            carService.setDateOfAppointment(DOAP);
            carService.setStatus(status);
            
            carServiceList.add(carService);
        }
        conn.close();

        // Return the ArrayList of CarService objects
        return carServiceList;
    }
public List<Booking> carServiceOnGoing(Customer customer) throws SQLException, ClassNotFoundException {
	
    List<Booking> carServiceList = new ArrayList<>();
	Connection conn = MyDatabase.doConnection();

	PreparedStatement list = conn.prepareStatement("SELECT car_service.SlotID, service.ServiceType, car_service.CarName, car_service.CarModel, car_service.CarType, car_service.DateOfAppointment, car_service.Status FROM car_service JOIN service ON car_service.ServiceID = service.ServiceID WHERE car_service.Status = 'ON GOING' OR car_service.Status = 'CONFIRMED' AND car_service.CustID = ?");
	list.setInt(1, customer.getCustID());
    ResultSet resultSet = list.executeQuery();

    while (resultSet.next()) {
    	int slotID = resultSet.getInt("SlotID");
        String serviceType = resultSet.getString("ServiceType");
        String carName = resultSet.getString("CarName");
        String carModel = resultSet.getString("CarModel");
        String carType = resultSet.getString("CarType");
        String DOAP = resultSet.getString("DateOfAppointment");
        String status = resultSet.getString("Status");
        
        Booking carService = new Booking();
        carService.setSlotID(slotID);
        carService.setServiceType(serviceType);
        carService.setCarName(carName);
        carService.setCarModel(carModel);
        carService.setCarType(carType);
        carService.setDateOfAppointment(DOAP);
        carService.setStatus(status);
        
        carServiceList.add(carService);
    }
    conn.close();

    // Return the ArrayList of CarService objects
    return carServiceList;
}
public List<Booking> carServiceCompleted(Customer customer) throws SQLException, ClassNotFoundException {
	
    List<Booking> carServiceList = new ArrayList<>();
	Connection conn = MyDatabase.doConnection();

	PreparedStatement list = conn.prepareStatement("SELECT car_service.SlotID, service.ServiceType, car_service.CarName, car_service.CarModel, car_service.CarType, car_service.DateOfAppointment, car_service.Status,car_service.Description FROM car_service JOIN service ON car_service.ServiceID = service.ServiceID WHERE car_service.Status = 'COMPLETED' AND car_service.CustID = ?");
	list.setInt(1, customer.getCustID());
    ResultSet resultSet = list.executeQuery();

    while (resultSet.next()) {
    	int slotID = resultSet.getInt("SlotID");
        String serviceType = resultSet.getString("ServiceType");
        String carName = resultSet.getString("CarName");
        String carModel = resultSet.getString("CarModel");
        String carType = resultSet.getString("CarType");
        String DOAP = resultSet.getString("DateOfAppointment");
        String status = resultSet.getString("Status");
        String description = resultSet.getString("Description");

        Booking carService = new Booking();
        carService.setSlotID(slotID);
        carService.setServiceType(serviceType);
        carService.setCarName(carName);
        carService.setCarModel(carModel);
        carService.setCarType(carType);
        carService.setDateOfAppointment(DOAP);
        carService.setStatus(status);
        carService.setDescription(description);

        
        carServiceList.add(carService);
    }
    conn.close();

    // Return the ArrayList of CarService objects
    return carServiceList;
}

public boolean bookingCancel(Booking booking) throws SQLException, ClassNotFoundException {
    Connection conn = MyDatabase.doConnection();

    PreparedStatement statement = conn.prepareStatement("UPDATE car_service SET Status = 'CANCELLED' WHERE SlotID=? AND (Status='APPROVED' OR Status='PENDING')" );

    statement.setInt(1, booking.getSlotID()); // Assuming getSlotID() returns the SlotID

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
}
