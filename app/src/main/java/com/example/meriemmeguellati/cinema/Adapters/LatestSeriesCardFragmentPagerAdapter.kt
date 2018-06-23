package com.example.meriemmeguellati.cinema.Adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.LatestSeriesResponseItem
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.MovieResponse
import com.example.meriemmeguellati.cinema.Fragments.FilmCardFragment
import com.example.meriemmeguellati.cinema.Fragments.SerieCardFragment
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.R
import java.util.ArrayList

class LatestSeriesCardFragmentPagerAdapter(fm: FragmentManager, private val baseElevation: Float) : FragmentStatePagerAdapter(fm), CardAdapter {

    private val fragments: MutableList<SerieCardFragment>
    private lateinit var data: ArrayList<Serie>

    init {
        fragments = ArrayList()
        data = ArrayList<Serie>()
    }

    fun addSeries(items: List<LatestSeriesResponseItem>) {

        var serie : Serie
        for (item in items){

            addCardFragment(SerieCardFragment())
            serie =  Serie(item.name, R.drawable.defaultposter, item.overview!!, R.drawable.defaultposter)
            serie.id = item.id
            serie.posterPath = item.poster_path?:""
            this.data.add(serie)
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
        return SerieCardFragment.getInstance(position)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val fragment : SerieCardFragment = super.instantiateItem(container, position)as SerieCardFragment
        val bundle = Bundle()
        bundle.putSerializable("serie",this.data[position])
        fragment.setArguments(bundle)
        fragments[position] = fragment
        return fragment
    }

    fun addCardFragment(fragment: SerieCardFragment) {
        fragments.add(fragment)
    }

}