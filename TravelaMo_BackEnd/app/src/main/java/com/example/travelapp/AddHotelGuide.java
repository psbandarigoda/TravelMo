package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddHotelGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel_guide);
    }

    public void guide(View v) {
        Intent intent = new Intent(this, ViewGuide.class);
        startActivity(intent);
    }

    public void hotel(View v) {
        Intent intent = new Intent(this, add_hotel.class);
        startActivity(intent);
    }

}
