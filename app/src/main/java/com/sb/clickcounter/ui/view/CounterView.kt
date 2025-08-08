package com.sb.clickcounter.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sb.clickcounter.R
import com.sb.clickcounter.model.DialogData
import com.sb.clickcounter.navigation.Screen
import com.sb.clickcounter.ui.state.DialogState
import com.sb.clickcounter.ui.state.SnackbarState
import com.sb.clickcounter.ui.state.UiState
import com.sb.clickcounter.viewmodel.BottomSheetItem
import com.sb.clickcounter.viewmodel.CounterViewModel
import com.sb.clickcounter.viewmodel.CounterViewModelContract
import com.swoozle.xchange.ui.view.DialogHostState
import com.swoozle.xchange.ui.view.DialogResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun CounterView(
    innerPadding: PaddingValues,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val counterViewModel: CounterViewModel = hiltViewModel()
    CounterViewUi(innerPadding, navController, counterViewModel, counterViewModel.uiState, snackbarHostState, counterViewModel.dataItems, counterViewModel.dialogHostState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterViewUi(
    innerPadding: PaddingValues,
    navController: NavHostController,
    counterViewModelContract: CounterViewModelContract,
    uiState: StateFlow<UiState>,
    snackbarHostState: SnackbarHostState,
    dataItems: List<BottomSheetItem>,
    dialogHostState: DialogHostState
) {
    var count by rememberSaveable { mutableIntStateOf(0) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val coroutineScope = rememberCoroutineScope()


    val plusClick = {
        if (count < 9999) count++
        // counterViewModelContract.showLoginDialog()
        // counterViewModelContract.showNoNetworkSnackbar()
//        coroutineScope.launch {
//            sheetState.show()
//        }
//        Unit
//        coroutineScope.launch {
//
//        }
        dialogHostState.launch {
            val result = dialogHostState.showDialog(
                title = "hi",
                positiveButton = "OK",
                negativeButton = "Cancel",
                message = "Success",
                icon = R.drawable.ic_launcher_foreground
            )
            when (result) {
                DialogResult.Negative -> {
                    println("Negative clicked")
                }

                DialogResult.Positive -> {
                    println("Positive clicked")
                }
            }
        }
    }
    val minusClick = {
        if (count > 0) count--
        // counterViewModelContract.showLogoutDialog()
        counterViewModelContract.showSessionTimeoutSnackbar()
    }
    val resetClick = {
        count = 0
        counterViewModelContract.showErrorDialog()
    }

    KeepScreenOn()

    val uIState by  uiState.collectAsState()
    val activeDialog = uIState.activeDialog

    val activeSnackbar = uIState.activeSnackbar
    if (activeSnackbar != null) {
        val message = when (activeSnackbar) {
            SnackbarState.NoNetwork -> "No network connection"
            SnackbarState.SessionTimeout -> "Session timed out"
        }
 // Blocking snackbar

//        AppSnackbar(snackbarHostState).also {
//            val coroutineScope = rememberCoroutineScope()
//            LaunchedEffect(activeSnackbar) {
//                coroutineScope.launch {
//                    Log.d("SUDHEESH", "show snackbar $message")
//                    val result =  snackbarHostState.showSnackbar(message = message, withDismissAction = true, duration = SnackbarDuration.Indefinite)
//                    if (result == SnackbarResult.Dismissed) {
//                        Log.d("SUDHEESH", "snackbar ActionPerformed")
//                        counterViewModelContract.hideSnackbar()
//                    }
//                }
//            }
//        }

        LaunchedEffect(activeSnackbar) {
            coroutineScope.launch {
                Log.d("SUDHEESH", "show snackbar $message")
                val result =  snackbarHostState.showSnackbar(message = message, withDismissAction = true, duration = SnackbarDuration.Indefinite)
                if (result == SnackbarResult.Dismissed) {
                    Log.d("SUDHEESH", "snackbar ActionPerformed")
                    counterViewModelContract.hideSnackbar()
                }
            }
        }
    }

    Log.d("SUDHEESH", "CounterViewUi: activeDialog $activeDialog activeSnackbar $activeSnackbar" )

    if (activeDialog != null) {

        var dialogData by remember { mutableStateOf(DialogData()) }
        var onConfirmRequest = { counterViewModelContract.hideDialog() }
        val onDismissRequest = { counterViewModelContract.hideDialog() }

        Log.d("SUDHEESH", "dialogData :$dialogData")


        when (activeDialog) {
            is DialogState.ApiError -> {
                dialogData = dialogData.copy(title = activeDialog.title, message = activeDialog.message)
                onConfirmRequest = {
                    counterViewModelContract.hideDialog()
                    navController.navigate(Screen.Settings)
                }
            }

            DialogState.Login -> {
                Log.d("SUDHEESH", "LoginDialog")
                dialogData = dialogData.copy(title = "Login", message = "Login Success")
            }

            DialogState.Logout -> {
                Log.d("SUDHEESH", "LogoutDialog")
                dialogData = dialogData.copy(title = "Logout", message = "Logout Success")
            }
        }
        AppDialog(dialogData, onConfirmRequest, onDismissRequest)
    }

    AppDialog(dialogHostState)

    val isVisible = sheetState.currentValue != SheetValue.Hidden

    if (isVisible) {
        AppBottomSheet(dataItems, sheetState) { }
    }

    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.fillMaxHeight(0.2f))
                CountText(count)
                Spacer(Modifier.fillMaxHeight(0.3f))
                ControlView(plusClick, minusClick, resetClick)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = innerPadding.calculateStartPadding(
                            LayoutDirection.Ltr
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CountText(count, Modifier.fillMaxWidth(0.45f))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(1f)
                        .fillMaxHeight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ControlView(plusClick, minusClick, resetClick)
                }

            }
        }
    }
}


@Preview(name = "PIXEL_7A", device = Devices.PIXEL_7A, showBackground = true, showSystemUi = true)
@Composable
fun CounterViewPreview() {
    val navController = rememberNavController()
    val testUiState = MutableStateFlow(UiState())
    val snackbarHostState = remember { SnackbarHostState() }

    val dataItems = List(2) { index -> BottomSheetItem(
        id = index,
        title = "Item $index"
    ) }

    Scaffold { innerPadding ->
        CounterViewUi(
            innerPadding = innerPadding,
            navController = navController,
            counterViewModelContract = object : CounterViewModelContract {
                override fun hideDialog() {}
                override fun hideSnackbar() {}
                override fun showLoginDialog() {}
                override fun showLogoutDialog() {}
                override fun showErrorDialog() {}
                override fun showNoNetworkSnackbar() {}
                override fun showSessionTimeoutSnackbar() {}
            },
            uiState = testUiState.asStateFlow(),
            snackbarHostState = snackbarHostState,
            dataItems = dataItems,
            dialogHostState = DialogHostState(rememberCoroutineScope())
        )
    }
}