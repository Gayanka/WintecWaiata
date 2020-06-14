package com.example.wintecwaiata;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

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
    private int height_original;
    private ViewGroup.LayoutParams params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

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
        params = viewPager.getLayoutParams();
        height_original = params.height;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


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

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_fs = displayMetrics.heightPixels;

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
            mainTab.setVisibility(View.GONE);
            lyricsTab.setVisibility(View.GONE);
            pagerView.setVisibility(View.GONE);
            params.height = height_fs;
            viewPager.setLayoutParams(params);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mainTab.setVisibility(View.VISIBLE);
            lyricsTab.setVisibility(View.VISIBLE);
            pagerView.setVisibility(View.VISIBLE);
            params.height = height_original;
            viewPager.setLayoutParams(params);
            getSupportActionBar().show();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
