package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo

@Entity()
data class FilmEntity (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name= "id") var id: Int,
        @ColumnInfo(name= "titre") var titre: String,
        @ColumnInfo(name= "affiche") var affiche: String,
        @ColumnInfo(name= "description") var description: String,
        @ColumnInfo(name= "trailer") var trailer : String,
        @ColumnInfo(name= "trailerposter") var trailerposter : Int
)
{
    constructor():this(0,"","","","",1)
}