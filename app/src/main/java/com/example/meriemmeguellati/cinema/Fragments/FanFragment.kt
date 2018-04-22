package com.example.meriemmeguellati.cinema.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Adapters.FilmCardFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Adapters.SerieCardFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.Activities.MainActivity
import com.example.meriemmeguellati.cinema.Adapters.SalleCardFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.Comment
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.R


/**
 * Created by Meriem Meguellati on 02/04/2018.
 */






class FanFragment : Fragment() {

    var fanFilms = ArrayList<Film>()
    var fanSeries = ArrayList<Serie>()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fan_fragment, container, false)
        val mesFilmsviewPager = view.findViewById<View>(R.id.mes_films) as ViewPager
        val mesFeuilletonsViewPager = view.findViewById<View>(R.id.mes_feuilletons) as ViewPager
        val mesSallesViewPager = view.findViewById<View>(R.id.mes_salles) as ViewPager
        val data = Data(resources)
        data.createData()
        fanFilms.addAll(data.filmsSuivis)
        fanSeries.addAll(data.seriesSuivies)

         if(this.arguments.getSerializable("fan") != null){
             val f = this.arguments.getSerializable("fan") as Film
             if(!this.fanFilms.contains(f))  this.fanFilms.add(f)

         }
        if(this.arguments.getSerializable("fanS") != null){
            val f = this.arguments.getSerializable("fanS") as Serie
            if(!this.fanSeries.contains(f))  this.fanSeries.add(f)

        }

        val pagerAdapter = FilmCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity),fanFilms)
        val fragmentCardShadowTransformer = ShadowTransformer(mesFilmsviewPager, pagerAdapter)
        fragmentCardShadowTransformer.enableScaling(true)

        mesFilmsviewPager.adapter = pagerAdapter
        mesFilmsviewPager.setPageTransformer(false, fragmentCardShadowTransformer)
        mesFilmsviewPager.offscreenPageLimit = 3

        val pagerAdapter2 = SerieCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity),fanSeries)
        val fragmentCardShadowTransformer2 = ShadowTransformer(mesFeuilletonsViewPager, pagerAdapter2)
        fragmentCardShadowTransformer2.enableScaling(true)
        mesFeuilletonsViewPager.adapter = pagerAdapter2
        mesFeuilletonsViewPager.setPageTransformer(false, fragmentCardShadowTransformer2)
        mesFeuilletonsViewPager.offscreenPageLimit = 3

        val pagerAdapter3 = SalleCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity))
        val fragmentCardShadowTransformer3 = ShadowTransformer(mesSallesViewPager, pagerAdapter3)
        fragmentCardShadowTransformer2.enableScaling(true)
        mesSallesViewPager.adapter = pagerAdapter3
        mesSallesViewPager.setPageTransformer(false, fragmentCardShadowTransformer3)
        mesSallesViewPager.offscreenPageLimit = 3

        return view
    }




}// Required empty public constructor