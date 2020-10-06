package com.allpracticals;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageFragment extends Fragment {
    public static final String KEY_POSITION = "position";
    public static final String KEY_COLOR = "color";

    @BindView(R.id.fragment_page_root_view)
    LinearLayoutCompat fragment_page_root_view;
    @BindView(R.id.fragment_page_title)
    AppCompatTextView fragment_page_title;

    public static PageFragment newInstance(int position, int color) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_page, container, false);

        init(result);
        assert getArguments() != null;
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        fragment_page_root_view.setBackgroundColor(color);
        fragment_page_title.setText("Page number: " + position);

        return result;
    }

    private void init(View view) {
        ButterKnife.bind(this, view);
    }

}