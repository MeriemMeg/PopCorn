package com.example.meriemmeguellati.cinema.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APISeriesCall
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.Language
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.NowPlayingResponse
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APImoviesCall
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.LatestSeriesResponse
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.Activities.MainActivity
import com.example.meriemmeguellati.cinema.Adapters.LatestSeriesCardFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Adapters.NowPlayingFilmFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Meriem Meguellati on 21/06/2018.
 */






class NoConnexionFragment : Fragment() {




    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.noconnexion_fragment, container, false)

        return view
    }






}// Required empty public constructor