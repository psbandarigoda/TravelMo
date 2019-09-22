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


    String value;
    ListView listView;
    DatabaseReference dbRef;
    FirebaseDatabase database;
    ArrayList list;
    ArrayAdapter<String> adapter;
    GuideModel guide;
    Button guideBtn,DeleteBtn,UpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guide);


        UpdateBtn = findViewById(R.id.UpdateBtn);
        guideBtn = findViewById(R.id.guideBtn);
        listView = findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        guide = new GuideModel();
        DeleteBtn = findViewById(R.id.DeleteBtn);

       Intent nic = getIntent();
       value = nic.getStringExtra("userObject");



        //ListView
        adapter = new ArrayAdapter<String>(this, R.layout.guide_info, R.id.guide_info, list);
        dbRef = database.getReference("kandy").child("ClientGuide");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    guide = ds.getValue(GuideModel.class);
                    list.add(" Name: " + guide.getTxtName() + " \n NIC: " + guide.getTxtNic() + " \n Contact Number: " + guide.getTxtCon() + " \n Age: "
                            + guide.getTxtAge() + " \n Email: " + guide.getTxtEmail() + " \n Description: " + guide.getTxtDes());


                }

                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent delUpdate = new Intent(ViewGuide.this, DelUpdateActivity.class);
                        GuideModel g = (GuideModel) adapterView.getItemAtPosition(i);
                        delUpdate.putExtra("name", g.getTxtName());
                        delUpdate.putExtra("key", g.getTxtNic());
                        delUpdate.putExtra("age", g.getTxtAge());
                        delUpdate.putExtra("email", g.getTxtEmail());
                        delUpdate.putExtra("des", g.getTxtDes());
                        delUpdate.putExtra("con", g.getTxtCon());
                        startActivity(delUpdate);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("kandy").child("ClientGuide");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(value)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("kandy").child("ClientGuide").child(value);
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(),"Guide deleted Successfully",Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(getApplicationContext(),"No Guide to delete",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(ViewGuide.this,ViewGuide.class);
                startActivity(intent);

            }
        });


    }

    public void guideBtn(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }


    public void UpdateBtn(View v) {
        Intent intent = new Intent(this, DelUpdateActivity.class);
        startActivity(intent);
    }

}
