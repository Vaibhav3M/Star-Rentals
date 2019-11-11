package model;

public class Vehicle {

    private String type;

    private int year;

    private String model;

    private String color;

    private String vehicleLicencePlate;

    private String status;

    private String image;

    public Vehicle() {

    }

    public Vehicle(String type, int year, String model, String color, String vehicleLicencePlate, String status, String image) {
        this.type = type;
        this.year = year;
        this.model = model;
        this.color = color;
        this.vehicleLicencePlate = vehicleLicencePlate;
        this.status = status;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleLicencePlate() {
        return vehicleLicencePlate;
    }

    public void setVehicleLicencePlate(String vehicleLicencePlate) {
        this.vehicleLicencePlate = vehicleLicencePlate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
