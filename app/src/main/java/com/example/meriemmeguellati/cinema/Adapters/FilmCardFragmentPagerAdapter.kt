package com.example.meriemmeguellati.cinema.Adapters

/**
 * Created by Meriem Meguellati on 31/03/2018.
 */
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.Fragments.FilmCardFragment
import com.example.meriemmeguellati.cinema.Model.Film

import java.util.ArrayList
import android.os.Bundle


class FilmCardFragmentPagerAdapter(fm: FragmentManager, private val baseElevation: Float,private var dataList: ArrayList<Film>) : FragmentStatePagerAdapter(fm), CardAdapter {

    private val fragments: MutableList<FilmCardFragment>
    private val data: ArrayList<Film> = dataList

    init {
        fragments = ArrayList()


            for (i in 0..(dataList.size -1)) {
                addCardFragment(FilmCardFragment())
            }

    }


    fun replaceAll(items: ArrayList<Film>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun deleteFilm(filmId : Int) {
       for( film in data){
           if (film.id == filmId) data.remove(film)
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


        val fragment :FilmCardFragment = super.instantiateItem(container, position)as FilmCardFragment
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
