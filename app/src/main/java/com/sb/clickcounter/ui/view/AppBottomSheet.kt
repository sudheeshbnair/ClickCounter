package com.sb.clickcounter.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sb.clickcounter.viewmodel.BottomSheetItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(sheetItems : List<BottomSheetItem>, sheetState: SheetState, onDismissRequest: () -> Unit) {

    val scope = rememberCoroutineScope()
    val bottomSheetItems = remember { mutableStateListOf<BottomSheetItem>().apply { addAll(sheetItems) } }

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }
            onDismissRequest.invoke()
        },
        sheetState = sheetState
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(bottomSheetItems){ index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        modifier = Modifier.weight(1f)
                    )
                    Checkbox(
                        checked = item.isSelected,
                        onCheckedChange = { isChecked ->
                            bottomSheetItems[index] = item.copy(isSelected = isChecked)
                        }
                    )
                }
            }
        }
    }
}