package com.example.travelmo;

public class UserDetailForHotelReserv {

    String name;
    String email;
    String days;
    String rooms;
    int phone;

    public UserDetailForHotelReserv() {
    }

    public UserDetailForHotelReserv(String name, String email, String days, String rooms, int phone) {
        this.name = name;
        this.email = email;
        this.days = days;
        this.rooms = rooms;
        this.phone = phone;
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

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
