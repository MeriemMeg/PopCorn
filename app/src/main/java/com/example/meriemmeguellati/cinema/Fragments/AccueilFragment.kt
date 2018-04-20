package com.example.meriemmeguellati.cinema.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.meriemmeguellati.cinema.Adapters.FilmCardFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Adapters.SerieCardFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.Activities.MainActivity
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.R


/**
 * Created by Meriem Meguellati on 02/04/2018.
 */






class AccueilFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.accueil_fragment, container, false)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        val viewPager2 = view.findViewById<ViewPager>(R.id.viewPager2)

        val data = Data(resources)
        data.createData()
        val pagerAdapter = FilmCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity),data.filmsEnCours)
        val fragmentCardShadowTransformer = ShadowTransformer(viewPager, pagerAdapter)
        fragmentCardShadowTransformer.enableScaling(true)

        viewPager.adapter = pagerAdapter
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer)
        viewPager.offscreenPageLimit = 3

        val pagerAdapter2 = SerieCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity),data.seriesEnCours)
        val fragmentCardShadowTransformer2 = ShadowTransformer(viewPager2, pagerAdapter2)
        fragmentCardShadowTransformer2.enableScaling(true)
        viewPager2.adapter = pagerAdapter2
        viewPager2.setPageTransformer(false, fragmentCardShadowTransformer)
        viewPager2.offscreenPageLimit = 3

        return view
    }




}// Required empty public constructor