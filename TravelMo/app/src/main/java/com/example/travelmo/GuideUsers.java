package com.example.travelmo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GuideUsers extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Button search,search2;
    TextView textViewName, textViewDes, textViewEmail, textViewContact;
    String valueDis;
    String valueChoice;
    DatabaseReference dbRef;
    String place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_users);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent id = getIntent();
        valueDis = id.getStringExtra("district");
        valueChoice = id.getStringExtra("choice");
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        System.out.println(valueDis);
        System.out.println(valueChoice);

        String des = valueDis;
        String cho = valueChoice;

        textViewName = findViewById(R.id.textViewName);
        textViewDes = findViewById(R.id.textViewDes);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewContact = findViewById(R.id.textViewContact);

        Intent gid = getIntent();
        place = gid.getStringExtra("district");

        dbRef = FirebaseDatabase.getInstance().getReference().child("GalleClient").child("Guide").child("G001");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    System.out.println("has Children");
                    //System.out.println(dataSnapshot.child("id").getValue().toString());
                    textViewName.setText(dataSnapshot.child("name").getValue().toString());
                    textViewDes.setText(dataSnapshot.child("description").getValue().toString());
                    textViewEmail.setText(dataSnapshot.child("email").getValue().toString());
                    textViewContact.setText(dataSnapshot.child("phoneNumber").getValue().toString());

                } else {
                    System.out.println("no Children");
                    Toast.makeText(getApplicationContext(), "No Values to retrive", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        search = findViewById(R.id.btnSearch);
        search2 = findViewById(R.id.btnSearch2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guide_users, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideUsers.this, GuideBooking.class);
                intent.putExtra("place", place);
                intent.putExtra("email","bgpsandaruwan@gmail.com");
                startActivity(intent);
            }
        });

        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideUsers.this, GuideBooking.class);
                intent.putExtra("place", place);
                intent.putExtra("email","sandaruwan@gmail.com");
                startActivity(intent);
            }
        });

    }
}
