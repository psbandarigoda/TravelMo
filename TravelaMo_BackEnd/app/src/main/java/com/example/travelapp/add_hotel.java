package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_hotel extends AppCompatActivity {

    EditText txtHotelID,txtHotelName, txtHotelAddr, txtHotelPhone, txtHotelEmail, txtHotelStar, txtHotelDesc;
    Button btnHotelAdd;

    Intent intentget;
<<<<<<< HEAD
    static String district = AddHotelGuide.dist;
=======
    String district;
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
    String hotelidextra;
    Hotel hotelObj;
    DatabaseReference dbRef;

<<<<<<< HEAD
    //image upload stuff
    Button btnChoose, btnUpload;
    ImageView imageView;
    Uri filePath;
    Uri downloadUrl;
    final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;

=======
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
    private void clearControls(){
        txtHotelID.setText("");
        txtHotelName.setText("");
        txtHotelAddr.setText("");
        txtHotelPhone.setText("");
        txtHotelEmail.setText("");
        txtHotelStar.setText("");
        txtHotelDesc.setText("");
        btnHotelAdd.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        this.intentget = getIntent();

        txtHotelID = findViewById(R.id.hotelid_editTxt);
        txtHotelName = findViewById(R.id.hotelname_editTxt);
        txtHotelAddr = findViewById(R.id.hotelAddress_editTxt);
        txtHotelPhone = findViewById(R.id.hotelPhone_editTxt);
        txtHotelEmail = findViewById(R.id.hotelEmail_editTxt);
        txtHotelStar = findViewById(R.id.hotelStarRating_editTxt);
        txtHotelDesc = findViewById(R.id.hotelDescription_editTxt);
        btnHotelAdd = findViewById(R.id.addHotel_btn);

        btnHotelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHotel();
            }
        });


<<<<<<< HEAD
        //image inits
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference(district+"/ClientHotel");

        btnChoose = (Button) findViewById(R.id.select_photobtn);
        btnUpload = (Button) findViewById(R.id.upload_photobtn);
        imageView = (ImageView) findViewById(R.id.showhotelphoto_img);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImg();
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImg();
            }
        });



=======
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72

    }

    public void add_hotel1(View v) {
        Intent intent = new Intent(this, hotel_del_update.class);
        intent.putExtra("district", district );
        intent.putExtra("hotel", hotelidextra );
        startActivity(intent);
    }




    public void addHotel(){
<<<<<<< HEAD
        dbRef = FirebaseDatabase.getInstance().getReference(district+"/ClientHotel");
=======
        dbRef = FirebaseDatabase.getInstance().getReference().child(district).child("Hotels");
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72
        hotelObj = new Hotel();

        try {
            if(TextUtils.isEmpty(txtHotelID.toString()))
                Toast.makeText(getApplicationContext(), "Please enter an ID [hotelnamewithoutspaces]", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelName.toString()))
                Toast.makeText(getApplicationContext(), "Please enter Hotel Name", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelAddr.toString()))
                Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelEmail.toString()))
                Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            if(TextUtils.isEmpty(txtHotelDesc.toString()))
                Toast.makeText(getApplicationContext(), "Please enter a description", Toast.LENGTH_SHORT).show();
            else{

                hotelObj.setHotelId(txtHotelID.getText().toString().trim());
                hotelObj.setHotelName(txtHotelName.getText().toString().trim());
                hotelObj.setHotelAddress(txtHotelAddr.getText().toString().trim());
                hotelObj.setHotelContactNumber(Integer.parseInt(txtHotelPhone.getText().toString().trim()));
                hotelObj.setHotelEmailAddress(txtHotelEmail.getText().toString().trim());
                hotelObj.setHotelStarRating(Integer.parseInt(txtHotelStar.getText().toString().trim()));
                hotelObj.setHotelDescription(txtHotelDesc.getText().toString().trim());
//                hotelObj.setHotelimage(downloadUrl.toString().trim());

                String path = txtHotelID.getText().toString();
                dbRef.child(path).setValue(hotelObj);
                hotelidextra = txtHotelID.getText().toString();
            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();
        }

<<<<<<< HEAD
    }

    //***********************select image*************************
    private void chooseImg() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null ){

            filePath = data.getData();
            System.out.println("FILEPATH ISSSSSS"+filePath);
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    //***********************upload image*************************
    private void uploadImg() {
        if (filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading....");
            progressDialog.show();

            StorageReference ref = storageReference.child(txtHotelID.getText().toString());
//            final StorageReference refUri = storageReference.child(txtHotelID.getText().toString()).child(filePath.getLastPathSegment());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(add_hotel.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(add_hotel.this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                            progressDialog.setMessage("Uploaded"+(int)progress+" %");
                        }
                    });
//            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    downloadUrl = uri;
//                    Toast.makeText(getBaseContext(), "Upload success! URL - " + downloadUrl.toString() , Toast.LENGTH_SHORT).show();
//                }
//            });

        }
=======
>>>>>>> 53d715b956432dfbfec8ef0de71371559eec7f72



    }
}
