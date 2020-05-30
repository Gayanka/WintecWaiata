package com.example.wintecwaiata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface MultimediaDao {

    @Insert
    void insert(Multimedia multimedia);

    @Update
    void  update(Multimedia multimedia);

    @Delete
    void delete(Multimedia multimedia);


}
