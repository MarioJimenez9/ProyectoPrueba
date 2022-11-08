package com.example.notesapp.componentes

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.notesapp.Screen

@Composable
fun BarraNavegacionBottomMedia(
    navController: NavController?,
    id : String
) {
    val items = listOf(
        Screen.MainScreen.route
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            label = { Text(text = "Media") },
            selected = true,
            onClick = { navController?.navigate(Screen.VistaMedia.withArgs(id)) },
            icon = { Icon(Icons.Filled.PlayArrow,"") }
        )
        BottomNavigationItem(
            label = { Text(text = "Recordatorios") },
            selected = true,
            onClick = { navController?.navigate(Screen.VistaRecordatorios.withArgs(id)) },
            icon = { Icon(Icons.Filled.Home,"") }
        )
    }
}

@Composable
@Preview
fun BarraNavegacionBottomMediaPreview(){
    BarraNavegacionBottomMedia(null,"1")
}