package com.example.travelmo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HotelBookingDetailEdit extends AppCompatActivity {

    Button save,cancel;
String place,value;
    EditText uName,uEmail,uday,uRoom,uPhone;
    DatabaseReference dref;
    UserDetailForHotelReserv hotel;
    String chiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking_detail_edit);

        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cans);

        uName = findViewById(R.id.uname);

        uRoom = findViewById(R.id.urooms);
        uday = findViewById(R.id.udays);
        uPhone = findViewById(R.id.uphone);
        hotel = new UserDetailForHotelReserv();


        Intent id = getIntent();
         place = id.getStringExtra("place");
         value = id.getStringExtra("id");
    }

    @Override
    protected void onResume(){
        super.onResume();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dref = FirebaseDatabase.getInstance().getReference().child(place).child("HotelUser");
                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(value)){
                            try {
                                hotel.setName(uName.getText().toString().trim());
                                hotel.setEmail(value);
                                hotel.setRooms(uRoom.getText().toString().trim());
                                hotel.setDays(uday.getText().toString().trim());
                                hotel.setPhone(Integer.parseInt(uPhone.getText().toString().trim()));

                                dref = FirebaseDatabase.getInstance().getReference().child(place).child("HotelUser").child(value) ;
                                dref.setValue(hotel);
                                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();

                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Contact No",Toast.LENGTH_LONG).show();

                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HotelBookingDetailEdit.this,HotelUsers.class);
                startActivity(intent);
            }
        });
    }


}
