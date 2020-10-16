package com.allpracticals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.allpracticals.R.*;

public class UpdateCountry extends AppCompatActivity {
    DBManager dbManager;
    @SuppressLint("NonConstantResourceId")
    @Nullable
    @BindView(id.et_title)
    AppCompatEditText et_title;
    @SuppressLint("NonConstantResourceId")
    @Nullable
    @BindView(id.et_description)
    AppCompatEditText et_description;
    @SuppressLint("NonConstantResourceId")
    @BindView(id.btn_update)
    AppCompatButton btn_update;
    @SuppressLint("NonConstantResourceId")
    @BindView(id.btn_delete)
    AppCompatButton btn_delete;
    private long _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_update_country);
        init();
        initDB();
        getIntentData();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    private void initDB() {
        dbManager = new DBManager(this);
        dbManager.open();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        assert et_title != null;
        et_title.setText(name);
        assert et_description != null;
        et_description.setText(desc);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({id.btn_update, id.btn_delete})
    public void onClickAction(View view) {
        switch (view.getId()) {
            case id.btn_update:
                assert et_title != null;
                String title = Objects.requireNonNull(et_title.getText()).toString();
                assert et_description != null;
                String desc = Objects.requireNonNull(et_description.getText()).toString();

                dbManager.update(_id, title, desc);
                this.returnHome();
                break;
            case id.btn_delete:
                assert et_title != null;
                new AlertDialog.Builder(this)
                        .setTitle("Delete record")
                        .setMessage("Are sure want to delete " + Objects.requireNonNull(et_title.getText()).toString() + " ?")
                        .setIcon(drawable.ic_baseline_verified_user_24)
                        .setPositiveButton(getString(string.positive_button), (dialog, which) -> {
                            dbManager.delete(_id);
                            this.returnHome();
                        })
                        .setNegativeButton(getString(string.negative_button), (dialog, which) -> dialog.dismiss())
                        .show();
                break;
        }
    }

    private void returnHome() {
        startActivity(new Intent(this, CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}