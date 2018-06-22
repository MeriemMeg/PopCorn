package com.example.meriemmeguellati.cinema.Adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.APIresponses.MovieResponse
import com.example.meriemmeguellati.cinema.Fragments.FilmCardFragment
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.R
import java.util.ArrayList




class  NowPlayingFilmFragmentPagerAdapter(fm: FragmentManager, private val baseElevation: Float) : FragmentStatePagerAdapter(fm), CardAdapter {

    private val fragments: MutableList<FilmCardFragment>
    private lateinit var data: ArrayList<Film>

    init {
        fragments = ArrayList()
        data = ArrayList<Film>()

    }


    fun addFilms(items: List<MovieResponse>) {

        var movie : Film
        for (item in items){
            addCardFragment(FilmCardFragment())

            movie = Film(item.title!!, R.drawable.p1, item.overview!!,item.posterPath!!, R.drawable.p1)
            movie.backdrop_path = item.backdropPath?:""
            movie.id = item.id
            this.data.add(movie)
        }

        notifyDataSetChanged()
    }



    override fun getBaseElevation(): Float {
        return baseElevation
    }

    override fun getCardViewAt(position: Int): CardView? {
        return fragments[position].cardView
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return FilmCardFragment.getInstance(position)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val fragment : FilmCardFragment = super.instantiateItem(container, position)as FilmCardFragment
        val bundle = Bundle()
        bundle.putSerializable("film",this.data[position])
        fragment.setArguments(bundle)
        fragments[position] = fragment
        return fragment
    }

    fun addCardFragment(fragment: FilmCardFragment) {
        fragments.add(fragment)
    }

}
