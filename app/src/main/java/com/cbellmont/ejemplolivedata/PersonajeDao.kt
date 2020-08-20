package com.cbellmont.ejemplolivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface PersonajeDao {
    @Query("SELECT * FROM Personaje")
    fun getAll(): List<Personaje>

    @Query("SELECT * FROM Personaje")
    fun getAllLive(): LiveData<List<Personaje>>


    @Query("SELECT * FROM Personaje WHERE id IN (:PersonajesId)")
    fun loadAllByIds(PersonajesId: IntArray): List<Personaje>

    @Query("SELECT * FROM Personaje WHERE nombre LIKE (:nombrePersonaje)")
    fun loadAllByTitle(nombrePersonaje: String): List<Personaje>

    @Insert
    fun insert(Personaje: Personaje)

    @Update
    fun update(Personaje: Personaje)

    @Insert
    fun insertAll(Personajes: List<Personaje>)

    @Delete
    fun delete(Personaje: Personaje)
}
