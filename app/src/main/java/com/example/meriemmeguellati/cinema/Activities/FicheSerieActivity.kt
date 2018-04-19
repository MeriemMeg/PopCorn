package com.example.meriemmeguellati.cinema.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewSaisonAdapter
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.SectionDataModel
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.Model.SingleItemModel
import com.example.meriemmeguellati.cinema.R


class FicheSerieActivity : AppCompatActivity() {

    lateinit var serie : Serie
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_serie_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar()!!.title=""
        val my_recycler_view = findViewById<RecyclerView>(R.id.saisons)

        my_recycler_view.setHasFixedSize(true)

        val intent = intent
        this.serie = intent.getSerializableExtra("serie") as Serie
        val adapter = RecyclerViewSaisonAdapter(this, this.serie.saisons)

        my_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        my_recycler_view.adapter = adapter

        val name = findViewById<TextView>(R.id.serie_name)
        name.text = this.serie.titre

        val description = findViewById<TextView>(R.id.series_descrp)
        description.text = this.serie.description

        val affiche = findViewById<FrameLayout>(R.id.affiche)
        affiche.setBackgroundResource(this.serie.poster)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_follow -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_rate -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        R.id.action_follow -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }
        android.R.id.home->{
            this.finish()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }



}
