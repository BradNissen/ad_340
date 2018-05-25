package com.example.helloworld;


import android.arch.persistence.room.Room;
import android.content.Context;

public class AppDatabaseSingleton {

    private static AppDatabase db;

    public static AppDatabase getDatabase(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context,
                    AppDatabase.class, "User")
                    .build();
        }

        return db;
    }

}