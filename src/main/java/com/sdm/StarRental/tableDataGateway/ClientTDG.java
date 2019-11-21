package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.Client;
import com.sdm.StarRental.objectUtilities.Utilities;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class ClientTDG implements IClientTDG {

    private Connection connection;

    public void establishConnection() {

        try {
            connection = Utilities.getSQLDb(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createClient(String firstName, String lastName, String phoneNumber, String licenseNumber, String licenseExpiryDate) throws Exception {

        establishConnection();

        String sql = "INSERT INTO c_clients (firstName, lastName, phoneNumber, licenseNumber, licenseExpiryDate) VALUES ('" + firstName + "', '" + lastName + "', '" + phoneNumber + "', '" + licenseNumber + "', '" + licenseExpiryDate + "')";

        Statement st;
        try {
            st = connection.createStatement();

            st.executeUpdate(sql);
        } catch
        (SQLException e) {
            System.out.println("Exception =" + e.getMessage());

            return false;
        } catch (Exception e) {
            System.out.println("Exception =" + e.getMessage());
            return false;
        }finally {
            closeConnection();
        }

        return true;
    }

    @Override
    public boolean deleteClient(String licenseNumber) throws Exception {

        establishConnection();

        String sql = "DELETE FROM c_clients WHERE licenseNumber ='"+licenseNumber+"'";

        Statement st;

        System.out.print(sql);
        try {

            st = connection.createStatement();

            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return true;
    }

    @Override
    public boolean modifyClient(String firstName, String lastName, String phoneNumber, String licenseNumber, String licenseExpiryDate) throws Exception {

        establishConnection();

        String sql2="UPDATE c_clients SET licenseNumber='"+licenseNumber+"' ,firstName='"+firstName+"' ,lastName='"+lastName+"' ,phoneNumber='"+phoneNumber+"' ,licenseExpiryDate='"+licenseExpiryDate+"' WHERE licenseNumber='"+licenseNumber+"'";

        System.out.println(sql2);
       // String sql = "UPDATE c_clients SET licenseNumber='"+ licenseNumber + " firstName=" + firstName + " lastName =" + lastName + " phoneNumber= " + phoneNumber + " licenseExpiryDate = " + licenseExpiryDate + "' WHERE licenseNumber = + '\"+licenseNumber+\"'";

        Statement st;

        try {

            st = connection.createStatement();
    st.executeUpdate(sql2);
          //  st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return true;
    }

    @Override
    public ArrayList<Client> getAllClients() throws Exception {

        establishConnection();

        String sql = "SELECT * FROM c_clients";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {

                resultList.add(Utilities.getClientObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsOneParam(String key, String value) throws Exception {

        establishConnection();

        String sql = "SELECT * FROM c_clients WHERE " + key + " = " + "'" + value + "'";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {

                resultList.add(Utilities.getClientObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsTwoParam(String key1, String value1, String key2, String value2) throws Exception {

        establishConnection();

        String sql = "SELECT * FROM c_clients WHERE " + key1 + " = " + "'" + value1 + "'" + " AND " + key2 + " = " + "'" + value2 + "'";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {

                resultList.add(Utilities.getClientObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }


        return resultList;
    }

    @Override
    public ArrayList<Client> getClientDetailsThreeParam(String value1, String value2, String value3) throws Exception {

        establishConnection();

        String sql = "SELECT * FROM c_clients WHERE " + "firstName" + " = " + "'" + value1 + "'" + " AND " + "lastName" + " = " + "'" + value2 + "'" + "AND" + "licenseNumber" + " = " + "'" + value3 + "'";

        ArrayList<Client> resultList = new ArrayList<>();

        Statement st;

        try {
            st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {

                resultList.add(Utilities.getClientObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }

        return resultList;
    }


}
