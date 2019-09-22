package com.example.travelapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HotelsList extends ArrayAdapter<Hotel> {
    private Activity context;
    List<Hotel> hotels;

    static Hotel hotelToUpdate;
    static boolean checker = false;

    public HotelsList(Activity context, List<Hotel> hotels) {
        super(context, R.layout.hotelviewdata, hotels);
        this.context = context;
        this.hotels = hotels;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.hotelviewdata, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.hotel_nametxtView);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.hotel_descTxtView);
        Button btnUpdate = (Button) listViewItem.findViewById(R.id.updatehotelbtn);
        Button btnDelete = (Button) listViewItem.findViewById(R.id.deletehotelbtn);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotel_del_update.deleteHotel(add_hotel.district,hotels.get(position).getHotelId());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotelToUpdate = hotels.get(position);
                checker = true;
            }
        });


        Hotel hotel = hotels.get(position);
        textViewName.setText(hotel.getHotelName());
        textViewDesc.setText(hotel.getHotelDescription());



        return listViewItem;
    }




}
