package com.example.meriemmeguellati.cinema.Activities

import android.app.Activity

import android.os.Bundle

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ListView
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewFilmLiesAdapter
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewResultsAdapter
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.R
import java.util.*
import android.icu.util.ULocale.getCountry
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewSerieResultsAdapter
import com.example.meriemmeguellati.cinema.Adapters.SeriesAdapter
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.Language
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.LatestSeriesResponse
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.NowPlayingResponse
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APImoviesCall
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APIsearchCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchResultsActivity  : Activity(){

    lateinit var list: RecyclerView
    var FilmListResult = ArrayList<Film>()
    var SerieListResult = ArrayList<Serie>()
    var serieSearch = false
    private var apiMovieSearchCall: Call<NowPlayingResponse>? = null
    private var apiSerieSearchCall: Call<LatestSeriesResponse>? = null
    lateinit var apiSearch :APIsearchCall
    lateinit var adapter1 : RecyclerViewSerieResultsAdapter
    lateinit var adapter2 : RecyclerViewResultsAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_results_activity)

        this.list = findViewById<RecyclerView>(R.id.results)

        this.list.setHasFixedSize(true)

        handleIntent(intent)



    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            val appData = intent.getBundleExtra(SearchManager.APP_DATA)

            if (appData != null) {
                val jargon = appData.getBoolean("serie")
                if (jargon) {
                    this.serieSearch = true
                    SearchSeries(query,this)
                } else {

                    SearchMovies(query,this)
                }

            } else {
                   SearchMovies(query,this)
            }



        }
    }



    private fun SearchMovies(query : String,context: Context) {
        apiSearch = APIsearchCall(query)
        apiMovieSearchCall = apiSearch.getService().searchMovie(Language().Country(),false)
        apiMovieSearchCall!!.enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(call: Call<NowPlayingResponse>, response: Response<NowPlayingResponse>) {
                if (response.isSuccessful) {

                    val items = response.body()!!.getResults()
                    var film : Film
                    if (items != null) {
                        for (item in items){

                            film = Film(item.title!!, R.drawable.p1, item.overview!!,item.posterPath?:"", R.drawable.p1)
                            film.id = item.id
                            film.backdrop_path = item.backdropPath?:""
                            FilmListResult.add(film)


                        }
                        if(FilmListResult.size==0) Toast.makeText(context, "Aucun résultat trouvé" , Toast.LENGTH_LONG).show()
                        adapter2 = RecyclerViewResultsAdapter(context, FilmListResult)
                        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        list.adapter = adapter2


                    }


                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                loadFailed()

            }
        })
    }


    private fun SearchSeries(query: String,context : Context) {
        apiSearch = APIsearchCall(query)
        apiSerieSearchCall = apiSearch.getService().searchSerie(Language().Country())
        apiSerieSearchCall!!.enqueue(object : Callback<LatestSeriesResponse> {
            override fun onResponse(call: Call<LatestSeriesResponse>, response: Response<LatestSeriesResponse>) {
                if (response.isSuccessful) {

                    val items = response.body()!!.getResults()
                    var serie : Serie
                    if (items != null) {
                        for (item in items ) {
                            serie =  Serie(item.name, R.drawable.p1, item.overview!!, R.drawable.p1)
                            serie.id = item.id
                            serie.posterPath = item.poster_path?:""
                            SerieListResult.add(serie)

                        }
                        if(SerieListResult.size==0) Toast.makeText(context, "Aucun résultat trouvé" , Toast.LENGTH_LONG).show()
                        adapter1 = RecyclerViewSerieResultsAdapter(context, SerieListResult)
                        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        list.adapter = adapter1
                    }



                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<LatestSeriesResponse>, t: Throwable) {
                loadFailed()

            }
        })
    }


    private fun loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
    }



}
