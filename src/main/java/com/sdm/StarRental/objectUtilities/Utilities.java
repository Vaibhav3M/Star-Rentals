package com.sdm.StarRental.objectUtilities;


import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.User;
import com.sdm.StarRental.model.Vehicle;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilities {


	public static boolean validateSession(HttpSession httpSession) {
		if (httpSession.getAttribute("userLoggedIn") != null && (Integer) httpSession.getAttribute("userLoggedIn") == 1) {
			return true;
		}
		return false;
	}
	
	
	public static User getUserObject(ResultSet userDetails) throws SQLException {
		User user = new User();
		user.setUserName(userDetails.getString("userName"));
		user.setPassword(userDetails.getString("password"));
		user.setUserType(userDetails.getString("userType"));
		return user;
	}
	
	public static Vehicle getVehicleObject(ResultSet vehicleDetails) throws SQLException {
		Vehicle vehicle = new Vehicle();
		// Type,Make,Model,Year,Color,License_Plate,Status
		vehicle.setType(vehicleDetails.getString("type"));
		vehicle.setMake(vehicleDetails.getString("make"));
		vehicle.setModel(vehicleDetails.getString("model"));
		vehicle.setYear(Integer.valueOf(vehicleDetails.getString("year")));
		vehicle.setColor(vehicleDetails.getString("color"));
		vehicle.setvehicleLicensePlate(vehicleDetails.getString("vehicleLicensePlate"));




		vehicle.setStatus(vehicleDetails.getString("status"));
		return vehicle;
	}

	public static Client getClientObject(ResultSet clientResultSet) throws SQLException{

		Client client = new Client();


		client.setFirstName(clientResultSet.getString("firstName"));
		client.setLastName(clientResultSet.getString("lastName"));
		client.setLicenseNumber(clientResultSet.getString("licenseNumber"));
		client.setLicenseExpiryDate(clientResultSet.getString("licenseExpiryDate"));
		client.setPhoneNumber(clientResultSet.getString("phoneNumber"));


		return client;

	}

	public static Transaction getTransactionObject(ResultSet transactionResultSet) throws SQLException{

		Transaction transaction = new Transaction();


		transaction.setTransactionID(transactionResultSet.getInt("transactionID"));
		transaction.setTransactionType(transactionResultSet.getString("transactionType"));
		transaction.setVehicleLicensePlate(transactionResultSet.getString("vehicleLicensePlate"));
		transaction.setClientLicenseNumber(transactionResultSet.getString("clientLicenseNumber"));
		transaction.setStatus(transactionResultSet.getString("status"));
		transaction.setTimeStamp(transactionResultSet.getString("timeStamp"));
		transaction.setBookingFrom(transactionResultSet.getString("bookingFrom"));
		transaction.setBookingTill(transactionResultSet.getString("bookingTill"));
		transaction.setTransactionBy(transactionResultSet.getString("transactionBy"));



		return transaction;






	}

}
