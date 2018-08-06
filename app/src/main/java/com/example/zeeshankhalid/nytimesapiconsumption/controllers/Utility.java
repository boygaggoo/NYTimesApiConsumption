package com.example.zeeshankhalid.nytimesapiconsumption.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.example.zeeshankhalid.nytimesapiconsumption.application.NYTimes;

/**
 * Created by khalidz on 12/19/2017.
 */


public class Utility {

    public static void showToast(String message) {
        Toast.makeText(NYTimes.getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Context context, IBinder view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view, 0);
    }
}
