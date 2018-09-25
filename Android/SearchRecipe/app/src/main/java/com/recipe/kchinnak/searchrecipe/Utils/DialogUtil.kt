package com.recipe.kchinnak.searchrecipe.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.recipe.kchinnak.searchrecipe.R

class DialogUtil(mContext: Context?, mLayoutInflater: LayoutInflater?) {

    var mVerifyEmailAlertDialog: AlertDialog = AlertDialog.Builder(mContext)
            .setTitle(R.string.verify_email_dialog_title)
            .setCancelable(false)
            .setView(mLayoutInflater?.inflate(R.layout.verify_email_dialog_layout, null, false))
            .create()


}