package com.example.travelmo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GuideBookingDetailEdit extends AppCompatActivity {

    Button saveEditDtl, confirm;
    String place, value;
    EditText uName, uEmail, uday, uRoom, uPhone;
    DatabaseReference dref;
    UserDetailForGuideReserv guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_booking_detail_edit);

        saveEditDtl = findViewById(R.id.save01);
        confirm = findViewById(R.id.confirm);
        confirm.setEnabled(false);
        confirm.setAlpha(0.5f);

        uName = findViewById(R.id.editTextName);
        //uEmail = findViewById(R.id.editTextEmail);
        uday = findViewById(R.id.editTextDays);
        uPhone = findViewById(R.id.editTextPhone);

        guide = new UserDetailForGuideReserv();

        Intent id = getIntent();
        place = id.getStringExtra("place");
        value = id.getStringExtra("id");

        Spinner spinner = (Spinner) findViewById(R.id.spinnerVehicle);

        List<String> categories = new ArrayList<String>();
        categories.add("Hiace");
        categories.add("maco-polo");
        categories.add("KHD");
        categories.add("Vanatte");
        categories.add("Dolphin");
        categories.add("Laylend Bus");
        ArrayAdapter<String> dataAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdaptor);
    }

    @Override
    protected void onResume() {
        super.onResume();

        saveEditDtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dref = FirebaseDatabase.getInstance().getReference().child(place).child("GuideReceiveUser");
                dref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(value)) {
                            try {
                                guide.setName(uName.getText().toString().trim());
                                guide.setEmail(value);
//                                guide.setRooms(uRoom.getText().toString().trim());
                                guide.setDays(uday.getText().toString().trim());
                                guide.setPhoneNumber(Integer.parseInt(uPhone.getText().toString().trim()));

                                dref = FirebaseDatabase.getInstance().getReference().child(place).child("GuideReceiveUser").child(value);
                                dref.setValue(guide);
                                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                                confirm.setEnabled(true);
                                confirm.setAlpha(1.0f);

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact No", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GuideBookingDetailEdit.this, GuideUsers.class);
                startActivity(intent);
            }
        });
    }
}
