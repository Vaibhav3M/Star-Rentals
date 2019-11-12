package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.ArrayList;

public class ClientTDG implements IClientTDG {

    @Autowired
    private Connection connection;

    @Override
    public boolean createClient(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception{

        return true;
    }

    @Override
    public boolean deleteClient(String licenseNumber) throws Exception{

        return true;
    }

    @Override
    public boolean modifyClient(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception{

        return true;
    }

    @Override
    public ArrayList<Client> getClientDetailsOneParam(String key, String value) throws Exception{

        ArrayList<Client> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsTwoParam(String key1,String value1, String key2, String value2) throws Exception{
        ArrayList<Client> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsThreeParam(String value1,String value2, String value3) throws Exception{

        ArrayList<Client> resultList = new ArrayList<>();


        return resultList;
    }

    @Override
    public ArrayList<Client> getAllClients() throws Exception{

        ArrayList<Client> resultList = new ArrayList<>();


        return resultList;
    }
}
