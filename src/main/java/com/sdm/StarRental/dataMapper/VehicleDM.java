package com.sdm.StarRental.dataMapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.tableDataGateway.VehicleTDG;


@Service
public class VehicleDM {
	@Autowired
	VehicleTDG vehicleTDG;
	
	
	
	 public boolean addNewVehicle(String type,int year, String model,String color,String vehicleLicensePlate, String status, String make) throws Exception{ 
		return this.vehicleTDG.addNewVehicle(type, year, model, color, vehicleLicensePlate, status, make);
	 }

	    public boolean deleteVehicle(String vehicleLicensePlate) throws Exception{
	    	return vehicleTDG.deleteVehicle(vehicleLicensePlate);
	    }

	    public boolean modifyVehicle(String type,int year, String model,String make, String color,String vehilceLicensePlate, String status,String image) throws Exception{
	    	return vehicleTDG.modifyVehicle(type, year, model, make, color, vehilceLicensePlate, status, image);
	    }

	    public ArrayList<Vehicle> getAllVehicles() throws Exception{ 
	    	return vehicleTDG.getAllVehicles();
	    }

	    public ArrayList<Vehicle> getVehicleFromOneCriteria(String value, String comparator, String criteria) throws Exception{
	    	return vehicleTDG.getVehicleFromOneCriteria(value, comparator, criteria);
	    }

	    public  ArrayList<Vehicle> getVehicleFromTwoCriteria(String value1,String value2, String comparator, String criteria1, String criteria2) throws Exception{
	    	return vehicleTDG.getVehicleFromTwoCriteria(value1, value2, comparator, criteria1, criteria2);
	    }

	    public  ArrayList<Vehicle> getVehicleFromAllCriteria(String value1,String value2,String value3,String value4, String value5, String value6, String value7, String comparator, String criteria1, String criteria2,String criteria3,String criteria4, String criteria5, String criteria6, String criteria7) throws Exception{
	    	return vehicleTDG.getVehicleFromAllCriteria(value1, value2, value3, value4, value5, value6, value7, comparator, criteria1, criteria2, criteria3, criteria4, criteria5, criteria6, criteria7);
	    }

	    public  ArrayList<Vehicle> getVehicleFromThreeCriteria(String value1,String value2,String value3, String comparator, String criteria1, String criteria2,String criteria3) throws Exception{
	    	return vehicleTDG.getVehicleFromThreeCriteria(value1, value2, value3, comparator, criteria1, criteria2, criteria3);
	    }

	    public Vehicle getVehicleByLicenseNo(String vehicleLicensePlate) throws Exception{ 
	    	return vehicleTDG.getVehicleByLicensePlate(vehicleLicensePlate);
	    }
}

