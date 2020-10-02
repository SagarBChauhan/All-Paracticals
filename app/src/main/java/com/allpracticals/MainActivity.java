package com.allpracticals;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    ActionBarDrawerToggle mDrawerToggle;
    DataModel[] drawerItem;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.left_drawer)
    ListView mDrawerList;

    private String[] mNavigationDrawerItemTitles;
    private String mDrawerTitle;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        ButterKnife.bind(this);
        mTitle = mDrawerTitle = (String) getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_item_array);

        setupToolBar();
        setDrawableItems();

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
        mDrawerList.setItemChecked(0, true);
        mDrawerList.setSelection(0);
        setTitle(mNavigationDrawerItemTitles[0]);
    }

    public void setDrawableItems() {
        String[] navTitle = getResources().getStringArray(R.array.navigation_drawer_item_array);
        int[] navIcon = {R.drawable.ic_baseline_account_circle_24,R.drawable.ic_baseline_assignment_ind_24,R.drawable.ic_baseline_list_alt_24,R.drawable.ic_baseline_verified_user_24};

        drawerItem = new DataModel[navTitle.length];
        for (int i = 0; i < navTitle.length; i++) {
            drawerItem[i] = new DataModel(navIcon[i],navTitle[i]);
        }
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    void setupToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new RegisterFragment();
                break;
            case 2:
                Intent intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                break;
            case 3:
                fragment = new PermissionFragment();
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = (String) title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}