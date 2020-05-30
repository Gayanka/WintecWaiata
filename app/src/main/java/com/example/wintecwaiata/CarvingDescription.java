package com.example.wintecwaiata;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carving_description")
public class CarvingDescription {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "carving_id")
    private int carvingId;
    private String description;
    @ColumnInfo(name = "multimedia_id")
    private int multimediaId;

    public CarvingDescription(int carvingId, String description, int multimediaId) {
        this.carvingId = carvingId;
        this.description = description;
        this.multimediaId = multimediaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarvingId() {
        return carvingId;
    }

    public String getDescription() {
        return description;
    }

    public int getMultimediaId() {
        return multimediaId;
    }
}
