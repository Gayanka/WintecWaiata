package com.example.wintecwaiata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VideoDetailsDao {
    @Query("SELECT * FROM VideoDetailsView WHERE videoId = :videoId")
    LiveData<List<VideoDetailsView>> getVideoDetails(int videoId);
}
