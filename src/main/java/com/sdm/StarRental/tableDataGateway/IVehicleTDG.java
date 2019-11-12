package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.Vehicle;

import java.util.ArrayList;

public interface IVehicleTDG {


    public boolean addNewVehicle(String type,int year, String model,String color,String licenseNumber, String status,String image) throws Exception;

    public boolean deleteVehicle(String licenseNumber) throws Exception;

    public boolean modifyVehicle(String type,int year, String model,String color,String licenseNumber, String status,String image) throws Exception;

    public ArrayList<Vehicle> getAllVehicles() throws Exception;

    public ArrayList<Vehicle> getVehicleFromOneCriteria(String value, String comparator, String criteria) throws Exception;

    public  ArrayList<Vehicle> getVehicleFromTwoCriteria(String value1,String value2, String comparator, String criteria1, String criteria2) throws Exception;

    public  ArrayList<Vehicle> getVehicleFromAllCriteria(String value1,String value2,String value3,String value4, String comparator, String criteria1, String criteria2,String criteria3,String criteria4) throws Exception;

    public  ArrayList<Vehicle> getVehicleFromThreeCriteria(String value1,String value2,String value3, String comparator, String criteria1, String criteria2,String criteria3) throws Exception;

    public Vehicle getVehicleByLicenseNo(String licenseNo) throws Exception;

}
