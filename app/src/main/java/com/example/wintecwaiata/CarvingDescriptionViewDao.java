package com.example.wintecwaiata;

import androidx.room.Query;

import java.util.List;

public interface CarvingDescriptionViewDao {
    @Query("SELECT\n" +
            "    description,\n" +
            "    filename\n" +
            "FROM\n" +
            "    carvingdescriptionview\n" +
            "WHERE\n" +
            "    carving_id = :carvingId")
    List<CarvingDescriptionView> getDescription(int carvingId);
}
