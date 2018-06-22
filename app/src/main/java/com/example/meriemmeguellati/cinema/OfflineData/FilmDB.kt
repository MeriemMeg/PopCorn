package com.example.meriemmeguellati.cinema.OfflineData

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = arrayOf(
                FilmEntity::class,
                PersonneEntity::class,
                FilmAssocieEntity::class
        ), version = 1
)
abstract class FilmDB : RoomDatabase(){
    abstract fun FilmDAO(): FilmDAO
    abstract fun PersonneDAO(): PersonneDAO
    abstract fun FilmAssocieDAO(): FilmAssocieDAO

    companion object {
        private var instance: FilmDB? = null

        fun getInstance(context: Context): FilmDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.applicationContext,
                        FilmDB::class.java, "film.db")
                        .build()

            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}