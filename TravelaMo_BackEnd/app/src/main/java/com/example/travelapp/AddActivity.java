package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    Spinner spinVehicle;
    EditText txtName,txtEmail,txtAge,txtCon,txtDes;
    Button guideAdd;
    GuideModel guide;
    DatabaseReference dbRef;


    private void clearControls(){
        txtName.setText("");
        txtAge.setText("");
        txtCon.setText("");
        txtDes.setText("");
        txtEmail.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spinVehicle = (Spinner) findViewById(R.id.spinnerVehicle);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtDes = findViewById(R.id.txtDes);
        txtCon = findViewById(R.id.txtCon);
        txtAge = findViewById(R.id.txtAge);

        guideAdd = findViewById(R.id.guideAdd);
        guide = new GuideModel();



        guideAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("kandy").child("ClientGuide");
                try{

                    if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Name",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtAge.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Age",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtCon.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Contact Number",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDes.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Description",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText()))
                        Toast.makeText(getApplicationContext(),"Please enter an Email",Toast.LENGTH_SHORT).show();
                    else{
                        guide.setTxtName(txtName.getText().toString().trim());
                        guide.setTxtAge(txtAge.getText().toString().trim());
                        guide.setTxtCon(Integer.parseInt(txtCon.getText().toString().trim()));
                        guide.setTxtEmail(txtEmail.getText().toString().trim());
                        guide.setTxtDes(txtDes.getText().toString().trim());


                        dbRef.child(txtName.getText().toString()).setValue(guide);

                        Toast.makeText(getApplicationContext(),"Guide Added Successfully",Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent intent = new Intent(AddActivity.this,DelUpdateActivity.class);
                        startActivity(intent);
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

                }

            }
        });






        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Hiace");
        categories.add("Maco-Polo");
        categories.add("KDH");
        categories.add("Vanatte");
        categories.add("Dolphin");
        categories.add("Leyland Bus");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinVehicle.setAdapter(dataAdapter);
    }





}