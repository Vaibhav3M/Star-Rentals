package model;

public class Transaction {

    private int transactionID;

    private String vehicleLicencePlate;

    private String clientLicenseNumber;

    private String status;

    private String timeStamp;

    private String bookingFrom;

    private String bookingTill;

    private String transactionBy;

    public Transaction() {
    }

    public Transaction(int transactionID, String vehicleLicencePlate, String clientLicenseNumber, String status, String timeStamp, String bookingFrom, String bookingTill, String transactionBy) {
        this.transactionID = transactionID;
        this.vehicleLicencePlate = vehicleLicencePlate;
        this.clientLicenseNumber = clientLicenseNumber;
        this.status = status;
        this.timeStamp = timeStamp;
        this.bookingFrom = bookingFrom;
        this.bookingTill = bookingTill;
        this.transactionBy = transactionBy;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getVehicleLicencePlate() {
        return vehicleLicencePlate;
    }

    public void setVehicleLicencePlate(String vehicleLicencePlate) {
        this.vehicleLicencePlate = vehicleLicencePlate;
    }

    public String getClientLicenseNumber() {
        return clientLicenseNumber;
    }

    public void setClientLicenseNumber(String clientLicenseNumber) {
        this.clientLicenseNumber = clientLicenseNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBookingFrom() {
        return bookingFrom;
    }

    public void setBookingFrom(String bookingFrom) {
        this.bookingFrom = bookingFrom;
    }

    public String getBookingTill() {
        return bookingTill;
    }

    public void setBookingTill(String bookingTill) {
        this.bookingTill = bookingTill;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }
}
