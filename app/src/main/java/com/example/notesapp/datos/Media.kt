package com.example.notesapp.datos

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class Media (
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    var idNota : Long = 0L,
    var url: String,
    var descripcion : String
);