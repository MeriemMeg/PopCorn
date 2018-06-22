package com.example.meriemmeguellati.cinema.Model

import android.net.Network
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

    var posterPath : String = ""
    var backdrop_path : String = ""
 var id : Int = 0
    var networks : List<com.example.meriemmeguellati.cinema.APIresponses.Network>? = null
 var estSuivi : Boolean = false
 var saisons : ArrayList<Saison> =  ArrayList<Saison>()
 var seriesLiees : ArrayList<Serie> =  ArrayList<Serie>()
 var commentaires : ArrayList<String> = ArrayList<String>()
 var evaluation : ArrayList<Int> = ArrayList<Int>()

 fun suivre (){
  this.estSuivi = true
 }

}