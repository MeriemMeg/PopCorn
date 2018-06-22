package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PersonneDAO {

    @Query("SELECT * FROM personne")
    fun getPersonnes(): List<PersonneEntity>

    @Insert
    fun insert(personne: PersonneEntity)
}