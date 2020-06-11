package com.example.wintecwaiata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.wintecwaiata.MainPageFragment.*;


public class VideoPlayerActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private VideoListViewModel videoListViewModel;

    private TabLayout mainTab;
    private TabItem tabKaraoke,tabLyrics,tabAudio;
    private ViewPager viewPager;
    private int videoCode;
    private String videoName;
    private ActionBar actionBar;
    ArrayList<String> videoLocation = new ArrayList<String>(3);
    ReadAllRawData readAllRawData = new ReadAllRawData();
//    SelectedBundle selectedBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        //initializing the tab items and viewpager
        mainTab = findViewById(R.id.tabLayout);
        tabKaraoke = findViewById(R.id.tabKaraoke);
        tabLyrics = findViewById(R.id.tabLyrics);
        tabAudio = findViewById(R.id.tabAudio);
        viewPager = findViewById(R.id.viewPager);
        actionBar = getSupportActionBar();

        //adding the pager adapter
//        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),
//                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
//                mainTab.getTabCount());

        Intent intent = getIntent();
        videoCode = intent.getIntExtra(VIDEO_ID_NAME, 0);
        videoName = intent.getStringExtra(VIDEO_TITLE_NAME);
        actionBar.setTitle(videoName);

        videoListViewModel = new ViewModelProvider(this, new VideoListViewModelFactory(this.getApplication())).get(VideoListViewModel.class);
        videoListViewModel.getVideoDetails(videoCode).observe(this, new Observer<List<VideoDetailsView>>() {
            @Override
            public void onChanged(List<VideoDetailsView> videoDetailsViews) {
                videoDetailsViews.get(0).getTextMaori();
                videoDetailsViews.get(0).getTextEnglish();
            }
        });


        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), mainTab.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTab));
    }

}
