package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    Spinner spinVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

<<<<<<< HEAD
        spinVehicle = (Spinner) findViewById(R.id.spinnerVehicle);
        txtName = (EditText)findViewById(R.id.txtName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtDes = (EditText)findViewById(R.id.txtDes);
        txtCon = (EditText)findViewById(R.id.txtCon);
        txtAge = (EditText)findViewById(R.id.txtAge);
        txtNic = (EditText)findViewById(R.id.txtNic);

        txtImage = (EditText)findViewById(R.id.txtImage);
        buttonChoose = (Button)findViewById(R.id.buttonChoose);
        image_view = (ImageView)findViewById(R.id.image_view);

        storageReference = FirebaseStorage.getInstance().getReference();
        dbRef = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
        guideAdd = (Button)findViewById(R.id.guideAdd);
        guide = new GuideModel();


        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonChoose) {
                    showFileChooser();
                } else if (view == guideAdd) {

                }else if (view == textViewShow){

                }
            }
        });



        guideAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child(AddHotelGuide.dist).child("ClientGuide");

                if(view == buttonChoose){
                    showFileChooser();
                }else if (view == guideAdd){
                    uploadFile();
                }else if (view == textViewShow){

                }
                try{

                    if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Name",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtNic.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a NIC",Toast.LENGTH_SHORT).show();
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
                        guide.setTxtNic(txtNic.getText().toString().trim());
                        guide.setTxtAge(txtAge.getText().toString().trim());
                        guide.setTxtCon(Integer.parseInt(txtCon.getText().toString().trim()));
                        guide.setTxtEmail(txtEmail.getText().toString().trim());
                        guide.setTxtDes(txtDes.getText().toString().trim());
                        String value = txtNic.getText().toString();



                        dbRef.child(value).setValue(guide);


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



=======
        Spinner spinner = (Spinner) findViewById(R.id.spinnerVehicle);
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72



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
        spinner.setAdapter(dataAdapter);
    }

    public void add_guide(View v) {
        Intent intent = new Intent(this, DelUpdateActivity.class);
        startActivity(intent);
    }




}