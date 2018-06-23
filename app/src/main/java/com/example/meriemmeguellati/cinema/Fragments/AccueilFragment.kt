package com.example.meriemmeguellati.cinema.Fragments


import android.content.Context
import android.net.ConnectivityManager
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



class AccueilFragment : Fragment() {


    private var apiMoviesCall: Call<NowPlayingResponse>? = null
    private val apiMovies = APImoviesCall()
    private var apiSeriesCall: Call<LatestSeriesResponse>? = null
    private val apiSeries = APISeriesCall()
    private lateinit var nowPlayingFilmsPagerAdapter : NowPlayingFilmFragmentPagerAdapter
    private lateinit var latestSeriesPagerAdapter : LatestSeriesCardFragmentPagerAdapter
    private lateinit var moviesViewPager : ViewPager
    private lateinit var seriesViewPager : ViewPager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.accueil_fragment, container, false)
        moviesViewPager = view.findViewById<ViewPager>(R.id.viewPager)
        seriesViewPager = view.findViewById<ViewPager>(R.id.viewPager2)


        nowPlayingFilmsPagerAdapter = NowPlayingFilmFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity))
        LoadLatestMovies()

        latestSeriesPagerAdapter = LatestSeriesCardFragmentPagerAdapter(childFragmentManager, MainActivity.dpToPixels(2, activity))
        LoadLatestSeries(context)

        return view
    }

    private fun LoadLatestMovies() {
        apiMoviesCall = apiMovies.getService().getNowPlayingMovie(Language().Country())
        apiMoviesCall!!.enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(call: Call<NowPlayingResponse>, response: Response<NowPlayingResponse>) {
                if (response.isSuccessful) {

                    nowPlayingFilmsPagerAdapter.addFilms(response.body()!!.getResults()!!)
                    val fragmentCardShadowTransformer = ShadowTransformer(moviesViewPager, nowPlayingFilmsPagerAdapter)
                    fragmentCardShadowTransformer.enableScaling(true)

                    moviesViewPager.adapter = nowPlayingFilmsPagerAdapter
                    moviesViewPager.setPageTransformer(false, fragmentCardShadowTransformer)
                    moviesViewPager.offscreenPageLimit = 3

                } else {
                    Toast.makeText(context, response.message() , Toast.LENGTH_LONG).show();
                }

            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun LoadLatestSeries(context: Context) {
        apiSeriesCall = apiSeries.getService().getNowPlayingSeries(Language().Country())
        apiSeriesCall!!.enqueue(object : Callback<LatestSeriesResponse> {
            override fun onResponse(call: Call<LatestSeriesResponse>, response: Response<LatestSeriesResponse>) {
                if (response.isSuccessful) {

                    latestSeriesPagerAdapter.addSeries(response.body()!!.getResults()!!)
                    val fragmentCardShadowTransformer2 = ShadowTransformer(seriesViewPager, latestSeriesPagerAdapter)
                    fragmentCardShadowTransformer2.enableScaling(true)
                    seriesViewPager.adapter = latestSeriesPagerAdapter
                    seriesViewPager.setPageTransformer(false, fragmentCardShadowTransformer2)
                    seriesViewPager.offscreenPageLimit = 3


                } else
                    Toast.makeText(context, response.message() , Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<LatestSeriesResponse>, t: Throwable) {
                loadFailed()

            }
        })
    }

    private fun loadFailed() {
        Toast.makeText(context, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
    }

    fun checkConnexion():Boolean{

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnectedOrConnecting
    }




}// Required empty public constructor