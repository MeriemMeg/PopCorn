package com.example.meriemmeguellati.cinema.Fragments

/**
 * Created by Meriem Meguellati on 31/03/2018.
 */
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Activities.FicheSerieActivity
import com.example.meriemmeguellati.cinema.Adapters.CardAdapter
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.R


class FilmCardFragment : Fragment() {
    lateinit var film : Film
    var cardView: CardView? = null

    @SuppressLint("DefaultLocale")


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.view_pager_item, container, false)

        cardView = view.findViewById(R.id.cardView)
        cardView!!.maxCardElevation = cardView!!.cardElevation * CardAdapter.MAX_ELEVATION_FACTOR

        this.film = this.arguments.getSerializable("film") as Film
        var filmPoster: ImageView
        filmPoster = view.findViewById(R.id.filmPoster)

        //filmPoster.setImageResource(this.film.affiche)
        Glide.with(getContext())
                .load(BuildConfig.BASE_URL_IMG + "w154" + this.film.posterPath)
                .apply(RequestOptions()
                        .placeholder(R.drawable.housetrailer)
                        .centerCrop()
                )
                .into(filmPoster)


        //évènements

        cardView!!.setOnClickListener{
            val intent = Intent(activity, FicheFilmActivity::class.java)
            intent.putExtra("film", this.film)
            startActivity(intent)
        }

        return view
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