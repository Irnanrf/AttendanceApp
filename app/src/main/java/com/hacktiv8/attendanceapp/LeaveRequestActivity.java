package com.hacktiv8.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.Activity;
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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LeaveRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button btnPick;
    private TextView lblStartDate, lblEndDate;
    private Spinner spinnerOnLeave;
    private Button btnSubmitLeaveRequest;
    private ImageButton btnBackLeaveRequest;

    String[] onLeaveReason = { "Sick Leave", "Family Emergency", "Doctor's Appointment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_leave_request);
        lblStartDate = findViewById(R.id.lblStartDate);
        lblEndDate = findViewById(R.id.lblEndDate);

        spinnerOnLeave = findViewById(R.id.spinnerOnLeave);
        spinnerOnLeave.setOnItemSelectedListener(this);



        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, onLeaveReason);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOnLeave.setAdapter(ad);


        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setValidator(DateValidatorPointForward.now());

        //MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select your date");
        builder.setCalendarConstraints(constraintBuilder.build());
        MaterialDatePicker materialDatePicker = builder.build();

        btnPick = findViewById(R.id.btnPick);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long,Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;
                String startDateString = DateFormat.format("dd/MM/yyyy", new Date(startDate)).toString();
                String endDateString = DateFormat.format("dd/MM/yyyy", new Date(endDate)).toString();
                lblStartDate.setText(startDateString);
                lblEndDate.setText(endDateString);

            }
        });

        btnSubmitLeaveRequest = findViewById(R.id.btnSubmitLeaveRequest);
        btnSubmitLeaveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate = lblStartDate.getText().toString();
                String endDate = lblEndDate.getText().toString();
                String onLeaveReason = spinnerOnLeave.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), "On Leave Reason : \n" + startDate + " - " + endDate + "\n" + onLeaveReason , Toast.LENGTH_LONG).show();
                Intent i = new Intent(LeaveRequestActivity.this, AttendanceActivity.class);
                startActivity(i);
            }
        });

        btnBackLeaveRequest = findViewById(R.id.btnBackLeaveRequest);
        btnBackLeaveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LeaveRequestActivity.this, AttendanceActivity.class);
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