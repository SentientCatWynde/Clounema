package com.example.clounema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clounema.MovieDetailings.DeadpoolDetailActivity;
import com.example.clounema.MovieDetailings.JokerDetailActivity;
import com.example.clounema.MovieDetailings.SBMDetailActivity;
import com.example.clounema.MovieDetailings.WWDetailsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    private Button signOutBtn;
    ImageView poster1, poster2, poster3, poster4, poster5, poster_ic1, poster_ic2, poster_ic3, poster_ic4, poster_ic5;
    /** Firebase img retrieval: https://www.youtube.com/watch?v=x3gfy0rOq6w
     * Normalnya kalau ada error, alt+enter untuk ngisi import dan gradle
     * tambahkan ke build gradle yang module:
     * implementation 'com.squareup.picasso:picasso:2.71828'
     * Karena aku ga pegang Firebase DB nya,
     * aku isi bagian mana yg perlu diganti sama data dlm db dengan comment kayak gini
    **/
    //private FirebaseDatabase dbFirebase = FirebaseDatabase.getInstance();
    //private DatabaseReference dbRef = dbFirebase.getReference();

    /** Dibawah ini adalah variabel penyimpan gambar dari DB */
    // private DatabaseReference poster_1, poster_2, poster_3, poster_4 , poster_5;

    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference posterTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //assign variable
        poster1 = findViewById(R.id.movie1);
        poster2 = findViewById(R.id.movie2);
        poster3 = findViewById(R.id.movie3);
        poster4 = findViewById(R.id.movie4);
        poster5 = findViewById(R.id.movie5);
        poster_ic1 = findViewById(R.id.movie_ic1);
        poster_ic2 = findViewById(R.id.movie_ic2);
        poster_ic3 = findViewById(R.id.movie_ic3);
        poster_ic4 = findViewById(R.id.movie_ic4);
        poster_ic5 = findViewById(R.id.movie_ic5);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navbar);
        //set home selected
        navigationView.setSelectedItemId(R.id.nav_home);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        // Debug Token: 65C530BE-0636-4602-8E17-2A9E32D5E32C

        myRef.setValue("Hello, World!");

        posterTemp = storageRef.child("Movie/avengers.jpg");
        try {
            final File localPoster = File.createTempFile("poster_1", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                /** Toast.makeText(HomeActivity.this, "Poster Retrieved",
                        Toast.LENGTH_SHORT).show();*/
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                poster1.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(HomeActivity.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        posterTemp = storageRef.child("Movie/joker.jpg");
        try {
            final File localPoster = File.createTempFile("poster_2", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                poster2.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(HomeActivity.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        posterTemp = storageRef.child("Movie/Deadpool.jpg");
        try {
            final File localPoster = File.createTempFile("poster_3", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                poster3.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(HomeActivity.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        posterTemp = storageRef.child("Movie/WonderWoman.jpg");
        try {
            final File localPoster = File.createTempFile("poster_4", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                poster4.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(HomeActivity.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        posterTemp = storageRef.child("Movie/StandByMe.png");
        try {
            final File localPoster = File.createTempFile("poster_5", "jpg");
            posterTemp.getFile(localPoster).addOnSuccessListener(taskSnapshot -> {
                Bitmap bitmap = BitmapFactory.decodeFile(localPoster.getAbsolutePath());
                poster5.setImageBitmap(bitmap);
            }).addOnFailureListener(e -> Toast.makeText(HomeActivity.this, "Poster Not Found",
                    Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Intent to MovieDetail
        poster1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MovieDetails.class);
                startActivity(intent);
            }
        });
        poster2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, JokerDetailActivity.class);
                startActivity(intent);
            }
        });
        poster3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DeadpoolDetailActivity.class);
                intent.putExtra("glSwitch", 3);
                startActivity(intent);
            }
        });
        poster4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WWDetailsActivity.class);
                intent.putExtra("glSwitch", 4);
                startActivity(intent);
            }
        });
        poster5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SBMDetailActivity.class);
                intent.putExtra("glSwitch", 5);
                startActivity(intent);
            }
        });

        poster_ic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MovieDetails.class);
                intent.putExtra("glSwitch", 1);
                startActivity(intent);
            }
        });
        poster_ic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, JokerDetailActivity.class);
                intent.putExtra("glSwitch", 2);
                startActivity(intent);
            }
        });
        poster_ic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DeadpoolDetailActivity.class);
                intent.putExtra("glSwitch", 3);
                startActivity(intent);
            }
        });

        poster_ic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WWDetailsActivity.class);
                intent.putExtra("glSwitch", 4);
                startActivity(intent);
            }
        });
        poster_ic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SBMDetailActivity.class);
                intent.putExtra("glSwitch", 5);
                startActivity(intent);
            }
        });

        //ItemSelectedList
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_ticket:
                        startActivity(new Intent(getApplicationContext(), OrderDetails.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}