package com.allpracticals;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.Objects;

public class PageAdapter extends FragmentPagerAdapter {
    private int[] colors;

    public PageAdapter(@NonNull FragmentManager fm, int[] colors,int behavior) {
        super(fm,behavior);
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(position, this.colors[position]));
    }
}
