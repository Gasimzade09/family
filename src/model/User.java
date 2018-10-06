package model;

import model.Person;

public class User {
    private String userName;
    private String password;
    private int id;
    private static int nextId;
    private int pinCode;        // for restore password
    private String gender;      // m or w

    static {
        nextId = 1;
    }

    {
        id = nextId;
        nextId++;
    }



    // Setters
    public void setUserName(String userName) { this.userName = userName; }

    public void setPassword(String password) { this.password = password; }

    public void setGender(String gender) { this.gender = gender; }

    public void setId(int id) { this.id = id; }

    public void setPinCode(int pinCode) { this.pinCode = pinCode; }

    // Getters
    public String getUserName() { return userName; }

    public String getPassword() { return password; }

    public String getGender() { return gender; }

    public int getId() { return id; }

    public static int getNextId() { return nextId; }

    public int getPinCode() { return pinCode; }

    // Constructor
    public User(String userName, String password, String gender, int id, int pinCode) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.id = id;
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "model.User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
    }
}