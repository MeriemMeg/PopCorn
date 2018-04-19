package com.example.meriemmeguellati.cinema.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.Adapters.FilmsAdapter
import com.example.meriemmeguellati.cinema.Adapters.SeriesAdapter
import com.example.meriemmeguellati.cinema.Model.Data
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.R
import java.util.ArrayList

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class SeriesFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var mview : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_movie, container, false)
        return mview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = mview.findViewById(R.id.recyler_view)

        recyclerView.layoutManager = GridLayoutManager(this.context, 3) as RecyclerView.LayoutManager?

        val series = prepareCovers()

        val adapter = SeriesAdapter(this.context, series)

       recyclerView.adapter = adapter

    }


    private fun prepareCovers() : ArrayList<Serie> {
        val data = Data(resources)
        data.createData()

        return data.series

    }
}