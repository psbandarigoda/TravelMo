package com.example.testappstudenttutorial5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtID, txtName, txtAdd, txtPhone;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Student student;
    int keyCount = 0;

    private void clearControls(){
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtPhone.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = findViewById(R.id.stuid_txtView);
        txtName = findViewById(R.id.stuname_txtView);
        txtAdd = findViewById(R.id.stuadd_txtView);
        txtPhone = findViewById(R.id.stucont_txtView);

        btnSave = findViewById(R.id.stuadd_btnView);
        btnShow = findViewById(R.id.stushow_btnView);
        btnUpdate = findViewById(R.id.stuupdate_btnView);
        btnDelete = findViewById(R.id.studelete_btnView);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowStudent();
            }
        });


    }

    public void AddStudent(){
        student = new Student();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
        try{
            if (TextUtils.isEmpty(txtID.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter ID", Toast.LENGTH_SHORT).show();

            if (TextUtils.isEmpty(txtName.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter Name", Toast.LENGTH_SHORT).show();

            if (TextUtils.isEmpty(txtAdd.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter Address", Toast.LENGTH_SHORT).show();

            else{
                student.setStudentId(txtID.getText().toString().trim());
                student.setStudentName(txtName.getText().toString().trim());
                student.setStudentAddress(txtAdd.getText().toString().trim());
                student.setStudentPhone(Integer.parseInt(txtPhone.getText().toString().trim()));

//                keyCount++;
//                String strOfCount = Integer.toString(keyCount);

                dbRef.child(txtID.getText().toString().trim()).setValue(student);
                Toast.makeText(getApplicationContext(),"Data Saved Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }

        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Please Enter A Valid PhoneNumber", Toast.LENGTH_SHORT).show();
        }
    }



    public void ShowStudent(){


        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Student").child(txtID.getText().toString().trim());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txtID.setText(dataSnapshot.child("studentId").getValue().toString());
                    txtName.setText(dataSnapshot.child("studentName").getValue().toString());
                    txtAdd.setText(dataSnapshot.child("studentAddress").getValue().toString());
                    txtPhone.setText(dataSnapshot.child("studentPhone").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}
