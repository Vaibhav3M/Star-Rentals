package com.sdm.StarRental.dataMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdm.StarRental.model.User;
import com.sdm.StarRental.tableDataGateway.IUserTDG;
import com.sdm.StarRental.tableDataGateway.UserTDG;


public class UserDM  {
	
	UserTDG userTDG;
	
	public UserDM() {
		
		this.userTDG = new UserTDG();
		
	}
	
	public User getUser(String userName, String password)throws Exception{
		userTDG.getInstance().establishConntection();
		User result = userTDG.getUser(userName, password);
		userTDG.getInstance().closeConnection();
		return result;
		
	}
	
	
    public boolean authUser(String userName,String password) throws Exception{
    	userTDG.getInstance().establishConntection();
    	boolean result = userTDG.authUser(userName, password);
    	userTDG.getInstance().closeConnection();
    return result;
    }
	
}
