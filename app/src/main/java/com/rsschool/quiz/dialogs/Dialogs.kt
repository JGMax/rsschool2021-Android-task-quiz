package com.rsschool.quiz.dialogs

import android.app.AlertDialog
import android.content.Context

import com.rsschool.quiz.R

fun getDialog(
    context: Context,
    id: DialogIds,
    positiveAction: () -> Unit = {},
    negativeAction: () -> Unit = {}
) {
    val builder = AlertDialog.Builder(context)
    when (id) {
        DialogIds.DIALOG_QUIT_FIRST_QUESTION -> {
            builder
                .setMessage(R.string.alert_massage)
                .setPositiveButton(R.string.continue_btn) { dialog, _ ->
                    positiveAction()
                    dialog.cancel()
                }
                .setNegativeButton(R.string.cancel_btn) { dialog, _ ->
                    negativeAction()
                    dialog.cancel()
                }
                .show()
        }
    }

}