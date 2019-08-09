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

        Spinner spinner = (Spinner) findViewById(R.id.spinnerVehicle);



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