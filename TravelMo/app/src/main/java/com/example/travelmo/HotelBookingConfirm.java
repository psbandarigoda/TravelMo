package com.example.travelmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HotelBookingConfirm extends AppCompatActivity {

    Button edit;
    Button confirm;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking_confirm);

        edit = findViewById(R.id.btnedit2);
        confirm = findViewById(R.id.btnconfirm2);
        cancel = findViewById(R.id.cancel);
    }

    @Override
    protected void onResume(){
        super.onResume();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelBookingConfirm.this,HotelBookingDetailEdit.class);
                startActivity(intent);

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelBookingConfirm.this,HotelUsers.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelBookingConfirm.this,HotelUsers.class);
                startActivity(intent);
            }
        });
    }
}
