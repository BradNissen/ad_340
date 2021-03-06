package com.example.helloworld.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class MatchItem implements Parcelable {

    public String imageUrl;
    public boolean liked;
    public String name;
    public String uid;
    public String lat;
    public String longitude;

    public MatchItem() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

//    public MatchItem(String imageUrl, boolean liked, String name, String lat, String longitude) {
//        this.imageUrl = imageUrl;
//        this.liked = liked;
//        this.name = name;
//        this.lat = lat;
//        this.longitude = longitude;
//    }

    public MatchItem(Parcel in) {
        imageUrl = in.readString();
        liked = in.readByte() != 0;
        name = in.readString();
        lat = in.readString();
        longitude = in.readString();

    }

    public static final Creator<MatchItem> CREATOR = new Creator<MatchItem>() {
        @Override
        public MatchItem createFromParcel(Parcel in) {
            return new MatchItem(in);
        }

        @Override
        public MatchItem[] newArray(int size) {
            return new MatchItem[size];
        }
    };

//    @Exclude
//    public Map<String, Object> toMap() {
//
//        HashMap<String, Object> result = new HashMap<>();
//
//        result.put("imageUrl", imageUrl);
//        result.put("liked", liked);
//        result.put("name", name);
//        result.put("uid", uid);
//        result.put("lat", lat);
//        result.put("longitude", longitude);
//
//        return result;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

//        dest.writeString(imageUrl);
//        dest.writeByte((byte) (liked ? 1 : 0));
//        dest.writeString(name);
//        dest.writeString(lat);
//        dest.writeString(longitude);
    }
}
