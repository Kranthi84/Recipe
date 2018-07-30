package com.recipe.kchinnak.searchrecipe.Utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.recipe.kchinnak.searchrecipe.R

class DialogUtil(mContext: Context?, mLayoutInflater: LayoutInflater?) {

    var mVerifyEmailAlertDialog: AlertDialog

    init {

        mVerifyEmailAlertDialog = AlertDialog.Builder(mContext)
                .setTitle(R.string.verify_email_dialog_title)
                .setCancelable(false)
                .setView(mLayoutInflater?.inflate(R.layout.verify_email_dialog_layout, null, false))
                .create()
    }


}