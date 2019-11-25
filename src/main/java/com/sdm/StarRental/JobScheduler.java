package com.sdm.StarRental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;

@Component
public class JobScheduler {

	private VehicleUnitOfWork vehicleUnitOfWork;
	
	public JobScheduler() {
		vehicleUnitOfWork = VehicleUnitOfWork.getInstance();
	}
	
	
	//fixedRate is in milliseconds
	@Scheduled(fixedRate = 10000)
	public void scheduleTaskWithFixedRate() { 
		vehicleUnitOfWork.scheduleCommit();;
	}
}
