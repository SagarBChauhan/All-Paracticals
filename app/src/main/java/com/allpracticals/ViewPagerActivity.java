package com.allpracticals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        init();
        setupViewPagerAndTabs();
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupViewPagerAndTabs() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), 0));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();
    }
}