package com.example.wintecwaiata;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.wintecwaiata.MainPageFragment.VIDEO_ID_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class MaoriLyricsFragment extends Fragment {
    private TextView lyrics;
    private VideoListViewModel videoListViewModel;
    private String mLyrics;
    private int videoCode;

    public MaoriLyricsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_maori__lyrics, container, false);
        lyrics = v.findViewById(R.id.maoriLyrics);

        Intent intent = getActivity().getIntent();
        videoCode = intent.getIntExtra(VIDEO_ID_NAME, 0);

        videoListViewModel = new ViewModelProvider(this, new VideoListViewModelFactory(getActivity().getApplication())).get(VideoListViewModel.class);
        videoListViewModel.getVideoDetails(videoCode).observe(this, new Observer<List<VideoDetailsView>>() {
            @Override
            public void onChanged(List<VideoDetailsView> videoDetailsViews) {
                mLyrics = videoDetailsViews.get(0).getTextMaori();
                lyrics.setText(mLyrics);
            }
        });
        return v;
    }
}
