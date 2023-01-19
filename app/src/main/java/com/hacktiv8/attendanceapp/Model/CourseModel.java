package com.hacktiv8.attendanceapp.Model;

public class CourseModel {

    String title;
    String logo;
    String location;

    public CourseModel(String title, String logo, String location) {
        this.title = title;
        this.logo = logo;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public String getLocation() {
        return location;
    }
}
