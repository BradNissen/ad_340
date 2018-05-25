package com.example.helloworld.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    private String email = "";

    @ColumnInfo
    private int hour;

    @ColumnInfo
    private int minute;

    @ColumnInfo(name = "search_distance")
    private int searchDistance;

    @ColumnInfo(name = "is_public")
    private boolean isPublic;

    @ColumnInfo
    private String gender;

    @ColumnInfo(name = "age_range")
    private int ageRange;







    @NonNull
    public String getEmail() {
        return email;
    }
    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSearchDistance() {
        return searchDistance;
    }

    public void setSearchDistance(int searchDistance) {
        this.searchDistance = searchDistance;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int ageRange) {
        this.ageRange = ageRange;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", hour=" + hour +
                ", minute=" + minute +
                ", searchDistance=" + searchDistance +
                ", isPublic=" + isPublic +
                ", gender='" + gender + '\'' +
                ", ageRange=" + ageRange +
                '}';
    }
}