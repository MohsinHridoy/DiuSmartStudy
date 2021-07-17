package com.example.vrstyproject.uploadbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vrstyproject.R;
import com.example.vrstyproject.model.PdfModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class UploadBookActivity extends AppCompatActivity {

    ImageView imageSerach, filelogo, cancelfile;
    Uri filePath;
    EditText fileTitle;
    Button upload;
    StorageReference storageRefereence;
    DatabaseReference databaserRefenece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_book);

        storageRefereence = FirebaseStorage.getInstance().getReference();
        databaserRefenece = FirebaseDatabase.getInstance().getReference("mydocuments");

        fileTitle = findViewById(R.id.etBookName);
        imageSerach = findViewById(R.id.ivSearch);
        upload = findViewById(R.id.btnUpload);
        filelogo = findViewById(R.id.ivFile);
        cancelfile = findViewById(R.id.ivDelete);


        initClickListener();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) {
            filePath = data.getData();
            cancelfile.setVisibility(View.VISIBLE);
            filelogo.setVisibility(View.VISIBLE);
            imageSerach.setVisibility(View.INVISIBLE);
        }
    }

    private void initClickListener() {
        cancelfile.setVisibility(View.INVISIBLE);
        filelogo.setVisibility(View.INVISIBLE);
        imageSerach.setVisibility(View.VISIBLE);

        getPermisssion();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processupload(filePath);
            }
        });


    }

    private void processupload(Uri filePath) {

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("File Uploading...!!!");
        pd.show();

        final StorageReference reference = storageRefereence.child("uploads/" + System.currentTimeMillis() + ".pdf");

        reference.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                PdfModel obj = new PdfModel(fileTitle.getText().toString(), uri.toString());

                                databaserRefenece.child(databaserRefenece.push().getKey()).setValue(obj);

                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_SHORT).show();
                                cancelfile.setVisibility(View.INVISIBLE);
                                filelogo.setVisibility(View.INVISIBLE);
                                imageSerach.setVisibility(View.VISIBLE);
                                fileTitle.setText("");
                            }
                        });

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        float percent=(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        pd.setMessage("Uploaded :"+percent+"%");

                    }
                });
    }

    private void getPermisssion() {
        imageSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent();
                                intent.setType("application/pdf");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Select Pdf Files"), 101);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

    }


}






















