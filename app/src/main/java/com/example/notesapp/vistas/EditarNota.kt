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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.componentes.BarraNavegacionAcciones
import com.example.notesapp.datos.Nota
import com.example.notesapp.datos.NotasDatabase
import com.example.notesapp.R
import com.example.notesapp.componentes.BarraNavegacionBottom
import com.example.notesapp.componentes.BarraNavegacionBottomMedia
import com.example.notesapp.viewmodel.SpinnerViewModel

@Composable
fun EditarNota (navController: NavController?, id: String?){


    val context = LocalContext.current;
    val db = NotasDatabase.getDatabase(context);
    var idLong = 0L
    if(id != null){
        idLong = id.toLong()
    }
    val nota = db.notaDao().getNota(idLong)
    var msgUpdated = stringResource(id = R.string.noteUpdated)
    var msgDeleted = stringResource(id = R.string.noteDeleted)

    var txtTitulo by remember { mutableStateOf(nota.titulo)}
    var txtDetalle by remember { mutableStateOf(nota.descripcion)}
    var esTarea by remember { mutableStateOf(nota.esTarea) }
    var fechaLimite by remember { mutableStateOf(nota.fechaLimite)}

    val viewModel: SpinnerViewModel = viewModel()
    val dateTime = viewModel.time.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BarraNavegacionAcciones( { eliminarNota(context, nota,navController,msgDeleted)}, {
            viewModel.selectDateTime(context)

            //val nota = Nota( id = idLong, titulo =  txtTitulo, descripcion = txtDetalle,esTarea,fechaLimite)
            //ActualizarNota(context,nota)

        } )
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
        Text(text = fechaLimite)

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        BarraNavegacionBottomMedia(navController = navController, id!!)
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

                if(dateTime.value != null){
                    if(dateTime.value!!.imprimir() != "0/0/0 - 0:0") {
                        fechaLimite = dateTime.value!!.imprimir()
                        esTarea = true
                    }
                }

                val nota = Nota( id = idLong, titulo =  txtTitulo, descripcion = txtDetalle,esTarea,fechaLimite)
                ActualizarNota(context,nota)
                Toast.makeText(context, msgUpdated , Toast.LENGTH_SHORT).show()
                navController?.navigate(Screen.MainScreen.route)
            },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.Black
        ) {
            Icon(Icons.Filled.Check,"")
        }
    }
}

fun eliminarNota(context: Context, nota: Nota, navController: NavController?, msgDeleted: String){
    val db = NotasDatabase.getDatabase(context)
    db.notaDao().deleteNota(nota)
    Toast.makeText(context, msgDeleted , Toast.LENGTH_SHORT).show()
    navController?.navigate(Screen.MainScreen.route)
}

fun ActualizarNota(context : Context, nota: Nota){
    val db = NotasDatabase.getDatabase(context)
    db.notaDao().updateNota(nota)
}

@Composable
@Preview
fun PreviewEditarNota(){
    NotaDetalle(null)
}
