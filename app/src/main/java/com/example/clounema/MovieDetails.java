package com.example.clounema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MovieDetails extends AppCompatActivity {

    private Button pilihJadwal;
    private int toggler = 0;
    private ImageView mvDposter;
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference posterTemp;
//    private DatabaseReference dbRef;
//    private TextView mdTitle, mdYear, mdSino, mdDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

//        mdDirector = findViewById(R.id.mdDirector);
//        mdSino = findViewById(R.id.mdSino);
//        mdTitle = findViewById(R.id.mdTitle);
//        mdYear = findViewById(R.id.mdYear);

        // dbRef = FirebaseDatabase.getInstance().getReference("Movie");
        posterTemp = storageRef.child("Movie/avengers.jpg");
        //Movie mv = new Movie();
//        mv.writeNewMovie("default_title", "50000", "nobody", "link_pending",
//                "this is a test creation to check how the database detects data and writes in the database",
//                "link_pending", "2021");
        // Toast.makeText(MovieDetails.this, "global Switch: " + toggler, Toast.LENGTH_SHORT).show();
//        dbRef = FirebaseDatabase.getInstance().getReference();


//        switch (toggler){
//            case 1:
//                // Read Avengers specifically
//                break;
//            case 2:
//                // Read Joker specifically
//                break;
//            case 3:
//                // Read Deadpool specifically
//                break;
//            case 4:
//                // Read Wonder Woman specifically
//                break;
//            case 5:
//                // Read StandByMe specifically
//                break;
//
//        }
        //Toast.makeText(MovieDetails.this, "Title: " + kanppai, Toast.LENGTH_SHORT).show();

        mvDposter = findViewById(R.id.mvDposter);
        posterTemp = storageRef.child("Movie/avengers.jpg");
        try {
            final File localPoster = File.createTempFile("poster_1", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                /** Toast.makeText(HomeActivity.this, "Poster Retrieved",
                 Toast.LENGTH_SHORT).show();*/
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                mvDposter.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(MovieDetails.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        pilihJadwal = findViewById(R.id.btn_pilih_jadwal);
        pilihJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetails.this, PilihJadwal.class);
                startActivity(intent);
            }
        });
    }
}