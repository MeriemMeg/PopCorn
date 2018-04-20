package com.example.meriemmeguellati.cinema.Model

import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
data class Film(
        val titre: String,
        val affiche: Int,
        val description: String,
        val trailer : String,
        val trailerposter : Int


)  : Serializable{
    var estEnCoursDeProjection : Boolean = false
    var estSuivi : Boolean = false
    var filmsLiés: ArrayList<Film> = ArrayList<Film> ()
    var commentaires : ArrayList<String> = ArrayList<String> ()
    var personnesAssociés : ArrayList<Personne> = ArrayList<Personne>()
    var sallesProjection : ArrayList<Salle> = ArrayList<Salle>()
    var evaluation : ArrayList<Int> = ArrayList<Int>()

    fun definirFilmLies (films : ArrayList<Film> ){

        this.filmsLiés.addAll(films)
    }

    fun definirCommentaires (commentaires : ArrayList<String> ){

        this.commentaires.addAll(commentaires)
    }

    fun definirPersonnes (personnes : ArrayList<Personne> ){

        this.personnesAssociés.addAll(personnes)
    }

    fun definirSalles (salles : ArrayList<Salle> ){

        this.sallesProjection.addAll(salles)
    }

    fun definirEvaluation (evaluations : ArrayList<Int> ){

        this.evaluation.addAll(evaluations)
    }

    fun suivre (){
        this.estSuivi = true
    }
    fun projeter (){
        this.estEnCoursDeProjection = true
    }

}