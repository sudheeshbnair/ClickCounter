package com.sb.clickcounter.ui.state

data class UiState(
    val activeDialog: DialogState? = null,
    val activeSnackbar: SnackbarState ? = null,
)

sealed interface DialogState {
    data object Login : DialogState
    data object Logout : DialogState
    data class ApiError(val title:String, val message: String) : DialogState
}

sealed interface SnackbarState {
    data object NoNetwork : SnackbarState
    data object SessionTimeout : SnackbarState
}