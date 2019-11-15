package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.objectUtilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.*;
import java.util.ArrayList;

public class TransactionTDG implements ITransactionTDG {

    @Autowired
    private Connection connection;


    @Override
    public boolean createTransaction(String vehicleLicensePlate, String transactionType,  String clientLicenseNumber, String status, String timeStamp, String bookingFrom, String bookingTill, String transactionBy) throws Exception {


        String sql = "INSERT INTO `c_transactions` (`transactionType`, `vehicleLicensePlate`, `clientLicenseNumber`, `status`, `bookingFrom`, `bookingTill`, `transactionBy`) VALUES ('"+transactionType+"', '"+vehicleLicensePlate+"', '"+clientLicenseNumber+"', '"+status+"', '"+bookingFrom+"', '"+bookingTill+"', '"+transactionBy+"')";

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
    public Transaction getTransactionByID(int id) throws Exception {

        Transaction resultTransaction;

        String sql = "SELECT * FROM c_transactions WHERE transactionID = " + id;

        Statement st;

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            resultTransaction = Utilities.getTransactionObject(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return resultTransaction;

    }


    @Override
    public ArrayList<Transaction> getAllTransactions() throws Exception {


        String sql = "SELECT * FROM c_transactions";

        Statement st;

        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForType(String transactionType) throws Exception{

        String sql = "SELECT * FROM c_transactions WHERE transactionType = " + "'"+transactionType+"'";

        Statement st;

        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;


    }

    @Override
    public ArrayList<Transaction> getTransactionForClient(String clientLicenseNumber) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE clientLicenseNumber = " + "'"+clientLicenseNumber+"'";

        Statement st;

        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;

    }

    @Override
    public ArrayList<Transaction> getTransactionForVehicle(String vehicleLicensePlate) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE vehicleLicensePlate = " + "'"+vehicleLicensePlate+"'";

        Statement st;

        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }


    @Override
    public ArrayList<Transaction> getTransactionForStatus(String status) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE status = " + "'"+status+"'";


        Statement st;

        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForRentedTill(String bookingTill) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE bookingTill = " + "'"+bookingTill+"'";

        Statement st;

        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForRentedFrom(String bookingFrom) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE bookingFrom = " + "'"+bookingFrom+"'";

        Statement st;

        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForTwoCriteria(String val1, String val2, String criteria1, String criteria2) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE " + criteria1 + " = " +  "'"+val1+"'" + " AND " + criteria2+ " = " + "'"+val2+"'";

        Statement st;
        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForThreeCriteria(String val1, String val2, String val3, String criteria1, String criteria2, String criteria3) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE " + criteria1 + " = " +  "'"+val1+"'" + " AND " + criteria2+ " = " + "'"+val2+"'" + "AND" + criteria3 + " = " + "'"+val3+"'";

        Statement st;
        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForFourCriteria(String val1, String val2, String val3, String val4, String criteria1, String criteria2, String criteria3, String criteria4) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE " + criteria1 + " = " +  "'"+val1+"'" + " AND " + criteria2+ " = " + "'"+val2+"'" + "AND" + criteria3 + " = " + "'"+val3+"'" + "AND" + criteria4 + " = " + "'"+val4+"'";

        Statement st;
        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForAllCriteria(String vehicleLicensePlate, String clientLicenseNumber, String status, String bookingFrom, String bookingTill, String transactionBy, String criteria) throws Exception {

        String sql = "SELECT * FROM c_transactions WHERE " + "vehicleLicensePlate" + " = " +  "'"+vehicleLicensePlate+"'" + " AND " + "clientLicenseNumber" + " = " + "'"+clientLicenseNumber+"'" + "AND" + "status" + " = " + "'"+status+"'" + "AND" + "bookingFrom" + " = " + "'"+bookingFrom+"'" + "AND" + "bookingTill" + " = " + "'"+bookingTill+"'" + "AND" + "transactionBy" + " = " + "'"+transactionBy+"'" ;

        Statement st;
        ArrayList<Transaction> resultList = new ArrayList<>();

        try {
            st = connection.createStatement();
            ResultSet resultSet= st.executeQuery(sql);

            while(resultSet.next()) {

                resultList.add(Utilities.getTransactionObject(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public ArrayList<Transaction> sortByStatus(ArrayList<Transaction> list) {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> sortByTransactiondate(ArrayList<Transaction> list) {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> sortBylicensePlate(ArrayList<Transaction> list) {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> sortByRentedTill(ArrayList<Transaction> list) {
        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> sortByRentedFrom(ArrayList<Transaction> list) {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> sortByClient(ArrayList<Transaction> list) {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }
}
