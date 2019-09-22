package com.example.travelapp;

public class Hotel {

    private String hotelId;
    private String hotelName;
    private String hotelAddress;
    private String hotelEmailAddress;
    private int hotelContactNumber;
    private int hotelStarRating;
    private String hotelDescription;
    private String hotelimage;


    public Hotel() {
    }

    public Hotel(String hotelId, String hotelName, String hotelAddress, String hotelEmailAddress, int hotelContactNumber, int hotelStarRating, String hotelDescription, String hotelimage) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelEmailAddress = hotelEmailAddress;
        this.hotelContactNumber = hotelContactNumber;
        this.hotelStarRating = hotelStarRating;
        this.hotelDescription = hotelDescription;
        this.hotelimage = hotelimage;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelEmailAddress() {
        return hotelEmailAddress;
    }

    public void setHotelEmailAddress(String hotelEmailAddress) {
        this.hotelEmailAddress = hotelEmailAddress;
    }

    public int getHotelContactNumber() {
        return hotelContactNumber;
    }

    public void setHotelContactNumber(int hotelContactNumber) {
        this.hotelContactNumber = hotelContactNumber;
    }

    public int getHotelStarRating() {
        return hotelStarRating;
    }

    public void setHotelStarRating(int hotelStarRating) {
        this.hotelStarRating = hotelStarRating;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelimage() {
        return hotelimage;
    }

    public void setHotelimage(String hotelimage) {
        this.hotelimage = hotelimage;
    }
}
