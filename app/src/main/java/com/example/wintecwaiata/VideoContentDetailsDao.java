package com.example.wintecwaiata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface VideoContentDetailsDao {
    @Insert
    void insert(VideoContentDetails videoContentDetails);

    @Update
    void update(VideoContentDetails videoContentDetails);

    @Delete
    void delete(VideoContentDetails videoContentDetails);
}
