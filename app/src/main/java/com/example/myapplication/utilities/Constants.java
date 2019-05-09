package com.example.myapplication.utilities;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Aakash on 08/05/2019.
 */

public class Constants {

    public static String baseUrl = "https://mp-android-challenge.herokuapp.com";

    public static String green = "green";
    public static String red = "red";

    public static String $1 = "$1";
    public static String $2 = "$2";
    public static String $3 = "$3";
    public static String $4 = "$4";

    public static String plain_text = "plain_text";
    public static String variable = "variable";
    public static String indicator = "indicator";

    public static String selectedScan = "selectedScan";

    private static ProgressDialog progressDialog;


    public static void showProgressDialog(Context ctx) {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public static void closeProgressDialog() {

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
