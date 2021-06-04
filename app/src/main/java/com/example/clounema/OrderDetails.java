package com.example.clounema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OrderDetails extends AppCompatActivity {
    private ImageView arw_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        arw_back = findViewById(R.id.arrow_back);

        arw_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}