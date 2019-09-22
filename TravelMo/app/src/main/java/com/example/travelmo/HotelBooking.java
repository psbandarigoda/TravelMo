package com.example.travelmo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelBooking extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Button next1;
    DatabaseReference dbref;
    UserDetailForHotelReserv hotel;
    EditText name, email, days, room, phone;
 
    SimpleDateFormat current = new SimpleDateFormat("ddMMyyyy");
    Date today = new Date();
    String day = current.format(today);

    String key,plc,ans,x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking);
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

        next1 = findViewById(R.id.save);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        days = findViewById(R.id.days);
        room = findViewById(R.id.rooms);
 
        hotel = new UserDetailForHotelReserv();

        Intent place = getIntent();
        plc = place.getStringExtra("place");
 



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotel_booking, menu);
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

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child(plc).child("HotelUser");

                try {
                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter E-Mail", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(room.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter No Of Rooms", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(phone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Phone No", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(days.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Days", Toast.LENGTH_LONG).show();
                    else {
                        hotel.setName(name.getText().toString().trim());
                        hotel.setEmail(email.getText().toString().trim());
                        hotel.setDays(days.getText().toString().trim());
                        hotel.setRooms(room.getText().toString().trim());
                        hotel.setPhone(Integer.parseInt(phone.getText().toString().trim()));
 

                         x =  day + name.getText().toString().trim();
 
                        dbref.child(x).setValue(hotel);

                        Toast.makeText(getApplicationContext(), "Hotel Booked..", Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent intent = new Intent(HotelBooking.this, HotelBookingConfirm.class);
                        intent.putExtra("userObject",x);
                        intent.putExtra("email",email.getText().toString());
                        intent.putExtra("name",name.getText().toString());
                        intent.putExtra("place",plc);
                        startActivity(intent);
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void clearControls() {
        name.setText("");
        email.setText("");
        days.setText("");
        room.setText("");
        phone.setText("");
    }



}
