package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddHotelGuide extends AppCompatActivity {

    String district;
    Intent getIntent;

    static String dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate

                (savedInstanceState);
        setContentView(R.layout.activity_add_hotel_guide);
        this.getIntent = getIntent();
        district = getIntent.getStringExtra("district");
        dist = district;
    }

    public void viewguide(View v) {
        Intent intent = new Intent(this, ViewGuide.class);
        intent.putExtra("district" , district);
        startActivity(intent);
    }

    public void addhotel(View v) {
        Intent intent = new Intent(this, add_hotel.class);
        intent.putExtra("district" , district);
        startActivity(intent);
    }

    public void addguide(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("district" , district);
        startActivity(intent);
    }

    public void viewhotel(View v) {
        Intent intent = new Intent(this, hotel_del_update.class);
        intent.putExtra("district" , district);
        startActivity(intent);
    }

}
