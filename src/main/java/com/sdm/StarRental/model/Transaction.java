package com.sdm.StarRental.model;

/**
 * The type Transaction.
 */
public class Transaction {

    private int transactionID;

    private String transactionType;

    private String vehicleLicensePlate;

    private String clientLicenseNumber;

    private String status;

    private String timeStamp;

    private String bookingFrom;

    private String bookingTill;

    private String transactionBy;

    /**
     * Instantiates a new Transaction.
     */
    public Transaction() {
    }

    /**
     * Instantiates a new Transaction.
     *
     * @param transactionID       the transaction id
     * @param transactionType     the type of transaction
     * @param vehicleLicensePlate the vehicle licence plate
     * @param clientLicenseNumber the client license number
     * @param status              the status
     * @param timeStamp           the time stamp
     * @param bookingFrom         the booking from
     * @param bookingTill         the booking till
     * @param transactionBy       the transaction by
     */
    public Transaction(int transactionID, String transactionType,String vehicleLicensePlate, String clientLicenseNumber, String status, String timeStamp, String bookingFrom, String bookingTill, String transactionBy) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.vehicleLicensePlate = vehicleLicensePlate;
        this.clientLicenseNumber = clientLicenseNumber;
        this.status = status;
        this.timeStamp = timeStamp;
        this.bookingFrom = bookingFrom;
        this.bookingTill = bookingTill;
        this.transactionBy = transactionBy;
    }

    /**
     * Gets transaction id.
     *
     * @return the transaction id
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * Sets transaction id.
     *
     * @param transactionID the transaction id
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }



    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets vehicle licence plate.
     *
     * @return the vehicle licence plate
     */
    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    /**
     * Sets vehicle licence plate.
     *
     * @param vehicleLicensePlate the vehicle licence plate
     */
    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    /**
     * Gets client license number.
     *
     * @return the client license number
     */
    public String getClientLicenseNumber() {
        return clientLicenseNumber;
    }

    /**
     * Sets client license number.
     *
     * @param clientLicenseNumber the client license number
     */
    public void setClientLicenseNumber(String clientLicenseNumber) {
        this.clientLicenseNumber = clientLicenseNumber;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets booking from.
     *
     * @return the booking from
     */
    public String getBookingFrom() {
        return bookingFrom;
    }

    /**
     * Sets booking from.
     *
     * @param bookingFrom the booking from
     */
    public void setBookingFrom(String bookingFrom) {
        this.bookingFrom = bookingFrom;
    }

    /**
     * Gets booking till.
     *
     * @return the booking till
     */
    public String getBookingTill() {
        return bookingTill;
    }

    /**
     * Sets booking till.
     *
     * @param bookingTill the booking till
     */
    public void setBookingTill(String bookingTill) {
        this.bookingTill = bookingTill;
    }

    /**
     * Gets transaction by.
     *
     * @return the transaction by
     */
    public String getTransactionBy() {
        return transactionBy;
    }

    /**
     * Sets transaction by.
     *
     * @param transactionBy the transaction by
     */
    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }
}
