package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "personne",
        foreignKeys = arrayOf(ForeignKey(entity = FilmEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("film_id"),
                onDelete = ForeignKey.CASCADE)))
data class PersonneEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name= "id") var id: Int,
        @ColumnInfo(name = "film_id") val filmId: Int,
        @ColumnInfo(name = "nom") val nom: String,
        @ColumnInfo(name = "photo") val photo: String
)