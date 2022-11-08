package com.example.notesapp.vistas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.notesapp.componentes.BarraNavegacion
import com.example.notesapp.datos.Nota
import com.example.notesapp.datos.NotasDatabase
import com.example.notesapp.R

@Composable
fun NotaDetalle (navController: NavController?){

    var txtTitulo by remember { mutableStateOf("")}
    var txtDetalle by remember { mutableStateOf("")}
    val context = LocalContext.current
    var msgAdd = stringResource(id = R.string.noteAdded)

    BarraNavegacion(id = R.string.app_name)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BarraNavegacion(R.string.app_name)
        Row(modifier = Modifier.weight(0.3f,true)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ){
                BasicTextField(
                    value = txtTitulo,
                    onValueChange = {
                        txtTitulo = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    textStyle = TextStyle(fontSize = 28.sp)
                )
                if (txtTitulo == ""){
                    Text(text = stringResource(id = R.string.title), fontSize = 28.sp)
                }
            }
        }
        Row(modifier = Modifier.weight(2.5f,true)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ){
                BasicTextField(
                    value = txtDetalle,
                    onValueChange = {
                        txtDetalle = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()

                        .fillMaxHeight(),
                    textStyle = TextStyle(fontSize = 16.sp)
                )
                if (txtDetalle == ""){
                    Text(text = stringResource(id = R.string.type), fontSize = 16.sp)
                }
            }
        }


    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                val nota = Nota( titulo =  txtTitulo, descripcion = txtDetalle,esTarea = false, fechaLimite =  "")
                insertarNota(context,nota)
                Toast.makeText(context, msgAdd , Toast.LENGTH_SHORT).show()
                navController?.navigate(Screen.MainScreen.route)
            },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.Black
        ) {
            Icon(Icons.Filled.Check,"")
        }
    }
}

fun insertarNota(context : Context, nota: Nota){
    val db = NotasDatabase.getDatabase(context)
    db.notaDao().addNota(nota)
}

@Composable
@Preview
fun PreviewNotaDetalle(){
    NotaDetalle(null)
}
