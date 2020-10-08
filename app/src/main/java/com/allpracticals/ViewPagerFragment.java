package com.allpracticals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragment extends Fragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setupViewPagerAndTabs(view);
    }

    private void init(View view) {
        ButterKnife.bind(this, view);
    }

    private void setupViewPagerAndTabs(View view) {
        assert getFragmentManager() != null;
        viewPager.setAdapter(new PageAdapter(getFragmentManager(), 0));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }
}