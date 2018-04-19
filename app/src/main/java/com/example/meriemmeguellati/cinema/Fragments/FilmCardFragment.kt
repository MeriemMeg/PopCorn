package com.example.meriemmeguellati.cinema.Fragments

/**
 * Created by Meriem Meguellati on 31/03/2018.
 */
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Activities.FicheSerieActivity
import com.example.meriemmeguellati.cinema.Adapters.CardAdapter
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.R


class FilmCardFragment : Fragment() {

    var cardView: CardView? = null

    @SuppressLint("DefaultLocale")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.view_pager_item, container, false)

        cardView = view.findViewById(R.id.cardView)
        cardView!!.maxCardElevation = cardView!!.cardElevation * CardAdapter.MAX_ELEVATION_FACTOR

        var filmPoster: ImageView
        filmPoster = view.findViewById(R.id.filmPoster)

        when(arguments.getInt("position")){
            0-> filmPoster.setImageResource(R.drawable.img8)
            1-> filmPoster.setImageResource(R.drawable.img6)
            2-> filmPoster.setImageResource(R.drawable.img2)
            3-> filmPoster.setImageResource(R.drawable.img3)
            4-> filmPoster.setImageResource(R.drawable.img4)
            5-> filmPoster.setImageResource(R.drawable.img5)
            6-> filmPoster.setImageResource(R.drawable.img1)
            7-> filmPoster.setImageResource(R.drawable.img7)
        }




        cardView!!.setOnClickListener{
            val intent = Intent(activity, FicheFilmActivity::class.java)
            val film = Film(activity.resources.getStringArray(R.array.film_1)[0],R.drawable.americanhustle,activity.resources.getStringArray(R.array.film_1)[2],"videoplayback",R.drawable.americanhustle)
            intent.putExtra("film", film)
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