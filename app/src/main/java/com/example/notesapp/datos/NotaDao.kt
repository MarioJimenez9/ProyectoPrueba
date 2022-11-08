package com.example.notesapp.datos

import androidx.room.*

@Dao
interface NotaDao {

    @Insert
    fun addMedia (media: Media)

    @Update
    fun updateMedia (media: Media)

    @Insert
    fun addNota(nota : Nota)

    @Insert
    fun addRecordatorio(recordatorio: Recordatorio)

    @Update
    fun updateNota(nota: Nota)

    @Delete
    fun deleteNota(nota: Nota)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getTodos():List<Nota>

    @Query("SELECT * FROM note_table where esTarea = 0 ORDER BY id ASC")
    fun getNotas(): List<Nota>

    @Query("SELECT * FROM note_table where esTarea = 1 ORDER BY id ASC")
    fun getTareas(): List<Nota>

    @Query("SELECT * FROM note_table where id = :id")
    fun getNota(id: Long): Nota

    @Query("SELECT * FROM media_table where idNota = :id")
    fun getMedias(id: Long): List<Media>

    @Query("SELECT * FROM recordatorio_table where idNota = :id")
    fun getRecordatorios(id: Long): List<Recordatorio>
}