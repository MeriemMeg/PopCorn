package com.example.meriemmeguellati.cinema.Adapters

/**
 * Created by Meriem Meguellati on 31/03/2018.
 */
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.Fragments.FilmCardFragment
import com.example.meriemmeguellati.cinema.Fragments.SerieCardFragment
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie

import java.util.ArrayList

class SerieCardFragmentPagerAdapter(fm: FragmentManager, private val baseElevation: Float,private var dataList: ArrayList<Serie>) : FragmentStatePagerAdapter(fm), CardAdapter {

    private val fragments: MutableList<SerieCardFragment>
    private val data: ArrayList<Serie> = dataList

    init {
        fragments = ArrayList()

        for (i in 0..(dataList.size -1)) {
            addCardFragment(SerieCardFragment())
        }
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
