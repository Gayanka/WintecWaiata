package com.example.wintecwaiata;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
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
    private TabLayout lyricsTab;
    private TabItem tabKaraoke,tabLyrics,tabAudio;
    private ViewPager viewPager;
    private ViewPager pagerView;
    private int videoCode;
    private String videoName;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        //initializing the tab items and viewpager
        mainTab = findViewById(R.id.tabLayout);
        lyricsTab = findViewById(R.id.tabLyricsView);
        tabKaraoke = findViewById(R.id.tabKaraoke);
        tabLyrics = findViewById(R.id.tabLyrics);
        tabAudio = findViewById(R.id.tabAudio);
        viewPager = findViewById(R.id.viewPager);
        pagerView = findViewById(R.id.pagerView);
        actionBar = getSupportActionBar();

        //adding the pager adapter
//        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),
//                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
//                mainTab.getTabCount());

        Intent intent = getIntent();
        videoCode = intent.getIntExtra(VIDEO_ID_NAME, 0);
        videoName = intent.getStringExtra(VIDEO_TITLE_NAME);
        actionBar.setTitle(videoName);

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

        LyricsPagerAdapter lyricsPagerAdapter = new LyricsPagerAdapter(getSupportFragmentManager(),lyricsTab.getTabCount());
        pagerView.setAdapter(lyricsPagerAdapter);

        lyricsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerView.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pagerView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(lyricsTab));
    }

}
