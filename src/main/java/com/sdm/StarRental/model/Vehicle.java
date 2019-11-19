package com.sdm.StarRental.model;

/**
 * The type Vehicle.
 */
public class Vehicle {

    private String type;

    private int year;

    private String model;

    private String color;

    private String vehicleLicensePlate;

    private String status;

    private String make;

    /**
     * Instantiates a new Vehicle.
     */
    public Vehicle() {

    }

    /**
     * Instantiates a new Vehicle.
     *
     * @param type                the vehicle type
     * @param year                the manufacturing year
     * @param model               the com.sdm.StarRental.model
     * @param color               the color
     * @param vehicleLicensePlate the vehicle licence plate number
     * @param status              the status of the vehicle
     * @param make               the make
     */
    public Vehicle(String type, int year, String model, String color, String vehicleLicensePlate, String status, String make) {

        this.type = type;
        this.year = year;
        this.model = model;
        this.color = color;
        this.vehicleLicensePlate = vehicleLicensePlate;
        this.status = status;
        this.make = make;
    }

    /**
     * Gets vehicle type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets vehicle type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets manufacturing year.
     *
     * @return the manufacturing year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets manufacturing year.
     *
     * @param year the manufacturing year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets com.sdm.StarRental.model.
     *
     * @return the com.sdm.StarRental.model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets com.sdm.StarRental.model.
     *
     * @param model the com.sdm.StarRental.model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets vehicle licence plate number.
     *
     * @return the vehicle licence plate number
     */
    public String getvehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    /**
     * Sets vehicle licence plate number.
     *
     * @param vehicleLicensePlate the vehicle licence plate number
     */
    public void setvehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    /**
     * Gets status of the vehicle.
     *
     * @return the status of the vehicle
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status of the vehicle.
     *
     * @param status the status of the vehicle
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets make.
     *
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets make.
     *
     * @param make of vehicle
     */
    public void setMake(String make) {
        this.make = make;
    }
}