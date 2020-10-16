package com.allpracticals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.allpracticals.R.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(id.toolbar)
    Toolbar toolbar;
    @SuppressLint("NonConstantResourceId")
    @BindView(id.drawer_layout)
    DrawerLayout drawer_layout;
    @SuppressLint("NonConstantResourceId")
    @BindView(id.navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        init();

        setupToolBar();
        setupDrawerLayout();
        setupNavigationView();
        showFirstFragment();
    }

    public void init() {
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        if (this.drawer_layout.isDrawerOpen(GravityCompat.START))
            this.drawer_layout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String title = null;
        switch (item.getItemId()) {
            case id.nav_menu_login:
                fragment = new LoginFragment();
                title = getText(string.nav_item_login).toString();
                break;
            case id.nav_menu_register:
                fragment = new RegisterFragment();
                title = getText(string.nav_item_register).toString();
                break;
            case id.nav_menu_view_user_list:
                startActivity(new Intent(this, UserActivity.class));
                title = getText(string.nav_item_view_user_list).toString();
                break;
            case id.nav_menu_rt_permission:
                fragment = new PermissionFragment();
                title = getText(string.nav_item_permission).toString();
                break;
            case id.nav_menu_easy_image:
                startActivity(new Intent(this, EasyImageActivity.class));
                title = getText(string.nav_item_easy_image).toString();
                break;
            case id.nav_menu_view_pager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                title = getText(string.nav_item_view_pager).toString();
                break;
            case id.nav_menu_bottom_nav:
                startActivity(new Intent(this, BottomNavActivity.class));
                title = getText(string.nav_item_bottom_nav).toString();
                break;
            case id.nav_menu_retrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                title = getText(string.nav_item_retrofit).toString();
                break;
            case id.nav_menu_sqLiteDB:
                startActivity(new Intent(this, CountryListActivity.class));
                title = getText(string.nav_item_sqLiteDB).toString();
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(id.content_frame, fragment).commit();
            toolbar.setTitle(title);
            this.drawer_layout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    void setupToolBar() {
        setSupportActionBar(toolbar);
    }

    private void setupDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showFirstFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(id.content_frame, new LoginFragment()).commit();
        setTitle(getText(string.nav_item_login).toString());
        this.navigationView.getMenu().getItem(0).setChecked(true);
    }
}