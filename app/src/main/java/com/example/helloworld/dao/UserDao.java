package com.example.helloworld.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.helloworld.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE email IN (:emails)")
    List<User> loadAllByIds(String[] emails);

    @Update
    void updateUsers(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);
}
