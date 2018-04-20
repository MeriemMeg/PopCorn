package com.example.meriemmeguellati.cinema.Activities

import android.app.Activity

import android.os.Bundle

import android.app.SearchManager
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
import com.example.meriemmeguellati.cinema.Model.Serie


class SearchResultsActivity  : Activity(){

    lateinit var list: RecyclerView
    var FilmListResult = ArrayList<Film>()
    var SerieListResult = ArrayList<Serie>()
    var serieSearch = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_results_activity)
        handleIntent(intent)
        this.list = findViewById<RecyclerView>(R.id.results)

        this.list.setHasFixedSize(true)

        val adapter1 = RecyclerViewSerieResultsAdapter(this, this.SerieListResult)
        val adapter2 = RecyclerViewResultsAdapter(this, this.FilmListResult)

        this.list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if(this.serieSearch){
            this.list.adapter = adapter1
        }else{
            this.list.adapter = adapter2
        }


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
                    showSerieResults(query)
                } else {

                    showFilmResults(query)
                }

            } else {
                    showFilmResults(query)
            }



        }
    }
    private fun showFilmResults(query: String) {
        val data = Data(resources)
        data.createData()
        val query2 =  query.toLowerCase(Locale.getDefault())
        if (query2.length == 0) {
            this.FilmListResult.addAll(data.films)
        } else {
            for (film in data.films) {
                if (film.titre.toLowerCase(Locale.getDefault()).contains(query2)) {
                    this.FilmListResult.add(film)
                }
            }
        }
        if(this.FilmListResult.size==0) Toast.makeText(this, "Aucun résultat trouvé" , Toast.LENGTH_LONG).show()

    }

    private fun showSerieResults(query: String) {
        val data = Data(resources)
        data.createData()
        val query2 =  query.toLowerCase(Locale.getDefault())
        if (query2.length == 0) {
            this.SerieListResult.addAll(data.series)
        } else {
            for (serie in data.series) {
                if (serie.titre.toLowerCase(Locale.getDefault()).contains(query2)) {
                    this.SerieListResult.add(serie)
                }
            }
        }
        if(this.SerieListResult.size==0) Toast.makeText(this, "Aucun résultat trouvé" , Toast.LENGTH_LONG).show()

    }



}
