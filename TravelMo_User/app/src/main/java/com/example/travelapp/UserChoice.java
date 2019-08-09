package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserChoice extends AppCompatActivity {

    Button hotel;
    Button guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);

        hotel = findViewById(R.id.hotel);
        guide = findViewById(R.id.guide);
    }
    @Override
    protected void onResume() {

        super.onResume();
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserChoice.this,HotelUser.class);
                startActivity(intent);
            }
        });

        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserChoice.this,GuideUser.class);
                startActivity(intent);
            }
        });
    }
}
