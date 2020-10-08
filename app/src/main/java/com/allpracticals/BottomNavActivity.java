package com.allpracticals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavActivity extends AppCompatActivity {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        init();
        setupNavigation();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    private void setupNavigation() {
        openFragment(LoginFragment.newInstance());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(LoginFragment.newInstance());
                    return true;
                case R.id.navigation_view_pager:
                    openFragment(ViewPagerFragment.newInstance());
                    return true;
                case R.id.navigation_account:
                    openFragment(PermissionFragment.newInstance());
                    return true;
            }
            return false;
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(BACK_STACK_ROOT_TAG);
        transaction.commit();
    }
}