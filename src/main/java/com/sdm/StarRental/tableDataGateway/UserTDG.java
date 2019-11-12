package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;

public class UserTDG implements IUserTDG {

    @Autowired
    private Connection connection;

    @Override
    public User getUser(String userType, String username, String password) throws Exception {
        return null;
    }

    @Override
    public boolean authUser(String userType, String username, String password) throws Exception {
        return false;
    }
}
