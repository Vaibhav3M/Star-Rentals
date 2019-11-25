package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.User;

public interface IUserTDG {

    public User getUser(String username, String password)throws Exception;
    public boolean authUser(String username,String password) throws Exception;

}
