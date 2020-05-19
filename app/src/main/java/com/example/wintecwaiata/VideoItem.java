package com.example.wintecwaiata;

import android.graphics.Bitmap;

public class VideoItem {
    Bitmap imageItemThumbnail;
    String videoTitle;

    public VideoItem(Bitmap imageItemThumbnail, String videoTitle) {
        this.imageItemThumbnail = imageItemThumbnail;
        this.videoTitle = videoTitle;
    }

    public Bitmap getImageItemThumbnail() {
        return imageItemThumbnail;
    }

    public void setImageItemThumbnail(Bitmap imageItemThumbnail) {
        this.imageItemThumbnail = imageItemThumbnail;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

}
