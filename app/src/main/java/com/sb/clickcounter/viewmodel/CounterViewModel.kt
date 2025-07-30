package com.sb.clickcounter.viewmodel

import androidx.lifecycle.ViewModel
import com.sb.clickcounter.ui.state.DialogState
import com.sb.clickcounter.ui.state.SnackbarState
import com.sb.clickcounter.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val _uiState : MutableStateFlow<UiState>
) : ViewModel(), CounterViewModelContract {

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    override fun hideDialog() {
        _uiState.update { it.copy(activeDialog = null) }
    }

    override fun showLoginDialog() {
        _uiState.update { it.copy(activeDialog = DialogState.Login) }
    }

    override fun showLogoutDialog() {
        _uiState.update { it.copy(activeDialog = DialogState.Logout) }
    }
    override fun showErrorDialog() {
        _uiState.update { it.copy(activeDialog = DialogState.ApiError("API Error", "API error occurred")) }
    }
    override fun showNoNetworkSnackbar() {
        _uiState.update { it.copy(activeSnackbar = SnackbarState.NoNetwork) }
    }

    override fun showSessionTimeoutSnackbar() {
        _uiState.update { it.copy(activeSnackbar = SnackbarState.SessionTimeout) }
    }

    override fun hideSnackbar() {
        _uiState.update { it.copy(activeSnackbar = null) }
    }
}

interface CounterViewModelContract {
    fun hideDialog()
    fun showLoginDialog()
    fun showLogoutDialog()
    fun showErrorDialog()
    fun hideSnackbar()
    fun showNoNetworkSnackbar()
    fun showSessionTimeoutSnackbar()
}