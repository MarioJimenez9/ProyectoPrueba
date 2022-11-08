package com.example.notesapp.vistas

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.R
import com.example.notesapp.Screen
import com.example.notesapp.componentes.BarraNavegacion
import com.example.notesapp.componentes.EditarMediaDatos
import com.example.notesapp.datos.Media
import com.example.notesapp.datos.NotasDatabase
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import java.io.File
import java.lang.Math.E

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun VistaMedia(
    navController: NavController?,
    id: String?
) {

    // Estado
    var imageUrl by remember { mutableStateOf<Uri?>(null) }
    var txtDescripcion by remember { mutableStateOf("") }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    var imgSelected  by remember { mutableStateOf(false) }
    var idLong = 0L
    var pictureSelected by remember { mutableStateOf("1")}

    if(id != null){
        idLong = id.toLong()
    }

    val db = NotasDatabase.getDatabase(context);

    val medias = db.notaDao().getMedias(idLong)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUrl = uri
    }

    val rnds2 = (0..100000).random()
    var rutaRelativa2 = rnds2.toString() + ".jpg"

    val archivo2 = File(
        context.getExternalFilesDir(null),
        rutaRelativa2
    )
    var uriFoto2 = FileProvider.getUriForFile(
        context,
        "com.example.notesapp.vistas",
        archivo2
    )

    val launcherFoto = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        if(it){
            // Se guardó el contenido de la imagen en la uri especificada
            val contentResolver: ContentResolver = context.contentResolver
            val source: ImageDecoder.Source = ImageDecoder.createSource(contentResolver, uriFoto2)
            val bitmap = ImageDecoder.decodeBitmap(source)
            //imageBitmap.value = bitmap.asImageBitmap()
        }
    }

    // Parte visual
    if(imgSelected == false) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BarraNavegacion(R.string.media_top)

            Surface(
                modifier = Modifier
                    .clickable {
                        //imageUrl = Uri.parse(media.url)
                        //var uri = Uri.parse(media.url)
                        //imageUrl = uriFoto
                        imgSelected = true
                    }
            ) {
                Text(text = imageUrl.toString())
            }

            LazyColumn(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
            ){



                items(medias){ media ->

                    val archivo = File(
                        context.getExternalFilesDir(null),
                        media.url
                    )
                    var uriFoto = FileProvider.getUriForFile(
                        context,
                        "com.example.notesapp.vistas",
                        archivo
                    )

                    Surface(
                        modifier = Modifier
                            .clickable {
                                //imageUrl = Uri.parse(media.url)
                                //var uri = Uri.parse(media.url)
                                imageUrl = uriFoto
                                imgSelected = true
                                txtDescripcion = media.descripcion
                            }
                            .padding(4.dp),
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            text = media.url + "-" + media.descripcion,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(5.dp),

                        )
                    }
                }
            }


        }
    }
    else {

            if(pictureSelected == "1") {
                val rnds = (0..100000).random()
                var rutaRelativa = rnds.toString() + ".jpg"
                Log.e("debug", rutaRelativa)
                var media = Media(idNota = idLong, url = rutaRelativa, descripcion = "Test")
                EditarMediaDatos(
                    idTitulo = R.string.app_name,
                    imageUrl = imageUrl!!,
                    {
                        media.descripcion = it
                        agregarMedia(context,media)
                        imgSelected = false
                    },
                    ruta = rutaRelativa,
                    {
                        imgSelected = false
                        Log.e("debug",it)
                    },
                    txtDescripcion
                )
            }
            else {
                var media = Media(idNota = idLong, url = rutaRelativa2, descripcion = "")
                EditarMediaDatos(
                    idTitulo = R.string.app_name,
                    imageUrl = imageUrl!!,
                    {
                        media.descripcion = it
                        agregarMedia(context,media)
                        imgSelected = false
                    },
                    ruta = rutaRelativa2,
                    {
                        imgSelected = false
                        Log.e("debug",it)
                    },
                    txtDescripcion
                )
            }

    }
    
    // boton flotante
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier.padding(bottom = 3.dp)
        ){
            FloatingActionButton(
                onClick = {
                    launcherFoto.launch(uriFoto2)
                    pictureSelected = "2"
                    imageUrl = uriFoto2
                },
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = Color.Black
            ) {
                Icon(Icons.Filled.Phone,"")
            }
        }
        Row (){
            FloatingActionButton(
                onClick = {
                    launcher.launch("image/*")
                },
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = Color.Black
            ) {
                Icon(Icons.Filled.Add,"")
            }
        }

    }
}

fun agregarMedia (context: Context, media : Media){
    val db = NotasDatabase.getDatabase(context)
    db.notaDao().addMedia(media)
}
