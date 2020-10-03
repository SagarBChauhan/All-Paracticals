package com.allpracticals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = String.valueOf(R.string.preference_file_key);
    public static final String Name = String.valueOf(R.string.preference_name_key);
    public static final String Email = String.valueOf(R.string.preference_email_key);

    User[] users;
    UserListAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }


    private void setupUserListData() {
        HashSet<String> stringsN = (HashSet<String>) sharedPreferences.getStringSet(Name, new HashSet<>());
        HashSet<String> stringsE = (HashSet<String>) sharedPreferences.getStringSet(Email, new HashSet<>());
        assert stringsN != null;
        Object[] arrayN = stringsN.toArray();
        assert stringsE != null;
        Object[] arrayE = stringsE.toArray();
        users = new User[arrayN.length];
        for (int i = 0; i < arrayN.length; i++) {
            users[i] = new User(arrayN[i].toString(), arrayE[i].toString());
        }

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
