package com.example.wintecwaiata;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;


@Entity(tableName = "multimedia")
public class Multimedia {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String filename;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] fileData;

    public Multimedia(String filename) {
        this.filename = filename;
    }

    public Multimedia(String filename, Context context) throws Exception {
        this.filename = filename;
        if (!this.filename.equals("")) {
            BitmapDrawable bmd = null;
            try {
                bmd = (BitmapDrawable)context.getResources().getDrawable(context.getResources().getIdentifier(filename.substring(0, filename.indexOf(".")), "drawable", context.getPackageName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap bitmap = bmd.getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            fileData = byteArrayOutputStream.toByteArray();
        }
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
