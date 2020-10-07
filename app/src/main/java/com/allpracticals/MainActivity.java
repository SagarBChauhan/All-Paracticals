package com.allpracticals;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        ButterKnife.bind(this);

        setupToolBar();
        setupDrawerLayout();
        setupNavigationView();
        showFirstFragment();
    }

    @Override
    public void onBackPressed() {
        if (this.drawer_layout.isDrawerOpen(GravityCompat.START))
            this.drawer_layout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String title = null;
        switch (item.getItemId()) {
            case R.id.nav_menu_login:
                fragment = new LoginFragment();
                title = getText(R.string.nav_item_login).toString();
                break;
            case R.id.nav_menu_register:
                fragment = new RegisterFragment();
                title = getText(R.string.nav_item_register).toString();
                break;
            case R.id.nav_menu_view_user_list:
                startActivity(new Intent(this, UserActivity.class));
                title = getText(R.string.nav_item_view_user_list).toString();
                break;
            case R.id.nav_menu_rt_permission:
                fragment = new PermissionFragment();
                title = getText(R.string.nav_item_permission).toString();
                break;
            case R.id.nav_menu_easy_image:
                startActivity(new Intent(this, EasyImageActivity.class));
                title = getText(R.string.nav_item_easy_image).toString();
                break;
            case R.id.nav_menu_view_pager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                title = getText(R.string.nav_item_view_pager).toString();
                break;
            case R.id.nav_menu_bottom_nav:
                startActivity(new Intent(this, BottomNavActivity.class));
                title = getText(R.string.nav_item_bottom_nav).toString();
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            toolbar.setTitle(title);
            this.drawer_layout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    void setupToolBar() {
        setSupportActionBar(toolbar);
    }

    private void setupDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showFirstFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
        toolbar.setTitle(getText(R.string.nav_item_login).toString());
        this.navigationView.getMenu().getItem(0).setChecked(true);
    }
}