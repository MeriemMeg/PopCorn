package com.example.meriemmeguellati.cinema

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.example.meriemmeguellati.cinema.Activities.MainActivity
import android.support.annotation.NonNull
import android.support.v4.widget.DrawerLayout
import android.content.ContextWrapper
import android.support.design.widget.NavigationView
import android.view.MenuItem
import android.view.View
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie


/**
 * Created by Meriem Meguellati on 22/04/2018.
 */

class NavDrawerHelper(context: Context) : ContextWrapper(context) {


    var funFilm : Film = Film("Aucun", "", "","", R.drawable.defaultposter)
    var isFilmFan : Boolean = false

    var funSerie: Serie = Serie("Aucun", R.drawable.defaultposter, "", R.drawable.defaultposter)
    var isSerieFan :Boolean = false

    fun setFanFilms (film :Film){
        this.funFilm =film
        this.isFilmFan = true
    }

    fun setFanSeries (serie : Serie){
        this.funSerie = serie
        this.isSerieFan = true
    }

    fun initNav(drawerLayout: DrawerLayout, navigationView: NavigationView, isFinish: Boolean) {

        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
           override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                val id = menuItem.getItemId()
                when (id) {
                    R.id.nav_accueil -> {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.putExtra("fragment", 1)
                        startActivity(intent)
                        if (isFinish) (baseContext as Activity).finish()
                        drawerLayout.closeDrawers()
                    }
                    R.id.nav_films -> {

                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.putExtra("fragment", 3)

                        startActivity(intent)
                        if (isFinish) (baseContext as Activity).finish()
                        drawerLayout.closeDrawers()

                    }
                    R.id.nav_fan -> {

                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.putExtra("fragment", 2)
                        if (isFilmFan)intent.putExtra("fan",funFilm)
                        if(isSerieFan) intent.putExtra("fanS",funSerie)
                        startActivity(intent)
                        if (isFinish) (baseContext as Activity).finish()
                        drawerLayout.closeDrawers()
                    }
                    R.id.nav_series -> {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.putExtra("fragment", 4)
                        startActivity(intent)
                        if (isFinish) (baseContext as Activity).finish()
                        drawerLayout.closeDrawers()
                    }
                    R.id.nav_salles -> {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.putExtra("fragment", 5)
                        startActivity(intent)
                        if (isFinish) (baseContext as Activity).finish()
                        drawerLayout.closeDrawers()
                    }
                    R.id.nav_settings -> {

                    }
                }
                return true
            }
        })

      /*  val actionBarDrawerToggle = object : ActionBarDrawerToggle(baseContext as Activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerClosed(v: View) {
                super.onDrawerClosed(v)
            }

           override fun onDrawerOpened(v: View) {
                super.onDrawerOpened(v)
            }
        }*/
        //drawerLayout.addDrawerListener(actionBarDrawerToggle)
       // actionBarDrawerToggle.syncState()
    }
}

