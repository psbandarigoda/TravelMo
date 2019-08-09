package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.ProtectionDomain;

public class HotelUserEdit extends AppCompatActivity {

    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_user_edit);

        save = findViewById(R.id.save);
    }

    @Override
    protected void onResume(){
        super.onResume();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelUserEdit.this,HotelConfirmBooking.class);
                startActivity(intent);
            }
        });
    }
}
