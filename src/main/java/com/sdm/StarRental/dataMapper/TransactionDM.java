package com.sdm.StarRental.dataMapper;

import java.util.ArrayList;

import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.tableDataGateway.TransactionTDG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDM {

    @Autowired
    TransactionTDG transactionTDG;

    public boolean createTransactionService(String vehicleLicencePlate, String transactionType, String clientLicenseNumber,String status,String timeStamp,String bookingFrom,String bookingTill,String transactionBy) throws Exception {

        return transactionTDG.createTransaction(vehicleLicencePlate,transactionType,clientLicenseNumber,status,timeStamp,bookingFrom,bookingTill,transactionBy);
    }

    public Transaction getTransactionByIDService(int id) throws Exception {

        return transactionTDG.getTransactionByID(id);
    }

    public ArrayList<Transaction> getAllTransactionsService() throws Exception {

        return transactionTDG.getAllTransactions();
    }

    public ArrayList<Transaction> getTransactionForTypeService(String transactionType) throws Exception {

        return transactionTDG.getTransactionForType(transactionType);
    }
    public ArrayList<Transaction> getTransactionForClientService(String clientLicenseNumber) throws Exception {

        return transactionTDG.getTransactionForClient(clientLicenseNumber);
    }

    public ArrayList<Transaction> getTransactionForVehicleService(String vehicleLicencePlate) throws Exception {

        return transactionTDG.getTransactionForVehicle(vehicleLicencePlate);
    }

    public ArrayList<Transaction> getTransactionForStatusService(String status) throws Exception {

        return transactionTDG.getTransactionForStatus(status);
    }

    public ArrayList<Transaction> getTransactionForRentedTillService(String bookingTill) throws Exception {

        return transactionTDG.getTransactionForRentedTill(bookingTill);
    }

    public ArrayList<Transaction> getTransactionForRentedFromService(String bookingFrom) throws Exception{

        return transactionTDG.getTransactionForRentedFrom(bookingFrom);
    }

    public ArrayList<Transaction> getTransactionForTwoCriteriaService(String val1, String val2, String criteria1,String criteria2) throws Exception{

        return transactionTDG.getTransactionForTwoCriteria(val1,val2,criteria1,criteria2);
    }

    public ArrayList<Transaction> getTransactionForThreeCriteriaService(String val1, String val2, String val3, String criteria1,String criteria2, String criteria3) throws Exception {

        return transactionTDG.getTransactionForThreeCriteria(val1,val2,val3,criteria1,criteria2,criteria3);
    }

    public ArrayList<Transaction> getTransactionForFourCriteriaService(String val1, String val2, String val3, String val4,String criteria1, String criteria2, String criteria3, String criteria4) throws Exception {

        return transactionTDG.getTransactionForFourCriteria(val1,val2,val3,val4,criteria1,criteria2,criteria3,criteria4);
    }

    public ArrayList<Transaction> getTransactionForAllCriteriaService(String vehicleLicencePlate,String clientLicenseNumber,String status,String bookingFrom,String bookingTill,String transactionBy, String criteria) throws Exception {

        return transactionTDG.getTransactionForAllCriteria(vehicleLicencePlate,clientLicenseNumber,status,bookingFrom,bookingTill,transactionBy,criteria);
    }

    public ArrayList<Transaction> sortByStatusService(ArrayList<Transaction> list) {

        return transactionTDG.sortByStatus(list);
    }

}
