package com.example.meriemmeguellati.cinema.Activities

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Window
import com.example.meriemmeguellati.cinema.Fragments.*
import com.example.meriemmeguellati.cinema.R
import android.app.SearchManager
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.preference.PreferenceFragment
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Adapters.CommentsFragment
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Notifications.SchedulerJob
import com.example.meriemmeguellati.cinema.R.id.drawer_layout


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener ,SearchView.OnQueryTextListener{

    var fragmentShown : Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Accueil"



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val PARAMS_NAME = "PARAMS"
        val params = getSharedPreferences(PARAMS_NAME, 0)
        val editeur = params.edit()
        editeur.putInt("precedent", 12)
        editeur.commit()

        SchedulerJob.scheduleJob(this)

        val intent = intent
        val fragment = intent.getIntExtra("fragment",1)
        if (fragment == 2) {

            if (!checkConnexion()) {
                showFragment(NoConnexionFragment())
                Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
            }
            else {
                val fragment = FanFragment()
                showFragment(fragment)
            }

        }
        else if (fragment == 3) {
            if (!checkConnexion()) {
                showFragment(NoConnexionFragment())
                Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
            }
            else showFragment(MovieFragment())
        }
        else if (fragment == 4){
            if (!checkConnexion()) {
                showFragment(NoConnexionFragment())
                Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
            }
            else showFragment(SeriesFragment())
        }
        else if (fragment == 5){
            if (!checkConnexion()) {
                showFragment(NoConnexionFragment())
                Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
            }
            else showFragment(SallesFragment())
        }
        else {
            if (!checkConnexion()) {
                showFragment(NoConnexionFragment())
                Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
            }
            else showFragment(AccueilFragment())
        }





        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val searchManager =  getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()))
        searchView.setOnQueryTextListener(this)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
                if(this.fragmentShown==1 || this.fragmentShown == 2){
                    onSearchRequested()
                }

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_accueil -> {
                // Handle the camera action
                if (!checkConnexion()) {
                    showFragment(NoConnexionFragment())
                    Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
                }else  showFragment(AccueilFragment())
                supportActionBar!!.title = "Accueil"
            }
            R.id.nav_fan -> {
                showFragment(FanFragment())

                supportActionBar!!.title = "Fan"

            }
            R.id.nav_films -> {
                if (!checkConnexion()) {
                    showFragment(NoConnexionFragment())
                    Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
                }else{
                    this.fragmentShown = 1
                    showFragment(MovieFragment())
                }

                supportActionBar!!.title = "Films"

            }
            R.id.nav_series -> {
                if (!checkConnexion()) {
                    showFragment(NoConnexionFragment())
                    Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
                }else {
                    this.fragmentShown = 2

                    showFragment(SeriesFragment())
                }

                supportActionBar!!.title = "Séries"


            }
            R.id.nav_salles -> {


                supportActionBar!!.title = "Salles Cinéma"
                showFragment(SallesFragment())
            }

            R.id.nav_settings -> {


                supportActionBar!!.title = "Settings"
                showFragment(BlankFragment())
                showSettingsFragment(SettingsFragment())
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    private fun showSettingsFragment(fragment: PreferenceFragment) {
        val fragmentManager = fragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, fragment)
                .commit()
    }

    override fun onQueryTextSubmit(query: String): Boolean {

        val searchIntent = Intent(this, SearchResultsActivity::class.java)
        searchIntent.putExtra(SearchManager.QUERY, query)
        val appData = Bundle()
        if(this.fragmentShown == 2){
            appData.putBoolean("serie", true) // put extra data to Bundle
        }
        else {
            appData.putBoolean("serie", false) // put extra data to Bundle
        }
        searchIntent.putExtra(SearchManager.APP_DATA, appData) // pass the search context data
        searchIntent.action = Intent.ACTION_SEARCH
        startActivity(searchIntent)

        return true // we start the search activity manually
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }


    fun checkConnexion():Boolean{

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnectedOrConnecting
    }

    companion object {

        /**
         * Change value in dp to pixels
         * @param dp
         * @param context
         */
        fun dpToPixels(dp: Int, context: Context): Float {
            return dp * context.resources.displayMetrics.density
        }
    }

}
