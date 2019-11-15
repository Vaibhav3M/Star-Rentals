package com.sdm.StarRental.model;

/**
 * The type User.
 */
public class User {

    private String userType;

    private String userName;

    private String password;
    
   


    /**
     * Instantiates a new User.
     */
    public User(){

    }

    /**
     * Instantiates a new User.
     *
     * @param userType the user type
     * @param userName the user name
     * @param password the password
     */
    public User(String userType, String userName, String password) {
        this.userType = userType;
        this.userName = userName;
        this.password = password;
        

    }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
   

}
