package com.example.travelmo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HotelBookingConfirm extends AppCompatActivity {

    Button edit;
    Button confirm;
    Button cancel;
    TextView name11,email,phone,room,day;
    DatabaseReference dbref;
    String value;
    ArrayList hot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking_confirm);

        edit = findViewById(R.id.btnedit2);
        confirm = findViewById(R.id.next);
        cancel = findViewById(R.id.cancel);

        name11 = findViewById(R.id.name1);
        email = findViewById(R.id.email1);
        phone = findViewById(R.id.phone1);
        room = findViewById(R.id.room1);
        day = findViewById(R.id.days1);

        Intent id = getIntent();
         value = id.getStringExtra("userObject");


    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("HotelUser").child(value);
    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             UserDetailForHotelReserv hotel = dataSnapshot.getValue(UserDetailForHotelReserv.class);
            

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });



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
