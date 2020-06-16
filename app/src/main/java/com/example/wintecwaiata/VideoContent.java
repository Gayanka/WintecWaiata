package com.example.wintecwaiata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_content")
public class VideoContent {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    @ColumnInfo (name = "multimedia_id")
    private int multimediaId;
    private int priority;

    public VideoContent(String title, int multimediaId, int priority) {
        this.title = title;
        this.multimediaId = multimediaId;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMultimediaId() {
        return multimediaId;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }
}
