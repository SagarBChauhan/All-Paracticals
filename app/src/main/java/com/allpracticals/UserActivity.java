package com.allpracticals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {
    User[] users;
    UserListAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();
        setupUserListData();
        setupLayoutManager();
        setupAdapter();
        setAdapter();
    }

    private void init() {
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
    }

    private void setupUserListData() {
        users = new User[]{
                new User("Sagar", "Sagar@Biz-insights.com"),
                new User("Amit", "Amit@Biz-insights.com"),
                new User("Sushil", "Shusil@Biz-insights.com"),
                new User("Parth", "Parth@Biz-insights.com"),
                new User("Darshan", "Darshan@Biz-insights.com")
        };
    }

    private void setupAdapter() {
        adapter = new UserListAdapter(users);
    }

    private void setupLayoutManager() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        recyclerView.setAdapter(adapter);
    }
}
