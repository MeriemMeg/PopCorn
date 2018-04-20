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
import com.example.meriemmeguellati.cinema.Activities.FicheSerieActivity
import com.example.meriemmeguellati.cinema.Adapters.CardAdapter
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.R


class SerieCardFragment : Fragment() {
    lateinit var serie : Serie
    var cardView: CardView? = null

    @SuppressLint("DefaultLocale")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.view_pager_item, container, false)

        cardView = view.findViewById(R.id.cardView)
        cardView!!.maxCardElevation = cardView!!.cardElevation * CardAdapter.MAX_ELEVATION_FACTOR

        this.serie = this.arguments.getSerializable("serie") as Serie
        var filmPoster: ImageView
        filmPoster = view.findViewById(R.id.filmPoster)
        filmPoster.setImageResource(this.serie.affiche)

        //évnements
        cardView!!.setOnClickListener{
            val intent = Intent(activity, FicheSerieActivity::class.java)
            intent.putExtra("serie", this.serie)
            startActivity(intent)
        }

        return view
    }

    companion object {

        fun getInstance(position: Int): Fragment {
            val f = SerieCardFragment()
            val args = Bundle()
            args.putInt("position", position)
            f.arguments = args

            return f
        }
    }
}