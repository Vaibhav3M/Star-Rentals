package model;

/**
 * The type Vehicle.
 */
public class Vehicle {

    private String type;

    private int year;

    private String model;

    private String color;

    private String vehicleLicencePlate;

    private String status;

    private String image;

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
     * @param model               the model
     * @param color               the color
     * @param vehicleLicencePlate the vehicle licence plate number
     * @param status              the status of the vehicle
     * @param image               the image
     */
    public Vehicle(String type, int year, String model, String color, String vehicleLicencePlate, String status, String image) {
        this.type = type;
        this.year = year;
        this.model = model;
        this.color = color;
        this.vehicleLicencePlate = vehicleLicencePlate;
        this.status = status;
        this.image = image;
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
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
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
    public String getVehicleLicencePlate() {
        return vehicleLicencePlate;
    }

    /**
     * Sets vehicle licence plate number.
     *
     * @param vehicleLicencePlate the vehicle licence plate number
     */
    public void setVehicleLicencePlate(String vehicleLicencePlate) {
        this.vehicleLicencePlate = vehicleLicencePlate;
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
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }
}
