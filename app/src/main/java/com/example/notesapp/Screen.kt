package com.example.notesapp

import androidx.navigation.NavArgs

sealed class Screen(val route: String){
    object MainScreen : Screen("Todas")
    object DetailScreen : Screen("detail_screen")
    object EditarNota : Screen("editar_nota")
    object VistaTareas: Screen("tareas")
    object VistaNotas: Screen("notas")
    object VistaMedia: Screen("mediaSelect")
    object DetalleMedia: Screen("detalleMedia")
    object VistaRecordatorios : Screen("vistaRecordatorios")
    object AgregarRecordatorio : Screen("addReminder")

    fun withArgs( vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}