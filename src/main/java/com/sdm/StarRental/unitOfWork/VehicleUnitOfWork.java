package com.sdm.StarRental.unitOfWork;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class VehicleUnitOfWork implements IUnitOfWork {

	public VehicleUnitOfWork() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Object element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDirty(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

}
