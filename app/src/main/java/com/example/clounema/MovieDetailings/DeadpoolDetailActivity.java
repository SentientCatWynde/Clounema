package com.example.clounema.MovieDetailings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clounema.MovieDetails;
import com.example.clounema.PilihJadwal;
import com.example.clounema.R; import com.example.clounema.MovieDetails;

public class DeadpoolDetailActivity extends AppCompatActivity {
    private Button pilihJadwal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadpool_detail);

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