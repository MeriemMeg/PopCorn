package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PersonneDAO {

    @Query("SELECT * FROM personne WHERE film_id= :filmId ")
    fun getPersonnes(filmId : Int): List<PersonneEntity>

    @Query("SELECT id FROM personne WHERE id = :id")
    fun getPersonne(id : Int): Int

    @Insert
    fun insert(personne: PersonneEntity)
}