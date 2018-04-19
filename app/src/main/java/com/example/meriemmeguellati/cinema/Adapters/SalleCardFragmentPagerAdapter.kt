package com.example.meriemmeguellati.cinema.Adapters

/**
 * Created by Meriem Meguellati on 31/03/2018.
 */
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.Fragments.SalleCardFragment

import java.util.ArrayList

class SalleCardFragmentPagerAdapter(fm: FragmentManager, private val baseElevation: Float) : FragmentStatePagerAdapter(fm), CardAdapter {

    private val fragments: MutableList<SalleCardFragment>

    init {
        fragments = ArrayList()

        for (i in 0..2) {
            addCardFragment(SalleCardFragment())
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
        return SalleCardFragment.getInstance(position)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        fragments[position] = fragment as SalleCardFragment
        return fragment
    }

    fun addCardFragment(fragment: SalleCardFragment) {
        fragments.add(fragment)
    }

}
