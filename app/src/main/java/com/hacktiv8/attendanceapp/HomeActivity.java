package com.hacktiv8.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private ImageButton btnAttendance, btnPersonalized, btnTraining, btnReward,btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        btnAttendance = findViewById(R.id.btnAttendance);
        btnPersonalized = findViewById(R.id.btnPersonalized);
        btnTraining = findViewById(R.id.btnTraining);
        btnReward = findViewById(R.id.btnReward);

        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, AttendanceActivity.class);
                startActivity(i);
            }
        });

        btnBackHome = findViewById(R.id.btnBackHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnTraining = findViewById(R.id.btnTraining);
        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, TrainingHomeActivity.class);
                startActivity(i);
            }
        });

    }
}