package com.komugi.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.Display;

import java.util.ArrayList;

/**
 * Created by iapp on 19/12/16.
 */
public class Constants {

    public static final String PREFS_NAME= "MyPrefsFile";
    public static final String DeviceType= "2";
    public static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

    public static ArrayList<String> spinnerList=new ArrayList<>();

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                .matches();
    }

    public static Display getDeviceDimension(Context con) {
        return ((Activity) con).getWindowManager().getDefaultDisplay();
    }

    public static boolean checkString(String st) {

        if (st == null || st.equals("") || st.equals("null") || st.trim().length() == 0) {

            return false;

        } else {

            return true;

        }
    }

  public static void alertDialog(Context con,String message){
      AlertDialog dialog= new AlertDialog.Builder(con)
              .setTitle("Alert")
              .setMessage(message)
              .setCancelable(false)
              .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                  }
              }).show();
  }
    public static boolean isNetworkAvailable(Context con) {
        ConnectivityManager connectivityManager = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
