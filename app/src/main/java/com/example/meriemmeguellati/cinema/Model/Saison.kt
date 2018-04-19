package com.example.meriemmeguellati.cinema.Model

import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
data class Saison(

        val serie : String,
        val num : Int,
        val affiche: Int,
        val description : String,
        val poster: Int,
        val trailer : String

): Serializable {
    var estSuivie : Boolean = false
    var personnages : ArrayList<Personne> = ArrayList<Personne> ()
    var episodes : ArrayList<Episode> = ArrayList<Episode>()
    var commentaires : ArrayList<String> = ArrayList<String>()
    var evaluation : ArrayList<Int> = ArrayList<Int>()

    fun suivre (){
        this.estSuivie = true
    }
}