package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.User;
import com.sdm.StarRental.objectUtilities.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserTDG implements IUserTDG {

    private UserTDG userTDG;
    private Connection connection;
    
    public UserTDG getInstance() {
    	if(userTDG== null) {
    		userTDG = new UserTDG();
    	}
    	
    	return userTDG;
    }
    
    public void establishConntection() {
   	 try {
            connection = Utilities.getSQLDb(connection);;
            
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
    
    public UserTDG() {

   	 try {
            connection = Utilities.getSQLDb(connection);
        }
        catch (Exception e){

        }
   
    	
    }
    
    


    @Override
    public User getUser(String userName, String password) throws Exception {
    	
    User user = null;
    
    Statement st;
    String sqlQuery = "SELECT userName,password,userType FROM c_users  WHERE userName='"+userName+"' AND password='" +password+"';";
    ResultSet rs;
    try {
//try again
		st = (Statement)connection.createStatement();

		rs= st.executeQuery(sqlQuery);
		if(rs.next()) {
		user = Utilities.getUserObject(rs);
		}

	} catch (SQLException e) {

		e.printStackTrace();

		return null;

	} catch (Exception e) {

		e.printStackTrace();

		return null;

	}

	return user;
    }

    @Override
    public boolean authUser(String userName, String password) throws Exception {
    	Statement st; 
    	String sqlQuery = "SELECT userName FROM c_users WHERE userName='"+userName+"' AND password='" +password+"'"; 
    	
    	User user; 
    	ResultSet rs; 
    	try {
    		st = (Statement)connection.createStatement(); 
    	rs= st.executeQuery(sqlQuery);

    			 } 
    	catch (SQLException e) 
    	{ e.printStackTrace(); 
    	return false; } 
    	catch(Exception e) { 
    		e.printStackTrace(); 
    		return false; 
    		} 
    	int size=0; 
    	if(rs!=null) {
    		rs.last(); size = rs.getRow();

    			  } 
    	if(size>0) { 
    		return true; 
    		} 
    	return false; 
    	}
    }

