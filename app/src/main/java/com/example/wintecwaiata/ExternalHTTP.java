package com.example.wintecwaiata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "external_http_resources")
public class ExternalHTTP {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "activity_name")
    private String activityName;
    @ColumnInfo(name = "item_id")
    private int itemId;
    private String http;

    public ExternalHTTP(String activityName, int itemId, String http) {
        this.activityName = activityName;
        this.itemId = itemId;
        this.http = http;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getActivityName() {
        return activityName;
    }

    public int getItemId() {
        return itemId;
    }

    public String getHttp() {
        return http;
    }
}
