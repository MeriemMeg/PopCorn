package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface FilmAssocieDAO {

    @Query("SELECT * FROM filmAssocie WHERE film_id= :id_film")
    fun getFilmsAssocies(id_film : Int): List<FilmAssocieEntity>

    @Query("SELECT id FROM filmAssocie WHERE id = :id")
    fun getFilm(id : Int): Int

    @Insert
    fun insert(filmAssocie: FilmAssocieEntity)
}