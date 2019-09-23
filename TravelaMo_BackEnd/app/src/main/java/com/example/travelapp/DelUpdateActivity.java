package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
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
    DatabaseReference dbref;
    Button UpdateBtn1;
    GuideModel guideObj;
    String district;
    Intent intent;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_update);

        this.intent = getIntent();
        editName = (EditText)findViewById(R.id.editName);
        editAge = (EditText)findViewById(R.id.editAge);
        editDes = (EditText)findViewById(R.id.editDes);
        editCon = (EditText)findViewById(R.id.editCon);
        editEmail = (EditText)findViewById(R.id.editEmail);
        UpdateBtn1 = (Button)findViewById(R.id.gUpdate);
        
        UpdateBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateGuide1();
            }
        });

        editName.setText(getIntent().getStringExtra("hname"));
        editAge.setText(getIntent().getStringExtra("hage"));
        editDes.setText(getIntent().getStringExtra("hdesc"));
        editCon.setText(getIntent().getStringExtra("gcon"));
        editEmail.setText(getIntent().getStringExtra("hid"));

        district = intent.getStringExtra("hdist");
        
   }

   public void updateGuide1(){
        dbref = FirebaseDatabase.getInstance().getReference().child(district).child("ClientGuide");
        guideObj = new GuideModel();

        try {
            if(TextUtils.isEmpty(editName.toString()))
                Toast.makeText(getApplicationContext(), "Please enter a Name", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(editAge.toString()))
                Toast.makeText(getApplicationContext(), "Please enter an Address", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(editEmail.toString()))
                Toast.makeText(getApplicationContext(), "Please enter an email", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(editDes.toString()))
                Toast.makeText(getApplicationContext(), "Please enter a description", Toast.LENGTH_SHORT).show();
            else{

                guideObj.setTxtName(editName.getText().toString().trim());
                guideObj.setTxtAge(editAge.getText().toString().trim());
                guideObj.setTxtCon(Integer.parseInt(editCon.getText().toString().trim()));
                guideObj.setTxtEmail(editEmail.getText().toString().trim());
                guideObj.setTxtDes(editDes.getText().toString().trim());
                String path = editEmail.getText().toString();
                dbref.child(path).setValue(guideObj);
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();
        }
        
    }


}
