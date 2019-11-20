package com.sdm.StarRental.exceptions;

public class SessionUnAuthorizedException extends Exception{


    public SessionUnAuthorizedException(String errorMessage){

        super(errorMessage);
    }

}
