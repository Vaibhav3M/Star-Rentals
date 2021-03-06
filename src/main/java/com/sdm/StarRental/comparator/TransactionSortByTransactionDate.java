package com.sdm.StarRental.comparator;

import com.sdm.StarRental.model.Transaction;

import java.util.Comparator;

public class TransactionSortByTransactionDate implements Comparator<Transaction>{

	public int compare(Transaction t1, Transaction t2)
    {
        return t1.getTimeStamp().replace("-", "").compareTo(t2.getTimeStamp().replace("-", ""));
    }	

}
