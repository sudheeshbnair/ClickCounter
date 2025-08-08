package com.swoozle.xchange.ui.view

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume


sealed interface DialogResult {
    data object Positive : DialogResult
    data object Negative : DialogResult
}

interface DialogVisuals {
    val title: String
    val icon : Int
    val message: String
    val positiveButton: String
    val negativeButton: String
}

interface DialogData {
    val visuals: DialogVisuals
    fun onPositive()
    fun onNegative()
}

@Stable
class DialogHostState(val scope : CoroutineScope) {
    private val mutex = Mutex()
    private val _currentDialogData = mutableStateOf<DialogData?>(null)
    val currentDialogData: State<DialogData?> = _currentDialogData


    fun launch(block: suspend CoroutineScope.() -> Unit) {
        scope.launch(block = block)
    }

    suspend fun showDialog(
        title: String,
        message: String,
        positiveButton: String,
        negativeButton: String,
        icon: Int
    ): DialogResult = mutex.withLock {
        suspendCancellableCoroutine { continuation ->
            val visuals = object : DialogVisuals {
                override val title = title
                override val icon: Int
                    get() = icon
                override val message = message
                override val positiveButton = positiveButton
                override val negativeButton = negativeButton
            }

            val dialogData = DialogDataImpl(visuals, continuation)
            _currentDialogData.value = dialogData
        }.also { dismiss() }
    }

    private class DialogDataImpl(
        override val visuals: DialogVisuals,
        private val continuation: CancellableContinuation<DialogResult>
    ) : DialogData {

        override fun onPositive() {
            if (continuation.isActive) continuation.resume(DialogResult.Positive)
        }

        override fun onNegative() {
            if (continuation.isActive) continuation.resume(DialogResult.Negative)
        }
    }

    fun dismiss() {
        _currentDialogData.value = null
    }
}