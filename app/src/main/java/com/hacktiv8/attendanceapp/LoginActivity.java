package com.hacktiv8.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Switch switchTheme;
    private Button btnLogin;
    private EditText txtEmail, txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

//        //For Debug
//        Intent i = new Intent(LoginActivity.this, TrainingHomeActivity.class);
//        startActivity(i);

        // Data Dummy Login
        String email = "admin";
        String password = "admin";

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        switchTheme = findViewById(R.id.switchtheme);
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(txtEmail.getText().toString().equals(email) && txtPassword.getText().toString().equals(password)){
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Email or Password Wrong!", Toast.LENGTH_SHORT).show();
                    }
                Toast.makeText(LoginActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}