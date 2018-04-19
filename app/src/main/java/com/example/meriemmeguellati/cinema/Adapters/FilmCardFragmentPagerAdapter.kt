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

import java.util.ArrayList

class FilmCardFragmentPagerAdapter(fm: FragmentManager, private val baseElevation: Float) : FragmentStatePagerAdapter(fm), CardAdapter {

    private val fragments: MutableList<FilmCardFragment>

    init {
        fragments = ArrayList()

        for (i in 0..7) {
            addCardFragment(FilmCardFragment())
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
        return FilmCardFragment.getInstance(position)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        fragments[position] = fragment as FilmCardFragment
        return fragment
    }

    fun addCardFragment(fragment: FilmCardFragment) {
        fragments.add(fragment)
    }

}
