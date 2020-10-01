package com.allpracticals;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    public static final String MyPREFERENCES = String.valueOf(R.string.session_preference_file_key);
    public static final String Name = String.valueOf(R.string.preference_name_key);
    public static final String MobileNo = String.valueOf(R.string.preference_mobile_no_key);
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String name;
    private String mobileNo;

    public Session(Context context) {
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public Session(Context context, String name, String mobileNo) {
        this.name = name;
        this.mobileNo = mobileNo;
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Name, name);
        editor.putString(MobileNo, mobileNo);
        editor.apply();
    }

    public String getName() {
        name=sharedPreferences.getString(Name,"");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        editor.putString(Name, name);
        editor.apply();
    }

    public String getMobileNo() {
        mobileNo=sharedPreferences.getString(MobileNo,"");
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        editor.putString(MobileNo, mobileNo);
        editor.apply();
    }

}
