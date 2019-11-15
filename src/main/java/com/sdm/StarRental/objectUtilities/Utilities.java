package com.sdm.StarRental.objectUtilities;

import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.model.Transaction;
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

	public static Client getClientObject(ResultSet clientResultSet) throws SQLException{

		Client client = new Client();

		if(clientResultSet.next()){
			clientResultSet.getString(1);
		}

		client.setFirstName(clientResultSet.getString("firstName"));
		client.setLastName(clientResultSet.getString("lastName"));
		client.setLicenseNumber(clientResultSet.getString("licenseNumber"));
		client.setLicenseExpiryDate(clientResultSet.getString("licenseExpiryDate"));
		client.setPhoneNumber(clientResultSet.getString("phoneNumber"));


		return client;

	}

	public static Transaction getTransactionObject(ResultSet transactionResultSet) throws SQLException{

		Transaction transaction = new Transaction();

		if(transactionResultSet.next()){
			transactionResultSet.getString(1);
		}

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
