package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewGuide extends AppCompatActivity {


    DatabaseReference db;
    ListView listViewGuideModels;
    List<GuideModel> guides;
    String district;

    public static void deleteGuideModel(String dist, String txtEmail) {
        DatabaseReference dhot = FirebaseDatabase.getInstance().getReference(dist+"/ClientGuide").child(txtEmail);
        dhot.removeValue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guide);

        listViewGuideModels = (ListView) findViewById(R.id.listView);

        guides = new ArrayList<GuideModel>();
        district = AddHotelGuide.dist;
        db = FirebaseDatabase.getInstance().getReference(district+"/ClientGuide");
        ViewGuide.checkerthread1 ch = new ViewGuide.checkerthread1(100000);
        ch.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                guides.clear();

                //iteratuon through alsll the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    GuideModel guide = (GuideModel) postSnapshot.getValue(GuideModel.class);
                    guides.add(guide);
                }

                GuideModelsList guideAdapter = new GuideModelsList(ViewGuide.this, guides);
                listViewGuideModels.setAdapter(guideAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void guideBtn(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }


//    public void UpdateBtn(View v) {
//        Intent intent = new Intent(this, DelUpdateActivity.class);
//        startActivity(intent);
//    }


    public void checkIfClicked(){
        if(GuideModelsList.checker1)
            updateGuide();
    }

    public void updateGuide(){
        String districtFromAdd = add_hotel.district;
        GuideModel h = GuideModelsList.guideToUpdate;
        Intent updateDelete = new Intent(ViewGuide.this, DelUpdateActivity.class);
        updateDelete.putExtra("hid", h.getTxtEmail());
        updateDelete.putExtra("hname", h.getTxtName());
        updateDelete.putExtra("hage", h.getTxtAge());

        String num = Integer.valueOf(h.getTxtCon()).toString();
        updateDelete.putExtra("hcont",  num);

        updateDelete.putExtra("hdesc", h.getTxtDes());
        updateDelete.putExtra("hdist", district);

        startActivity(updateDelete);
        GuideModelsList.checker1 = false;
    }

    class checkerthread1 extends Thread{
        int seconds;

        checkerthread1(int seconds){
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

}
