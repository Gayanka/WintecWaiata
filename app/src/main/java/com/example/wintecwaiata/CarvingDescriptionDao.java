package com.example.wintecwaiata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CarvingDescriptionDao {

    @Insert
    void insert(CarvingDescription carvingDescription);

    @Update
    void update(CarvingDescription carvingDescription);

    @Delete
    void delete(CarvingDescription carvingDescription);

}
