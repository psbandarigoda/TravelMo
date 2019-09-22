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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HotelUsers extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Button search;
    String place;
    String count = "1001";

    private RecyclerView recycler ;
    private ImageAdapter imageAdapter;

    private DatabaseReference dref;
    List ulist;
    ProgressBar pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hottel_users);
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
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

//        search = findViewById(R.id.search2);


        Intent id = getIntent();
        place = id.getStringExtra("district");

        //recycler = findViewById(R.id.recycle);
        recycler.setHasFixedSize(true);

       // pro = findViewById(R.id.progress);
        recycler.setLayoutManager(new LinearLayoutManager(this));
         ulist = new ArrayList<>();

        dref = FirebaseDatabase.getInstance().getReference("kandy").child("ClientHotel");

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//                   Kandy kandy = postSnapshot.getValue(Kandy.class);
//                    ulist.add(kandy);

                    String user = postSnapshot.getKey();
                    String score = postSnapshot.getValue(String.class);
                    Kandy kan = new Kandy(user, score);
                    ulist.add(kan);

                }

                imageAdapter = new ImageAdapter(HotelUsers.this,ulist);
                recycler.setAdapter(imageAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HotelUsers.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hottel_users, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume(){
        super.onResume();

//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HotelUsers.this,HotelBooking.class);
//                intent.putExtra("place",place);
////                intent.putExtra("count",returnid());
//                startActivity(intent);
//            }
//        });
    }
//
//    public String returnid() {
//
//        count = String.valueOf(Integer.valueOf(count) + 1);
//
//        return count;
//    }
}
