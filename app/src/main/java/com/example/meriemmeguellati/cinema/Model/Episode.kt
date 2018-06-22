package com.example.meriemmeguellati.cinema.Model

import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
 data class Episode(
        val serie: String,
        val saison : Int,
        val num : Int,
        val affiche : Int,
        val poster : Int,
        val trailer : String

) : Serializable{
    var networks : List<com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.Network>? = null
    var description : String = ""
    var id :Int = 0
    var still_path :String = ""
    val diffusion :  ArrayList<Chaine> =  ArrayList<Chaine>()
    val commentaires : ArrayList<String> =  ArrayList<String>()
    val evaluation : ArrayList<Int> =  ArrayList<Int> ()
}