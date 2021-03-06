package com.allpracticals;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_mobile_no)
    AppCompatEditText mMobile;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_password)
    AppCompatEditText mPassword;

    public static LoginFragment newInstance() {
        return (new LoginFragment());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getActivity()).setTitle(getText(R.string.nav_item_login).toString());
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
                    Toast.makeText(getContext(), R.string.msg_login_success, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), R.string.msg_login_fail, Toast.LENGTH_SHORT).show();
                }
                break;
            case (R.id.btn_register):
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new RegisterFragment(), "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                getActivity().setTitle("Register");
                break;
        }
    }

    public boolean isFormValidate() {
        if (new FormValidation().checkEmptyEditText(mMobile)) {
            Toast.makeText(getActivity(), R.string.msg_mobile_no_empty, Toast.LENGTH_SHORT).show();
            mMobile.requestFocus();
            return false;
        } else if (!new FormValidation().checkMobileNumber(mMobile)) {
            Toast.makeText(getActivity(), R.string.msg_mobile_no_invalid, Toast.LENGTH_SHORT).show();
            mMobile.requestFocus();
            return false;
        }

        if (new FormValidation().checkEmptyEditText(mPassword)) {
            Toast.makeText(getActivity(), R.string.msg_password_empty, Toast.LENGTH_SHORT).show();
            mPassword.requestFocus();
            return false;
        } else if (!new FormValidation().checkPassword(mPassword)) {
            mPassword.requestFocus();
            Toast.makeText(getActivity(), R.string.msg_password_invalid, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}