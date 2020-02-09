package com.garyfimo.breakingbadinfo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.garyfimo.breakingbadinfo.data.personaje.Personaje
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonajeDao {
    @Query("SELECT * FROM personaje")
    fun getAll(): Flow<List<Personaje>>

    @Query("SELECT * FROM personaje WHERE id LIKE :id ")
    fun findById(id: String): Flow<Personaje>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(personajes: List<Personaje>)
}