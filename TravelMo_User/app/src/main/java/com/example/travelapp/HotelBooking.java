package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HotelBooking extends AppCompatActivity {

    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking);

        confirm = findViewById(R.id.save);
    }
    @Override
    protected void onResume(){
        super.onResume();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelBooking.this,HotelConfirmBooking.class);
                startActivity(intent);
            }
        });
    }

}

