package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DelUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_update);
    }

    public void delete(View v) {
        Intent intent = new Intent(this, DelUpdateActivity.class);
        startActivity(intent);

    }

    public void update(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
