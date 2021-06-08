package com.example.clounema.MovieDetailings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clounema.PilihJadwal;
import com.example.clounema.R;

public class WWDetailsActivity extends AppCompatActivity {
    private Button pilihJadwal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_w_details);

        pilihJadwal = findViewById(R.id.btn_pilih_jadwal);
        pilihJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WWDetailsActivity.this, PilihJadwal.class);
                startActivity(intent);
            }
        });
    }
}