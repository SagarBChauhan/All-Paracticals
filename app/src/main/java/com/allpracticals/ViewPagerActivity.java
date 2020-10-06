package com.allpracticals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        init();
        setupViewPager();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    private void setupViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.colorPagesViewPager),0));
    }
}