package com.example.wintecwaiata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface VideoContentDao {

    @Insert
    void insert(VideoContent videoContent);

    @Update
    void update(VideoContent videoContent);

    @Delete
    void delete(VideoContent videoContent);

}
