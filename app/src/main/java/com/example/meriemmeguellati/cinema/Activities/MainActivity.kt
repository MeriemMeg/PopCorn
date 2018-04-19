package com.example.meriemmeguellati.cinema.Activities

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.view.View
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Window
import com.example.meriemmeguellati.cinema.Adapters.CommentsFragment
import com.example.meriemmeguellati.cinema.Fragments.*
import com.example.meriemmeguellati.cinema.R


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            showFragment(AccueilFragment())
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_accueil -> {
                // Handle the camera action
                showFragment(AccueilFragment())
                supportActionBar!!.title = "Accueil"
            }
            R.id.nav_fan -> {
                supportActionBar!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
                supportActionBar!!.title = "Fan"
                showFragment(FanFragment())
            }
            R.id.nav_films -> {
                supportActionBar!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
                supportActionBar!!.title = "Films"
                showFragment(MovieFragment())
            }
            R.id.nav_series -> {

                supportActionBar!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
                supportActionBar!!.title = "Séries"
                showFragment(SeriesFragment())

            }
            R.id.nav_salles -> {

                supportActionBar!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
                supportActionBar!!.title = "Salles Cinéma"
                showFragment(SallesFragment())
            }
            R.id.nav_personnes -> {
                supportActionBar!!.title = "Personnes"
                showFragment(CommentsFragment())
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
