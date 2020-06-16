package com.example.wintecwaiata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ExternalHTTPDao {
    @Insert
    void insert(ExternalHTTP externalHTTP);

    @Update
    void update(ExternalHTTP externalHTTP);

    @Delete
    void delete(ExternalHTTP externalHTTP);

    @Query("SELECT\n" +
            "    http\n" +
            "FROM\n" +
            "    external_http_resources\n" +
            "WHERE\n" +
            "    activity_name = :activityName\n" +
            "    ")
    LiveData<String> getExternalHTTP(String activityName);

    @Query("SELECT\n" +
            "    http\n" +
            "FROM\n" +
            "    external_http_resources\n" +
            "WHERE activity_name = :activityName AND item_id = :itemId")
    LiveData<String> getExternalHTTP(String activityName, int itemId);
}
