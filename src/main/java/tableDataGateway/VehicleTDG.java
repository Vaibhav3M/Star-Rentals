package tableDataGateway;

import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.ArrayList;

public class VehicleTDG implements IVehicleTDG {

    @Autowired
    private Connection connection;

    public VehicleTDG() {


    }

    @Override
    public boolean addNewVehicle(String type,int year, String model,String color,String licenseNumber, String status,String image) throws Exception{


        return true;
    }

    @Override
    public boolean deleteVehicle(String licenseNumber) throws Exception{

        return true;
    }

    @Override
    public boolean modifyVehicle(String type,int year, String model,String color,String licenseNumber, String status,String image) throws Exception{

        return true;
    }

    @Override
    public ArrayList<Vehicle> getAllVehicles() throws Exception{

        ArrayList<Vehicle> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public ArrayList<Vehicle> getVehicleFromOneCriteria(String value, String comparator, String criteria) throws Exception{

        ArrayList<Vehicle> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromTwoCriteria(String value1,String value2, String comparator, String criteria1, String criteria2) throws Exception{

        ArrayList<Vehicle> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromAllCriteria(String value1,String value2,String value3,String value4, String comparator, String criteria1, String criteria2,String criteria3,String criteria4) throws Exception{

        ArrayList<Vehicle> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromThreeCriteria(String value1,String value2,String value3, String comparator, String criteria1, String criteria2,String criteria3) throws Exception{

        ArrayList<Vehicle> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public Vehicle getVehicleByLicenseNo(String licenseNo) throws Exception{


        return null;
    }
}
