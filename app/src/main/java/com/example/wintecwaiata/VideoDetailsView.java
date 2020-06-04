package com.example.wintecwaiata;

import androidx.room.DatabaseView;

@DatabaseView("SELECT \n" +
        "    video_content_details.video_id as videoId,\n" +
        "    video_content_details.text_maori as textMaori,\n" +
        "    video_content_details.text_english as textEnglish,\n" +
        "    multimedia_karaoke.filename as karaokeFile,\n" +
        "    multimedia_lyrics.filename as lyricsFile,\n" +
        "    multimedia_audio.filename as audioFile\n" +
        "FROM\n" +
        "    video_content_details \n" +
        "        LEFT JOIN multimedia multimedia_karaoke\n" +
        "        ON video_content_details.multimedia_id_karaoke = multimedia_karaoke.id\n" +
        "        LEFT JOIN multimedia multimedia_lyrics\n" +
        "        ON video_content_details.multimedia_id_lyrics = multimedia_lyrics.id\n" +
        "        LEFT JOIN multimedia multimedia_audio\n" +
        "        " +
        "ON video_content_details.multimedia_id_audio = multimedia_audio.id")
public class VideoDetailsView {
    private int videoId;
    private String textMaori;
    private String textEnglish;
    private String karaokeFile;
    private String lyricsFile;
    private String audioFile;

    public VideoDetailsView(int videoId, String textMaori, String textEnglish, String karaokeFile, String lyricsFile, String audioFile) {
        this.videoId = videoId;
        this.textMaori = textMaori;
        this.textEnglish = textEnglish;
        this.karaokeFile = karaokeFile;
        this.lyricsFile = lyricsFile;
        this.audioFile = audioFile;
    }

    public int getVideoId() {
        return videoId;
    }

    public String getTextMaori() {
        return textMaori;
    }

    public String getTextEnglish() {
        return textEnglish;
    }

    public String getKaraokeFile() {
        return karaokeFile;
    }

    public String getLyricsFile() {
        return lyricsFile;
    }

    public String getAudioFile() {
        return audioFile;
    }
}
