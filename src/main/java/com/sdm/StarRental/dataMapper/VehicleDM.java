package com.sdm.StarRental.dataMapper;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdm.StarRental.comparator.CatalogSortByColor;
import com.sdm.StarRental.comparator.CatalogSortByLicensePlate;
import com.sdm.StarRental.comparator.CatalogSortByMake;
import com.sdm.StarRental.comparator.CatalogSortByModel;
import com.sdm.StarRental.comparator.CatalogSortByStatus;
import com.sdm.StarRental.comparator.CatalogSortByType;
import com.sdm.StarRental.comparator.CatalogSortByYear;
import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.tableDataGateway.VehicleTDG;


public class VehicleDM {
	
	VehicleTDG vehicleTDG;
	
	
	
public VehicleDM() {
		
		this.vehicleTDG = VehicleTDG.getInstance();
	}
	
	
	 public boolean addNewVehicle(String type,int year, String model,String color,String vehicleLicensePlate, String status, String make) throws Exception{ 
		 boolean result = vehicleTDG.addNewVehicle(type, year, model, color, vehicleLicensePlate, status, make);
		 return result;
	 }

	    public boolean deleteVehicle(String vehicleLicensePlate) throws Exception{
	    	boolean result = vehicleTDG.deleteVehicle(vehicleLicensePlate);
	    	return result;
	    }


	    public boolean modifyVehicle(String type,String make, String model,int year, String color,String vehilceLicensePlate, String status) throws Exception{
	    	 boolean result = vehicleTDG.modifyVehicle(type, year, model, make, color, vehilceLicensePlate, status);
	    	return result;


	    }

	    public ArrayList<Vehicle> getAllVehicles() throws Exception{ 
	    	ArrayList<Vehicle> result = vehicleTDG.getAllVehicles();
	    	return result;
	    }

	    public ArrayList<Vehicle> getVehicleFromOneCriteria(String value, String comparator, String criteria) throws Exception{
	    	ArrayList<Vehicle> result = vehicleTDG.getVehicleFromOneCriteria(value, comparator, criteria);
	    	return result;
	    }

	    public  ArrayList<Vehicle> getVehicleFromTwoCriteria(String value1,String value2, String comparator, String criteria1, String criteria2) throws Exception{
	    	ArrayList<Vehicle> result =  vehicleTDG.getVehicleFromTwoCriteria(value1, value2, comparator, criteria1, criteria2);
	    	return result;
	    }

	    public  ArrayList<Vehicle> getVehicleFromAllCriteria(String value1,String value2,String value3,String value4, String value5, String value6, String value7, String comparator, String criteria1, String criteria2,String criteria3,String criteria4, String criteria5, String criteria6, String criteria7) throws Exception{
	    	ArrayList<Vehicle> result  = vehicleTDG.getVehicleFromAllCriteria(value1, value2, value3, value4, value5, value6, value7, comparator, criteria1, criteria2, criteria3, criteria4, criteria5, criteria6, criteria7);
	    	return result;
	    }

	    public  ArrayList<Vehicle> getVehicleFromThreeCriteria(String value1,String value2,String value3, String comparator, String criteria1, String criteria2,String criteria3) throws Exception{
	    	ArrayList<Vehicle> result  = vehicleTDG.getVehicleFromThreeCriteria(value1, value2, value3, comparator, criteria1, criteria2, criteria3);
	    	return result;
	    }
	    
	    public Vehicle getVehicleByLicenseNo(String vehicleLicensePlate) throws Exception{ 
	    	Vehicle result = vehicleTDG.getVehicleByLicensePlate(vehicleLicensePlate);
	    	return result;
	    }


		public ArrayList<Vehicle> sortByYear(ArrayList<Vehicle> vehicles) {
			Collections.sort(vehicles, new CatalogSortByYear());
			return vehicles;
			
		}


		public ArrayList<Vehicle> sortByModel(ArrayList<Vehicle> vehicles) {
			Collections.sort(vehicles, new CatalogSortByModel());
			return vehicles;
		}


		public ArrayList<Vehicle> sortByLicensePlate(ArrayList<Vehicle> vehicles) {
			Collections.sort(vehicles, new CatalogSortByLicensePlate());
			return vehicles;
			
		}


		public ArrayList<Vehicle> sortByMake(ArrayList<Vehicle> vehicles) {
			Collections.sort(vehicles, new CatalogSortByMake());
			return vehicles;
		
		}


		public ArrayList<Vehicle> sortByColor(ArrayList<Vehicle> vehicles) {
			Collections.sort(vehicles, new CatalogSortByColor());
			return vehicles;
		}


		public ArrayList<Vehicle> sortByType(ArrayList<Vehicle> vehicles) {
			Collections.sort(vehicles, new CatalogSortByType());
			return vehicles;
		}


		public ArrayList<Vehicle> sortByStatus(ArrayList<Vehicle> vehicles) {
			// TODO Auto-generated method stub
			Collections.sort(vehicles, new CatalogSortByStatus());
			return vehicles;
		}
}

