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
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Adapters.CommentsFragment
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.R.id.drawer_layout


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener ,SearchView.OnQueryTextListener{

    var fragmentShown : Int = 0

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
        val intent = intent
        val fragment = intent.getIntExtra("fragment",1)
        if (fragment == 2) {

            val fragment =  FanFragment()
            val bundle = Bundle()
            var film = intent.getSerializableExtra("fan")
            if (film!= null) bundle.putSerializable("fan",film)
            var serie = intent.getSerializableExtra("fanS")
            if (serie!= null) bundle.putSerializable("fanS",serie)
            fragment.setArguments(bundle)
            showFragment(fragment)

        }
        else if (fragment == 3) {
            showFragment(MovieFragment())
        }
        else if (fragment == 4){
            showFragment(SeriesFragment())
        }
        else if (fragment == 5){
            showFragment(SallesFragment())
        }
        else {
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
                showFragment(AccueilFragment())
                supportActionBar!!.title = "Accueil"
            }
            R.id.nav_fan -> {

                supportActionBar!!.title = "Fan"
                showFragment(FanFragment())
            }
            R.id.nav_films -> {
                this.fragmentShown = 1
                supportActionBar!!.title = "Films"
                showFragment(MovieFragment())
            }
            R.id.nav_series -> {
                this.fragmentShown = 2

                supportActionBar!!.title = "Séries"
                showFragment(SeriesFragment())

            }
            R.id.nav_salles -> {


                supportActionBar!!.title = "Salles Cinéma"
                showFragment(SallesFragment())
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
    fun showEvaluation(){
        var mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        var mView: View = layoutInflater.inflate(R.layout.evaluation, null)

        var ratingBar: RatingBar = mView.findViewById<RatingBar>(R.id.stars);
        mBuilder.setView(mView)
        var dialog: AlertDialog = mBuilder.create()
        dialog.show()

        val close : Button = mView?.findViewById<Button>(R.id.closePop)
        close.setOnClickListener {view ->
            dialog.cancel()
        }

        val evaluer : Button = mView?.findViewById<Button>(R.id.submit)
        evaluer.setOnClickListener {view ->
            val note: Float = ratingBar.getRating()
            Toast.makeText(this, note.toString() , Toast.LENGTH_LONG).show();
            dialog.cancel()
        }
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
