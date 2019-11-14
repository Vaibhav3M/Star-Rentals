package com.sdm.StarRental.objectUtilities;

import com.sdm.StarRental.model.User;
import com.sdm.StarRental.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilities {

	public Utilities() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public static User getUserObject(ResultSet userDetails) throws SQLException {
		User user = new User();
		user.setUserName(userDetails.getString(1));
		user.setPassword(userDetails.getString(2));
		user.setUserType(userDetails.getString(3));
		return user;
	}
	
	public static Vehicle getVehicleObject(ResultSet vehicleDetails) throws SQLException {
		Vehicle vehicle = new Vehicle();
		// Type,Make,Model,Year,Color,License_Plate,Status
		vehicle.setType(vehicleDetails.getString(1));
		vehicle.setMake(vehicleDetails.getString(2));
		vehicle.setModel(vehicleDetails.getString(3));
		vehicle.setYear(Integer.valueOf(vehicleDetails.getString(4)));
		vehicle.setColor(vehicleDetails.getString(5));
		vehicle.setVehicleLicencePlate(vehicleDetails.getString(6));
		vehicle.setStatus(vehicleDetails.getString(7));
		return vehicle;
	}

}
