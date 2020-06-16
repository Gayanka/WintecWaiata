package com.example.wintecwaiata;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CarvingWithDescription {
    @Embedded public Carving carving;
    @Relation(
            parentColumn = "id",
            entityColumn = "carvingId"
    )
    public List<CarvingDescription> carvingDescriptionList;
}
