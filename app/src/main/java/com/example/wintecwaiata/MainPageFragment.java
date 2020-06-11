package com.example.wintecwaiata;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MainPageFragment extends Fragment {
    private VideoListViewModel videoListViewModel;
    private RecyclerView recyclerView;
    private TextView textView_songLength;
    public static final String VIDEO_ID_NAME = "video_id";
    public static final String VIDEO_TITLE_NAME = "video_title";
    public static final String VIDEO_FILE_NAME = "video_file_name";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.mainpagefragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_videolist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final VideoListAdapter videoListAdapter = new VideoListAdapter();
        recyclerView.setAdapter(videoListAdapter);

        textView_songLength = view.findViewById(R.id.textView_songLength);


        videoListViewModel = new ViewModelProvider(this, new VideoListViewModelFactory(this.getActivity().getApplication()))
                .get(VideoListViewModel.class);
        videoListViewModel.getVideoListView().observe(this, new Observer<List<VideoListView>>() {
            @Override
            public void onChanged(final List<VideoListView> videoListViews) {
                videoListAdapter.setVideoList(videoListViews);
                textView_songLength.setText(videoListAdapter.getItemCount() + " songs");
                videoListAdapter.setOnItemClickListener(new VideoListAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(view.getContext(), VideoPlayerActivity.class);
                        intent.putExtra(VIDEO_ID_NAME, videoListViews.get(position).getId());
                        //Log.d(TAG, "OnItemClick: " + videoListViews.get(position).getId());
                        intent.putExtra(VIDEO_TITLE_NAME, videoListViews.get(position).getTitle());
                        startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
}
