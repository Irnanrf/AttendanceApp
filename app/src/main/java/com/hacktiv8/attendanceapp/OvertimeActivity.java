package com.hacktiv8.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class OvertimeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] overtimeReason = { "Labor Shortages", "Unexpected Demand", "Extended Seasonal Hours"};

    private Button btnPickOvertime;
    private TextView lblDateOvertime;
    private Spinner spinnerOvertime;
    private Button btnSubmitOvertime;
    private ImageButton btnBackOvertime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_overtime);

        lblDateOvertime = findViewById(R.id.lblDateOvertime);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        lblDateOvertime.setText(formatter.format(date));

        spinnerOvertime = findViewById(R.id.spinnerOvertime);
        spinnerOvertime.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, overtimeReason);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOvertime.setAdapter(ad);

        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setValidator(DateValidatorPointForward.now());

        //MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select your date");
        builder.setCalendarConstraints(constraintBuilder.build());
        MaterialDatePicker materialDatePicker = builder.build();

        btnPickOvertime = findViewById(R.id.btnPickOvertime);
        btnPickOvertime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Long dateOvertime = selection;
                String startDateString = DateFormat.format("dd/MM/yyyy", new Date(dateOvertime)).toString();
                lblDateOvertime.setText(startDateString.toString());
            }
        });

        btnSubmitOvertime = findViewById(R.id.btnSubmitOvertime);
        btnSubmitOvertime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String overtimeDate = lblDateOvertime.getText().toString();
                String overtimeReason = spinnerOvertime.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), "Overtime Reason : \n" + overtimeDate + "\n" + overtimeReason , Toast.LENGTH_LONG).show();
                Intent i = new Intent(OvertimeActivity.this, AttendanceActivity.class);
                startActivity(i);
            }
        });

        btnBackOvertime = findViewById(R.id.btnBackOvertime);
        btnBackOvertime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OvertimeActivity.this, AttendanceActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id)
    {
//        Toast.makeText(getApplicationContext(), onLeaveReason[position], Toast.LENGTH_LONG).show();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}