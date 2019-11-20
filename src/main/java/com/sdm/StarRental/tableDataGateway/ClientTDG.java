package com.sdm.StarRental.tableDataGateway;

import com.mysql.cj.api.mysqla.result.Resultset;
import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.objectUtilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class ClientTDG implements IClientTDG {

    @Autowired
    private Connection connection;

    @Override
    public boolean createClient(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception{


        String sql = "INSERT INTO `c_client` (`firstName`, `lastName`, `phoneNumber`, `licenseNumber`, `licenseExpiryDate`) VALUES ('"+firstName+"', '"+lastName+"', '"+phoneNumber+"', '"+licenseNumber+"', '"+licenseExpiryDate+"')";

        Statement st;
        try { st = connection.createStatement();

            st.executeUpdate(sql); }
        catch
        (SQLException e) {
            System.out.println("Exception =" + e.getMessage());

            return false; }
        catch (Exception e) {
            System.out.println("Exception =" + e.getMessage());
            return false; }

        return true;
    }

    @Override
    public boolean deleteClient(String licenseNumber) throws Exception{

        String sql = "DELETE FROM `c_clients` WHERE licenseNumber =" + licenseNumber;

        Statement st;

        try {

        st = connection.createStatement();

        st.executeQuery(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean modifyClient(String firstName, String lastName, String phoneNumber,String licenseNumber, String licenseExpiryDate) throws Exception{

		String sql = "UPDATE c_clients SET licenseNumber = '"+licenseNumber+"' firstName='"+firstName+"'lastName ='"+lastName+"' phoneNumber= '"+phoneNumber+"' licenseExpiryDate = '"+licenseExpiryDate+"' WHERE licenseNumber = + '\"+licenseNumber+\"'";
		
        Statement st;

        try {

            st = connection.createStatement();

            st.executeQuery(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public ArrayList<Client> getAllClients() throws Exception{

        String sql = "SELECT * FROM c_clients";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()){

                resultList.add(Utilities.getClientObject(resultSet));

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsOneParam(String key, String value) throws Exception{

        String sql = "SELECT * FROM c_clients WHERE " + key + " = " +  "'"+value+"'";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()){

                resultList.add(Utilities.getClientObject(resultSet));

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsTwoParam(String key1,String value1, String key2, String value2) throws Exception{

        String sql = "SELECT * FROM c_clients WHERE " + key1 + " = " +  "'"+value1+"'" + " AND " + key2+ " = " + "'"+value2+"'";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()){

                resultList.add(Utilities.getClientObject(resultSet));

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsThreeParam(String value1,String value2, String value3) throws Exception{

        String sql = "SELECT * FROM c_clients WHERE " + "firstName" + " = " +  "'"+value1+"'" + " AND " + "lastName"+ " = " + "'"+value2+"'" + "AND" + "licenseNumber"+ " = " + "'"+value3+"'";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()){

                resultList.add(Utilities.getClientObject(resultSet));

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return resultList;
    }


}
