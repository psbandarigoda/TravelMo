package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HotelConfirmBooking extends AppCompatActivity {

    Button edit;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_confirm_booking);

        edit = findViewById(R.id.edit2);
        confirm = findViewById(R.id.confirm2);
    }

    @Override
    protected void onResume(){
        super.onResume();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelConfirmBooking.this,HotelUserEdit.class);
                startActivity(intent);

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelConfirmBooking.this,HotelUser.class);
                startActivity(intent);
            }
        });
    }
}
