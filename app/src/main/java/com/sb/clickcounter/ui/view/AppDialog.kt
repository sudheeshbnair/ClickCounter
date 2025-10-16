package com.sb.clickcounter.ui.view

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.sb.clickcounter.model.DialogData
import com.sb.clickcounter.ui.state.DialogHostState

@Composable
fun AppDialog(dialogData: DialogData, onConfirmRequest: () -> Unit, onDismissRequest: () -> Unit) {
    Log.d("SUDHEESH", "AppDialog : $dialogData")
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(dialogData.title)
            Log.d("SUDHEESH", "AlertDialog: $dialogData") },
        text = { Text(dialogData.message) },
        confirmButton = {
            TextButton(onClick = onConfirmRequest) {
                Text(dialogData.confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(dialogData.dismissButtonText)
            }
        }
    )
}

@Composable
fun AppDialog(dialogHostState: DialogHostState) {
    dialogHostState.currentDialogData.value?.visuals?.let {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(it.title)},
            text = { Text(it.message) },
            confirmButton = {
                TextButton(onClick = {dialogHostState.currentDialogData.value?.onPositive()}) {
                    Text(it.positiveButton)
                }
            },
            dismissButton = {
                TextButton(onClick = {dialogHostState.currentDialogData.value?.onNegative()}) {
                    Text(it.negativeButton)
                }
            }
        )
    }
}