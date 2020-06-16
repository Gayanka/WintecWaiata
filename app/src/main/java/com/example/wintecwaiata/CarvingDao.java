package com.example.wintecwaiata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface CarvingDao {

    @Insert
    void insert(Carving carving);

    @Update
    void update(Carving carving);

    @Delete
    void delete (Carving carving);

}
