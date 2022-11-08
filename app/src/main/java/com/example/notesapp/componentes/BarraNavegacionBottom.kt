package com.example.notesapp.componentes

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.notesapp.R
import com.example.notesapp.Screen

@Composable
fun BarraNavegacionBottom(navController: NavController?) {
    val items = listOf(
        Screen.MainScreen.route
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            label = { Text(text = "Todos") },
            selected = true,
            onClick = { navController?.navigate(Screen.MainScreen.route) },
            icon = {Icon(Icons.Filled.Home,"")}
        )
        BottomNavigationItem(
            label = { Text(text = "Notas") },
            selected = true,
            onClick = { navController?.navigate(Screen.VistaNotas.route) },
            icon = {Icon(Icons.Filled.Check,"")}
        )
        BottomNavigationItem(
            label = { Text(text = "Tareas") },
            selected = true,
            onClick = { navController?.navigate(Screen.VistaTareas.route) },
            icon = {Icon(Icons.Filled.DateRange,"")}
        )
    }
}

@Composable
@Preview
fun BarraNavegacionBottomPreview(){
    BarraNavegacionBottom(null)
}