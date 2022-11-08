package com.example.notesapp.componentes

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

@Composable
fun EditarMediaDatos(
    idTitulo : Int,
    imageUrl: Uri,
    onSaveButton: (txtDescripcion: String) -> Unit,
    ruta: String,
    regresar: (txtCoso : String) -> Unit,
    txtDesc: String
) {

    val context = LocalContext.current
    var text by remember { mutableStateOf(txtDesc) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    //text = txtDesc

    imageUrl?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    Column() {
        BarraNavegacion(id = idTitulo)
        Row() {

            //bitmap.value?.let { bitmap ->
            Image(
                bitmap = bitmap.value!!.asImageBitmap(),
                contentDescription = "Gallery Image",
                modifier = Modifier.size(400.dp)
            )
            //}
        }
        Row() {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Descripcion") }
            )
        }
        Row( modifier = Modifier.padding(4.dp) ) {
            Button(

                onClick = {
                    val archivo = File(
                        context.getExternalFilesDir(null),
                        ruta
                    )
                    copiaImagen(imageUrl, archivo.absolutePath,context.contentResolver)
                    onSaveButton(text)
                }
            ) {
                Text(text = "GUARDAR")
            }
        }
        Row( modifier = Modifier.padding(4.dp) ) {
            Button(
                onClick = { regresar("hi") }
            ) {
                Text(text = "REGRESAR")
            }
        }
    }
}

fun copiaImagen(uri: Uri, archivo: String, contentResolver: ContentResolver) {
    val inputStream: InputStream? =
        contentResolver.openInputStream(uri)
    val outputStream: OutputStream = FileOutputStream(archivo)
    val buf = ByteArray(1024)
    var len: Int
    if (inputStream != null) {
        while (inputStream.read(buf).also { len = it } > 0) {
            outputStream.write(buf, 0, len)
        }
    }
    outputStream.close()
    inputStream?.close()
}