package com.example.wintecwaiata;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.wintecwaiata.MainPageFragment.VIDEO_ID_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsFragment extends Fragment {
    private UniversalVideoView mVideoView;
    private UniversalMediaController mMediaController;
    private int videoCode;
    private VideoListViewModel videoListViewModel;
    private String video;

    public LyricsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        mVideoView = v.findViewById(R.id.videoLyrics);
        mMediaController = v.findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);

        Intent intent = getActivity().getIntent();
        videoCode = intent.getIntExtra(VIDEO_ID_NAME, 0);
        Log.d(TAG, "onCreateView: " + videoCode);


        videoListViewModel = new ViewModelProvider(this, new VideoListViewModelFactory(getActivity().getApplication())).get(VideoListViewModel.class);
        videoListViewModel.getVideoDetails(videoCode).observe(this, new Observer<List<VideoDetailsView>>() {
            @Override
            public void onChanged(List<VideoDetailsView> videoDetailsViews) {
                try {
                    video = videoDetailsViews.get(0).getLyricsFile();
                    if (video.contains(".")) {
                        video = video.substring(0, video.indexOf("."));
                    }
                    Field field = R.raw.class.getField(video);
                    String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + field.getInt(null);
                    Uri uri = Uri.parse(videoPath);
                    mVideoView.setVideoURI(uri);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()){
            if (!isVisibleToUser){
                mVideoView.pause();
                mMediaController.hide();
            }

            if (isVisibleToUser){
                mMediaController.show();
            }
        }
    }

    @Override
    public void onResume() {
        mVideoView.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mVideoView.suspend();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mVideoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
