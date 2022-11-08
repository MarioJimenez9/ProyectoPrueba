package com.example.notesapp

class Fecha (
    var year: Int,
    var month: Int,
    var day: Int,
    var hour: Int,
    var minute: Int
) {

    fun imprimir() : String {
        return "$day/$month/$year - $hour:$minute "
    }

}