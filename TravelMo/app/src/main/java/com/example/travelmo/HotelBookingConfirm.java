package com.example.travelmo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
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
    TextView txt_name, txt_mail, txt_days, txt_vehicle, txt_phoneNumber;
    DatabaseReference dbRef;
    String value, place, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_booking_confirm);

        editDtl = findViewById(R.id.btnedit2);
        confirmDtl = findViewById(R.id.confirm);
        cancel = findViewById(R.id.cancel112);

        txt_name = findViewById(R.id.textViewName);
        txt_mail = findViewById(R.id.textViewEmail);
        txt_days = findViewById(R.id.textViewDays);
        txt_vehicle = findViewById(R.id.textViewVehicle);
        txt_phoneNumber = findViewById(R.id.textViewPhoneNum);

        Intent id = getIntent();
        value = id.getStringExtra("userObject");
        place = id.getStringExtra("place");

        Intent emails = getIntent();
        email = emails.getStringExtra("email");


        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child(place).child("GuideReceiveUser").child(value);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {

                    txt_name.setText(dataSnapshot.child("name").getValue().toString());
                    txt_mail.setText(dataSnapshot.child("email").getValue().toString());
                    txt_days.setText(dataSnapshot.child("days").getValue().toString());
                    txt_vehicle.setText(dataSnapshot.child("vehicle").getValue().toString());
                    txt_phoneNumber.setText(dataSnapshot.child("phoneNumber").getValue().toString());

                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();

                } else {
                    System.out.println("no Children");
                    Toast.makeText(getApplicationContext(), "No Values to retrive", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        editDtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideBookingConfirm.this, GuideBookingDetailEdit.class);
                intent.putExtra("id", value);
                intent.putExtra("place", place);
                startActivity(intent);
            }
        });


        confirmDtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
                Toast.makeText(getApplicationContext(), "Email Sending", Toast.LENGTH_LONG).show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child(place).child("GuideReceiveUser");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(value)) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child(place).child("GuideReceiveUser").child(value);
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(GuideBookingConfirm.this, GuideUsers.class);
                startActivity(intent);
            }
        });
    }


    private void sendMail() {
        String recipientList = email;
        String[] recipient = recipientList.split(",");
        String subject = "TravelMo Guid Booking Service";
        String message = "TravelMo(pvt)ltd Guid Booking Service " +
                "\n\n I'm --CustomerName-- I want to Book --Count--days\n" +
                "my contact number is --ContactNumber-- Can you please inform to this number.\n\n" +
                "Best Regards.";


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an Email Method"));
    }

}
