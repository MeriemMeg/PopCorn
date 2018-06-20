package com.example.meriemmeguellati.cinema.Model

import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
data class Personne (
        val nom : String,
        val naissance : String,
        val fiche : Int,
        val photo : Int,
        val biographie : String

): Serializable {
    var id : Int = 0
    var profil : String = ""
    val filmographie : ArrayList<Film> = ArrayList<Film> ()
    val commentaires : ArrayList<String> = ArrayList<String> ()
    val evaluation : ArrayList<Int> = ArrayList<Int> ()


    fun definirfilmographie (films : ArrayList<Film> ){

        this.filmographie.addAll(films)
    }

    fun definirCommentaires (commentaires : ArrayList<String> ){

        this.commentaires.addAll(commentaires)
    }


    fun definirEvaluation (evaluations : ArrayList<Int> ){

        this.evaluation.addAll(evaluations)
    }
}