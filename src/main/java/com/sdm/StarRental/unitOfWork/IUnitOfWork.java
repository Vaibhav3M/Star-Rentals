package com.sdm.StarRental.unitOfWork;

public interface IUnitOfWork<E, K> {
	
	void create(E element);
	
	void update(E element);
	
	void delete(K key);
	
	boolean isDirty(K key);
	
	void commit();
	

}
