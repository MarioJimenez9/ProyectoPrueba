package com.example.notesapp.datos

import android.content.Context
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nota::class,Media::class, Recordatorio::class], version = 3, exportSchema = false)
abstract class NotasDatabase : RoomDatabase() {
    abstract fun notaDao(): NotaDao

    companion object{
        @Volatile
        private var INSTANCE: NotasDatabase? = null

        fun getDatabase(context : Context) : NotasDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotasDatabase::class.java,
                    "notas_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}