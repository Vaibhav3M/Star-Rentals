package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.Vehicle;
import com.sdm.StarRental.objectUtilities.Utilities;
import com.sdm.StarRental.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleTDG implements IVehicleTDG {

  //  @Autowired
    private Connection connection;

    public VehicleTDG() {
    	 try {
             connection = getSQLDb();
         }
         catch (Exception e){

         }
    }

    private Connection getSQLDb() throws SQLException, Exception {
		// TODO Auto-generated method stub
    	Class.forName("com.mysql.cj.jdbc.Driver");
        String url      = "jdbc:mysql://localhost:3306/car_rental";
        String user     = "root";
        String pass = "root";

        connection =
            DriverManager.getConnection(url,user,pass);

        return connection;
	}

	@Override
    public boolean addNewVehicle(String type,int year, String model,String color,String licensePlate, String status,String make,String image) throws Exception{



		String sql="INSERT INTO c_catalog (Type,Make,Model,Year,Color,License_Plate,Status) VALUES ('"+type+"','"+make+"','"+model+"',"+year+",'"+color+"','"+licensePlate+"','"+status+"')";
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
		return true;
		
	
    }

    @Override
    public boolean deleteVehicle(String licensePlate) throws Exception{

    	String sql="DELETE FROM c_catalog WHERE LICENSE_PLATE='"+licensePlate+"'";
		Statement st;
		try {
			st = (Statement) connection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
    }

    @Override
    public boolean modifyVehicle(String type,int year, String model,String make, String color,String licensePlate, String status,String image) throws Exception{
    	//		String sql="INSERT INTO c_catalog (Type,Make,Model,Year,Color,License_Plate,Status) VALUES ('"+type+"','"+make+"','"+model+"',"+year+",'"+color+"','"+licensePlate+"','"+status+"')";

    	String sql="UPDATE c_catalog SET Type='"+type+"' ,Make='"+make+"' ,Model='"+model+"' ,Year='"+year+"' ,Color='"+color+"' ,Status='"+status+"' ,License_Plate='"+licensePlate+"' WHERE License_Plate='"+licensePlate+"'";
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
		return true;
    }

    @Override
    public ArrayList<Vehicle> getAllVehicles() throws Exception{
String sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog";

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
		}
		return result;
    }

    @Override
    public ArrayList<Vehicle> getVehicleFromOneCriteria(String value, String comparator, String criteria) throws Exception{

    	String sql="";
		if(!criteria.equalsIgnoreCase("year")) {
		 sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE "+criteria+"='"+value+"'";
		}else {
	     sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE year "+comparator+Integer.valueOf(value);
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
		}
		return result;
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromTwoCriteria(String value1,String value2, String comparator, String criteria1, String criteria2) throws Exception{
    	String sql="";
		if(!criteria1.equalsIgnoreCase("year") && !criteria2.equalsIgnoreCase("year")) {
		 sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE "+criteria1+"='"+value1+"' AND "
		+criteria2+"='"+value2+"'";
		}else if(criteria1.equalsIgnoreCase("year")){
	     sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE year"+comparator+Integer.valueOf(value1)+" AND "
		+criteria2+"='"+value2+"'";
		}
		else if(criteria2.equalsIgnoreCase("year")){
		     sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE year"+comparator+Integer.valueOf(value2)+" AND "
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
		}
		return result;	
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromAllCriteria(String value1,String value2,String value3,String value4, String value5, String value6, String value7, String comparator, String criteria1, String criteria2,String criteria3,String criteria4, String criteria5, String criteria6, String criteria7) throws Exception{

    	String sql="";
		 sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE year"+comparator+Integer.valueOf(value1)+" AND "
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
	}
	return result;	
    }

    @Override
    public  ArrayList<Vehicle> getVehicleFromThreeCriteria(String value1,String value2,String value3, String comparator, String criteria1, String criteria2,String criteria3) throws Exception{

    	String sql="";
		if(criteria1.equalsIgnoreCase("year")){
		 sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE year"+comparator+Integer.valueOf(value1)+" AND "
					+criteria2+"='"+value2+"' AND "+criteria3+"='"+value3+"'";
		}else {
			sql="Select Type,Make,Model,Year,Color,License_Plate,Status from c_catalog WHERE "+criteria1+"='"+value1+"' AND "
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
	}
	return result;	
    }

    @Override
    public Vehicle getVehicleByLicenseNo(String licenseNo) throws Exception{


        return null;
    }
}
