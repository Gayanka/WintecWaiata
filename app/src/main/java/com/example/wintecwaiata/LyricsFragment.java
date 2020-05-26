package com.example.wintecwaiata;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsFragment extends Fragment {
    private VideoView videoView;
    private MediaController mediaController;

    public LyricsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        videoView = v.findViewById(R.id.videoLyrics);

        VideoPlayerActivity videoPlayerActivity = (VideoPlayerActivity) getActivity();
        Bundle results = null;
        try {
            results = videoPlayerActivity.setVideo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ArrayList<String> video = results.getStringArrayList("video");
        String videoPath = video.get(1);

//        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.ekorekoe_2;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        mediaController = new MediaController(v.getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        return v;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()){
            if (!isVisibleToUser){
                videoView.pause();
            }

            if (isVisibleToUser){
                videoView.pause();
            }
        }
    }

}
