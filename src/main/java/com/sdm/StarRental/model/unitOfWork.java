package com.sdm.StarRental.model;

import com.sdm.StarRental.Enum.unitOfWorkAction;

public class unitOfWork<E> {

	public unitOfWork() {
		// TODO Auto-generated constructor stub
		
	}
	
	private unitOfWorkAction action;
	private E e;
	
	
	public unitOfWork(unitOfWorkAction action, E e) {
		// TODO Auto-generated constructor stub
		this.action=action;
		this.e =e;
	}


	public unitOfWorkAction getAction() {
		return action;
	}


	public void setAction(unitOfWorkAction action) {
		this.action = action;
	}


	public E getE() {
		return e;
	}


	public void setE(E e) {
		this.e = e;
	}
	
	
	

}
