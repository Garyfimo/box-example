package com.garyfimo.breakingbadinfo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.garyfimo.breakingbadinfo.data.personaje.Personaje

@Database(
    version = 1,
    exportSchema = false,
    entities = [Personaje::class]
)
abstract class DatabaseModel : RoomDatabase() {

    abstract fun personajeDao(): PersonajeDao

    companion object {
        operator fun invoke(context: Context): DatabaseModel {

            return Room
                .inMemoryDatabaseBuilder(
                    context,
                    DatabaseModel::class.java
                )
                .build()
        }
    }
}