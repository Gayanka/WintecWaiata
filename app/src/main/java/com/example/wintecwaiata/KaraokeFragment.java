package com.example.wintecwaiata;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.lang.reflect.Field;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.wintecwaiata.MainPageFragment.VIDEO_ID_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaraokeFragment extends Fragment {
    private VideoListViewModel videoListViewModel;
    private int videoCode;
    private String video;
    private UniversalVideoView mVideoView;
    private UniversalMediaController mMediaController;


    public KaraokeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.fragment_karaoke, container, false);
//        videoView = v.findViewById(R.id.videoKaraoke);
        mVideoView = v.findViewById(R.id.videoKaraoke);
        mMediaController = v.findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);

        //get the mother activity and load the video location
        Intent intent = getActivity().getIntent();
        videoCode = intent.getIntExtra(VIDEO_ID_NAME, 0);
        Log.d(TAG, "onCreateView: " + videoCode);

        videoListViewModel = new ViewModelProvider(this, new VideoListViewModelFactory(getActivity().getApplication())).get(VideoListViewModel.class);
        videoListViewModel.getVideoDetails(videoCode).observe(this, new Observer<List<VideoDetailsView>>() {
            @Override
            public void onChanged(List<VideoDetailsView> videoDetailsViews) {
                try {
                    video = videoDetailsViews.get(0).getKaraokeFile();
                    if (video.contains(".")) {
                        video = video.substring(0, video.indexOf("."));
                    }
                    Field field = R.raw.class.getField(video);
                    String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + field.getInt(null);
                    Uri uri = Uri.parse(videoPath);
                    mVideoView.setVideoURI(uri);
                    //mVideoView.start();
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        mVideoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            @Override
            public void onScaleChange(boolean isFullscreen) {
                int flg = getActivity().getWindow().getAttributes().flags;
                if ((flg & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN){
                    isFullscreen = true;
                }
                if (isFullscreen) {
                    //write your code if need to do something in Full Screen

                } else {

                }
            }

            @Override
            public void onPause(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onStart(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {

            }
        });


        return v;
    }

    //pause the video if fragment change
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
