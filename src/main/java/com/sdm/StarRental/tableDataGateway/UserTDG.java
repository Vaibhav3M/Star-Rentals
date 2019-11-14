package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.User;
import com.sdm.StarRental.objectUtilities.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserTDG implements IUserTDG {

    @Autowired
    private Connection connection;

    @Override
    public User getUser(String username, String password) throws Exception {
    	
    User user = null;
    
    Statement st;
    String sqlQuery = "SELECT Username,Password,Name,User_Type FROM c_users  WHERE Username='"+username+"' AND Password='" +password+"';";
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
    public boolean authUser(String username, String password) throws Exception {
    	Statement st; 
    	String sqlQuery = "SELECT Name FROM c_users WHERE Username='"+username+"' AND Password='" +password+"'"; 
    	
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

