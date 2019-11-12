package com.sdm.StarRental.model;

/**
 * The type Client.
 */
public class Client {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String licenseNumber;

    private String licenseExpiryDate;

    /**
     * Instantiates a new Client.
     */
    public Client() {
    }

    /**
     * Instantiates a new Client.
     *
     * @param firstName         the first name
     * @param lastName          the last name
     * @param phoneNumber       the phone number
     * @param licenseNumber     the license number
     * @param licenseExpiryDate the license expiry date
     */
    public Client(String firstName, String lastName, String phoneNumber, String licenseNumber, String licenseExpiryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
    }


    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets license number.
     *
     * @return the license number
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets license number.
     *
     * @param licenseNumber the license number
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Gets license expiry date.
     *
     * @return the license expiry date
     */
    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    /**
     * Sets license expiry date.
     *
     * @param licenseExpiryDate the license expiry date
     */
    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }
}
