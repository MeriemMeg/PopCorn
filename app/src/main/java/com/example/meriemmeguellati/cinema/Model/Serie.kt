package com.example.meriemmeguellati.cinema.Model

import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
 data class Serie(
        val titre : String,
        val affiche : Int,
        val description : String,
        val poster : Int

) : Serializable {

 var saisons : ArrayList<Saison> =  ArrayList<Saison>()
 var seriesLiees : ArrayList<Serie> =  ArrayList<Serie>()
 var commentaires : ArrayList<String> = ArrayList<String>()
 var evaluation : ArrayList<Int> = ArrayList<Int>()

 fun ajouterSaison (s : ArrayList<Saison>){
    this.saisons.addAll(s)
 }
}