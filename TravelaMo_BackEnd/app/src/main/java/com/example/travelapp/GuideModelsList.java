package com.example.travelapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GuideModelsList extends ArrayAdapter<GuideModel> {
    private Activity context;
    List<GuideModel> guides;

    static GuideModel guideToUpdate;
    static boolean checker1 = false;

    public GuideModelsList(Activity context, List<GuideModel> guides) {
        super(context, R.layout.guide_info, guides);
        this.context = context;
        this.guides = guides;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.guide_info, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.guide_info);
        Button btnUpdate = (Button) listViewItem.findViewById(R.id.UpdateBtn);
        Button btnDelete = (Button) listViewItem.findViewById(R.id.DeleteBtn);

        GuideModel guide = guides.get(position);
        textViewName.setText(guide.getTxtName());


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGuide.deleteGuideModel(AddHotelGuide.dist,guides.get(position).getTxtEmail());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guideToUpdate = guides.get(position);
                checker1 = true;
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guideToUpdate = guides.get(position);
                checker1 = true;
            }
        });



        return listViewItem;
    }
}
