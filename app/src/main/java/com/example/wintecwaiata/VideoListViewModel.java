package com.example.wintecwaiata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VideoListViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<VideoListView>> videoListView;

    public VideoListViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        videoListView = appRepository.getVideoListViews();
    }

    public void insert(VideoContent videoContent) {
        appRepository.insert(videoContent);
    }

    public void insert(VideoContentDetails videoContentDetails) {
        appRepository.insert(videoContentDetails);
    }

    public void update(VideoContent videoContent) {
        appRepository.update(videoContent);
    }

    public void update(VideoContentDetails videoContentDetails) {
        appRepository.update(videoContentDetails);
    }

    public void delete(VideoContent videoContent) {
        appRepository.delete(videoContent);
    }

    public void delete(VideoContentDetails videoContentDetails) {
        appRepository.delete(videoContentDetails);
    }


    public LiveData<List<VideoListView>> getVideoListView() {
        return videoListView;
    }

    public LiveData<List<VideoDetailsView>> getVideoDetails(int videoId) {
        return appRepository.getVideoDetails(videoId);
    }
}
