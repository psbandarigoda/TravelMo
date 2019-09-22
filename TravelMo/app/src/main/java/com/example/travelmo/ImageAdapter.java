package com.example.travelmo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.travelmo.HotelUsers.distr;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    private Context desc;
    private List<Kandy> mupload;



    public ImageAdapter(  Context desc, List<Kandy> mupload) {

        this.desc = desc;
        this.mupload = mupload;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        System.out.print(distr);

        View v = LayoutInflater.from(desc).inflate(R.layout.image_view,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Kandy upload = mupload.get(position);
            holder.name.setText(upload.getName());
            holder.description.setText(upload.getDescription());

        Picasso.with(desc)
                .load(upload.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(250,300)
                .centerCrop()
                .into(holder.image);



        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(desc,HotelBooking.class);
                intent.putExtra( "place",distr);
                desc.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mupload.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        TextView name,description;
        ImageView image;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameimg);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
        }
    }
}
