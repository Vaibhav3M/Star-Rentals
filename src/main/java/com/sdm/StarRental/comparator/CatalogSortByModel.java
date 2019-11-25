package com.sdm.StarRental.comparator;

import java.util.Comparator;

import com.sdm.StarRental.model.Vehicle;

public class CatalogSortByModel implements Comparator<Vehicle> {
	// TODO Auto-generated constructor stub
	 public int compare(Vehicle a, Vehicle b) 
	    { 
	        return a.getModel().compareTo( b.getModel()); 
	    } 
}
