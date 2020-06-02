package com.example.wintecwaiata;

import androidx.room.DatabaseView;

@DatabaseView("SELECT\n" +
        "    video_content.id,\n" +
        "    video_content.title,\n" +
        "    video_content.priority,\n" +
        "    multimedia.filename\n" +
        "FROM\n" +
        "    video_content \n" +
        "        LEFT JOIN multimedia\n" +
        "        ON video_content.multimedia_id = multimedia.id" +
        " \n" +
        "    ")
public class VideoListView {
    private int id;
    private String title;
    private int priprity;
    private String filename;

    public VideoListView(int id, String title, int priprity, String filename) {
        this.id = id;
        this.title = title;
        this.priprity = priprity;
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPriprity() {
        return priprity;
    }

    public String getFilename() {
        return filename;
    }
}
