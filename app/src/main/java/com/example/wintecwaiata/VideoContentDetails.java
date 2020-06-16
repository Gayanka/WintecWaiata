package com.example.wintecwaiata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_content_details")
public class VideoContentDetails {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "video_id")
    private int videoId;
    @ColumnInfo(name = "multimedia_id_karaoke")
    private int multimediaIdKaraoke;
    @ColumnInfo(name = "multimedia_id_lyrics")
    private int multimediaIdLyrics;
    @ColumnInfo(name = "multimedia_id_audio")
    private int multimediaIdAudio;
    @ColumnInfo(name = "text_maori")
    private String textMaori;
    @ColumnInfo(name = "text_english")
    private String textEnglish;

    public VideoContentDetails(int multimediaIdKaraoke, int multimediaIdLyrics, int multimediaIdAudio, String textMaori, String textEnglish, int videoId) {
        this.multimediaIdKaraoke = multimediaIdKaraoke;
        this.multimediaIdLyrics = multimediaIdLyrics;
        this.multimediaIdAudio = multimediaIdAudio;
        this.textMaori = textMaori;
        this.textEnglish = textEnglish;
        this.videoId = videoId;
    }

    public int getId() {
        return id;
    }

    public int getMultimediaIdKaraoke() {
        return multimediaIdKaraoke;
    }

    public int getMultimediaIdLyrics() {
        return multimediaIdLyrics;
    }

    public int getMultimediaIdAudio() {
        return multimediaIdAudio;
    }

    public String getTextMaori() {
        return textMaori;
    }

    public String getTextEnglish() {
        return textEnglish;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
