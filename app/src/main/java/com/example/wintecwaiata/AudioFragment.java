package com.example.wintecwaiata;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

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
public class AudioFragment extends Fragment {
    private VideoView videoView;
    private MediaController mediaController;
    private int videoCode;
    private VideoListViewModel videoListViewModel;
    private String video;

    public AudioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_audio, container, false);
        videoView = v.findViewById(R.id.videoAudio);
        Intent intent = getActivity().getIntent();
        videoCode = intent.getIntExtra(VIDEO_ID_NAME, 0);
        videoListViewModel = new ViewModelProvider(this, new VideoListViewModelFactory(getActivity().getApplication())).get(VideoListViewModel.class);
        videoListViewModel.getVideoDetails(videoCode).observe(this, new Observer<List<VideoDetailsView>>() {
            @Override
            public void onChanged(List<VideoDetailsView> videoDetailsViews) {
                try {
                    video = videoDetailsViews.get(0).getAudioFile();
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
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }
}
