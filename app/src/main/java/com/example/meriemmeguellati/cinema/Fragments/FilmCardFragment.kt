package com.example.meriemmeguellati.cinema.Fragments

/**
 * Created by Meriem Meguellati on 31/03/2018.
 */
import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Activities.FicheSerieActivity
import com.example.meriemmeguellati.cinema.Adapters.CardAdapter
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Personne
import com.example.meriemmeguellati.cinema.OfflineData.*
import com.example.meriemmeguellati.cinema.R


class FilmCardFragment : Fragment() {
    lateinit var film : Film
    var cardView: CardView? = null
    private var db: FilmDB? = null
    private var personneDao: PersonneDAO? = null
    private var personneEntity: List<PersonneEntity>? = null
    private var filmDao: FilmAssocieDAO? = null
    private var filmEntity: List<FilmAssocieEntity>? = null

    @SuppressLint("DefaultLocale")


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.view_pager_item, container, false)

        cardView = view.findViewById(R.id.cardView)
        cardView!!.maxCardElevation = cardView!!.cardElevation * CardAdapter.MAX_ELEVATION_FACTOR

        this.film = this.arguments.getSerializable("film") as Film
        val mode = this.arguments.getString("mode")
        var filmPoster: ImageView
        filmPoster = view.findViewById(R.id.filmPoster)

        //filmPoster.setImageResource(this.film.affiche)
        Glide.with(getContext())
                .load(BuildConfig.BASE_URL_IMG + "w500" + this.film.posterPath)
                .apply(RequestOptions()
                        .placeholder(R.drawable.defaultposter)
                        .centerCrop()
                )
                .into(filmPoster)


        //évènements
        cardView!!.setOnClickListener{
            if (mode === "online") {
                onlineMode()
            } else {
                offlineMode()
            }
        }



        return view
    }
    fun onlineMode(){
        val intent = Intent(activity, FicheFilmActivity::class.java)
        intent.putExtra("film", film)
        intent.putExtra("mode", "online")
        startActivity(intent)
    }

    fun offlineMode(){
        var act = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                act.db = FilmDB.getInstance(act.context!!)
                act.filmDao = db?.FilmAssocieDAO()
                act.personneDao = db?.PersonneDAO()
                personneEntity = act.personneDao?.getPersonnes()
                filmEntity = act.filmDao?.getFilmsAssocies()

                return null
            }

            override fun onPostExecute(result: Void?) {
                val intent = Intent(activity, FicheFilmActivity::class.java)
                intent.putExtra("film", film)
                intent.putExtra("mode", "offline")

                if (personneEntity != null) {

                    val personnes = java.util.ArrayList<Personne>()

                    for (i in 0..(personneEntity!!.size-1)){
                        var personne = Personne(
                                personneEntity!![i].nom,
                                "",
                                R.drawable.jenniferlawrence,
                                R.drawable.jamescorden,
                                "")
                        personne.id = personneEntity!![i].id
                        personnes.add(personne)
                    }
                    if(personnes.size>0){
                        intent.putExtra("personnes", personnes)
                    }else {
                        Toast.makeText(act.context,"Aucun personnage n'a été trouvé", Toast.LENGTH_SHORT).show()
                    }

                }

                if (filmEntity != null) {

                    val films = java.util.ArrayList<Film>()

                    for (i in 0..(filmEntity!!.size-1)){
                        var film = Film(
                                filmEntity!![i].titre,
                                filmEntity!![i].affiche,
                                "",
                                "",
                                0)
                        film.id = filmEntity!![i].id
                        films.add(film)
                    }
                    if(films.size>0){
                        intent.putExtra("filmsAssocies", films)
                    }else {
                        Toast.makeText(act.context,"Aucun personnage n'a été trouvé", Toast.LENGTH_SHORT).show()
                    }

                }

                startActivity(intent)
            }
        }.execute()
    }



    companion object {

        fun getInstance(position: Int): Fragment {
            val f = FilmCardFragment()
            val args = Bundle()
            args.putInt("position", position)
            f.arguments = args

            return f
        }
    }
}