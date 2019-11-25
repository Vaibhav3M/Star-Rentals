package com.sdm.StarRental.unitOfWork;

import com.sdm.StarRental.Enum.unitOfWorkAction;
import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.unitOfWork;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public class TransactionUnitOfWork implements IUnitOfWork<Transaction,String> {

	//TODO: assign unique values
	@Value("{unit-of-work.batch-no}")
	private String unitOfWorkBatchNo;
	private HashMap<String, unitOfWork<Transaction>> data = new HashMap();

	private static TransactionUnitOfWork transactionUnitOfWork;


	TransactionDM transactionDM;

	private TransactionUnitOfWork(){
		this.transactionDM = new TransactionDM();

	}
	private TransactionUnitOfWork(TransactionDM transactionDM) {

		this.transactionDM = transactionDM;
	}

	public static TransactionUnitOfWork getInstance(){

		if(transactionUnitOfWork == null){
			transactionUnitOfWork = new TransactionUnitOfWork();
		}

		return transactionUnitOfWork;
	}




	@Override
	public void  create(Transaction element) {
		data.put(element.getVehicleLicensePlate()+element.getClientLicenseNumber(), mapToObject(element,unitOfWorkAction.CREATE));
		commit();
	}

	@Override
	public void update(Transaction element) {
//		if (data.containsKey(element.getTransactionID())){
//
//			data.put(element.getTransactionID(), mapToObject(element,unitOfWorkAction.UPDATE));
//		}
//		commit();
	}

	@Override
	public void delete(String key) {

//		if(data.containsKey(key)) {
//			Transaction catalog = new Transaction();
//			//catalog.setTransactionID(key);
//			data.put(key, mapToObject(catalog, unitOfWork.DELETE));
//		}
//		commit();
	}

	@Override
	public boolean isDirty(String key) {
		return false;
	}

	@Override
	public void commit() {

		if (data.size() == 3) {
			data.forEach((key, element) -> {
				Transaction transaction = element.getE();
				commitCreateTransaction(transaction);

			});
			data = new HashMap<String, unitOfWork<Transaction>>();
		}

	}

	private void commitCreateTransaction(Transaction transaction) {
		try {
			transactionDM.createTransactionService(transaction.getVehicleLicensePlate(),transaction.getTransactionType(),transaction.getClientLicenseNumber(),transaction.getStatus(),transaction.getTimeStamp(),transaction.getBookingFrom(),transaction.getBookingTill(),transaction.getTransactionBy());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private unitOfWork<Transaction> mapToObject(Transaction element, unitOfWorkAction action) {
		unitOfWork<Transaction> object = new unitOfWork<Transaction>(action, element);
		return object;

	}

}
