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

public class RegisterFragment extends Fragment {

    @BindView(R.id.edit_text_first_name)
    AppCompatEditText mFirstName;
    @BindView(R.id.edit_text_last_name)
    AppCompatEditText mLastName;
    @BindView(R.id.edit_text_email)
    AppCompatEditText mEmail;
    @BindView(R.id.edit_text_mobile_no)
    AppCompatEditText mMobileNo;
    @BindView(R.id.edit_text_password)
    AppCompatEditText mPassword;
    @BindView(R.id.edit_text_password_confirm)
    AppCompatEditText mConfPassword;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        ButterKnife.bind(this, view);
    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    public void onClickAction(View view) {
        switch (view.getId()) {
            case (R.id.btn_register):
                if (isFormValidate()) {
                    Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                }
                break;
            case (R.id.btn_login):
                Toast.makeText(getActivity(), "Login button click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean isFormValidate() {
        if (new FormValidation().checkEmptyEditText(mFirstName)) {
            Toast.makeText(getActivity(), "First name is empty", Toast.LENGTH_SHORT).show();
            mFirstName.requestFocus();
            return false;
        }
        if (new FormValidation().checkEmptyEditText(mLastName)) {
            Toast.makeText(getActivity(), "Last name is empty", Toast.LENGTH_SHORT).show();
            mLastName.requestFocus();
            return false;
        }
        if (new FormValidation().checkEmptyEditText(mEmail)) {
            Toast.makeText(getActivity(), "Email is empty", Toast.LENGTH_SHORT).show();
            mEmail.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkEmail(mEmail)) {
                Toast.makeText(getActivity(), "email is invalid", Toast.LENGTH_SHORT).show();
                mEmail.requestFocus();
                return false;
            }
        }
        if (new FormValidation().checkEmptyEditText(mMobileNo)) {
            Toast.makeText(getActivity(), "Mobile number is empty", Toast.LENGTH_SHORT).show();
            mMobileNo.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkMobileNumber(mMobileNo)) {
                Toast.makeText(getActivity(), "Invalid mobile number", Toast.LENGTH_SHORT).show();
                mMobileNo.requestFocus();
                return false;
            }
        }
        if (new FormValidation().checkEmptyEditText(mPassword)) {
            Toast.makeText(getActivity(), "Password is empty", Toast.LENGTH_SHORT).show();
            mPassword.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkPassword(mPassword)) {
                Toast.makeText(getActivity(), "Password is invalid", Toast.LENGTH_SHORT).show();
                mPassword.requestFocus();
                return false;
            }
        }
        if (new FormValidation().checkEmptyEditText(mConfPassword)) {
            Toast.makeText(getActivity(), "Confirm password is empty", Toast.LENGTH_SHORT).show();
            mConfPassword.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkPassword(mConfPassword)) {
                Toast.makeText(getActivity(), "Confirm password is invalid", Toast.LENGTH_SHORT).show();
                mConfPassword.requestFocus();
                return false;
            }
        }
        if (!new FormValidation().checkConfirmPassword(mPassword, mConfPassword)) {
            Toast.makeText(getActivity(), "Password & Confirm password not matched ", Toast.LENGTH_SHORT).show();
            mConfPassword.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}