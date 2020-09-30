package com.allpracticals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {
    @BindView(R.id.edit_text_mobile_no)
    AppCompatEditText mMobile;
    @BindView(R.id.edit_text_password)
    AppCompatEditText mPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClickAction(View view) {
        switch (view.getId()) {
            case (R.id.btn_login):
                if (isFormValidate()) {
                    Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                }
                break;
            case (R.id.btn_register):
                Toast.makeText(getActivity(), "Register button click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean isFormValidate() {
        if (new FormValidation().checkEmptyEditText(mMobile)) {
            Toast.makeText(getActivity(), "Mobile number is empty", Toast.LENGTH_SHORT).show();
            mMobile.requestFocus();
            return false;
        } else if (!new FormValidation().checkMobileNumber(mMobile)) {
            Toast.makeText(getActivity(), "Mobile number is invalid", Toast.LENGTH_SHORT).show();
            mMobile.requestFocus();
            return false;
        }

        if (new FormValidation().checkEmptyEditText(mPassword)) {
            Toast.makeText(getActivity(), "Password is empty", Toast.LENGTH_SHORT).show();
            mPassword.requestFocus();
            return false;
        } else if (!new FormValidation().checkPassword(mPassword)) {
            mPassword.requestFocus();
            Toast.makeText(getActivity(), "Password is Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}