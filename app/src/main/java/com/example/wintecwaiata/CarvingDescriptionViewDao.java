package com.example.wintecwaiata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarvingDescriptionViewDao {
    @Query("SELECT\n" +
            "    carvingdescriptionview.carving_id,\n" +
            "    carvingdescriptionview.description,\n" +
            "    carvingdescriptionview.filename,\n" +
            "    carvingdescriptionview.fileData\n" +
            "FROM\n" +
            "    carvingdescriptionview\n" +
            "WHERE\n" +
            "    carvingdescriptionview.carving_id = :carvingId")
    LiveData<List<CarvingDescriptionView>> getDescription(int carvingId);
}
