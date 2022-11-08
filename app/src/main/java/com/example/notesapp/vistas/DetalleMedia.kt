package com.example.notesapp.vistas

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetalleMedia(navController: NavController, url : String?) {

    //var imageUrl : Uri
    //imageUrl = Uri.parse(url)
    //val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    //var context = LocalContext.current

    // parte visual

    /*
    imageUrl?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        bitmap.value?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Gallery Image",
                modifier = Modifier.size(400.dp)
            )
        }
    }
    *
     */
    
    Text(text = "testing...")
    
}