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
import com.example.meriemmeguellati.cinema.R


class SerieCardFragment : Fragment() {

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
            0-> filmPoster.setImageResource(R.drawable.img10)
            1-> filmPoster.setImageResource(R.drawable.img11)
            2-> filmPoster.setImageResource(R.drawable.img12)
            3-> filmPoster.setImageResource(R.drawable.img13)
            4-> filmPoster.setImageResource(R.drawable.img14)
            5-> filmPoster.setImageResource(R.drawable.img17)
            6-> filmPoster.setImageResource(R.drawable.img15)
            7-> filmPoster.setImageResource(R.drawable.img16)
        }


        /*   val title = view.findViewById(R.id.title) as TextView
           val button = view.findViewById(R.id.button) as Button

           title.text = String.format("Card %d", arguments.getInt("position"))
           button.setOnClickListener {
               Toast.makeText(activity, "Button in Card " + arguments.getInt("position")
                       + "Clicked!", Toast.LENGTH_SHORT).show()
           }*/

        cardView!!.setOnClickListener{
            val intent = Intent(activity, FicheSerieActivity::class.java)
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