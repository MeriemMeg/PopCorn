package com.example.meriemmeguellati.cinema.Data

/**
 * Created by Meriem Meguellati on 18/04/2018.
 */
import android.content.res.Resources
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.R
import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
data class Data (
        var res : Resources

): Serializable {

    var films : ArrayList<Film> = ArrayList<Film>()
    var series : ArrayList<Serie> = ArrayList<Serie>()
    var personnes : ArrayList<Personne> = ArrayList<Personne>()
    var salle : ArrayList<Salle> = ArrayList<Salle>()
    var filmsEnCours : ArrayList<Film> = ArrayList<Film>()
    var seriesEnCours : ArrayList<Serie> = ArrayList<Serie>()
    var filmsSuivis : ArrayList<Film> = ArrayList<Film>()
    var seriesSuivies : ArrayList<Serie> = ArrayList<Serie>()
    var commentaire : ArrayList<Comment> = ArrayList<Comment>()










    fun createComments(){
        var profile: IntArray = intArrayOf(
                R.drawable.jamescorden,
                R.drawable.jenniferlawrence,
                R.drawable.jamescorden,
                R.drawable.profile,
                R.drawable.jamescorden,
                R.drawable.jamescorden
        )


        this.commentaire.add(Comment(
                res.getStringArray(R.array.comment_1)[0].toInt(),
                res.getStringArray(R.array.comment_1)[1],
                res.getStringArray(R.array.comment_1)[2],
                profile[0],
                res.getStringArray(R.array.comment_1)[4],
                res.getStringArray(R.array.comment_1)[5].toInt()
        ))
        this.commentaire.add(Comment(
                res.getStringArray(R.array.comment_2)[0].toInt(),
                res.getStringArray(R.array.comment_2)[1],
                res.getStringArray(R.array.comment_2)[2],
                profile[1],
                res.getStringArray(R.array.comment_2)[4],
                res.getStringArray(R.array.comment_2)[5].toInt()))
        this.commentaire.add(Comment(
                res.getStringArray(R.array.comment_3)[0].toInt(),
                res.getStringArray(R.array.comment_3)[1],
                res.getStringArray(R.array.comment_3)[2],
                profile[2],
                res.getStringArray(R.array.comment_3)[4],
                res.getStringArray(R.array.comment_3)[5].toInt()))
        this.commentaire.add(Comment(
                res.getStringArray(R.array.comment_4)[0].toInt(),
                res.getStringArray(R.array.comment_4)[1],
                res.getStringArray(R.array.comment_4)[2],
                profile[3],
                res.getStringArray(R.array.comment_4)[4],
                res.getStringArray(R.array.comment_4)[5].toInt()))


    }







}