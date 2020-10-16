package com.allpracticals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCountryActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_subject)
    AppCompatEditText et_subject;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_description)
    AppCompatEditText et_description;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_add_record)
    AppCompatButton btn_add_record;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);
        init();
        initDB();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    private void initDB() {
        dbManager = new DBManager(this);
        dbManager.open();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_add_record)
    public void onClickAction() {
        final String name = Objects.requireNonNull(et_subject.getText()).toString();
        final String desc = Objects.requireNonNull(et_description.getText()).toString();

        dbManager.insert(name, desc);
        startActivity(new Intent(this, CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

}