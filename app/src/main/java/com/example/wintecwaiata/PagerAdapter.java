package com.example.wintecwaiata;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;

    public PagerAdapter(FragmentManager fm, int tabsNumber) {
        super(fm);
        this.tabsNumber = tabsNumber;
    }

//    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
//
//        super(fm, behavior);
//        this.tabsNumber = tabs;
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new KaraokeFragment();
            case 1:
                return new LyricsFragment();
            case 2:
                return new AudioFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
