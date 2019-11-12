package com.sdm.StarRental.tableDataGateway;

import com.sdm.StarRental.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.ArrayList;

public class TransactionTDG implements ITransactionTDG {

    @Autowired
    private Connection connection;

    @Override
    public boolean createTransaction(int id, String vehicleLicencePlate, String clientLicenseNumber, String status, String timeStamp, String bookingFrom, String bookingTill, String transactionBy) throws Exception {
        return false;
    }

    @Override
    public Transaction getTransactionByID(int id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Transaction> getAllTransactions() throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForClient(String clientLicenseNumber) throws Exception {
        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForVehicle(String vehicleLicencePlate) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForDueDate(String bookingTill) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForStatus(String status) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForRentedTill(String bookingTill) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForRentedFrom(String bookingFrom) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForTwoCriteria(String val1, String val2, String criteria1, String criteria2) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForThreeCriteria(String val1, String val2, String val3, String criteria1, String criteria2, String criteria3) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForFourCriteria(String val1, String val2, String val3, String val4, String criteria1, String criteria2, String criteria3, String criteria4) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

        return resultList;
    }

    @Override
    public ArrayList<Transaction> getTransactionForAllCriteria(String vehicleLicencePlate, String clientLicenseNumber, String status, String timeStamp, String bookingFrom, String bookingTill, String transactionBy, String criteria) throws Exception {

        ArrayList<Transaction> resultList = new ArrayList<>();

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
