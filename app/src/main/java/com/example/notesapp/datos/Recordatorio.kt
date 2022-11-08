package com.example.notesapp.datos

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordatorio_table")
data class Recordatorio (
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    var idNota : Long = 0L,
    var Fecha : String
);