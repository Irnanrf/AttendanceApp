package com.hacktiv8.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.hacktiv8.attendanceapp.Model.AttendanceAdapter;
import com.hacktiv8.attendanceapp.Model.AttendanceModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AttendanceActivity extends AppCompatActivity {

    private TextView lblTanggal, lblJam, lblStatus;
    private Button btnClockIn, btnLeaveRequest;
    private ImageButton btnBackAttendance;

    private List<AttendanceModel> attendanceList;
    private AttendanceAdapter attendanceAdapter;
    private TextClock clockTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_attendance);

        lblTanggal = findViewById(R.id.lblTanggal);
        //lblJam = findViewById(R.id.lblJam);

        // Get Current Date
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMM yyyy");
        Date date = Calendar.getInstance().getTime();;
        System.out.println(formatter.format(date));
        lblTanggal.setText(String.valueOf(formatter.format(date)));



        // on below line we are initializing our variables.
        clockTC = findViewById(R.id.idTCClock);

        // on below line we are setting 12 hour
        // format for our text clock
        clockTC.setFormat24Hour("hh:mm");

        // RecyclerView
        RecyclerView attendanceRecyclerView = (RecyclerView) findViewById(R.id.rvHistory);
        attendanceList = new ArrayList<AttendanceModel>();

        attendanceList.add(new AttendanceModel("Monday, 09 Jan 2022", "Jakarta, Indonesia", "08:00", "17:00", "Clocked Out"));
        attendanceList.add(new AttendanceModel("Tuesday, 10 Jan 2022", "Jakarta, Indonesia", "08:00", "17:30", "Clocked Out"));
        attendanceList.add(new AttendanceModel("Wednesday, 11 Jan 2022", "Jakarta, Indonesia", "08:01", "17:00", "Clocked Out"));

        attendanceAdapter = new AttendanceAdapter(getApplicationContext(), attendanceList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        attendanceRecyclerView.setLayoutManager(layoutManager);
        attendanceRecyclerView.setAdapter(attendanceAdapter);

        //Check If Today Data Already In
        boolean found = false;
        for (int i = 0; i < attendanceList.size(); i++) {
            if(attendanceList.get(i).getTanggal().equals(lblTanggal.getText().toString()) && !attendanceList.get(i).getClockIn().equals("-") && !attendanceList.get(i).getClockOut().equals("-")){
                found = true;
            }
        }

        if(found){
            btnClockIn.setEnabled(false);
        }

        btnClockIn = findViewById(R.id.btnClockIn);
        btnClockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int LAUNCH_SECOND_ACTIVITY = 1;
                Intent i = new Intent(AttendanceActivity.this, ClockInActivity.class);
                i.putExtra("Clock", clockTC.getText().toString());
                startActivityForResult(i,LAUNCH_SECOND_ACTIVITY);
            }
        });


        btnBackAttendance = findViewById(R.id.btnBackAttendance);
        btnBackAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AttendanceActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        btnLeaveRequest = findViewById(R.id.btnLeaveRequest);
        btnLeaveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AttendanceActivity.this, LeaveRequestActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String location=data.getStringExtra("locationClockIn");
                String date=data.getStringExtra("dateClockIn");
                String time=data.getStringExtra("timeClockIn");
                //String reason=data.getStringExtra("reasonClockIn");

                boolean found = false;
                int index = -1;
                for (int i = 0; i < attendanceList.size(); i++) {
                    if(attendanceList.get(i).getTanggal().equals(date)){
                        index = i;
                        found = true;
                    }
                }

                if (!found){
                    Collections.reverse(attendanceList);
                    attendanceList.add(new AttendanceModel(date, location, time, "-", "Clocked In"));
                    Collections.reverse(attendanceList);
                    attendanceAdapter.notifyDataSetChanged();

                    Toast.makeText(AttendanceActivity.this, "Clocked In", Toast.LENGTH_SHORT).show();
                } else {
                    attendanceList.set(index, new AttendanceModel(date, location, time, time,"Clocked Out"));
                    Toast.makeText(AttendanceActivity.this, "Clocked Out", Toast.LENGTH_SHORT).show();
                    attendanceAdapter.notifyDataSetChanged();
                }

                // Check if today data already filled
                boolean filled = false;
                for (int i = 0; i < attendanceList.size(); i++) {
                    if(attendanceList.get(i).getTanggal().equals(lblTanggal.getText().toString()) && !attendanceList.get(i).getClockIn().equals("-") && !attendanceList.get(i).getClockOut().equals("-")){
                        filled = true;
                    }
                }

                if(filled){
                    btnClockIn.setEnabled(false);
                }


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }
}