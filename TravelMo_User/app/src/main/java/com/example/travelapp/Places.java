package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Places extends AppCompatActivity {

    Button place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        place = findViewById(R.id.kandy1);
    }

    @Override
    protected void onResume(){
        super.onResume();

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Places.this,UserChoice.class);
                startActivity(intent);
            }
        });



    }
}
