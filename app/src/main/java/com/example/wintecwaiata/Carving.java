package com.example.wintecwaiata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carving")
public class Carving {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    @ColumnInfo(name = "multimedia_id")
    private int multimediaId;
    private int priority;

    public Carving(String title, int multimediaId, int priority) {
        this.title = title;
        this.multimediaId = multimediaId;
        this.priority = priority;
    }


    public void setId(int id) {
        this.id = id;
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

}
