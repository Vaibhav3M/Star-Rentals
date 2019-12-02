package com.sdm.StarRental.constants;

public class Constants {

    // Minimum number of CUD needed to UOW to commit
    public final static int UOW_Size_Threshold = 3;

    //Minimum amount of time in milliseconds UOW will wait before commit
    public final static int UOW_Time_Threshold = 20000;
}
