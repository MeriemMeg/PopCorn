package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface FilmAssocieDAO {

    @Query("SELECT * FROM filmAssocie")
    fun getFilmsAssocies(): List<FilmAssocieEntity>

    @Insert
    fun insert(filmAssocie: FilmAssocieEntity)
}