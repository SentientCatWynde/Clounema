package com.example.clounema.MovieDetailings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.clounema.PilihJadwal;
import com.example.clounema.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class JokerDetailActivity extends AppCompatActivity {
    private Button pilihJadwal;
    private ImageView mvDposter;
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference posterTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker_detail);

        mvDposter = findViewById(R.id.mvDposter);
        posterTemp = storageRef.child("Movie/joker.jpg");
        try {
            final File localPoster = File.createTempFile("poster_1", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                /** Toast.makeText(HomeActivity.this, "Poster Retrieved",
                 Toast.LENGTH_SHORT).show();*/
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                mvDposter.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(JokerDetailActivity.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        pilihJadwal = findViewById(R.id.btn_pilih_jadwal);
        pilihJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JokerDetailActivity.this, PilihJadwal.class);
                startActivity(intent);
            }
        });
    }
}