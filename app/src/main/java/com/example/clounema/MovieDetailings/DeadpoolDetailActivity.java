package com.example.clounema.MovieDetailings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.clounema.MovieDetails;
import com.example.clounema.PilihJadwal;
import com.example.clounema.R; import com.example.clounema.MovieDetails;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class DeadpoolDetailActivity extends AppCompatActivity {
    private Button pilihJadwal;
    private ImageView mvDposter;
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference posterTemp;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadpool_detail);

        videoView = findViewById(R.id.mvTrailer);
        String vidPath = "android.resource://" + getPackageName() + "/" + R.raw.deadpool_trailer;

        Uri uri = Uri.parse(vidPath);
        videoView.setVideoURI(uri);
        // videoView.start();

        MediaController mvController = new MediaController(this);
        videoView.setMediaController(mvController);
        mvController.setAnchorView(videoView);

        mvDposter = findViewById(R.id.mvDposter);
        posterTemp = storageRef.child("Movie/Deadpool.jpg");
        try {
            final File localPoster = File.createTempFile("poster_1", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                /** Toast.makeText(HomeActivity.this, "Poster Retrieved",
                 Toast.LENGTH_SHORT).show();*/
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                mvDposter.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(DeadpoolDetailActivity.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        pilihJadwal = findViewById(R.id.btn_pilih_jadwal);
        pilihJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeadpoolDetailActivity.this, PilihJadwal.class);
                startActivity(intent);
            }
        });
    }
}