package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "filmAssocie",
        foreignKeys = arrayOf(ForeignKey(entity = FilmEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("film_id"),
                onDelete = ForeignKey.CASCADE)))
data class FilmAssocieEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name= "id") var id: Int,
        @ColumnInfo(name = "film_id") val filmId: Int,
        @ColumnInfo(name = "titre") val titre: String,
        @ColumnInfo(name = "affiche") val affiche: Int
)