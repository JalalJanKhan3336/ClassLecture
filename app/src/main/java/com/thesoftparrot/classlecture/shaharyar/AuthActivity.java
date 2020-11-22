package com.thesoftparrot.classlecture.shaharyar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.databinding.ActivityAuthBinding;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    private List<String> paths;
    private List<String> mUploadedImagesDownloadUrlList;

    private ActivityAuthBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mUploadedImagesDownloadUrlList = new ArrayList<>();

        mBinding.browseImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImages();
            }
        });

        mBinding.uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImages(paths);
            }
        });

        mBinding.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindDownloadedImagesToRecyclerView(mUploadedImagesDownloadUrlList);
            }
        });
    }

    private void bindDownloadedImagesToRecyclerView(List<String> downloadedImagesList) {
        ImageListAdapter adapter = new ImageListAdapter(this, downloadedImagesList);
        mBinding.downloadedImagesList.setAdapter(adapter);
    }

    private void uploadImages(List<String> imagesList) {
        StorageReference rootRef = FirebaseStorage.getInstance().getReference();

        if(imagesList != null){
            for(int i = 0; i < imagesList.size(); i++){

                String imagePath = imagesList.get(i);

                File file = new File(imagePath);
                Uri uri = Uri.fromFile(file);

                Log.d("TAG", "_uploadImages_ImagePath: "+imagePath);
                Log.d("TAG", "_uploadImages_ImagePath_Uri: "+uri);

                final String imageName = "image_No."+i+".png";

                rootRef
                        .child("images")
                        .child(imageName)
                        .putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String msg = imageName+" uploaded!";
                                Toast.makeText(AuthActivity.this, msg, Toast.LENGTH_SHORT).show();

                                getDownloadUrl(taskSnapshot);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                Log.e("UploadImage", "_onFailure_Image_Upload: "+e.getMessage());

                                String msg = imageName+" upload failed!";
                                Toast.makeText(AuthActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        }

    }

    private void getDownloadUrl(UploadTask.TaskSnapshot taskSnapshot) {
        if(taskSnapshot != null){
            StorageMetadata metadata = taskSnapshot.getMetadata();

            if(metadata != null){
                StorageReference reference = metadata.getReference();
                if(reference != null){
                    reference.getDownloadUrl()
                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d("TAG", " _onSuccess_Download_Url: "+uri.toString());
                                    mUploadedImagesDownloadUrlList.add(uri.toString());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("TAG", "onFailure_Download_Url: "+e.getMessage());
                                }
                            });
                }
            }
        }
    }

    // Will pick images from gallery
    private void pickImages() {
        new ImagePicker
                .Builder(this)
                .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.PNG)
                .scale(600, 600)
                .allowMultipleImages(true)
                .enableDebuggingMode(true)
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null){
            if(requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE){
                paths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);

                if(paths != null && paths.size() > 0){
                    String imagePath = paths.get(0);
                    loadCoverImage(imagePath);

                    bindListToRecyclerView(paths);
                }
            }
        }

    }

    private void bindListToRecyclerView(List<String> imagesPathList) {
        ImageListAdapter adapter = new ImageListAdapter(this, imagesPathList);
        mBinding.recyclerView.setAdapter(adapter);
    }

    private void loadCoverImage(String imagePath) {
        Glide
                .with(this)
                .load(imagePath)
                .into(mBinding.coverImage);
    }

    private void fetchFromRealtimeDatabase(){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef
                .child("Friends")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}