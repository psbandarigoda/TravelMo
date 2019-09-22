package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_hotel extends AppCompatActivity {

    EditText txtHotelID,txtHotelName, txtHotelAddr, txtHotelPhone, txtHotelEmail, txtHotelStar, txtHotelDesc;
    Button btnHotelAdd;

    Intent intentget;
    String district;
    String hotelidextra;
    Hotel hotelObj;
    DatabaseReference dbRef;

    private void clearControls(){
        txtHotelID.setText("");
        txtHotelName.setText("");
        txtHotelAddr.setText("");
        txtHotelPhone.setText("");
        txtHotelEmail.setText("");
        txtHotelStar.setText("");
        txtHotelDesc.setText("");
        btnHotelAdd.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

         this.intentget = getIntent();
        district =  intentget.getStringExtra("district");

        txtHotelID = findViewById(R.id.hotelid_editTxt);
        txtHotelName = findViewById(R.id.hotelname_editTxt);
        txtHotelAddr = findViewById(R.id.hotelAddress_editTxt);
        txtHotelPhone = findViewById(R.id.hotelPhone_editTxt);
        txtHotelEmail = findViewById(R.id.hotelEmail_editTxt);
        txtHotelStar = findViewById(R.id.hotelStarRating_editTxt);
        txtHotelDesc = findViewById(R.id.hotelDescription_editTxt);
        btnHotelAdd = findViewById(R.id.addHotel_btn);

        btnHotelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHotel();
            }
        });



    }

    public void add_hotel1(View v) {
        Intent intent = new Intent(this, hotel_del_update.class);
        intent.putExtra("district", district );
        intent.putExtra("hotel", hotelidextra );
        startActivity(intent);
    }




    public void addHotel(){
        dbRef = FirebaseDatabase.getInstance().getReference().child(district).child("Hotels");
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
                    hotelidextra = txtHotelID.getText().toString();
                }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();
        }


    }
}
