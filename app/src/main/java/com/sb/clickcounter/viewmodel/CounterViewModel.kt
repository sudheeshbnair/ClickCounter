package com.sb.clickcounter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sb.clickcounter.ui.state.DialogState
import com.sb.clickcounter.ui.state.SnackbarState
import com.sb.clickcounter.ui.state.UiState
import com.swoozle.xchange.ui.view.DialogHostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val _uiState : MutableStateFlow<UiState>
) : ViewModel(), CounterViewModelContract {

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val dialogHostState by mutableStateOf(DialogHostState(scope = viewModelScope))

    val dataItems = List(20) { index -> BottomSheetItem(
        id = index,
        title = "Item $index"
    ) }

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

data class BottomSheetItem(
    val id: Int? = null,
    val title: String,
    var isSelected: Boolean = false,
    var iconId: Int? = null
)