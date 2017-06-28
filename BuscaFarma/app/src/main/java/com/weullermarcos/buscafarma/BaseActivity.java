package com.weullermarcos.buscafarma;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ismael on 25/03/2017.
 */

public class BaseActivity extends AppCompatActivity {
    protected Dialog mDialog;

    protected final String TAG = getClass().getName();

    public void createDialog(String message, Context context) {
        closeDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();
    }

    public void createDialog(String message, String title, Context context) {
        closeDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();
    }

    public void createProgressDialog(String message, String title, Context context) {
        closeDialog();

        mDialog = ProgressDialog.show(context, title, message, false, false);
    }

    public void closeDialog() {
        if(mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }
}
