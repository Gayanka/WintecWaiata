package com.example.wintecwaiata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VideoListViewDao {
    @Query("SELECT * FROM VideoListView")
    LiveData<List<VideoListView>> getAllVideos();
}
