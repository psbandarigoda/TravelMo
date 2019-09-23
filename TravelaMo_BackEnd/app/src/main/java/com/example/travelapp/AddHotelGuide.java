package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddHotelGuide extends AppCompatActivity {

<<<<<<< HEAD
    String district;
    Intent getIntent;

    static String dist;
=======
    Intent intentget;
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate

                (savedInstanceState);
        setContentView(R.layout.activity_add_hotel_guide);
<<<<<<< HEAD
        this.getIntent = getIntent();
        district = getIntent.getStringExtra("district");
        dist = district;
    }

    public void viewguide(View v) {
        Intent intent = new Intent(this, ViewGuide.class);
        intent.putExtra("district" , district);
=======
        this.intentget = getIntent();
    }

    public void guide(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("district",this.intentget.getStringExtra("district") );
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
        startActivity(intent);
    }

    public void addhotel(View v) {
        Intent intent = new Intent(this, add_hotel.class);
<<<<<<< HEAD
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
=======
        intent.putExtra("district",this.intentget.getStringExtra("district") );
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
        startActivity(intent);
    }

}
