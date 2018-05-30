package com.example.helloworld.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MatchItem implements Parcelable {

    public String imageUrl;
    public boolean favorite;
    public String name;
    public String uid;
    public String lat;
    public String longitude;

    public MatchItem() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
//
//    public MatchItem(String imageUrl, boolean liked, String name) {
//        this.imageUrl = imageUrl;
//        this.favorite = liked;
//        this.name = name;
//    }

    public MatchItem(Parcel in) {
//        imageUrl = in.readString();
//        favorite = in.readByte() != 0;
//        name = in.readString();

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
//        result.put("liked", favorite);
//        result.put("name", name);
//        result.put("uid", uid);
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
//        dest.writeByte((byte) (favorite ? 1 : 0));
//        dest.writeString(name);
    }
}
