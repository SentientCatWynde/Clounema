package com.example.clounema.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clounema.LoginActivity;
import com.example.clounema.R;
import com.example.clounema.SignUpActivity;

public class OnBoardingOne extends AppCompatActivity {

    private Button btnHome, btnMasuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_one);

        btnHome = findViewById(R.id.btn_home);
        btnMasuk = findViewById(R.id.btn_masuk);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingOne.this, OnBoardingTwo.class));
            }
        });
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingOne.this, LoginActivity.class));
            }
        });
    }
}