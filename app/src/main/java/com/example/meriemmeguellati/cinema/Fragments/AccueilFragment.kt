package com.example.meriemmeguellati.cinema.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.meriemmeguellati.cinema.APIresponses.Language
import com.example.meriemmeguellati.cinema.APIresponses.NowPlayingResponse
import com.example.meriemmeguellati.cinema.APIuser
import com.example.meriemmeguellati.cinema.Adapters.SerieCardFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.Activities.MainActivity
import com.example.meriemmeguellati.cinema.Adapters.NowPlayingFilmFragmentPagerAdapter
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Meriem Meguellati on 02/04/2018.
 */






class AccueilFragment : Fragment() {


    private var apiCall: Call<NowPlayingResponse>? = null
    private val apiUser = APIuser()
    private lateinit var nowPlayingFilmsPagerAdapter : NowPlayingFilmFragmentPagerAdapter
    private lateinit var viewPager : ViewPager
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.accueil_fragment, container, false)
        viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        val viewPager2 = view.findViewById<ViewPager>(R.id.viewPager2)

        val data = Data(resources)
        data.createData()
        //val pagerAdapter = FilmCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity),data.filmsEnCours)
        nowPlayingFilmsPagerAdapter = NowPlayingFilmFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity))
        loadData()

        val pagerAdapter2 = SerieCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity),data.seriesEnCours)
        val fragmentCardShadowTransformer2 = ShadowTransformer(viewPager2, pagerAdapter2)
        fragmentCardShadowTransformer2.enableScaling(true)
        viewPager2.adapter = pagerAdapter2
        viewPager2.setPageTransformer(false, fragmentCardShadowTransformer2)
        viewPager2.offscreenPageLimit = 3

        return view
    }

    private fun loadData() {
        apiCall = apiUser.getService().getNowPlayingMovie(Language().Country())
        apiCall!!.enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(call: Call<NowPlayingResponse>, response: Response<NowPlayingResponse>) {
                if (response.isSuccessful) {

                    nowPlayingFilmsPagerAdapter.addFilms(response.body()!!.getResults()!!)
                    val fragmentCardShadowTransformer = ShadowTransformer(viewPager, nowPlayingFilmsPagerAdapter)
                    fragmentCardShadowTransformer.enableScaling(true)

                    viewPager.adapter = nowPlayingFilmsPagerAdapter
                    viewPager.setPageTransformer(false, fragmentCardShadowTransformer)
                    viewPager.offscreenPageLimit = 3

                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadFailed() {
        Toast.makeText(context, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
    }




}// Required empty public constructor