package com.example.wintecwaiata;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "multimedia")
public class Multimedia {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String filename;

    public Multimedia(String filename) {
        this.filename = filename;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public int getId() {
        return id;
    }
}
