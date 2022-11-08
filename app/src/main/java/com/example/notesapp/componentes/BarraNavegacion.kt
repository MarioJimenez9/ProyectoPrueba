package com.example.notesapp.componentes

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.notesapp.R

@Composable
fun BarraNavegacion (id: Int){
    TopAppBar(title = {
        Text(text = stringResource(id = id))
    })
}
