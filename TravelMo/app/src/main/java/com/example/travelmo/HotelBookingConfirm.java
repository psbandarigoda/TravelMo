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
    TextView name11, email, phone, room, day;
    DatabaseReference dbref;
    String value, mai, place;
    ArrayList hot;
    DatabaseReference dref;

    ArrayList<UserDetailForHotelReserv> products;
    DatabaseReference productsRef;
    DatabaseReference mDatabase;


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
        place = id.getStringExtra("place");

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child(place).child("HotelUser").child(value);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    name11.setText(dataSnapshot.child("name").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    day.setText(dataSnapshot.child("days").getValue().toString());
                    room.setText(dataSnapshot.child("rooms").getValue().toString());
//                     phone.setText(dataSnapshot.child("phone").getValue().toString());

                    Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendMail();
//                Intent intent = new Intent(HotelBookingConfirm.this, HotelUsers.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelBookingConfirm.this, HotelBookingDetailEdit.class);
                intent.putExtra("id", value);
                intent.putExtra("place", place);
                startActivity(intent);

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelBookingConfirm.this, HotelUsers.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dref = FirebaseDatabase.getInstance().getReference().child(place).child("HotelUser");
                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(value)) {
                            dref = FirebaseDatabase.getInstance().getReference().child(place).child("HotelUser").child(value);
                            dref.removeValue();
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Intent intent = new Intent(HotelBookingConfirm.this, HotelUsers.class);
                startActivity(intent);
            }
        });
    }


}
