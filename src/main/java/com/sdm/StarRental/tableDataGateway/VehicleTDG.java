package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.objectUtilities.Utilities;
import com.sdm.StarRental.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class VehicleTDG implements IVehicleTDG {

	private static VehicleTDG vehicleTDG;
 
    private Connection connection;
    
    private VehicleTDG() {
    	
    }
    
    public static VehicleTDG getInstance() {
    	if (vehicleTDG == null) {
    	  vehicleTDG = new VehicleTDG();
    	} 
    	return vehicleTDG;
    }
    

    public void establishConnection() {
    	 try {
             connection =Utilities.getSQLDb(connection);;
             
         }
         catch (Exception e){

         }

    }
    
    public void closeConnection() {
    	try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   

	@Override
    public boolean addNewVehicle(String type,int year, String model,String color,String licensePlate, String status,String make) throws Exception{

		establishConnection();

		String sql="INSERT INTO c_catalog (type,make,model,year,color,vehicleLicensePlate,status) VALUES ('"+type+"','"+make+"','"+model+"',"+year+",'"+color+"','"+licensePlate+"','"+status+"')";
		System.out.println(sql);
		Statement st;
		try {
			st = (Statement) connection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		finally {
            closeConnection();
        }
		return true;
		
	
    }

    @Override
    public boolean deleteVehicle(String licensePlate) throws Exception{

    	String sql="DELETE FROM c_catalog WHERE vehicleLicensePlate='"+licensePlate+"'";
		Statement st;
		try {
			st = (Statement) connection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}finally {
            closeConnection();
        }
		
		return true;
    }

    @Override
    public boolean modifyVehicle(String type,int year, String model,String make, String color,String licensePlate, String status) throws Exception{

    	 establishConnection();
    	String sql="UPDATE c_catalog SET type='"+type+"' ,make='"+make+"' ,model='"+model+"' ,year='"+year+"' ,color='"+color+"' ,status='"+status+"' ,vehicleLicensePlate='"+licensePlate+"' WHERE vehicleLicensePlate='"+licensePlate+"'";
		System.out.println(sql);
    	Statement st;
		try {
			st = (Statement) connection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}finally {
            closeConnection();
        }
		return true;
    }

    @Override
    public ArrayList<Vehicle> getAllVehicles() throws Exception{
    	 establishConnection();
    	String sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog";

ResultSet rs;
		Statement st;
		ArrayList<Vehicle> result=new ArrayList<>();
		try {
			st = (Statement)connection.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()){
				Vehicle v = Utilities.getVehicleObject(rs);
				result.add(v);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
            closeConnection();
        }
		return result;
    }

    @Override
    public ArrayList<Vehicle> getVehicleFromOneCriteria(String value, String comparator, String criteria) throws Exception{
    	 establishConnection();
    	String sql="";
		if(!criteria.equalsIgnoreCase("year")) {
		 sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE "+criteria+"='"+value+"'";
		}else {
	     sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE year "+comparator+Integer.valueOf(value);
		}
		Statement st;
		ArrayList<Vehicle> result=new ArrayList<>();
		try {
			st = (Statement) connection.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				Vehicle v = Utilities.getVehicleObject(rs);
				result.add(v);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
            closeConnection();
        }
		return result;
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromTwoCriteria(String value1,String value2, String comparator, String criteria1, String criteria2) throws Exception{
    	 establishConnection();
    	String sql="";
		if(!criteria1.equalsIgnoreCase("year") && !criteria2.equalsIgnoreCase("year")) {
		 sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE "+criteria1+"='"+value1+"' AND "
		+criteria2+"='"+value2+"'";
		}else if(criteria1.equalsIgnoreCase("year")){
	     sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE year"+comparator+Integer.valueOf(value1)+" AND "
		+criteria2+"='"+value2+"'";
		}
		else if(criteria2.equalsIgnoreCase("year")){
		     sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE year"+comparator+Integer.valueOf(value2)+" AND "
			+criteria1+"='"+value1+"'";
			}
		Statement st;
		ArrayList<Vehicle> result=new ArrayList<>();
		try {
			st = (Statement) connection.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				Vehicle v = Utilities.getVehicleObject(rs);
				result.add(v);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
            closeConnection();
        }
		return result;	
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromAllCriteria(String value1,String value2,String value3,String value4, String value5, String value6, String value7, String comparator, String criteria1, String criteria2,String criteria3,String criteria4, String criteria5, String criteria6, String criteria7) throws Exception{
    	 establishConnection();
    	String sql="";
		 sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE year"+comparator+Integer.valueOf(value1)+" AND "
					+criteria2+"='"+value2+"' AND "+criteria3+"='"+value3+"' AND " +criteria4+"='"+value4+"'AND " +criteria5+"='"+value5+"' AND" +criteria6+"='"+value6+"'"
							+ "AND" +criteria7+"='"+value7+"'";
	Statement st;
	ArrayList<Vehicle> result=new ArrayList<>();
	try {
		st = (Statement) connection.createStatement();
		ResultSet rs= st.executeQuery(sql);
		while(rs.next()) {
			Vehicle v = Utilities.getVehicleObject(rs);
			result.add(v);
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}finally {
        closeConnection();
    }
	return result;	
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromThreeCriteria(String value1,String value2,String value3, String comparator, String criteria1, String criteria2,String criteria3) throws Exception{
    	 establishConnection();
    	String sql="";
		if(criteria1.equalsIgnoreCase("year")){
		 sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE year"+comparator+Integer.valueOf(value1)+" AND "
					+criteria2+"='"+value2+"' AND "+criteria3+"='"+value3+"'";
		}else {
			sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE "+criteria1+"='"+value1+"' AND "
					+criteria2+"='"+value2+"' AND "+criteria3+"='"+value3+"'";	
		}
	Statement st;
	ArrayList<Vehicle> result=new ArrayList<>();
	try {
		st = (Statement) connection.createStatement();
		ResultSet rs= st.executeQuery(sql);
		while(rs.next()) {
			Vehicle v = Utilities.getVehicleObject(rs);
			result.add(v);
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}finally {
        closeConnection();
    }
	return result;	
    }

    @Override
    public Vehicle getVehicleByLicensePlate(String licensePlate) throws Exception{
    	 establishConnection();
    	String sql="";
		 sql="Select type,make,model,year,color,vehicleLicensePlate,status from c_catalog WHERE vehicleLicensePlate='"+licensePlate+"'";

		Statement st;
		Vehicle result = null;
		
		try {
			st = (Statement) connection.createStatement();
			ResultSet rs= st.executeQuery(sql);
			while(rs.next()) {
				result = Utilities.getVehicleObject(rs);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
            closeConnection();
        }
		return result;
    
    }
}
