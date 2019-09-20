package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddHotelGuide extends AppCompatActivity {

    Intent intentget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel_guide);
        this.intentget = getIntent();
    }

    public void guide(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("district",this.intentget.getStringExtra("district") );
        startActivity(intent);
    }

    public void hotel(View v) {
        Intent intent = new Intent(this, add_hotel.class);
        intent.putExtra("district",this.intentget.getStringExtra("district") );
        startActivity(intent);
    }

}
