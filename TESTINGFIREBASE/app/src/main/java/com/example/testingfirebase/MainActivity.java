package com.example.testingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText3;
    Button button3;
    Spinner spinner3;

    DatabaseReference databaseTest;
    ListView listView;

    List<TestClass> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseTest = FirebaseDatabase.getInstance().getReference("ads");

        editText3 = (EditText) findViewById(R.id.editText3);
        button3 = (Button) findViewById(R.id.button);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        listView = (ListView) findViewById(R.id.listView2);


        testList = new ArrayList<>();

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AddMethod();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseTest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                testList.clear();

                for(DataSnapshot testListSnapshot: dataSnapshot.getChildren()){
                    TestClass testClassGet = testListSnapshot.getValue(TestClass.class);
                    testList.add(testClassGet);
                }

                TestList adapter = new TestList(MainActivity.this,testList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void AddMethod(){
        String text = editText3.getText().toString().trim();
        String type = spinner3.getSelectedItem().toString();

        if(!TextUtils.isEmpty(text)){

           String id1 = databaseTest.push().getKey();

           TestClass testdataobj = new TestClass(id1, type, text);

           databaseTest.child(id1).setValue(testdataobj);

            Toast.makeText(this, "Test Data Added", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Enter Text Please", Toast.LENGTH_SHORT).show();
        }
    }
}
