package com.example.wintecwaiata;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MultimediaWithCarvingDescription {
    @Embedded public Multimedia multimedia;
    @Relation(
            parentColumn = "id",
            entityColumn = "multimediaId"
    )
    public List<CarvingDescription> carvingDescriptionList;
}
