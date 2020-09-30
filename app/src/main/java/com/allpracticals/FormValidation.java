package com.allpracticals;


import android.util.Patterns;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {
    public boolean checkEmptyEditText(AppCompatEditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    public boolean checkMobileNumber(AppCompatEditText editText) {
        if (editText.getText().toString().length() > 10) {
            return false;
        } else if (editText.getText().toString().length() < 10) {
            return false;
        } else return Patterns.PHONE.matcher(editText.getText().toString().trim()).matches();
    }

    public boolean checkEmail(AppCompatEditText editText) {
        return Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString().trim()).matches();
    }

    public boolean checkPassword(AppCompatEditText editText) {
        /*
          returns true if valid
          returns false if invalid

          (?=.*\d)		#   must contains one digit from 0-9
          (?=.*[a-z])	#   must contains one lowercase characters
          (?=.*[A-Z])	#   must contains one uppercase characters
          (?=.*[@#$%])	#   must contains one special symbols in the list "@#$%"
          .	            #   match anything with previous condition checking
          {6,20}	    #   length at least 6 characters and maximum of 20
        */

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(editText.getText().toString().trim());
        return matcher.matches();
    }

    public boolean checkConfirmPassword(AppCompatEditText editTextPassword, AppCompatEditText editTextConfirmPassword) {
        return editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString());
    }
}
