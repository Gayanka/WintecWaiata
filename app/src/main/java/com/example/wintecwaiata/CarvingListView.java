package com.example.wintecwaiata;

import androidx.room.DatabaseView;

@DatabaseView("SELECT\n" +
        "    CarvingsWithPictureNum.id,\n" +
        "    CarvingsWithPictureNum.title,\n" +
        "    CarvingsWithPictureNum.picture_num,\n" +
        "    Multimedia.filename\n" +
        "FROM\n" +
        "(SELECT \n" +
        "    " +
        "carving.id AS id, \n" +
        "    carving.title AS title, \n" +
        "    carving.multimedia_id AS multimedia_id, \n" +
        "    COUNT(carving_description.id) AS picture_num \n" +
        "FROM carving \n" +
        "    " +
        "LEFT JOIN carving_description \n" +
        "    ON carving.id = carving_description.carving_id \n" +
        "GROUP BY \n" +
        "    " +
        "carving.id, \n" +
        "    carving.title, \n" +
        "    carving.multimedia_id \n" +
        "ORDER BY carving.priority) AS CarvingsWithPictureNum LEFT JOIN multimedia ON CarvingsWithPictureNum.multimedia_id = multimedia.id")
public class CarvingListView {
    private int id;
    private String title;
    private int picture_num;
    private String filename;

    public CarvingListView(int id, String title, int picture_num, String filename) {
        this.id = id;
        this.title = title;
        this.picture_num = picture_num;
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPicture_num() {
        return picture_num;
    }

    public String getFilename() {
        return filename;
    }
}
