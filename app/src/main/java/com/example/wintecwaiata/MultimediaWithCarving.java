package com.example.wintecwaiata;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MultimediaWithCarving {
    @Embedded public Multimedia multimedia;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userCreatorId"
    )
    public List<Carving> carvingList;
}
