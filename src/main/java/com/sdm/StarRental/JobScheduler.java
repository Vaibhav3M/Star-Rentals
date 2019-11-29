package com.sdm.StarRental;

import com.sdm.StarRental.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sdm.StarRental.dataMapper.ClientDM;
import com.sdm.StarRental.unitOfWork.ClientUnitOfWork;
import com.sdm.StarRental.unitOfWork.TransactionUnitOfWork;
import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;

@Component
public class JobScheduler {

	private VehicleUnitOfWork vehicleUnitOfWork;
	private ClientUnitOfWork clienUnitOfWork;
	private TransactionUnitOfWork transactionUnitOfWork;
	
	public JobScheduler() {
		vehicleUnitOfWork = VehicleUnitOfWork.getInstance();
		ClientDM clientDM = new ClientDM();
		clienUnitOfWork = ClientUnitOfWork.getInstance(clientDM);
		transactionUnitOfWork = TransactionUnitOfWork.getInstance();
		
	}
	
	
	//fixedRate is in milliseconds
	@Scheduled(fixedRate = Constants.UOW_Time_Threshold)
	public void scheduleTaskWithFixedRate() { 
		vehicleUnitOfWork.scheduleCommit();
		clienUnitOfWork.scheduleCommit();
		transactionUnitOfWork.scheduleCommit();
		
	}
}
