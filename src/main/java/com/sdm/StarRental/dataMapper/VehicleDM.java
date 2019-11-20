package com.sdm.StarRental.dataMapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.tableDataGateway.VehicleTDG;


@Service
public class VehicleDM {
	
	VehicleTDG vehicleTDG;
	
	
	
public VehicleDM() {
		
		this.vehicleTDG = new VehicleTDG();
	}
	
	
	 public boolean addNewVehicle(String type,int year, String model,String color,String vehicleLicensePlate, String status, String make) throws Exception{ 
		 vehicleTDG.getInstance().establishConntection();
		 
		 boolean result = this.vehicleTDG.addNewVehicle(type, year, model, color, vehicleLicensePlate, status, make);
		 vehicleTDG.getInstance().closeConnection();
		 return result;
	 }

	    public boolean deleteVehicle(String vehicleLicensePlate) throws Exception{
	    	vehicleTDG.establishConntection();
	    	boolean result = vehicleTDG.deleteVehicle(vehicleLicensePlate);
	    	vehicleTDG.getInstance().closeConnection();
	    	return result;
	    }


	    public boolean modifyVehicle(String type,String make, String model,int year, String color,String vehilceLicensePlate, String status) throws Exception{

	    	 vehicleTDG.getInstance().establishConntection();
	    	 boolean result = vehicleTDG.modifyVehicle(type, year, model, make, color, vehilceLicensePlate, status);
	    	 vehicleTDG.getInstance().closeConnection();
	    	return result;

	    }

	    public ArrayList<Vehicle> getAllVehicles() throws Exception{ 
	    	vehicleTDG.getInstance().establishConntection();
	    	ArrayList<Vehicle> result = vehicleTDG.getAllVehicles();
	    	 vehicleTDG.getInstance().closeConnection();
	    	return result;
	    }

	    public ArrayList<Vehicle> getVehicleFromOneCriteria(String value, String comparator, String criteria) throws Exception{
	    	vehicleTDG.getInstance().establishConntection();
	    	ArrayList<Vehicle> result = vehicleTDG.getVehicleFromOneCriteria(value, comparator, criteria);
	    	 vehicleTDG.getInstance().closeConnection();
	    	return result;
	    }

	    public  ArrayList<Vehicle> getVehicleFromTwoCriteria(String value1,String value2, String comparator, String criteria1, String criteria2) throws Exception{
	    	vehicleTDG.getInstance().establishConntection();
	    	ArrayList<Vehicle> result =  vehicleTDG.getVehicleFromTwoCriteria(value1, value2, comparator, criteria1, criteria2);
	    	 vehicleTDG.getInstance().closeConnection();
	    	return result;
	    }

	    public  ArrayList<Vehicle> getVehicleFromAllCriteria(String value1,String value2,String value3,String value4, String value5, String value6, String value7, String comparator, String criteria1, String criteria2,String criteria3,String criteria4, String criteria5, String criteria6, String criteria7) throws Exception{
	    	vehicleTDG.getInstance().establishConntection();
	    	ArrayList<Vehicle> result  = vehicleTDG.getVehicleFromAllCriteria(value1, value2, value3, value4, value5, value6, value7, comparator, criteria1, criteria2, criteria3, criteria4, criteria5, criteria6, criteria7);
	    	 vehicleTDG.getInstance().closeConnection();
	    	return result;
	    }

	    public  ArrayList<Vehicle> getVehicleFromThreeCriteria(String value1,String value2,String value3, String comparator, String criteria1, String criteria2,String criteria3) throws Exception{
	    	vehicleTDG.getInstance().establishConntection();
	    	ArrayList<Vehicle> result  = vehicleTDG.getVehicleFromThreeCriteria(value1, value2, value3, comparator, criteria1, criteria2, criteria3);
	    	 vehicleTDG.getInstance().closeConnection();
	    	return result;
	    }
	    
	    public Vehicle getVehicleByLicenseNo(String vehicleLicensePlate) throws Exception{ 
	    	vehicleTDG.getInstance().establishConntection();
	    	Vehicle result = vehicleTDG.getVehicleByLicensePlate(vehicleLicensePlate);
	    	 vehicleTDG.getInstance().closeConnection();
	    	return result;
	    }
}

