package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class hotel_del_update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_del_update);
    }
    public void hdelete(View v) {
        Intent intent = new Intent(this, hotel_del_update.class);
        startActivity(intent);

    }

    public void hupdate(View v) {
        Intent intent = new Intent(this, add_hotel.class);
        startActivity(intent);
    }


   
}
