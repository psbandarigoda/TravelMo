package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DelUpdateActivity extends AppCompatActivity {

//    TextView txtName1,txtEmail1,txtAge1,txtCon1,txtDes1;//spinnerVehicle;
//    Button updatebtn, deletebtn;
//    GuideModel guide;
//    String value;
    ListView listView;
    DatabaseReference dbRef;
    FirebaseDatabase database;
    ArrayList list;
    ArrayAdapter<String> adapter;
    GuideModel guide;
    Button guideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_update);

        guideBtn = findViewById(R.id.guideBtn);
        listView = (ListView)findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("kandy").child("ClientGuide");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.guide_info,R.id.guide_info,list);
        guide = new GuideModel();


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    guide = ds.getValue(GuideModel.class);
                    list.add(guide.getTxtName().toString()  +" \n " +guide.getTxtCon() + " \n "
                            +guide.getTxtAge().toString() + " \n " +guide.getTxtDes().toString() + " \n " + guide.getTxtEmail().toString() );

                }
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        txtName1 = findViewById(R.id.txtName1);
//        txtDes1 = findViewById(R.id.txtDes1);
//        txtAge1 = findViewById(R.id.txtAge1);
//        txtCon1 = findViewById(R.id.txtCon1);
//        txtEmail1 = findViewById(R.id.txtEmail1);
//        //spinnerVehicle = findViewById(R.id.spinnerVehicle);
//        updatebtn = findViewById(R.id.updatebtn);
//        deletebtn = findViewById(R.id.deletebtn);
//       // value = txtName.getText().toString();


//        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Guide").child(value);
//        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.hasChildren()) {
//                    txtName1.setText(dataSnapshot.child("name").getValue().toString());
//                    txtDes1.setText(dataSnapshot.child("desc").getValue().toString());
//                    txtEmail1.setText(dataSnapshot.child("email").getValue().toString());
//                    txtAge1.setText(dataSnapshot.child("age").getValue().toString());
//                    txtCon1.setText(dataSnapshot.child("phone").getValue().toString());
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "No Values to retrieve", Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });




//
//        updatebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Guide");
//                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.hasChild(value)){
//                            try {
//                                guide.setTxtName(txtName1.getText().toString().trim());
//                                guide.setTxtAge(txtAge1.getText().toString().trim());
//                                guide.setTxtCon(Integer.parseInt(txtCon1.getText().toString().trim()));
//                                guide.setTxtEmail(txtEmail1.getText().toString().trim());
//                                guide.setTxtDes(txtDes1.getText().toString().trim());
//
//                                dbRef = FirebaseDatabase.getInstance().getReference().child("Guide").child(value);
//                                dbRef.setValue(guide);
//                               //clearControls();
//
//                                Toast.makeText(getApplicationContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(DelUpdateActivity.this,DelUpdateActivity.class);
//                                startActivity(intent);
//                            }
//                            catch (NumberFormatException e){
//                                Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                        else
//                            Toast.makeText(getApplicationContext(),"No source to Update",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
//
//
//        deletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Guide");
//                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.hasChild(value)){
//                            dbRef = FirebaseDatabase.getInstance().getReference().child("Guide").child(value);
//                            dbRef.removeValue();
//                            //clearControls();
//                            Toast.makeText(getApplicationContext(),"Guide deleted Successfully",Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(DelUpdateActivity.this,DelUpdateActivity.class);
//                        startActivity(intent);
//
//
//                        }
//                        else
//                            Toast.makeText(getApplicationContext(),"No Guide to delete",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
   }
    public void guideBtn(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }


}
