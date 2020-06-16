package com.example.wintecwaiata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarvingListViewDao {
    @Query("SELECT\n" +
            "    id,\n" +
            "    title,\n" +
            "    picture_num,\n" +
            "    filename\n" +
            "FROM\n" +
            "    carvinglistview")
    LiveData<List<CarvingListView>> getCarvingList();
}
