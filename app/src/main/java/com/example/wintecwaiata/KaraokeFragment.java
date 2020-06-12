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

import java.lang.reflect.Field;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.wintecwaiata.MainPageFragment.VIDEO_ID_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaraokeFragment extends Fragment {
    private VideoView videoView;
    private MediaController mediaController;
    private VideoListViewModel videoListViewModel;
    private int videoCode;
    private String video;


    public KaraokeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.fragment_karaoke, container, false);
        videoView = v.findViewById(R.id.videoKaraoke);

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
                    videoView.setVideoURI(uri);
                    mediaController = new MediaController(v.getContext());
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    videoView.start();
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
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
                videoView.pause();
                mediaController.hide();
            }

            if (isVisibleToUser){
                mediaController.show();
            }
        }
    }


    @Override
    public void onResume() {
        videoView.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        videoView.suspend();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
