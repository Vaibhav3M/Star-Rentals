package com.sdm.StarRental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sdm.StarRental.unitOfWork.VehicleUnitOfWork;

@Component
public class JobScheduler {

	private VehicleUnitOfWork vehicleUnitOfWork;
	
	//fixedRate is in milliseconds
	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		//System.out.println("Job Executed");
		//vehicleUnitOfWork.commit();
	}
}
