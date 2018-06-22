package com.example.meriemmeguellati.cinema.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Adapters.SeriesAdapter
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.R
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.Language
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.LatestSeriesResponse
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APISeriesCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class SeriesFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var mview : View
    private var apiSeriesCall: Call<LatestSeriesResponse>? = null
    private val apiSeries = APISeriesCall()
    lateinit var adapter : SeriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_movie, container, false)
        return mview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = mview.findViewById(R.id.recyler_view)

        recyclerView.layoutManager = GridLayoutManager(this.context, 3) as RecyclerView.LayoutManager?
        LoadLatestSeries()


    }

    private fun LoadLatestSeries() {
        apiSeriesCall = apiSeries.getService().getNowPlayingSeries(Language().Country())
        apiSeriesCall!!.enqueue(object : Callback<LatestSeriesResponse> {
            override fun onResponse(call: Call<LatestSeriesResponse>, response: Response<LatestSeriesResponse>) {
                if (response.isSuccessful) {

                    val items = response.body()!!.getResults()
                    var serie : Serie
                    var series = ArrayList<Serie>()
                    if (items != null) {
                        for (item in items ) {
                            serie =  Serie(item.name, R.drawable.p1, item.overview!!, R.drawable.p1)
                            serie.id = item.id
                            serie.posterPath = item.poster_path?:""
                            series.add(serie)

                        }
                    }


                    adapter = SeriesAdapter(context,series)

                    recyclerView.adapter = adapter

                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<LatestSeriesResponse>, t: Throwable) {
                loadFailed()

            }
        })
    }

    private fun loadFailed() {
        Toast.makeText(context, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
    }




}