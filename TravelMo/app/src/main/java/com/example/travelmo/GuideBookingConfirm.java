package com.example.travelmo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class GuideBookingConfirm extends AppCompatActivity {

    Button editDtl;
    Button confirmDtl;
    Button cancel;
    TextView txt_name,txt_mail,txt_days,txt_vehicle,txt_phoneNumber;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_booking_confirm);

        editDtl = findViewById(R.id.btnedit2);
        confirmDtl = findViewById(R.id.next);
        cancel = findViewById(R.id.cancel112);

        txt_name=findViewById(R.id.textViewName);
        txt_mail=findViewById(R.id.textViewEmail);
        txt_days=findViewById(R.id.textViewDays);
        txt_vehicle=findViewById(R.id.textViewVehicle);
        txt_phoneNumber=findViewById(R.id.textViewPhoneNum);

        final Intent intent = getIntent();
        String message = intent.getStringExtra("user");



                dbRef = FirebaseDatabase.getInstance().getReference().child("GuideReceiveUser").child("0001");

                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            System.out.println("has Children");
                            //System.out.println(dataSnapshot.child("id").getValue().toString());
                            txt_name.setText(dataSnapshot.child("name").getValue().toString());
                            txt_mail.setText(dataSnapshot.child("email").getValue().toString());
                            txt_days.setText(dataSnapshot.child("days").getValue().toString());
                            txt_vehicle.setText(dataSnapshot.child("vehicle").getValue().toString());
                            txt_phoneNumber.setText(dataSnapshot.child("phoneNumber").getValue().toString());

                        }else{
                            System.out.println("no Children");
                            Toast.makeText(getApplicationContext(),"No Values to retrive",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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

//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(GuideBookingConfirm.this,GuideUsers.class);
//                startActivity(intent);
//            }
//        });
    }


}
