package com.example.wintecwaiata;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LyricsPagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;

    public LyricsPagerAdapter(@NonNull FragmentManager fm,int tabsNumber) {
        super(fm);
        this.tabsNumber = tabsNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new MaoriLyricsFragment();
            case 1:
                return new EnglishLyricsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
