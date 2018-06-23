package com.example.meriemmeguellati.cinema.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Adapters.FilmsAdapter
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.R
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.Language
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.NowPlayingResponse
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APImoviesCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class MovieFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var mview : View
    private var apiMoviesCall: Call<NowPlayingResponse>? = null
    private val apiMovies = APImoviesCall()
    lateinit var adapter : FilmsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_movie, container, false)
        return mview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = mview.findViewById(R.id.recyler_view)

        recyclerView.layoutManager = GridLayoutManager(this.context, 3)
        LoadPopularMovies()


    }


    private fun LoadPopularMovies() {
        apiMoviesCall = apiMovies.getService().getPopularMovie(Language().Country())
        apiMoviesCall!!.enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(call: Call<NowPlayingResponse>, response: Response<NowPlayingResponse>) {
                if (response.isSuccessful) {

                    val items = response.body()!!.getResults()
                    var film : Film
                    var films = ArrayList<Film>()
                    if (items != null) {
                        for (item in items){

                            film = Film(item.title!!, "", item.overview!!,item.posterPath!!, R.drawable.defaultposter)
                            film.id = item.id
                            film.backdrop_path = item.backdropPath?:""
                            films.add(film)

                        }
                    }
                    adapter = FilmsAdapter(context,films)

                    recyclerView.adapter = adapter


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
}