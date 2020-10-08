package com.allpracticals;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LoginFragment.newInstance();
            case 1:
                return RegisterFragment.newInstance();
            case 2:
                return PermissionFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Login";
            case 1:
                return "Register";
            case 2:
                return "Permission";
            default:
                return null;
        }
    }
}
