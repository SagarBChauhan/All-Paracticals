package com.allpracticals;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment {

    public static final String MyPREFERENCES = String.valueOf(R.string.preference_file_key);
    public static final String Name = String.valueOf(R.string.preference_name_key);
    public static final String Email = String.valueOf(R.string.preference_email_key);
    public static final String Count = String.valueOf(R.string.preference_count_key);

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_first_name)
    AppCompatEditText mFirstName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_last_name)
    AppCompatEditText mLastName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_email)
    AppCompatEditText mEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_mobile_no)
    AppCompatEditText mMobileNo;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_password)
    AppCompatEditText mPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edit_text_password_confirm)
    AppCompatEditText mConfPassword;

    SharedPreferences sharedPreferences;

    public static RegisterFragment newInstance() {
        return (new RegisterFragment());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getActivity()).setTitle(getText(R.string.nav_item_register).toString());
        init(view);
    }

    private void init(View view) {
        ButterKnife.bind(this, view);
        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    public void onClickAction(View view) {
        switch (view.getId()) {
            case (R.id.btn_register):
                if (isFormValidate()) {
                    Set<String> nameSet;
                    Set<String> emailSet;

                    nameSet = sharedPreferences.getStringSet(Name, new HashSet<>());
                    emailSet = sharedPreferences.getStringSet(Email, new HashSet<>());

                    assert emailSet != null;
                    assert nameSet != null;
                    if (!emailSet.contains(Objects.requireNonNull(mEmail.getText()).toString()) && !nameSet.contains(Objects.requireNonNull(mFirstName.getText()).toString() + " " + Objects.requireNonNull(mLastName.getText()).toString())) {
                        nameSet.add(Objects.requireNonNull(mFirstName.getText()).toString() + " " + Objects.requireNonNull(mLastName.getText()).toString());
                        emailSet.add(Objects.requireNonNull(mEmail.getText()).toString());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putStringSet(Name, nameSet);
                        editor.putStringSet(Email, emailSet);
                        editor.apply();
                        Toast.makeText(getContext(), getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), UserActivity.class));
                    } else {
                        Toast.makeText(getActivity(), R.string.msg_warning_duplicate_entry, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), getString(R.string.msg_login_fail), Toast.LENGTH_SHORT).show();
                }
                break;
            case (R.id.btn_login):
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new LoginFragment(), "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                getActivity().setTitle("Login");
                break;
        }
    }

    private boolean isFormValidate() {
        if (new FormValidation().checkEmptyEditText(mFirstName)) {
            Toast.makeText(getActivity(), R.string.msg_first_name_empty, Toast.LENGTH_SHORT).show();
            mFirstName.requestFocus();
            return false;
        }
        if (new FormValidation().checkEmptyEditText(mLastName)) {
            Toast.makeText(getActivity(), R.string.msg_last_name_empty, Toast.LENGTH_SHORT).show();
            mLastName.requestFocus();
            return false;
        }
        if (new FormValidation().checkEmptyEditText(mEmail)) {
            Toast.makeText(getActivity(), R.string.msg_email_empty, Toast.LENGTH_SHORT).show();
            mEmail.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkEmail(mEmail)) {
                Toast.makeText(getActivity(), R.string.msg_email_invalid, Toast.LENGTH_SHORT).show();
                mEmail.requestFocus();
                return false;
            }
        }
        if (new FormValidation().checkEmptyEditText(mMobileNo)) {
            Toast.makeText(getActivity(), R.string.msg_mobile_no_empty, Toast.LENGTH_SHORT).show();
            mMobileNo.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkMobileNumber(mMobileNo)) {
                Toast.makeText(getActivity(), R.string.msg_mobile_no_invalid, Toast.LENGTH_SHORT).show();
                mMobileNo.requestFocus();
                return false;
            }
        }
        if (new FormValidation().checkEmptyEditText(mPassword)) {
            Toast.makeText(getActivity(), R.string.msg_password_empty, Toast.LENGTH_SHORT).show();
            mPassword.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkPassword(mPassword)) {
                Toast.makeText(getActivity(), R.string.msg_password_invalid, Toast.LENGTH_SHORT).show();
                mPassword.requestFocus();
                return false;
            }
        }
        if (new FormValidation().checkEmptyEditText(mConfPassword)) {
            Toast.makeText(getActivity(), R.string.msg_confirm_password_empty, Toast.LENGTH_SHORT).show();
            mConfPassword.requestFocus();
            return false;
        } else {
            if (!new FormValidation().checkPassword(mConfPassword)) {
                Toast.makeText(getActivity(), R.string.msg_confirm_password_invalid, Toast.LENGTH_SHORT).show();
                mConfPassword.requestFocus();
                return false;
            }
        }
        if (!new FormValidation().checkConfirmPassword(mPassword, mConfPassword)) {
            Toast.makeText(getActivity(), R.string.msg_password_not_matched, Toast.LENGTH_SHORT).show();
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