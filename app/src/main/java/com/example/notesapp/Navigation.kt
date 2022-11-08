package com.example.notesapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.componentes.BarraNavegacion
import com.example.notesapp.componentes.BarraNavegacionAcciones
import com.example.notesapp.vistas.*

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Navigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            VistaNotas( navController = navController , 0)
        }
        composable(route = Screen.VistaTareas.route){
            VistaNotas( navController = navController , 1)
        }
        composable(route = Screen.VistaNotas.route){
            VistaNotas( navController = navController , 2)
        }
        composable(route = Screen.DetailScreen.route){
            NotaDetalle( navController = navController )
        }
        composable(
            route = Screen.EditarNota.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ){
            EditarNota( navController = navController, id = it.arguments?.getString("id") )
        }
        composable(
            route = Screen.VistaMedia.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ){
            VistaMedia(navController = navController, id = it.arguments?.getString("id"))
        }
        composable(
            route = Screen.DetalleMedia.route + "/{url}",
            arguments = listOf(
                navArgument("url"){
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ){
            DetalleMedia(navController = navController, url = it.arguments?.getString("url"))
        }
        composable(
            route = Screen.VistaRecordatorios.route + "/{id}",
            arguments = listOf(
            navArgument("id"){
                type = NavType.StringType
                defaultValue = "0"
                nullable = true
            }
            )
        ){
            VistaRecordatorios(navController = navController, idRecordatorio = it.arguments?.getString("id") )
        }
        composable(
            route = Screen.AgregarRecordatorio.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ) {
            AgregarRecordatorio(navController = navController, id = it.arguments?.getString("id")!!)
        }
    }
}