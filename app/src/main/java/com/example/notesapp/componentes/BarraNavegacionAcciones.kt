package com.example.notesapp.componentes

import android.content.Context
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.notesapp.datos.Nota
import com.example.notesapp.R

@Composable
fun BarraNavegacionAcciones (
    onOkButton: () -> Unit,
    onSelectFecha: ()-> Unit
){

    var showDialog by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.updateTitle))
        },
        actions = {
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Back arrow"
                )
            }
            IconButton(onClick = { onSelectFecha() }) {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Back arrow"
                )
            }
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text =  stringResource(id = R.string.deleteTitle) ) },
            text = { Text(text = stringResource(id = R.string.dialogDelete)) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onOkButton()
                }) {
                    Text(text = stringResource(id = R.string.YES))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text(text = stringResource(id = R.string.NO))
                }
            },
        )
    }

}