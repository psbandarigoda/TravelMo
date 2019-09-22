package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 234;

    private StorageReference storageReference;
    Spinner spinVehicle;
    EditText txtName,txtEmail,txtAge,txtCon,txtDes,txtNic,txtImage;
    Button guideAdd,buttonChoose;
    GuideModel guide;
    DatabaseReference dbRef;
    ImageView image_view;
    TextView textViewShow;

    private Uri filePath;


    private void clearControls(){
        txtName.setText("");
        txtAge.setText("");
        txtCon.setText("");
        txtDes.setText("");
        txtEmail.setText("");
        txtNic.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spinVehicle = (Spinner) findViewById(R.id.spinnerVehicle);
        txtName = (EditText)findViewById(R.id.txtName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtDes = (EditText)findViewById(R.id.txtDes);
        txtCon = (EditText)findViewById(R.id.txtCon);
        txtAge = (EditText)findViewById(R.id.txtAge);
        txtNic = (EditText)findViewById(R.id.txtNic);

        txtImage = (EditText)findViewById(R.id.txtImage);
        buttonChoose = (Button)findViewById(R.id.buttonChoose);
        image_view = (ImageView)findViewById(R.id.image_view);

        storageReference = FirebaseStorage.getInstance().getReference();
        dbRef = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
        guideAdd = (Button)findViewById(R.id.guideAdd);
        guide = new GuideModel();


        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonChoose) {
                    showFileChooser();
                } else if (view == guideAdd) {

                }else if (view == textViewShow){

                }
            }
        });



        guideAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("kandy").child("ClientGuide");

                if(view == buttonChoose){
                    showFileChooser();
                }else if (view == guideAdd){
                    uploadFile();
                }else if (view == textViewShow){

                }
                try{

                    if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Name",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtNic.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a NIC",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtAge.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Age",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtCon.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Contact Number",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDes.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter a Description",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText()))
                        Toast.makeText(getApplicationContext(),"Please enter an Email",Toast.LENGTH_SHORT).show();

                    else{
                        guide.setTxtName(txtName.getText().toString().trim());
                        guide.setTxtNic(txtNic.getText().toString().trim());
                        guide.setTxtAge(txtAge.getText().toString().trim());
                        guide.setTxtCon(Integer.parseInt(txtCon.getText().toString().trim()));
                        guide.setTxtEmail(txtEmail.getText().toString().trim());
                        guide.setTxtDes(txtDes.getText().toString().trim());
                        String value = txtNic.getText().toString();



                        dbRef.child(value).setValue(guide);


                        Toast.makeText(getApplicationContext(),"Guide Added Successfully",Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent intent = new Intent(AddActivity.this,DelUpdateActivity.class);
                        startActivity(intent);
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

                }

            }
        });






        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Hiace");
        categories.add("Maco-Polo");
        categories.add("KDH");
        categories.add("Vanatte");
        categories.add("Dolphin");
        categories.add("Leyland Bus");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinVehicle.setAdapter(dataAdapter);

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image_view.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Getting selected file extension
    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    //uploading the image
    public void uploadFile(){
        if (filePath != null){

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            final String value = txtNic.getText().toString();

            StorageReference sRef = storageReference.child("kandy").child("ClientGuide").child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "."+getFileExtension(filePath));

            //adding file to the reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();

                            Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_LONG).show();

                            GuideUpload upload = new GuideUpload(txtImage.getText().toString().trim()
                                    , taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                            String uploadId = dbRef.push().getKey();
                            dbRef.child(value).child(uploadId).setValue(upload);
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded" + ((int)progress) + "%...");

                        }
                    });
        }else {
            //display an error if no file is selected.
        }
    }


}