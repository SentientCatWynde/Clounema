package com.example.clounema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PilihJadwal extends AppCompatActivity {

    private Button pickTime;
    private BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_jadwal);

        pickTime = findViewById(R.id.btn_jadwal1);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create BottomSheetDialog
                bottomSheetDialog = new BottomSheetDialog(PilihJadwal.this, R.style.BottomSheetDialogTheme);
                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_dialog, null);

                sheetView.findViewById(R.id.btn_checkout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PilihJadwal.this, OrderDetails.class);
                        startActivity(intent);
                    }
                });
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }

        });
    }
}