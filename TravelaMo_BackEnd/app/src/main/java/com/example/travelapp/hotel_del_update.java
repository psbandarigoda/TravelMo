package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class hotel_del_update extends AppCompatActivity {

    DatabaseReference db;
    ListView listViewHotels;
    List<Hotel> hotels;
    String district;

//    public static void startActToUpdate(String district, Hotel hotel) {
//        new hotel_del_update().updateHotel(district,hotel);
//    }

    public void checkIfClicked(){
        if(HotelsList.checker)
            updateHotel();
    }

    public void updateHotel(){
        String districtFromAdd = add_hotel.district;
        Hotel h = HotelsList.hotelToUpdate;
        Intent updateDelete = new Intent(hotel_del_update.this, HotelDeleteOrUpdate.class);
        updateDelete.putExtra("hid", h.getHotelId());
        updateDelete.putExtra("hname", h.getHotelName());
        updateDelete.putExtra("hadd", h.getHotelAddress());

        String num = Integer.valueOf(h.getHotelContactNumber()).toString();
        updateDelete.putExtra("hcont",  num);

        String star= Integer.valueOf(h.getHotelStarRating()).toString();
        updateDelete.putExtra("hstar", star);
        updateDelete.putExtra("hdesc", h.getHotelDescription());
        updateDelete.putExtra("hemail", h.getHotelEmailAddress());
        updateDelete.putExtra("hdist", district);
        startActivity(updateDelete);
        HotelsList.checker =false;
    }

    class checkerthread extends Thread{
        int seconds;

        checkerthread(int seconds){
            this.seconds = seconds;
        }

        @Override
        public void run(){
            for(int i=0; i< seconds; i++){
                checkIfClicked();
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_del_update);

        listViewHotels = (ListView) findViewById(R.id.listHotel_listView);

        hotels = new ArrayList<Hotel>();
        district = add_hotel.district;
        db = FirebaseDatabase.getInstance().getReference(district+"/ClientHotel");

        checkerthread ch = new checkerthread(100000);
        ch.start();
    }



    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hotels.clear();

                //iteratuon through alsll the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Hotel hotel = (Hotel) postSnapshot.getValue(Hotel.class);
                    hotels.add(hotel);
                }

                HotelsList hotelAdapter = new HotelsList(hotel_del_update.this, hotels);
                listViewHotels.setAdapter(hotelAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void deleteHotel(String district, String id){
        DatabaseReference dhot = FirebaseDatabase.getInstance().getReference(district+"/ClientHotel").child(id);
        dhot.removeValue();
    }

    //intents
    public void hdelete(View v) {
        Intent intent = new Intent(this, hotel_del_update.class);
        startActivity(intent);

    }

    public void hupdate(View v) {
        Intent intent = new Intent(this, add_hotel.class);
        startActivity(intent);
    }



}
