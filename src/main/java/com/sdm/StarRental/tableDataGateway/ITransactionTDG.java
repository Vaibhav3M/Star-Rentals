package com.sdm.StarRental.tableDataGateway;

import java.util.ArrayList;

import com.sdm.StarRental.model.Transaction;

public interface ITransactionTDG {


    public boolean createTransaction(String vehicleLicencePlate, String transactionType, String clientLicenseNumber,String status,String timeStamp,String bookingFrom,String bookingTill,String transactionBy) throws Exception;

    public Transaction getTransactionByID(int id) throws Exception;

    public ArrayList<Transaction> getAllTransactions() throws Exception;
    public ArrayList<Transaction> getTransactionForType(String transactionType) throws Exception;
    public ArrayList<Transaction> getTransactionForClient(String clientLicenseNumber) throws Exception;
    public ArrayList<Transaction> getTransactionForVehicle(String vehicleLicencePlate) throws Exception;
    public ArrayList<Transaction> getTransactionForStatus(String status) throws Exception;
    public ArrayList<Transaction> getTransactionForRentedTill(String bookingTill) throws Exception;
    public ArrayList<Transaction> getTransactionForRentedFrom(String bookingFrom) throws Exception;
    public ArrayList<Transaction> getTransactionForTwoCriteria(String val1, String val2, String criteria1,String criteria2) throws Exception;
    public ArrayList<Transaction> getTransactionForThreeCriteria(String val1, String val2, String val3, String criteria1,String criteria2, String criteria3) throws Exception;
    public ArrayList<Transaction> getTransactionForFourCriteria(String val1, String val2, String val3, String val4,String criteria1, String criteria2, String criteria3, String criteria4) throws Exception;
    public ArrayList<Transaction> getTransactionForAllCriteria(String vehicleLicencePlate,String clientLicenseNumber,String status,String bookingFrom,String bookingTill,String transactionBy, String criteria) throws Exception;


    public ArrayList<Transaction> sortByStatus(ArrayList<Transaction> list);
    public ArrayList<Transaction> sortByTransactiondate(ArrayList<Transaction> list);
    public ArrayList<Transaction> sortBylicensePlate(ArrayList<Transaction> list);
    public ArrayList<Transaction> sortByRentedTill(ArrayList<Transaction> list);
    public ArrayList<Transaction> sortByRentedFrom(ArrayList<Transaction> list);
    public ArrayList<Transaction> sortByClient(ArrayList<Transaction> list);
}
