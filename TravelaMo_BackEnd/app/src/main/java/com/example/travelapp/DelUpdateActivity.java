package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.provider.ContactsContract;
import android.text.TextUtils;
=======
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
import android.view.View;

public class DelUpdateActivity extends AppCompatActivity {

<<<<<<< HEAD
    EditText editName,editAge,editDes,editCon,editEmail,editNic;
    DatabaseReference dbref;
    Button UpdateBtn1;
    GuideModel guideObj;
    String district;
    Intent intent;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
=======
    @Override
    protected void onCreate(Bundle savedInstanceState) {
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_update);
    }

<<<<<<< HEAD
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

=======
    public void delete(View v) {
        Intent intent = new Intent(this, DelUpdateActivity.class);
        startActivity(intent);

    }
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72

    public void update(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}
