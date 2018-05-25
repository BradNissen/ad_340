package com.example.helloworld;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.helloworld.dao.UserDao;
import com.example.helloworld.entity.User;


@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}