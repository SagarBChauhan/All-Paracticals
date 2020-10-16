package com.allpracticals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListActivity extends AppCompatActivity {

    final String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC};
    final int[] to = new int[]{R.id.tv_id, R.id.tv_title, R.id.tv_desc};
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.list_country)
    ListView list_country;
    Cursor cursor;
    private SimpleCursorAdapter adapter;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        init();
        initDB();
        fetchData();
        setupAdapter();
        list_country.setOnItemClickListener((parent, view, position, id) -> {
            AppCompatTextView tv_id = view.findViewById(R.id.tv_id);
            AppCompatTextView tv_title = view.findViewById(R.id.tv_title);
            AppCompatTextView tv_desc = view.findViewById(R.id.tv_desc);

            String c_id = tv_id.getText().toString();
            String c_title = tv_title.getText().toString();
            String c_desc = tv_desc.getText().toString();

            Intent intent=new Intent(getApplicationContext(),UpdateCountry.class);
            intent.putExtra("title",c_title);
            intent.putExtra("desc",c_desc);
            intent.putExtra("id",c_id);
            startActivity(intent);
        });
    }

    private void init() {
        ButterKnife.bind(this);
    }

    private void initDB() {
        dbManager = new DBManager(this);
        dbManager.open();
    }

    private void fetchData() {
        cursor = dbManager.fetch();
        list_country.setEmptyView(findViewById(R.id.empty));
    }

    private void setupAdapter() {
        adapter = new SimpleCursorAdapter(this, R.layout.list_country, cursor, from, to);
        list_country.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_record) {
            startActivity(new Intent(this, AddCountryActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
