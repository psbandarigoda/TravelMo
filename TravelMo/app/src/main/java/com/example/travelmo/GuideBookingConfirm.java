package com.example.travelmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuideBookingConfirm extends AppCompatActivity {

    Button editDtl;
    Button confirmDtl;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_booking_confirm);

        editDtl = findViewById(R.id.btnedit2);
        confirmDtl = findViewById(R.id.btnconfirm2);
        cancel = findViewById(R.id.cancel112);
    }

    @Override
    protected void onResume(){
        super.onResume();

        editDtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideBookingConfirm.this,GuideBookingDetailEdit.class);
                startActivity(intent);

            }
        });

        confirmDtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideBookingConfirm.this,GuideUsers.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideBookingConfirm.this,GuideUsers.class);
                startActivity(intent);
            }
        });
    }
}
