package com.example.yudhisthira.quandoo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.yudhisthira.quandoo.data.Table;
//import okhttp3.mockwebserver.MockResponse;

/**
 * Created by yudhisthira
 */

public class Utils {
    public static void showAvailableAlert(Context context, Table table, DialogInterface.OnClickListener onPositiveButton) {
        new AlertDialog.Builder(context)
                .setMessage("Book table #" + table.getTableNumber())
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, onPositiveButton)
                .setNegativeButton(android.R.string.no, (dialog, id) -> {
                    dialog.cancel();
                })
                .create()
                .show();
    }

    public static void showUnavailableAlert(Context context, Table table) {
        new AlertDialog.Builder(context)
                .setMessage("Table #" + table.getTableNumber() + " not available")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> {
                    dialog.cancel();
                })
                .create()
                .show();
    }
}
