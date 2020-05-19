package com.example.wintecwaiata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;


public class VideoPlayerActivity extends AppCompatActivity {
    private TabLayout mainTab;
    private TabItem tabKaraoke,tabLyrics,tabAudio;
    private ViewPager viewPager;
    private int videoCode;
    private String videoName;
    private ActionBar actionBar;
    ArrayList<String> videoLocation;
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
        videoCode = intent.getIntExtra("videoCode", 0);
        videoName = intent.getStringExtra("videoName");
        actionBar.setTitle(videoName);
        try {
            setVideo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

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

    //send selected video to fragment
    public Bundle setVideo() throws IllegalAccessException {

        readAllRawData.listRaw();

            if (videoCode == 0) {
                videoLocation = readAllRawData.listEkorekoe;
            } else if (videoCode == 1) {
                videoLocation = readAllRawData.listHemaimaiaroha;
            } else if (videoCode == 2) {
                videoLocation = readAllRawData.listWaikatoteawa;
            } else if (videoCode == 3) {
                videoLocation = readAllRawData.listTutiramainga;
            } else if (videoCode == 4) {
                videoLocation = readAllRawData.listpupuketehihiri;
            } else if (videoCode == 5) {
                videoLocation = readAllRawData.listItewhare;
            } else if (videoCode == 6) {
                videoLocation = readAllRawData.listPuatekowhai;
            }

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("video",videoLocation);
        return bundle;
    }

}
