package com.test.mvcnytimessample.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.test.mvcnytimessample.MVCApplication;
import com.test.mvcnytimessample.R;

public class Progress {
    private final ProgressDialog progressDialog;

    public Progress(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    public void showProgressDialog() {

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(MVCApplication.getMyAppContext().getString(R.string.progress_msg));
        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progress_dialog);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void cancelProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
        }
    }
}
