package com.example.travelmo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Places extends AppCompatActivity {

    Button kandy, galle, rathnapura, nuwaraeliya, anuradapura, jaffna, matara;
//    String kandyname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kandy = findViewById(R.id.kandy1);
        galle = findViewById(R.id.galle);
    }

    @Override
    protected void onResume() {
        super.onResume();

        kandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Places.this, UserChoice.class);
                intent.putExtra("district","kandy");
                startActivity(intent);
            }
        });

        galle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Places.this, UserChoice.class);
                intent.putExtra("district","galle");
                startActivity(intent);
            }
        });
    }

//    public String returnkandy() {
//        kandyname = String.valueOf(String.valueOf(kandy));
//        return kandyname;
//    }
}
