package com.example.wintecwaiata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.ByteArrayOutputStream;



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
