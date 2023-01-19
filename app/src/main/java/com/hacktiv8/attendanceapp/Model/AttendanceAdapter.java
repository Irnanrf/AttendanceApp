package com.hacktiv8.attendanceapp.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.attendanceapp.R;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {

    private Context context;
    private List<AttendanceModel> attendanceList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View theView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        return new ViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AttendanceModel attendance = attendanceList.get(position);
        holder.tvDate.setText(attendance.getTanggal());
        holder.tvLocation.setText(attendance.getLokasi());
        holder.tvClockIn.setText(attendance.getClockIn());
        holder.tvClockOut.setText(attendance.getClockOut());
        holder.tvStatus.setText(attendance.getStatus());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate, tvLocation, tvClockIn, tvClockOut, tvStatus;

        public ViewHolder(View itemView){
            super(itemView);
            tvDate = itemView.findViewById(R.id.attendance_date);
            tvLocation = itemView.findViewById(R.id.attendance_location);
            tvClockIn = itemView.findViewById(R.id.attendance_clockin);
            tvClockOut = itemView.findViewById(R.id.attendance_clockout);
            tvStatus = itemView.findViewById(R.id.attendance_status);
        }
    }

    public AttendanceAdapter(Context context, List<AttendanceModel> attendanceList){
        this.context = context;
        this.attendanceList = attendanceList;
    }

}
