package com.example.notesapp.vistas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.Screen
import com.example.notesapp.componentes.BarraNavegacionBottomMedia
import com.example.notesapp.datos.Nota
import com.example.notesapp.datos.NotasDatabase
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import com.example.notesapp.R
import com.example.notesapp.componentes.BarraNavegacion

@Composable
fun VistaRecordatorios(navController: NavController, idRecordatorio: String?) {

    var context = LocalContext.current
    val db = NotasDatabase.getDatabase(context);
    val recordatorios = db.notaDao().getRecordatorios(idRecordatorio?.toLong()!!)

    Column(

    ) {
        BarraNavegacion(id = R.string.titulo_recordatorio)
        LazyColumn(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ){
            items(recordatorios){ recordatorio ->
                Surface(
                    modifier = Modifier
                    .padding(4.dp),
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = recordatorio.Fecha)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        BarraNavegacionBottomMedia(navController = navController, idRecordatorio!!)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(bottom = 50.dp)
        ,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                navController?.navigate(Screen.AgregarRecordatorio.withArgs(idRecordatorio!!))
            },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.Black
        ) {
            Icon(Icons.Filled.Add,"")
        }
    }

}