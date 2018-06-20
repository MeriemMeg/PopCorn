package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.*

@Dao
interface FilmDAO {
    @Query("SELECT * FROM filmEntity")
    fun getFilms(): List<FilmEntity>


    @Query("SELECT id FROM filmEntity LIMIT 1 ")
    fun getLatestId(): Int

    @Query("SELECT id FROM filmEntity WHERE id = :idFilm  ")
    fun getFilm(idFilm : Int): Int

    @Insert
    fun ajouter(note : FilmEntity)

    @Query("Delete  FROM filmEntity WHERE id = :idFilm ")
    fun supprimer(idFilm : Int)
}