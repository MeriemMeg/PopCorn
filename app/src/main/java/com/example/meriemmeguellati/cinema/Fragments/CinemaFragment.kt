package com.example.meriemmeguellati.cinema.Fragments


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.Adapters.CinemaPagerAdapter
import com.example.meriemmeguellati.cinema.R
import kotlinx.android.synthetic.main.fragment_cinema.*

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class CinemaFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cinema, container, false)
        val pagerAdapter = CinemaPagerAdapter(this.fragmentManager)
        pagerAdapter!!.addFragments(MovieFragment(), "Films")
        pagerAdapter!!.addFragments(SallesFragment(), "Salles")

        //add pagerAdapter to viewPager
        val cinemaViewPager  = view.findViewById<ViewPager>(R.id.viewPagerSalles)
        cinemaViewPager.adapter = pagerAdapter

        //set up viewPager with the tabLayout
        val cinemaTabLayout = view.findViewById<TabLayout>(R.id.cinemaTabLayout)
        cinemaTabLayout.setupWithViewPager(cinemaViewPager)
        return view
    }




}
