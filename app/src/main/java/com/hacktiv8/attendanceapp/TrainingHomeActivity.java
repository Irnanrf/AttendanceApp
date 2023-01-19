package com.hacktiv8.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hacktiv8.attendanceapp.Model.AttendanceModel;
import com.hacktiv8.attendanceapp.Model.CourseAdapter;
import com.hacktiv8.attendanceapp.Model.CourseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingHomeActivity extends AppCompatActivity {

    ImageButton btnBackTraining;
    RecyclerView rvCourse;
    CourseAdapter clubAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_training_home);

        rvCourse = (RecyclerView) findViewById(R.id.rvCourse);
        List<CourseModel> courseList = new ArrayList<>();
        courseList.add(new CourseModel("Perdagangan yang Bikin Satu Negara Kecanduan Zat Terlarang", "https://www.freepnglogos.com/uploads/play-button-png/play-button-blank-blank-and-pbs-animated-interviews-10.png", "KQJGZdkKIKY"));
        courseList.add(new CourseModel("Lebih Penting Mana: Pintar, Sehat, atau Sejahtera?", "https://www.freepnglogos.com/uploads/play-button-png/play-button-blank-blank-and-pbs-animated-interviews-10.png", "o0TtAq4e3K0"));

        clubAdapter = new CourseAdapter(getApplicationContext(), courseList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvCourse.setLayoutManager(layoutManager);
        rvCourse.setAdapter(clubAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String videoTitle = bundle.getString("videoTitle");
            if(videoTitle.equals("")){

            } else {
                boolean found = false;
                int index = -1;
                for (int i = 0; i < courseList.size(); i++) {
                    if(courseList.get(i).getTitle().equals(videoTitle)){
                        index = i;
                        found = true;
                    }
                }

                if (!found){

                } else {
                    System.out.println("Found Index ; " + index);
                    String courseTitle = courseList.get(index).getTitle().toString();
                    String courseLocation = courseList.get(index).getLocation().toString();
                    courseList.set(index, new CourseModel(courseTitle, "https://cdn-icons-png.flaticon.com/512/8215/8215539.png", courseLocation));
                    clubAdapter.notifyDataSetChanged();
                }
            }
        }

        btnBackTraining = findViewById(R.id.btnBackTraining);
        btnBackTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrainingHomeActivity.this, HomeActivity.class);
                startActivity(i);

            }
        });
    }
}