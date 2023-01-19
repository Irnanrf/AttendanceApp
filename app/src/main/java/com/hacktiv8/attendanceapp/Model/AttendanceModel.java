package com.hacktiv8.attendanceapp.Model;

public class AttendanceModel {

    String tanggal;
    String lokasi;
    String clockIn;
    String clockOut;
    String status;

    public AttendanceModel(String tanggal, String lokasi, String clockIn, String clockOut, String status) {
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getClockIn() {
        return clockIn;
    }

    public void setClockIn(String clockIn) {
        this.clockIn = clockIn;
    }

    public String getClockOut() {
        return clockOut;
    }

    public void setClockOut(String clockOut) {
        this.clockOut = clockOut;
    }
}
