package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HotelDeleteOrUpdate extends AppCompatActivity {

    Intent intent;
    EditText txtHotelID,txtHotelName, txtHotelAddr, txtHotelPhone, txtHotelEmail, txtHotelStar, txtHotelDesc;
    Button buttonUpdate, buttonDelete;
    DatabaseReference dbRef;
    Hotel hotelObj;
    String district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_delete_or_update);
        this.intent = getIntent();

        txtHotelID =  findViewById(R.id.hotelIDUpdate_editTxt);
        txtHotelAddr = findViewById(R.id.hotelAddressUpdate_editTxt);
        txtHotelName = findViewById(R.id.hotelNameUpdate_editTxt);
        txtHotelPhone = findViewById(R.id.hotelContactUpdate_editTxt);
        txtHotelStar = findViewById(R.id.hotelStarUpdate_editTxt);
        txtHotelDesc = findViewById(R.id.hotelDescriptionUpdate_editTxt);
        txtHotelEmail = findViewById(R.id.hotelEmailUpdate_editTxt);

        buttonUpdate = (Button) findViewById(R.id.update_btn);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHotel();
            }
        });

        txtHotelID.setText(intent.getStringExtra("hid"));
        txtHotelAddr.setText(intent.getStringExtra("hadd"));
        txtHotelName.setText(intent.getStringExtra("hname"));
        txtHotelPhone.setText(intent.getStringExtra("hcont"));
        txtHotelStar.setText(intent.getStringExtra("hstar"));
        txtHotelDesc.setText(intent.getStringExtra("hdesc"));
        txtHotelEmail.setText(intent.getStringExtra("hemail"));

        district = intent.getStringExtra("hdist");



    }

    public void updateHotel(){
        dbRef = FirebaseDatabase.getInstance().getReference().child(district).child("ClientHotel");
        hotelObj = new Hotel();

        try {
            if(TextUtils.isEmpty(txtHotelID.toString()))
                Toast.makeText(getApplicationContext(), "Please enter an ID [hotelnamewithoutspaces]", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelName.toString()))
                Toast.makeText(getApplicationContext(), "Please enter Hotel Name", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelAddr.toString()))
                Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelEmail.toString()))
                Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelDesc.toString()))
                Toast.makeText(getApplicationContext(), "Please enter a description", Toast.LENGTH_SHORT).show();
            else{
                hotelObj.setHotelId(txtHotelID.getText().toString().trim());
                hotelObj.setHotelName(txtHotelName.getText().toString().trim());
                hotelObj.setHotelAddress(txtHotelAddr.getText().toString().trim());
                hotelObj.setHotelContactNumber(Integer.parseInt(txtHotelPhone.getText().toString().trim()));
                hotelObj.setHotelEmailAddress(txtHotelEmail.getText().toString().trim());
                hotelObj.setHotelStarRating(Integer.parseInt(txtHotelStar.getText().toString().trim()));
                hotelObj.setHotelDescription(txtHotelDesc.getText().toString().trim());
                String path = txtHotelID.getText().toString();
                dbRef.child(path).setValue(hotelObj);
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();
        }

    }
}
