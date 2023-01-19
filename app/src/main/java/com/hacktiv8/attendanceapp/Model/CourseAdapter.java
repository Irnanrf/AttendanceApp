package com.hacktiv8.attendanceapp.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.attendanceapp.R;
import com.hacktiv8.attendanceapp.TrainingTestActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    private Context context;
    private List<CourseModel> courseList;

    public int REQUEST_CODE = 111;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View theView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_layout, parent, false);
        return new ViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModel course = courseList.get(position);
        holder.tvCourseName.setText(course.getTitle());
        Picasso.get().load(course.getLogo()).fit().centerInside().into(holder.imgCourseLogo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendData1 = new Intent(context, TrainingTestActivity.class);
                sendData1.putExtra("videoTitle", course.getTitle());
                sendData1.putExtra("videoSource", course.getLocation());
                context.startActivity(sendData1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCourseName;
        public ImageView imgCourseLogo;

        public ViewHolder(View itemView){
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.lblVideoTitle);
            imgCourseLogo = itemView.findViewById(R.id.course_logo);
        }
    }

    public CourseAdapter(Context context, List<CourseModel> courseList){
        this.context = context;
        this.courseList = courseList;
    }
}
