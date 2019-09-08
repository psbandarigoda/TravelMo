package com.example.travelmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HotelBookingDetailEdit extends AppCompatActivity {

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking_detail_edit);

        save = findViewById(R.id.save);
    }

    @Override
    protected void onResume(){
        super.onResume();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelBookingDetailEdit.this,HotelBookingConfirm.class);
                startActivity(intent);
            }
        });
    }
}
