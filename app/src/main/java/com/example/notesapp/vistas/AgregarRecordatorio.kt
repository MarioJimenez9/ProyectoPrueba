package com.example.notesapp.vistas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.notesapp.Screen
import com.example.notesapp.datos.NotasDatabase
import com.example.notesapp.datos.Recordatorio
import com.example.notesapp.viewmodel.SpinnerViewModel

@Composable
fun AgregarRecordatorio(navController: NavController, id : String) {

    val viewModel: SpinnerViewModel = viewModel()
    val dateTime = viewModel.time.observeAsState()
    var context = LocalContext.current

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row( modifier = Modifier.padding(bottom = 5.dp) ) {
            Button(onClick = { viewModel.selectDateTime(context) }) {
                Text(text = "Seleccionar Fecha")
            }
        }
        Row(modifier = Modifier.padding(bottom = 5.dp)) {
            Text(text = dateTime.value?.imprimir()!!)
        }
        Row() {
            Button(onClick = {
                var recordatorio = Recordatorio(idNota = id.toLong(), Fecha = dateTime.value?.imprimir()!!)
                val db = NotasDatabase.getDatabase(context)
                db.notaDao().addRecordatorio(recordatorio)
                navController.navigate(Screen.VistaRecordatorios.withArgs(id))
            }) {
                Text(text = "Agregar Recordatorio")
            }
        }
    }
}