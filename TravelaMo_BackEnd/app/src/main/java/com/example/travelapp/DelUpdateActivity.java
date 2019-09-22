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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DelUpdateActivity extends AppCompatActivity {

    EditText editName,editAge,editDes,editCon,editEmail,editNic;
    //DatabaseReference dbref;
    Button UpdateBtn;
    GuideModel guide;
    String value;
    FirebaseDatabase database;



    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_update);

        //database = FirebaseDatabase.getInstance();

        editName = (EditText)findViewById(R.id.editName);
        editAge = (EditText)findViewById(R.id.editAge);
        editDes = (EditText)findViewById(R.id.editDes);
        editCon = (EditText)findViewById(R.id.editCon);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editNic = (EditText)findViewById(R.id.editNic);
        UpdateBtn = (Button)findViewById(R.id.UpdateBtn);

        editName.setText(getIntent().getStringExtra("name"));
        editAge.setText(getIntent().getStringExtra("age"));
        editDes.setText(getIntent().getStringExtra("des"));
        editCon.setText(getIntent().getStringExtra("con"));
        editEmail.setText(getIntent().getStringExtra("email"));
        editNic.setText(getIntent().getStringExtra("nic"));

        guide = new GuideModel();

       Intent nic = getIntent();
       value = nic.getStringExtra("userObject");

       // dbref = FirebaseDatabase.getInstance().getReference("kandy").child("ClientGuide");

       
//       UpdateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("kandy").child("ClientGuide");
//                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.hasChild(value)){
//                            try {
//                                guide.setTxtName(editName.getText().toString().trim());
//                                guide.setTxtAge(editAge.getText().toString().trim());
//                                guide.setTxtCon(Integer.parseInt(editCon.getText().toString().trim()));
//                                guide.setTxtEmail(editEmail.getText().toString().trim());
//                                guide.setTxtDes(editDes.getText().toString().trim());
//                                guide.setTxtNic(editNic.getText().toString().trim());
//
//                                dbRef = FirebaseDatabase.getInstance().getReference().child("kandy").child("ClientGuide").child(value);
//                                dbRef.setValue(guide);
//
//                                Toast.makeText(getApplicationContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
//
//
//                            }
//                            catch (NumberFormatException e){
//                                Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();
//                            }
//                            Intent intent = new Intent(DelUpdateActivity.this,View.class);
//                            startActivity(intent);
//                        }
//
//
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



   }


}
