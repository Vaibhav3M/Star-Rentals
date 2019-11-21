package com.sdm.StarRental.dataMapper;

import java.util.ArrayList;


import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.tableDataGateway.ClientTDG;
import org.springframework.stereotype.Service;

@Service
public class ClientDM {

    ClientTDG clientTDG = ClientTDG.getInstance();


    public boolean createClientService(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception {

        return clientTDG.createClient(firstName,lastName,phoneNumber,licenseNumber,licenseExpiryDate);
    }

    public boolean deleteClientService(String licenseNumber) throws Exception{

        return clientTDG.deleteClient(licenseNumber);
    }

    public boolean modifyClientService(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception{

        return  clientTDG.modifyClient(firstName,lastName,phoneNumber,licenseNumber,licenseExpiryDate);
    }

    public ArrayList<Client> getClientDetailsOneParamService(String key, String value) throws Exception{

        return clientTDG.getClientDetailsOneParam(key,value);
    }

    public ArrayList<Client> getClientDetailsTwoParamService(String key1,String value1, String key2, String value2) throws Exception{

        return clientTDG.getClientDetailsTwoParam(key1,value1,key2,value2);
    }

    public ArrayList<Client> getClientDetailsThreeParamService(String value1,String value2, String value3) throws Exception{

        return clientTDG.getClientDetailsThreeParam(value1,value2,value3);
    }

    public ArrayList<Client> getAllClientsService() throws Exception{

        return clientTDG.getAllClients();
    }

}
