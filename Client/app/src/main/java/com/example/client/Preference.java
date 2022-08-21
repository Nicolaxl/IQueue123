package com.example.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {
    public Preference(){

    }

    public static boolean saveUname(String uname, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_UNAME, uname);
        editor.apply();
        return true;
    }

    public static String getUname(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_UNAME, null);
    }

    public static boolean savePassword(String pass, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_PASS, pass);
        editor.apply();
        return true;
    }

    public static String getPassword(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_PASS, null);
    }

    public static boolean saveNumber(String number, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_NUMBER,number);
        editor.apply();
        return true;
    }

    public static String getNumber(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_NUMBER, null);
    }

    public static boolean saveNumberA(String number, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_NUMBERA,number);
        editor.apply();
        return true;
    }

    public static String getNumberA(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_NUMBERA, null);
    }

    public static boolean saveCounter(String counter, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_COUNTER,counter);
        editor.apply();
        return true;
    }

    public static String getCounter(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_COUNTER, null);
    }

    public static boolean saveCounter2(String counter, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_COUNTER2,counter);
        editor.apply();
        return true;
    }

    public static String getCounter2(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_COUNTER2, null);
    }

    public static boolean saveCounter3(String counter, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_COUNTER3,counter);
        editor.apply();
        return true;
    }

    public static String getCounter3(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_COUNTER3, null);
    }

    public static boolean saveCounterA(String counter, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_COUNTERA,counter);
        editor.apply();
        return true;
    }

    public static String getCounterA(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_COUNTERA, null);
    }

    public static boolean saveCounterA2(String counter, Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.KEY_COUNTERA2,counter);
        editor.apply();
        return true;
    }

    public static String getCounterA2(Context con){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(con);
        return pref.getString(Constants.KEY_COUNTERA2, null);
    }
}
