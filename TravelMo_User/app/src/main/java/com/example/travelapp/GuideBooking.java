package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class GuideBooking extends AppCompatActivity {

    Button next2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_booking);

        next2 = findViewById(R.id.next2);

        Spinner spinner = (Spinner) findViewById(R.id.spinner5);

        List<String> categories = new ArrayList<String>();
        categories.add("Hiace");
        categories.add("maco-polo");
        categories.add("KHD");
        categories.add("Vanatte");
        categories.add("Dolphin");
        categories.add("Laylend Bus");
        ArrayAdapter<String> dataAdaptor = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        dataAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdaptor);
    }

    @Override
    protected void onResume(){
        super.onResume();

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideBooking.this,GuideConfirmBooking.class);
                startActivity(intent);
            }
        });
    }



}
