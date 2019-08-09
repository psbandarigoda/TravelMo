package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuideConfirmBooking extends AppCompatActivity {

    Button editDtl;
    Button confirmDtl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_confirm_booking);

        editDtl = findViewById(R.id.edit2);
        confirmDtl = findViewById(R.id.confirm2);
    }

    @Override
    protected void onResume(){
        super.onResume();

        editDtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideConfirmBooking.this,GuideBookingDtlEdit.class);
                startActivity(intent);

            }
        });

        confirmDtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideConfirmBooking.this,GuideUser.class);
                startActivity(intent);
            }
        });
    }
}
