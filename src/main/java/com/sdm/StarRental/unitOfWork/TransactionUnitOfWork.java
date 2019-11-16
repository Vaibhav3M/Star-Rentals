package com.sdm.StarRental.unitOfWork;

import com.sdm.StarRental.Enum.unitOfWorkAction;
import com.sdm.StarRental.dataMapper.TransactionDM;
import com.sdm.StarRental.model.Transaction;
import com.sdm.StarRental.model.unitOfWork;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

public class TransactionUnitOfWork implements IUnitOfWork<Transaction,String> {

	public TransactionUnitOfWork() { }

	TransactionDM transactionDM;

	//TODO: assign unique values
	@Value("{unit-of-work.batch-no}")
	private String unitOfWorkBatchNo;

	private HashMap<Integer, unitOfWork<Transaction>> data = new HashMap();


	@Override
	public void create(Transaction element) {
		data.put(element.getTransactionID(), mapToObject(element,unitOfWorkAction.CREATE));
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

		if (data.size() == 5) {
			data.forEach((key, element) -> {
				Transaction transaction = element.getE();
				commitCreateClient(transaction);

			});
			data = new HashMap<Integer, unitOfWork<Transaction>>();
		}

	}

	private void commitCreateClient(Transaction transaction) {
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
