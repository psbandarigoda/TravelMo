package com.example.travelmo;

public class UserDetailForGuideReserv {

    String name;
    String email;
    String days;
    String vehicle;
    int phoneNumber;

    public UserDetailForGuideReserv() {
    }

    public UserDetailForGuideReserv(String name, String email, String days, String vehicle, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.days = days;
        this.vehicle = vehicle;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
