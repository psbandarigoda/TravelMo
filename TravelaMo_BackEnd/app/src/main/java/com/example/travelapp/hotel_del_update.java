package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class hotel_del_update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_del_update);
<<<<<<< HEAD

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
=======
    }
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
    public void hdelete(View v) {
        Intent intent = new Intent(this, hotel_del_update.class);
        startActivity(intent);

    }

    public void hupdate(View v) {
        Intent intent = new Intent(this, add_hotel.class);
        startActivity(intent);
    }



}
