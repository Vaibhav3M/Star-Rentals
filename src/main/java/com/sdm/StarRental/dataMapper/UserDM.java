package com.sdm.StarRental.dataMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdm.StarRental.model.User;
import com.sdm.StarRental.tableDataGateway.IUserTDG;
import com.sdm.StarRental.tableDataGateway.UserTDG;

@Service
public class UserDM  {
	
	@Autowired
	private UserTDG userTDG;
	
	public void setUser(UserTDG userTDG) {
		this.userTDG = userTDG;
	}
	
	
	public User getUser(String userName, String password)throws Exception{
		return this.userTDG.getUser(userName, password);
		
	}
	
	
    public boolean authUser(String userName,String password) throws Exception{
    return this.userTDG.authUser(userName, password);
    }
	
}
