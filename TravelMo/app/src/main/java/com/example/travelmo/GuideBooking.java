package com.example.travelmo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.TextUtils;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuideBooking extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Button conform;
    EditText Text_Name, Text_Email, Text_Days, Text_Phone;
    UserDetailForGuideReserv detailForGuideReserv;
    DatabaseReference dbRef;
    int count = 111111;
    String plc,x,email;
    SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
    Date todayDate = new Date();
    String thisDate = currentDate.format(todayDate);
    String vehicleName;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_booking);
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

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerVehicle);

        List<String> categories = new ArrayList<String>();
        categories.add("Micro");
        categories.add("Mini");
        categories.add("Car");
        categories.add("Minivan");
        categories.add("Van");
        categories.add("VIP");
        ArrayAdapter<String> dataAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdaptor);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                vehicleName = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Text_Name = findViewById(R.id.editTextName);
        Text_Email = findViewById(R.id.editTextEmail);
        Text_Days = findViewById(R.id.editTextDays);
        Text_Phone = findViewById(R.id.editTextPhone);

        conform = findViewById(R.id.btnNext);

        Intent place = getIntent();
        plc = place.getStringExtra("place");

        Intent gid = getIntent();
        email = gid.getStringExtra("email");

        detailForGuideReserv = new UserDetailForGuideReserv();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guide_booking, menu);
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

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child(plc).child("GuideReceiveUser");
                try {
                    if (TextUtils.isEmpty(Text_Name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(Text_Email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter E-Mail", Toast.LENGTH_LONG).show();
                    else if(!(Text_Email.getText().toString()).matches(emailPattern))
                        Toast.makeText(getApplicationContext(), "invalide E-Mail", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(Text_Days.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter No Of Days", Toast.LENGTH_LONG).show();
                    else if (TextUtils.isEmpty(Text_Phone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Phone No", Toast.LENGTH_LONG).show();
                    else {
                        detailForGuideReserv.setName(Text_Name.getText().toString().trim());
                        detailForGuideReserv.setEmail(Text_Email.getText().toString().trim());
                        detailForGuideReserv.setDays(Text_Days.getText().toString().trim());
                        detailForGuideReserv.setVehicle(vehicleName.trim());
                        detailForGuideReserv.setPhoneNumber(Integer.parseInt(Text_Phone.getText().toString().trim()));

                        x = thisDate + Text_Name.getText().toString().trim();
                        dbRef.child(x).setValue(detailForGuideReserv);

                        Toast.makeText(getApplicationContext(), "Guide Booking..", Toast.LENGTH_LONG).show();
                        clearControls();

                        Intent intent = new Intent(GuideBooking.this, GuideBookingConfirm.class);
                        intent.putExtra("userObject", x);
                        intent.putExtra("place", plc);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void clearControls() {
        Text_Name.setText("");
        Text_Email.setText("");
        Text_Days.setText("");
        Text_Phone.setText("");
    }

}
